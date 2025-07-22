FROM apache/airflow:2.9.1

USER root

# Install NGINX
RUN apt-get update && apt-get install -y nginx && apt-get clean

# Create a writable temp directory for client body uploads
RUN mkdir -p /tmp/nginx/body && \
    chmod -R 777 /tmp/nginx

# Replace default NGINX config (assumes your config uses /tmp/nginx/body)
COPY airflow-nginx.conf /etc/nginx/sites-enabled/default

# Copy and make entrypoint executable
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Optional: Remove default nginx user directive warning if not using root
RUN sed -i '/^user/d' /etc/nginx/nginx.conf

# Switch back to airflow user
USER airflow

EXPOSE 8080

ENTRYPOINT ["/entrypoint.sh"]
