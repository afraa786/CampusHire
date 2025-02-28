# Conversational RAG with PDF Integration

This is a Streamlit-based web application that allows users to upload PDF documents and interact with them via a Conversational Retrieval Augmented Generation (RAG) model. The app uses vector embeddings for document retrieval and a language model (LLM) for answering user queries based on the uploaded documents.

## Features

- **Upload PDF Documents**: Upload a PDF file to create a vector database.
- **Chat Interface**: Engage in a conversation where the app reads from the PDF and answers questions.
- **Real-time VectorDB Construction**: Dynamically builds a vector database from the uploaded document for fast retrieval.
- **Persistent Chat History**: The app keeps track of the conversation history.


### Prerequisites

Before running the application, ensure you have the following installed:
- Python 3.8+
- Streamlit
- Langchain
- Huggigface

### Steps

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/conversational-pdf-rag.git
   cd conversational-pdf-rag
