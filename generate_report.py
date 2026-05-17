from flask import Blueprint, Response, request
import json
import time

generate_report_bp = Blueprint(
    "generate_report",
    __name__
)

@generate_report_bp.route(
    "/generate-report",
    methods=["GET"]
)
def generate_report():

    text = request.args.get("input", "")

    report = f"""
Executive Summary:
Vendor risk detected.

Overview:
{text}

Recommendations:
1. Review vendor contract
2. Monitor delays
3. Conduct audit
"""

    def event_stream():

        words = report.split()

        for word in words:

            yield f"data: {json.dumps({'token': word + ' '})}\n\n"

            time.sleep(0.1)

        yield "data: [DONE]\n\n"

    return Response(
        event_stream(),
        content_type="text/event-stream"
    )