import os
import tempfile

import streamlit as st

from pdfBot.src.embedding import build_base_embeddings
from pdfBot.src.llm import build_llm

@st.cache_resource
def load_base_embeddings():
    return build_base_embeddings()

@st.cache_resource
def load_llm():
    return build_llm()

# @st.cache_resource
# def load_reranker():
#     return build_reranker()


def perform(func, filebtytes, **kwargs):
    """
    Helper function to perform a function on a file-like object.

    This function creates a temporary file, writes the file-like object to
    the temporary-file, ans call the function on the temporary file.
    The temporary file is then deleted

    Args:
        func (function): the function to call
        filebytes (bytes): The file-like object to write to a temporary file.
        **kwargs: Additional keywords arguments to pass to the function.
    """

    fh, temp_filename = tempfile.mkstemp()
    try:
        with open(temp_filename, "wb") as f:
            f.write(filebtytes)
            f.flush()
            return func(f.name, **kwargs)
        
    finally:
        os.close(fh)
        os.remove(temp_filename)

