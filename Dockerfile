FROM openjdk:23-slim AS build

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*
WORKDIR /app

COPY pom.xml .
COPY src ./src


RUN mvn clean package -DskipTests


FROM openjdk:23-jdk-slim

WORKDIR /app

COPY --from=build /app/target/java-http-server-1.0-SNAPSHOT.jar app.jar
COPY src/main/resources/ /app/src/main/resources/

EXPOSE 8000

# Run the application
CMD ["java", "-jar", "app.jar"]
