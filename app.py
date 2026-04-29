from services.chroma_client import add_documents

docs = [
    "Vendor risk increases when compliance is low",
    "Financial instability is a key vendor risk factor",
    "Cybersecurity weaknesses can expose sensitive data",
    "Poor vendor performance affects business operations",
    "Lack of compliance leads to legal risks"
]

add_documents(docs)