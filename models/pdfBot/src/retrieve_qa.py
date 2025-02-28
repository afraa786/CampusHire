"""
Retrival Chain
"""
from langchain.prompts import PromptTemplate
from langchain.vectorstores.base import VectorStore
from langchain_core.output_parsers import StrOutputParser
from operator import itemgetter
from langchain.llms.base import LLM
from langchain_core.runnables import Runnable
from langchain.load import loads, dumps

from .prompt_template import MULTI_QUERY_TEMPLATE, CONTEXT_QUERY_TEMPLATE

def multiqueryRag(Vectordb: VectorStore, llm: LLM) -> Runnable:
    prompt = PromptTemplate.from_template(MULTI_QUERY_TEMPLATE)
    question_answer_prompt = PromptTemplate.from_template(CONTEXT_QUERY_TEMPLATE)
    retriever = Vectordb.as_retriever()

    generate_queries = (
        prompt
        | llm
        | StrOutputParser()
        | (lambda x: x.split("\n"))
    )
    retrieval_chain = generate_queries | retriever.map() | get_unique_union

    final_rag_chain = (
        {'context': retrieval_chain,
         'question': itemgetter('question'),
         'chat_history': itemgetter('chat_history')
         }
        | question_answer_prompt
        | llm
        | StrOutputParser()  # Assuming this returns a string already
        | (lambda x: str(x))  # Ensure the final output is a string
    )   

    return final_rag_chain



def get_unique_union(documents: list[list]) -> list:
    """Unique union of relative docs"""

    # Flatten list of lists, and convert each Document to string
    flatten_docs = [dumps(doc) for sublist in documents for doc in sublist]

    # Get unique documents
    unique_docs = list(set(flatten_docs))

    return [loads(doc) for doc in unique_docs]