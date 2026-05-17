from flask import Blueprint, request, jsonify
import time

batch_process_bp = Blueprint(
    "batch_process",
    __name__
)

@batch_process_bp.route(
    "/batch-process",
    methods=["POST"]
)
def batch_process():

    data = request.json

    items = data.get("items", [])

    # Limit validation
    if len(items) > 20:

        return jsonify({
            "error": "Maximum 20 items allowed"
        }), 400

    results = []

    for item in items:

        # 100ms delay
        time.sleep(0.1)

        result = {
            "input": item,
            "risk_level": "High Risk"
            if "delay" in item.lower()
            else "Low Risk",

            "processed": True
        }

        results.append(result)

    return jsonify({
        "total_items": len(items),
        "results": results
    })