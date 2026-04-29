from services.chroma_client import add_documents, query_documents

docs = [
    "Vendor risk increases when compliance is low",
    "Financial instability is a key vendor risk factor",
    "Cybersecurity weaknesses can expose sensitive data"
]

add_documents(docs)

result = query_documents("vendor risk")

print("FINAL RESULT:", result)