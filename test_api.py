from app import app

client = app.test_client()


def test_health():

    response = client.get("/health")

    assert response.status_code == 200


def test_generate_report():

    response = client.get(
        "/generate-report?input=vendor risk"
    )

    assert response.status_code == 200


def test_analyse_document():

    response = client.post(
        "/analyse-document",
        json={
            "text": "Vendor has payment delay"
        }
    )

    data = response.get_json()

    assert response.status_code == 200
    assert "findings" in data


def test_invalid_route():

    response = client.get("/wrong-url")

    assert response.status_code == 404


def test_home_route():

    response = client.get("/")

    assert response.status_code == 200