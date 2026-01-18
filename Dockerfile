# Use Java 21 (matches your local setup)
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give execution permission
RUN chmod +x mvnw

# Download dependencies (faster builds)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port used by Spring Boot
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/*.jar"]
