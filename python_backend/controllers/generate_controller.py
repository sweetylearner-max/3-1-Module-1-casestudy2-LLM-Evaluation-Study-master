from flask import request, jsonify
from services.llm_service import generate_text

def generate():

    data = request.get_json()

    if not data:
        return jsonify({
            "status": "error",
            "message": "No JSON data received"
        }), 400

    prompt = data.get("prompt")

    if not prompt:
        return jsonify({
            "status": "error",
            "message": "Prompt is required"
        }), 400

    response = generate_text(prompt)

    return jsonify({
        "status": "success",
        "prompt": prompt,
        "response": response
    })