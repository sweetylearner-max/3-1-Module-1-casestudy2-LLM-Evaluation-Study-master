from flask import request, jsonify
from services.evaluation_service import evaluate_text

def evaluate():

    data = request.get_json()

    if not data:
        return jsonify({
            "status": "error",
            "message": "No JSON data received"
        }), 400

    prompt = data.get("prompt", "")
    response = data.get("response", "")

    result = evaluate_text(prompt, response)

    return jsonify(result)