from airflow import DAG
from airflow.operators.python import PythonOperator
from datetime import datetime
import pandas as pd
import matplotlib.pyplot as plt
import psycopg2

def fetch_sensor_data():
    conn = psycopg2.connect(
        dbname="toilabs",
        user="toilabs_user",
        password="toilabs_pass",
        host="postgres",
        port=5432
    )
    query = "SELECT * FROM sensor_data;"
    df = pd.read_sql_query(query, conn)
    conn.close()

    # Save raw data to CSV
    df.to_csv('/opt/airflow/dags/output/sensor_data.csv', index=False)

    # Create dashboard chart
    if 'device_id' in df.columns and 'hydration_level' in df.columns:
        summary = df.groupby("device_id")["hydration_level"].mean().reset_index()
        plt.figure(figsize=(10,6))
        plt.bar(summary["device_id"], summary["hydration_level"])
        plt.title("Average Sensor Value per Device")
        plt.xlabel("Device ID")
        plt.ylabel("Average Hydration Level")
        plt.tight_layout()
        plt.savefig("/opt/airflow/dags/output/dashboard.png")
    else:
        print("Required columns missing in data.")

with DAG(
    dag_id="generate_sensor_dashboard",
    schedule_interval="@hourly",
    start_date=datetime(2025, 7, 20),
    catchup=False
) as dag:

    generate_dashboard = PythonOperator(
        task_id="fetch_and_plot_sensor_data",
        python_callable=fetch_sensor_data
    )

    generate_dashboard
