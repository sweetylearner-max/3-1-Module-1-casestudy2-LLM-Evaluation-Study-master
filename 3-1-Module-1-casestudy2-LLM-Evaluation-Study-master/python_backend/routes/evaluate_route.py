from flask import Blueprint
from controllers.evaluate_controller import evaluate

evaluate_bp = Blueprint("evaluate", __name__)

@evaluate_bp.route("/evaluate", methods=["POST"])
def evaluate_route():
    return evaluate()