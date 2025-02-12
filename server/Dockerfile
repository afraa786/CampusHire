# --- Use Maven Image with JDK ---
    FROM maven:3.9.6-eclipse-temurin-17

    # Set the working directory
    WORKDIR /app
    
    # Copy the entire project
    COPY . .
    
    # Expose the application port
    EXPOSE 8080
    
    # Run the Spring Boot application directly using Maven
    CMD ["mvn", "spring-boot:run"]
    