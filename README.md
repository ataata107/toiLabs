# 🧪 ToiLabs Sensor Monitoring Platform

An end-to-end full-stack IoT monitoring platform that collects sensor data, runs predictions using an ML model, stores them in a database, and displays real-time results in a React dashboard. The platform is fully integrated with AWS services for scalability and reliability.

---

## 🔧 Tech Stack

| Layer            | Technology                                |
| ---------------- | ----------------------------------------- |
| Frontend         | React, WebSocket, Nginx                   |
| Backend          | Spring Boot, Kafka Consumer, REST API     |
| Machine Learning | Flask (Python), Predictive Model          |
| Data Storage     | PostgreSQL                                |
| Messaging        | Apache Kafka (MSK), Zookeeper             |
| Infrastructure   | AWS CloudFormation, ECS Fargate, ALB, RDS |
| ETL              | Airflow                                   |

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
- 🧰 GitHub Actions for CI/CD pipeline
- 🌐 AWS MSK for managed Kafka messaging
- 🏗️ CloudFormation templates for infrastructure provisioning

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
├── cloudformation/      # AWS CloudFormation templates
└── .github/             # GitHub Actions CI workflows
```

---

## 🚀 Local Development Setup

### 1. Clone the Repository

```bash
git clone https://github.com/ataata107/toiLabs.git
cd toiLabs
git checkout local_dev

#Use main branch for AWS deployment
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
[ Kafka Topic (AWS MSK) ] ---> [ Spring Boot Kafka Consumer ]
                                │
                                ├─> Sends to ML Model (Flask)
                                ├─> Stores in PostgreSQL (AWS RDS)
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

No `.env` file needed. All environment variables are defined in `docker-compose.yml`, `application.properties`, and CloudFormation templates.

---

## 🧰 GitHub Actions

On every push to `main`, GitHub CI:

- Checks out your repo
- Builds all Docker services via Compose
- Deploys AWS infrastructure using CloudFormation
- Validates builds across all components

Workflow file: `.github/workflows/docker-build.yml`

---

## 🏗️ AWS Infrastructure Overview

The platform uses AWS services for scalability and reliability. The infrastructure is provisioned using CloudFormation templates:

### **CloudFormation Templates**

1. **Networking (`toilabs-network.yaml`)**:

   - Creates a VPC with public and private subnets.
   - Configures an Internet Gateway and Route Tables.

2. **Shared Resources (`toilabs-resources.yaml`)**:

   - Sets up an Application Load Balancer (ALB).
   - Creates RDS PostgreSQL instance.
   - Configures AWS MSK (Managed Kafka).
   - Defines IAM roles and security groups.

3. **ECS Services (`toilabs-services.yaml`)**:
   - Deploys ECS Fargate services for:
     - Frontend (React + Nginx)
     - Backend (Spring Boot)
     - ML Model (Flask)
     - Airflow (DAG automation)

---

## 🐳 Docker Compose Overview

- PostgreSQL with persistent volume
- Zookeeper & Kafka brokers (local setup)
- Flask ML model API
- Spring Boot Kafka consumer + WebSocket backend
- Nginx-backed React frontend
- Airflow DAGs for ETL automation

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
