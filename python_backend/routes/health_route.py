from flask import Blueprint
from controllers.health_controller import health

health_bp = Blueprint("health", __name__)

@health_bp.route("/health", methods=["GET"])
def health_route():
    return health()