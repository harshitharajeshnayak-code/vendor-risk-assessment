from flask import Flask, request ,jsonify
from routes.describe import describe_bp   

app = Flask(__name__)

app.register_blueprint(describe_bp)       

@app.route("/health")
def health():
    return jsonify({"status": "AI service running"})

if __name__ == "__main__":
    app.run(port=5000, debug=True)