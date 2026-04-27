from flask import Flask, request, jsonify

app = Flask(__name__)

def analyze_risk(text):
    text = text.lower()
    if "delay" in text or "late" in text:
        return "High Risk"
    elif "average" in text:
        return "Medium Risk"
    return "Low Risk"

@app.route("/")
def home():
    return "Flask is running"

@app.route("/describe", methods=["POST"])
def describe():
    data = request.json
    if not data or "text" not in data:
        return jsonify({"error": "Text is required"}), 400

    text = data["text"]
    risk = analyze_risk(text)

    return jsonify({
        "input": text,
        "risk_level": risk,
        "message": "Risk analysis completed"
    })

if __name__ == "__main__":
    app.run(debug=False)
