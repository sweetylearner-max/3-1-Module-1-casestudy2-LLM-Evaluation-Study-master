import os
from dotenv import load_dotenv

load_dotenv()

class Config:
    SECRET_KEY = os.getenv("SECRET_KEY", "agent_workflow")
    API_KEY = os.getenv("API_KEY", "")
    MODEL_NAME = os.getenv("MODEL_NAME", "gemini-2.5-flash")