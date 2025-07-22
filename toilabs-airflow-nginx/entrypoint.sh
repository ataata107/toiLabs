#!/bin/bash

airflow db upgrade

airflow users create \
    --username admin \
    --password admin \
    --firstname Air \
    --lastname Flow \
    --role Admin \
    --email admin@example.com || true

airflow scheduler &

airflow webserver --url-prefix /airflow &

nginx -g 'daemon off;'