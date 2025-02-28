"""
Load and split documents
"""

import uuid
from typing import Sequence

from langchain.schema import Document
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain_community.document_loaders import PyMuPDFLoader

def load_pdf(filename: str) -> Sequence:
    """Load pdfs"""
    return PyMuPDFLoader(file_path=filename).load()


def simple_text_split(docs: Sequence[Document], chunk_size: int, 
                      chunk_overlap: int)-> Sequence[Document]:
    """Text chunking using langchain RecursiveCharacterTextSplitter"""

    text_splitter = RecursiveCharacterTextSplitter(
        chunk_size=chunk_size,
        chunk_overlap=chunk_overlap,
        separators=["\n\n", "\n", ". ", " ", ""],
        length_function=len
    )
    texts = text_splitter.split_documents(docs)

    for t in texts:
        t.metadata["page_number"] = t.metadata["page"] + 1
    
    return texts



    

    