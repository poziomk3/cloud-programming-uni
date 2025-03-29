#docker build -t user-service . --no-cache
#docker tag user-service:latest 339888520068.dkr.ecr.us-east-1.amazonaws.com/cloud-programming:user-service
#docker push 339888520068.dkr.ecr.us-east-1.amazonaws.com/cloud-programming:user-service

# ----------- Build stage -----------
FROM maven:3.9-eclipse-temurin AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


# ----------- Distroless Runtime stage -----------
FROM gcr.io/distroless/java21-debian12

WORKDIR /app
COPY --from=build /app/target/*user-service*.jar /app/user-service.jar

EXPOSE 8080

# Pass env vars at runtime via Docker Compose or CLI (recommended)
ENTRYPOINT ["java", "-jar", "/app/user-service.jar"]
