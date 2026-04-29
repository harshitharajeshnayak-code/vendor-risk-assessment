from flask import Blueprint, request, jsonify
from services.groq_client import call_groq

recommend_bp = Blueprint("recommend", __name__)

@recommend_bp.route("/recommend", methods=["POST"])
def recommend():
    try:
        data = request.get_json()

        if not data or "input" not in data:
            return jsonify({"error": "Input is required"}), 400

        user_input = data["input"]

        prompt = f"""
        You are a vendor risk analyst.

        Based on the issue below, provide exactly 3 actionable recommendations.

        Return ONLY valid JSON in this format:
        {{
          "recommendations": [
            {{
              "action_type": "string",
              "description": "string",
              "priority": "High|Medium|Low"
            }}
          ]
        }}

        Issue: {user_input}
        """

        response = call_groq(prompt)

        return jsonify({
            "input": user_input,
            "recommendations": response
        }), 200

    except Exception as e:
        return jsonify({"error": str(e)}), 500