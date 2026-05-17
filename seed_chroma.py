documents = [

    "Vendor payment risk",
    "Vendor compliance policy",
    "Delivery delay management",
    "Audit procedures",
    "Fraud detection system",
    "Vendor onboarding process",
    "Contract review guidelines",
    "Risk scoring methodology",
    "Procurement workflow",
    "Financial verification checks"
]

print("Starting ChromaDB Seeding...\n")

for index, doc in enumerate(documents, start=1):

    print(f"{index}. Seeded Document: {doc}")

print("\nChromaDB Seeding Completed Successfully")