# Stage 1: Build React App
FROM node:18 AS build

WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

# Stage 2: Serve React App
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html

# Optional: Configure NGINX to handle React routes
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
