from flask import Blueprint, request, jsonify
from services.groq_client import call_groq
from datetime import datetime
import json

recommend_bp = Blueprint("recommend", __name__)

def fallback_response():
    return {
        "generated_at": datetime.utcnow().isoformat(),
        "is_fallback": True,
        "recommendations": [
            {
                "action_type": "REVIEW",
                "description": "Manually review vendor risk",
                "priority": "HIGH",
                "estimated_impact": "Basic risk mitigation"
            }
        ]
    }


@recommend_bp.route("/recommend", methods=["POST"])
def recommend():
    data = request.get_json()

    title = data.get("title")
    description = data.get("description")
    steps = data.get("steps")

    # ❌ Validation
    if not title or not description or not steps:
        return jsonify({"error": "Missing required fields"}), 400

    # 🔥 STRICT PROMPT (VERY IMPORTANT)
    prompt = f"""
You are a risk analysis AI.

Return ONLY valid JSON. No explanation.

Format:
{{
  "recommendations": [
    {{
      "action_type": "string",
      "description": "string",
      "priority": "HIGH | MEDIUM | LOW",
      "estimated_impact": "string"
    }}
  ]
}}

Input:
Title: {title}
Description: {description}
Steps: {steps}
"""

    response = call_groq(prompt)

    # ❌ If Groq fails
    if not response:
        return jsonify(fallback_response())

    # ✅ Parse JSON safely
    try:
        parsed = json.loads(response)

        return jsonify({
            "generated_at": datetime.utcnow().isoformat(),
            "is_fallback": False,
            "recommendations": parsed.get("recommendations", [])
        })

    except Exception:
        return jsonify(fallback_response())