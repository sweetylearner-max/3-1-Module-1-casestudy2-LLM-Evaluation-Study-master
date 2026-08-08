from flask import Flask
from flask_cors import CORS

from routes.generate_route import generate_bp
from routes.evaluate_route import evaluate_bp
from routes.health_route import health_bp

app = Flask(__name__)
CORS(app)

app.register_blueprint(generate_bp)
app.register_blueprint(evaluate_bp)
app.register_blueprint(health_bp)

@app.route("/")
def home():
    return {
        "project":"LLM Evaluation Study",
        "status":"Running"
    }

if __name__ == "__main__":
    app.run(debug=True)