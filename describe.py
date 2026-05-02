from flask import Blueprint, request, jsonify

describe_bp = Blueprint("describe", __name__)

def analyze_risk(text):
    text = text.lower()

    if "delay" in text or "late" in text:
        return "High Risk"
    elif "average" in text:
        return "Medium Risk"
    else:
        return "Low Risk"


@describe_bp.route("/describe", methods=["POST"])
def describe():
    data = request.get_json(force=True)

    if not data or "text" not in data:
        return jsonify({"error": "Text is required"}), 400

    text = data["text"]
    risk = analyze_risk(text)

    return jsonify({
        "input": text,
        "risk_level": risk,
        "message": "Risk analysis completed"
    })