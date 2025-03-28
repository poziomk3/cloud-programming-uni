# Start from an OpenJDK 21 base image
FROM eclipse-temurin:21-jdk as build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml ./
RUN mkdir -p target
COPY .mvn .mvn
COPY mvnw .
RUN ./mvnw dependency:go-offline

# Copy project files
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# ---

# Use a minimal JRE image for running
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/user-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
