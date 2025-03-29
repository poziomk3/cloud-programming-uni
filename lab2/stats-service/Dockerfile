#docker build -t stats-service . --no-cache
#docker tag stats-service:latest 339888520068.dkr.ecr.us-east-1.amazonaws.com/cloud-programming:stats-service
#docker push 339888520068.dkr.ecr.us-east-1.amazonaws.com/cloud-programming:stats-service

# ----------- Build stage -----------
FROM maven:3.9-eclipse-temurin AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


# ----------- Distroless Runtime stage -----------
FROM gcr.io/distroless/java21-debian12

WORKDIR /app
COPY --from=build /app/target/*stats-service*.jar /app/stats-service.jar

EXPOSE 8080

# Pass env vars at runtime via Docker Compose or CLI (recommended)
ENTRYPOINT ["java", "-jar", "/app/stats-service.jar"]
