from pdfBot.config.core import config

CHAT_FORMATS = {
    "llama2": """<s> [INST] <<SYS>>{system}<</SYS>>
{user}
[/INST]""",
    "mistral": """<s> [INST] {system}
{user}
[/INST]""",
    "zephyr": """<|system|>
{system}</s>
<|user|>
{user}</s>
<|assistant|>""",
    "gemma": """<start_of_turn>user
{system}
{user}<end_of_turn>
<start_of_turn>model""",
    "llama3": """<|begin_of_text|><|start_header_id|>system<|end_header_id|>
{system}<|eot_id|><|start_header_id|>user<|end_header_id|>
{user}<|eot_id|><|start_header_id|>assistant<|end_header_id|>""",
    "phi3": """<|user|>
{system}
{user}<|end|>
<|assistant|>""",
    "gemini": "{system}\n{user}",
    "gpt": "{system}\n{user}",
}

class Prompts:
    def __init__(self, prompt_type: str):
        self.chat_format = CHAT_FORMATS.get(prompt_type)
        if self.chat_format is None:
            print("chat_format is not present")
            self.chat_format="{syste}\n{user}"

    @property    
    def qa(self):
        system = (
            "You are a helpful, repectful and and honest assistant for question-answering tasks. "
            "Use the following pieces of retrieved context to answer the user's question. "
            "If you don't know answer, just say you don't know, don't try to make up answer."
        )

        user = "Context:\n{context}\n\nQuestion: {question}\nAnswer:"
        return self.chat_format.format(system=system, user=user)
    
    @property
    def condense_question(self):
        system = ""
        user = (
            "Given the following chat history and a follow up question, "
            "rephrase the follow up question to be a standalone question, in its original language. \n\n"
            "Chat History:\n{chat_history}\n\nFollow Up Question: {question}\nStandalone Question"
        )
        return self.chat_format.format(system=system, user=user)
    
    @property
    def hyde(self):
        system = "You are a helpful, respectful and honest assistant for question answering system"
        user = (
            "Please answer the user's question about a document. \nQuestion: {question}"
        )
        return self.chat_format.format(system=system, user=user)
    
    @property
    def multiple_queries(self):
        system = (
            """
            You are an AI language model assistant. Your task is to generate 6 different
            versions of the given user question to retrieve relevant documents from a vector
            database. By generating multiple perspective on the user question, your goal is to
            help the user overcome some of the limitations of the distance-based similarity search.
            Provide these alternative question seprated by newlines. Original question.
            """
        )

        user = "Question: {question}"
        return self.chat_format.format(system=system, user=user)
     
    @property
    def generated_result(self):
        system = "You are a helpful assistant"
        user = (
            "Provide an example answer to the given question, that might be found in document.\n"
            "Question: {question}\nOutput:"
        )
        return self.chat_format.format(system=system, user=user)
    

    @property
    def context_query(self):
        system = (
            "You are a helpful, repectful and and honest assistant for question-answering tasks. "
            "Rephrase the orgignal question based on following chat history"
            "Use the following pieces of retrieved context  to answer the user's question. "
            "If you don't know answer, just say you don't know, don't try to make up answer."
        )
        user = "Context:\n{context}\n\nQuestion: {question}\n\nChat-History:{chat_history}\nAnswer:"
        return self.chat_format.format(system=system, user=user)
    

prompts = Prompts(config.PROMPT_TYPE)
QA_TEMPLATE = prompts.qa
CONDENSE_QUESTION_TEMPLATE = prompts.condense_question
HYDE_TEMPLATE = prompts.hyde
MULTI_QUERY_TEMPLATE = prompts.multiple_queries
GENRATED_RESULT_TEMPLATE = prompts.generated_result
CONTEXT_QUERY_TEMPLATE = prompts.context_query