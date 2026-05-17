from flask import Blueprint, request, jsonify

recommend_bp = Blueprint("recommend", __name__)

@recommend_bp.route("/recommend", methods=["POST"])
def recommend():

    data = request.get_json()

    description = data.get("description","")

    recommendations = [
        {
            "action_type":"SECURITY",
            "description":"Enable encryption",
            "priority":"HIGH"
        }
    ]

    return jsonify({
        "input": description,
        "recommendations": recommendations
    })