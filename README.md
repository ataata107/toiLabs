# 🧪 ToiLabs Sensor Monitoring Platform

An end-to-end full-stack IoT monitoring platform that collects sensor data, runs predictions using an ML model, stores them in a database, and displays real-time results in a React dashboard.

---

## 🔧 Tech Stack

| Layer            | Technology                                      |
| ---------------- | ----------------------------------------------- |
| Frontend         | React, WebSocket, Nginx                         |
| Backend          | Spring Boot, Kafka Consumer, REST API           |
| Machine Learning | Flask (Python), Predictive Model                |
| Data Storage     | PostgreSQL                                      |
| Messaging        | Apache Kafka, Zookeeper                         |
| Infrastructure   | Docker Compose, GitHub Actions (CI/CD), Airflow |
| ETL              | Airflow                                         |

---

## 📌 Features

- 📡 Real-time sensor data updates
- 🤖 Predictions from ML model (e.g., alert levels)
- 💾 Persistence in PostgreSQL
- 📊 Live dashboard with animations & highlights
- 🧠 Backend processing with Kafka and Spring
- 🔁 WebSocket-powered UI updates
- 🚢 Fully containerized stack (Docker)
- 📈 Airflow DAG for automated dashboard generation

---

## 📁 Project Structure

```
ToiLabs/
├── docker-compose.yml
├── frontend/            # React + Nginx frontend
├── ml_service/          # Flask-based ML prediction service
├── kafka_producer/      # Python Kafka producer
├── airflow/             # Airflow DAGs for automation
├── spring_boot_app/     # Spring Boot backend with Kafka + DB
│   └── health/          # Core backend logic (controller/service/repo)
└── .github/             # GitHub Actions CI workflows
```

---

## 🚀 Local Development Setup

### 1. Clone the Repository

```bash
git clone https://github.com/ataata107/toiLabs.git
cd toiLabs
```

### 2. Build and Start All Services

```bash
docker-compose build --no-cache
docker-compose up
```

The services will launch:

- 🔗 **Frontend:** http://localhost:3000
- ⚙️ **Backend:** http://localhost:8080
- 🧠 **ML Model:** http://localhost:6000/predict
- 🐘 **PostgreSQL:** localhost:5432
- 🌐 **Airflow:** http://localhost:8089 (default credentials: `airflow` / `airflow`)

### 3. Kafka Producer

Send dummy sensor data via:

```bash
cd kafka_producer
python produce.py
```

Ensure `kafka-python` is installed:

```bash
pip install kafka-python
```

---

## 🔄 System Architecture

```text
[ Kafka Producer ]
       │
       ▼
[ Kafka Topic ] ---> [ Spring Boot Kafka Consumer ]
                                │
                                ├─> Sends to ML Model (Flask)
                                ├─> Stores in PostgreSQL
                                ├─> Broadcast via WebSocket
                                └─> Triggers Airflow DAG
                                                 │
                                                 ▼
                                    [ React Frontend Dashboard ]
```

---

## 📦 REST Endpoints

| Endpoint                 | Description                           |
| ------------------------ | ------------------------------------- |
| `GET /latest-per-device` | Latest sensor record per device       |
| `GET /ws/**`             | WebSocket endpoint                    |
| `POST /api/sensor`       | Sensor data ingestion (Kafka trigger) |

---

## 🧪 Testing

- Backend: JUnit tests in `spring_boot_app/health/src/test`
- Manual test: Open browser at http://localhost:3000 and verify real-time updates.

---

## 🔐 Environment Configuration

No `.env` file needed. All environment variables are defined in `docker-compose.yml` and `application.properties`.

---

## 🧰 GitHub Actions

On every push to `main`, GitHub CI:

- Checks out your repo
- Builds all Docker services via Compose
- Validates builds across all components

Workflow file: `.github/workflows/docker-compose-build.yml`

---

## 🐳 Docker Compose Overview

- PostgreSQL with persistent volume
- Zookeeper & Kafka brokers
- Flask ML model API
- Spring Boot Kafka consumer + WebSocket backend
- Nginx-backed React frontend
- Airflow build and save the files on the local folder

---

## 🧠 Future Improvements

- User authentication (JWT)
- Admin panel for device monitoring
- Device registration service
- ML model retraining via Airflow
- Grafana integration for dashboards

---

## 👨‍💻 Author

Built by Shazeb
Feel free to fork, star ⭐, and contribute!

---
