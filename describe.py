from flask import Blueprint, request, jsonify
from datetime import datetime

describe_bp = Blueprint("describe", __name__)

@describe_bp.route("/describe", methods=["POST"])
def describe():

    data = request.json

    print("DATA:", data)

    description = data["description"]

    return jsonify({
        "risk_analysis": f"Risk found: {description}",
        "generated_at": datetime.now().isoformat()
    })