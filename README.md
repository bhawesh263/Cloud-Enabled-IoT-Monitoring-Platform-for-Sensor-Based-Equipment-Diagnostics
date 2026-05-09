# Cloud-Enabled IoT Monitoring Platform

A Java-based backend application designed to receive IoT sensor data from simulated industrial equipment, process the data, detect abnormal equipment behavior, and expose REST APIs.

## Features

- **Spring Boot Backend**: REST APIs to ingest and query sensor readings.
- **Anomaly Detection**: Evaluates equipment metrics (temperature, vibration, pressure) against thresholds and assigns statuses (`NORMAL`, `WARNING`, `CRITICAL`).
- **IoT Simulator**: Built-in scheduled task that automatically generates and posts simulated sensor data.
- **H2 Database**: Lightweight in-memory database for local testing and data persistence.
- **Dockerized**: Includes a multi-stage `Dockerfile` for easy containerization.
- **CI/CD Ready**: Configured with a GitHub Actions workflow for automated testing and building.

## Technologies Used

- **Java 17**
- **Spring Boot 3.x** (Web, Data JPA, Actuator, Validation)
- **H2 Database**
- **Docker**
- **GitHub Actions**
- **JUnit 5 / Mockito**

## Architecture

1. **IoT Sensor Simulator**: Generates equipment data and sends HTTP POST requests.
2. **REST Controller**: Validates and accepts the JSON payload.
3. **Reading Service & Anomaly Detection**: Processes the data, applies threshold rules, and determines equipment health status.
4. **Data Layer**: Stores the readings into the SQL database.

## How to Run Locally

### 1. Run via Maven
Ensure you have Java 17 installed. You can start the application using the Maven wrapper:
```bash
./mvnw spring-boot:run
```
Once the server starts, the simulator will automatically begin generating data every 5 seconds.

### 2. View the API
You can fetch the stored data via the REST API:
```bash
curl http://localhost:8080/api/readings
```

### 3. View the Database
Open your browser and navigate to the H2 Console:
- **URL**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL**: `jdbc:h2:mem:iotdb`
- **Username**: `sa`
- **Password**: *(leave blank)*

### 4. Run Tests
To run the automated test suite:
```bash
./mvnw clean test
```

## Deployment (Docker)

You can build and run this application as a Docker container:
```bash
# Build the image
docker build -t iot-backend .

# Run the container
docker run -p 8080:8080 iot-backend
```
