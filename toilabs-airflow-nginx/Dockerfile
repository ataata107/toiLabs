FROM apache/airflow:2.9.1

USER root

# Install NGINX
RUN apt-get update && apt-get install -y nginx && apt-get clean

# Copy NGINX config and entrypoint
COPY airflow-nginx.conf /etc/nginx/sites-enabled/default
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Switch back to airflow user
USER airflow

EXPOSE 8080

ENTRYPOINT ["/entrypoint.sh"]