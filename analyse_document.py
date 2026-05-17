from flask import Blueprint, request, jsonify

analyse_document_bp = Blueprint(
    "analyse_document",
    __name__
)

@analyse_document_bp.route(
    "/analyse-document",
    methods=["POST"]
)
def analyse_document():

    data = request.json

    text = data.get("text", "")

    findings = []

    if "delay" in text.lower():

        findings.append({
            "type": "Risk",
            "message": "Vendor delivery delays detected",
            "severity": "High"
        })

    if "payment" in text.lower():

        findings.append({
            "type": "Risk",
            "message": "Payment issue identified",
            "severity": "Medium"
        })

    findings.append({
        "type": "Insight",
        "message": "Document analysed successfully",
        "severity": "Low"
    })

    return jsonify({
        "document_text": text,
        "findings": findings
    })