"""
Embeddings
"""

from langchain_huggingface import HuggingFaceEmbeddings
from pdfBot.config.core import config

import os


def build_base_embeddings():
    """Building base embeddings"""

    base_embeddings = HuggingFaceEmbeddings(
        model_name=os.path.join(config.EMBEDDING_MODEL),
        model_kwargs= {"device": config.DEVICE}
    )
    return base_embeddings






