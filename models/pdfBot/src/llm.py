"""
LLM
"""

import os
from langchain.callbacks import StreamingStdOutCallbackHandler
from pdfBot.config.core import config


DEFAULT_MAX_TOKEN = 512
DEFUALT_TEMPERATURE = 0.2

def build_llm():
    """Builds LLM define in config"""

    from dotenv import load_dotenv
    load_dotenv()

    model = chatgroq(
        model_name=config.MODEL_CONFIG.model_name,
        config = {
        "max_tokens": config.MODEL_CONFIG.max_tokens,
        "temperature": config.MODEL_CONFIG.temperature}
        )
    return model



def chatgroq(model_name: str="mixtral-8x7b-32768", config: dict=None):

    """FOR GROQ MODEL"""
    from langchain_groq import ChatGroq

    if config is None:
        config = { 
            "max_tokens": DEFAULT_MAX_TOKEN,
            "temperature": DEFUALT_TEMPERATURE 
        }
    llm = ChatGroq(
        model=model_name,
        **config,
        streaming=True
    )
    return llm

