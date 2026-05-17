# AI Service — Vendor Risk Assessment

## Overview

This AI service provides intelligent vendor risk analysis APIs using Flask.

Features:
- Generate AI reports
- Analyse documents
- Batch process vendor records
- SSE streaming responses
- Health monitoring

---

# Tech Stack

- Python
- Flask
- Pytest
- REST API
- SSE (Server Sent Events)

---

# Prerequisites

Install:

- Python 3.10+
- pip
- VS Code

---

# Install Dependencies

```bash
pip install flask
pip install pytest
```

---

# Project Structure

```plaintext
ai_service/
│
├── routes/
│   ├── generate_report.py
│   ├── analyse_document.py
│   └── batch_process.py
│
├── tests/
│   └── test_api.py
│
├── app.py
│
└── README.md
```

---

# Run Application

```bash
python app.py
```

Application runs on:

```plaintext
http://127.0.0.1:5000
```

---

# API Endpoints

## 1. Health Endpoint

### Request

```http
GET /health
```

### Response

```json
{
  "status": "UP"
}
```

---

# 2. Generate Report

### Request

```http
GET /generate-report?input=vendor risk
```

### Description

Streams AI report response using SSE.

### Response Example

```plaintext
data: {"token":"Vendor "}
data: {"token":"risk "}
```

---

# 3. Analyse Document

### Request

```http
POST /analyse-document
```

### JSON Body

```json
{
  "text": "Vendor has payment issues"
}
```

### Response

```json
{
  "findings": [
    {
      "type": "Risk",
      "message": "Payment issue identified",
      "severity": "Medium"
    }
  ]
}
```

---

# 4. Batch Process

### Request

```http
POST /batch-process
```

### JSON Body

```json
{
  "items": [
    "vendor delay",
    "payment issue"
  ]
}
```

### Response

```json
{
  "total_items": 2,
  "results": []
}
```

---

# Run Tests

```bash
python -m pytest tests/test_api.py
```

---

# Author

AI Developer 1