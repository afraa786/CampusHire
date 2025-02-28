from setuptools import setup, find_packages

setup(
    name="pdfBot",
    version="0.1",
    packages=find_packages(),
    install_requires=[
        "streamlit",
        "langchain",
        # Add other dependencies
    ],
)
