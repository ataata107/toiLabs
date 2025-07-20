#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

pip install matplotlib pandas sqlalchemy psycopg2-binary

# Initialize Airflow DB
airflow db init

# Create the default admin user (only if not exists)
airflow users create \
  --username admin \
  --password admin \
  --firstname Air \
  --lastname Flow \
  --role Admin \
  --email admin@example.com || true

# Start scheduler in background
airflow scheduler &

# Start webserver
exec airflow webserver
