from flask import Flask, jsonify
from routes.describe import describe_bp
from routes.recommend import recommend_bp

app = Flask(__name__)

app.register_blueprint(describe_bp)
app.register_blueprint(recommend_bp)

@app.route("/health")
def health():
    return jsonify({"status": "AI running"})


@app.route("/")
def home():
    return "Flask is running"


if __name__ == "__main__":
    app.run(port=5000, debug=True)