from flask import Flask
from routes.generate_report import generate_report_bp

app = Flask(__name__)

# Register Blueprint
app.register_blueprint(generate_report_bp)

# Health Route
@app.route("/health")
def health():
    return {
        "status": "UP",
        "service": "AI Service"
    }

if __name__ == "__main__":
    app.run(
        host="0.0.0.0",
        port=5000,
        debug=True
    )