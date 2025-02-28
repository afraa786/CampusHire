import os
import sys

# Add the root directory to the PYTHONPATH
sys.path.append(os.path.dirname(os.path.abspath(__file__)))

import streamlit as st
from langchain_community.callbacks.streamlit import StreamlitCallbackHandler

from src.retrieve_qa import multiqueryRag
from streamlit_app.utils import perform, load_base_embeddings, load_llm
from src.vectordb import build_vectordb, load_vectordb, delete_vectordb
from config.core import config


Title = "Conversational RAG"
st.set_page_config(page_title=Title)

LLM = load_llm()
BASE_EMBEDDINGS = load_base_embeddings()
VECTORDB_PATH = config.VECTORDB.PATH

def init_chat_history():
    """Inititalise chat history"""
    # clear_button = st.sidebar.button("Clear Chat", key="clear")
    if "chat_history" not in st.session_state:
        st.session_state["chat_history"] = list()
        st.session_state["display_history"] = [("", "Hello! How can I help you?")]

def doc_conv_qa():

    # Create two columns for layout: one for PDF display and the other for chat
    col1, col2 = st.columns([1, 2])
    with st.sidebar:
        st.title(Title)
        
        uploaded_file = st.file_uploader(
            "Upload a PDF and build VectorDB", type=["PDF"]
        )
        if st.button("Start Chat"):
            if uploaded_file is None:
                st.error("No PDF uploaded")
                st.stop()

            if os.path.exists(VECTORDB_PATH):
                delete_vectordb(VECTORDB_PATH)

            with st.spinner("Building VectorDB..."):
                perform(
                    build_vectordb,
                    uploaded_file.read(),
                    embedding_function=BASE_EMBEDDINGS,
                )

        if not os.path.exists(VECTORDB_PATH):
            st.info("No documents uploaded .")
            st.stop()

        try:
            with st.status("Hunting....", expanded=False) as status:
                vectordb = load_vectordb(BASE_EMBEDDINGS, VECTORDB_PATH)
                rag_chain = multiqueryRag(vectordb, LLM)
                status.update(
                    label="Loading complete!", state="complete", expanded=False
                )
                st.success(body="Reading from your documents")
        
        except Exception as e:
            st.error(e)
            st.stop()

    

    st.sidebar.write("----")
    st.title("Chat with pdf Data")
    init_chat_history()


    # Display chat history
    for question, answer  in st.session_state.display_history:
        if question != "":
            with st.chat_message("user"):
                st.markdown(question)
        
        with st.chat_message("assistant"):
            st.markdown(answer)
        
    if user_query := st.chat_input("Your query"):
        with st.chat_message("user"):
            st.markdown(user_query)

    
    if user_query is not None:
        with st.chat_message("assistant"):
            response = rag_chain.invoke(
                {
                    "question": user_query,
                    "chat_history":st.session_state.chat_history,
                },
            )
            st.markdown(response)
            
            st.session_state.chat_history.append((user_query, response))
            st.session_state.display_history.append(
                (user_query, response)
            )

if __name__ == "__main__":
    doc_conv_qa()
