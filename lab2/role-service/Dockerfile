#docker build -t role-service . --no-cache

# ----------- Build stage -----------
FROM maven:3.9-eclipse-temurin AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests


# ----------- Distroless Runtime stage -----------
FROM gcr.io/distroless/java21-debian12

WORKDIR /app
COPY --from=build /app/target/*role-service*.jar /app/role-service.jar

EXPOSE 8080

# Pass env vars at runtime via Docker Compose or CLI (recommended)
ENTRYPOINT ["java", "-jar", "/app/role-service.jar"]
