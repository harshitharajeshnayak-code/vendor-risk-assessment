from flask import Blueprint, request, jsonify
from datetime import datetime
from services.groq_client import call_groq
import json

generate_report_bp = Blueprint("generate_report", __name__)

@generate_report_bp.route("/generate-report", methods=["POST"])
def generate_report():
    try:
        data = request.get_json()

        if not data:
            return jsonify({"error": "Input JSON is required"}), 400

        vendor_name = data.get("vendor_name", "Unknown Vendor")
        issue = data.get("issue", "")
        category = data.get("category", "")
        risk_level = data.get("risk_level", "")
        risk_score = data.get("risk_score", "")

        if not issue:
            return jsonify({"error": "Issue field is required"}), 400

        prompt = f"""
        You are an expert vendor risk analyst.

        Generate a professional vendor risk assessment report in JSON format.

        Return ONLY valid JSON with this exact structure:
        {{
          "title": "string",
          "executive_summary": "string",
          "overview": "string",
          "top_items": ["string", "string", "string"],
          "recommendations": ["string", "string", "string"]
        }}

        Vendor Details:
        Vendor Name: {vendor_name}
        Issue: {issue}
        Category: {category}
        Risk Level: {risk_level}
        Risk Score: {risk_score}
        """

        response = call_groq(prompt)

        cleaned = response.strip().replace("```json", "").replace("```", "")
        report_json = json.loads(cleaned)

        report_json["generated_at"] = datetime.utcnow().isoformat()

        return jsonify(report_json), 200

    except json.JSONDecodeError:
        return jsonify({"error": "Invalid AI response format"}), 500
    except Exception as e:
        return jsonify({"error": str(e)}), 500