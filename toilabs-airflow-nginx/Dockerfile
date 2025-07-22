FROM apache/airflow:2.9.1

USER root

# Install NGINX
RUN apt-get update && \
    apt-get install -y nginx && \
    apt-get clean

# Create writable temp dir for NGINX (to avoid permission issues)
RUN mkdir -p /tmp/nginx/body && \
    chmod -R 777 /tmp/nginx

# Remove default 'user' directive from nginx.conf (avoids warnings when not running as root)
RUN sed -i '/^user/d' /etc/nginx/nginx.conf

# Copy custom NGINX config and entrypoint script
COPY airflow-nginx.conf /etc/nginx/sites-enabled/default
COPY entrypoint.sh /entrypoint.sh

# Make entrypoint script executable
RUN chmod +x /entrypoint.sh
# RUN chown -R airflow:airflow /var/lib/nginx /var/log/nginx /tmp/nginx


# Switch back to airflow user (for best practice)
# USER airflow

# Expose the port NGINX listens on
EXPOSE 8080

# Set entrypoint
ENTRYPOINT ["/entrypoint.sh"]
