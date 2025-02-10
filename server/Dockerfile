# Use an official Maven image to build the application
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .  
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean install -DskipTests

# Use a lightweight JDK image for the final build
FROM eclipse-temurin:17-jdk

# Install Maven in the final image
RUN apt-get update && apt-get install -y maven

# Set the working directory
WORKDIR /app

# Copy the source code and dependencies from the build stage
COPY --from=build /app .

# Expose the port
EXPOSE 8080

# Run the application using the Maven Spring Boot plugin
CMD ["mvn", "spring-boot:run", "-Dserver.port=8080"]
