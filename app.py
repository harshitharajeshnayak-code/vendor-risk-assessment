from flask import Flask, request, jsonify

app = Flask(__name__)

def analyze_risk(text):
    text = text.lower()
    score = 0
    reasons = []

    if "delay" in text or "late" in text:
        score += 40
        reasons.append("Delay issue detected")

    if "payment" in text:
        score += 30
        reasons.append("Payment issue detected")

    if "compliance" in text:
        score += 20
        reasons.append("Compliance issue detected")

    if "average" in text:
        score += 10
        reasons.append("Average vendor performance")

    if score >= 70:
        level = "High Risk"
    elif score >= 40:
        level = "Medium Risk"
    else:
        level = "Low Risk"

    return score, level, reasons


@app.route("/")
def home():
    return "Flask is running"


@app.route("/describe", methods=["POST"])
def describe():
    data = request.json

    if not data or "text" not in data:
        return jsonify({"error": "Text is required"}), 400

    text = data["text"]
    score, level, reasons = analyze_risk(text)

    return jsonify({
        "input": text,
        "risk_score": score,
        "risk_level": level,
        "reason": reasons
    })


if __name__ == "__main__":
    app.run(debug=False)