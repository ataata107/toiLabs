# --------------------
# Build stage
# --------------------
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# --------------------
# Runtime stage
# --------------------
FROM eclipse-temurin:17
WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Create a directory for MSK IAM auth JAR
RUN mkdir -p /app/libs

# Download MSK IAM Auth JAR
ADD https://github.com/aws/aws-msk-iam-auth/releases/download/v2.3.2/aws-msk-iam-auth-2.3.2-all.jar /app/libs/

# Make sure the classpath includes the IAM JAR
EXPOSE 8081

ENTRYPOINT ["java", "-cp", "app.jar:/app/libs/aws-msk-iam-auth-2.3.2-all.jar", "org.springframework.boot.loader.JarLauncher"]
