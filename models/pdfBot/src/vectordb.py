"""
Vector DataBase
"""
from typing import Sequence
import os
import uuid

from pdfBot.config.core import config

from langchain.embeddings.base import Embeddings
from langchain.vectorstores.base  import VectorStore
from langchain_chroma import Chroma
from langchain.schema import Document
from . embedding import build_base_embeddings
from . parser import load_pdf, simple_text_split


def build_vectordb(filename: str, embedding_function: Embeddings) -> None:
    "Building vector of documnets"

    parts = load_pdf(filename)
    docs = simple_text_split(parts, chunk_size=config.chunk_size, 
                             chunk_overlap=config.chunk_overlap)
    
    vectordb_path = config.VECTORDB.PATH
    
    save_vectordb(docs, embedding_function, vectordb_path)



def save_vectordb(docs: Sequence[Document], embedding_function: Embeddings, 
                  persist_directory: str) -> None:
    
    vectorstore = Chroma(
        collection_name="lanchain",
        embedding_function=embedding_function,
        persist_directory=persist_directory
    )

    uuids = [str(uuid.uuid4()) for _ in range(len(docs))]
    _ = vectorstore.add_documents(docs, ids=uuids)

    print(_)



def load_vectordb(embedding_function: Embeddings, persist_directory: str) -> VectorStore:
    "load vectordb"

    vectorstore = Chroma(
        collection_name="lanchain",
        embedding_function=embedding_function,
        persist_directory=persist_directory,
    )

    return vectorstore


def delete_vectordb(persist_directory: str) -> None:
    "delete vectordb"

    vectorstore = Chroma(
        collection_name="lanchain",
        persist_directory=persist_directory
    )
    vectorstore.delete_collection()

    
 



