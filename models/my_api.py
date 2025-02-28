from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import os
from pdfBot.src.retrieve_qa import multiqueryRag
from pdfBot.streamlit_app.utils import perform, load_base_embeddings, load_llm
from pdfBot.src.vectordb import build_vectordb, load_vectordb, delete_vectordb
from pdfBot.config.core import config
import sys
from pathlib import Path
import sys
sys.path.append('/home/fareed-sayed/Documents/rag_system')


# Add the parent directory to Python path
# sys.path.append(str(Path(__file__).resolve().parent))


app = FastAPI()

LLM = load_llm()
BASE_EMBEDDINGS = load_base_embeddings()
VECTORDB_PATH = config.VECTORDB.PATH
PDF_PATH = "research_data.pdf"  # Hardcoded path to your PDF document

# Initialize chat history and display history
chat_history = {}
display_history = {}

# Pydantic model to define the expected request body
class QuestionRequest(BaseModel):
    question: str

@app.on_event("startup")
async def startup():
    # Build the VectorDB from the hardcoded PDF
    if os.path.exists(VECTORDB_PATH):
        delete_vectordb(VECTORDB_PATH)
        
    with open(PDF_PATH, "rb") as pdf_file:
        perform(build_vectordb, pdf_file.read(), embedding_function=BASE_EMBEDDINGS)

    # Load the VectorDB
    vectordb = load_vectordb(BASE_EMBEDDINGS, VECTORDB_PATH)
    rag_chain = multiqueryRag(vectordb, LLM)

    # Store the rag_chain in the app's state
    app.state.rag_chain = rag_chain

@app.post("/ask/")
async def ask_question(request: QuestionRequest):
    """Endpoint to ask a question and get an answer from the RAG system"""
    if not hasattr(app.state, "rag_chain"):
        raise HTTPException(status_code=500, detail="RAG system not initialized")

    rag_chain = app.state.rag_chain

    # Initialize session data for the user (for simplicity, here using a static key for example)
    user_id = "user_123"  # You can use something dynamic like user session IDs
    if user_id not in chat_history:
        chat_history[user_id] = []
        display_history[user_id] = [("", "Hello! How can I help you?")]

    # Process the question through the RAG system
    try:
        response = rag_chain.invoke({
            "question": request.question,
            "chat_history": chat_history[user_id],
        })
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

    # Update chat history
    chat_history[user_id].append((request.question, response))
    display_history[user_id].append((request.question, response))

    return {"question": request.question, "answer": response}
