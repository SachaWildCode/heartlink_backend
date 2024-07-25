# Stage 1: Build the application
FROM eclipse-temurin:22-jdk-alpine AS build

# Set the working directory for the build
WORKDIR /build

# Copy the pom.xml and download dependencies
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final image
FROM eclipse-temurin:22-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file from the build stage
COPY --from=build /build/target/heartlink-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]