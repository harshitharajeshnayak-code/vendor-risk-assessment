from flask import Flask
from routes.generate_report import generate_report_bp
from routes.analyse_document import analyse_document_bp

app = Flask(__name__)

app.register_blueprint(generate_report_bp)
app.register_blueprint(analyse_document_bp)

@app.route("/health")
def health():
    return {
        "status": "UP"
    }

if __name__ == "__main__":
    app.run(
        host="0.0.0.0",
        port=5000,
        debug=True
    )