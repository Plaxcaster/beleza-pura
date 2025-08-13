# Stage 1: Build the JAR
FROM eclipse-temurin:21-jdk-jammy as builder
WORKDIR /app
COPY . .
# Give execute permission to mvnw and run the build
RUN chmod +x mvnw && \
    ./mvnw clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=builder /app/target/beleza-pura-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]