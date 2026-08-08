from flask import Blueprint
from controllers.generate_controller import generate

generate_bp = Blueprint("generate", __name__)

@generate_bp.route("/generate", methods=["POST"])
def generate_route():
    return generate()