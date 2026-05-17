# AI Service Demo Script

## Project Introduction

Hello everyone.

This project is an AI-powered Vendor Risk Assessment System built using Flask APIs.

The system analyses vendor-related risks, generates AI reports, supports batch processing, and provides real-time streaming responses.

---

# 1. Health Endpoint Demo

## API

GET /health

## URL

http://127.0.0.1:5000/health

## Expected Output

{
  "status": "UP"
}

## Explanation

This endpoint checks whether the AI service is running properly.

---

# 2. Generate Report Demo

## API

GET /generate-report?input=vendor risk

## URL

http://127.0.0.1:5000/generate-report?input=vendor risk

## Expected Output

Streaming AI tokens.

Example:

data: {"token":"Vendor "}
data: {"token":"risk "}

## Explanation

This endpoint uses Server Sent Events (SSE) to stream AI-generated report responses in real time similar to ChatGPT typing effect.

---

# 3. Analyse Document Demo

## API

POST /analyse-document

## JSON Input

{
  "text": "Vendor has payment issue and delivery delay"
}

## Expected Output

{
  "findings": [
    {
      "type": "Risk",
      "message": "Vendor delivery delays detected",
      "severity": "High"
    }
  ]
}

## Explanation

This endpoint analyses vendor documents and identifies risks such as payment issues and delays.

---

# 4. Batch Process Demo

## API

POST /batch-process

## JSON Input

{
  "items": [
    "vendor payment issue",
    "delivery delay",
    "normal vendor"
  ]
}

## Expected Output

Batch processing results for all records.

## Explanation

This endpoint processes multiple vendor records together and returns structured AI results.

---

# 5. Pytest Demo

## Command

python -m pytest tests/test_api.py

## Expected Output

5 passed

## Explanation

Automated unit tests verify API functionality and ensure endpoint stability.

---

# Project Features

- AI-powered vendor risk analysis
- Real-time streaming responses
- Batch processing support
- Automated API testing
- Modular Flask architecture

---

# Closing Statement

This AI service helps organisations identify vendor-related risks efficiently using scalable AI APIs and real-time processing.