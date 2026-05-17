from flask import Flask

from routes.generate_report import generate_report_bp
from routes.analyse_document import analyse_document_bp
from routes.batch_process import batch_process_bp

from services.model_loader import get_model

app = Flask(__name__)

# Load AI Model
model = get_model()

print("AI Model Loaded Successfully")

# Register Blueprints
app.register_blueprint(generate_report_bp)
app.register_blueprint(analyse_document_bp)
app.register_blueprint(batch_process_bp)

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