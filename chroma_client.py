import chromadb
from sentence_transformers import SentenceTransformer
import uuid

client = chromadb.PersistentClient(path="./chroma_data")
collection = client.get_or_create_collection(name="vendor_docs")

model = SentenceTransformer("all-MiniLM-L6-v2")


def add_documents(docs):
    print("ADDING DOCS...")
    for doc in docs:
        embedding = model.encode(doc).tolist()

        collection.add(
            documents=[doc],
            embeddings=[embedding],
            ids=[str(uuid.uuid4())]
        )
    print("ADDED SUCCESS")


def query_documents(query):
    print("QUERYING:", query)

    query_embedding = model.encode(query).tolist()

    results = collection.query(
        query_embeddings=[query_embedding],
        n_results=3
    )

    print("RAW RESULT:", results)

    return results["documents"]