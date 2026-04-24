from flask import Blueprint, request, jsonify

describe_bp = Blueprint('describe', __name__)

@describe_bp.route('/describe', methods=[ 'GET','POST'])
def describe():
    data = request.get_json()
    return jsonify({
        "message": "Describe working",
        "input": data
    })