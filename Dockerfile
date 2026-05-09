# Build stage
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /workspace/app

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn
# Copy the pom.xml file
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy your other files
COPY src src

# Build the application
RUN ./mvnw package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-jammy
VOLUME /tmp
WORKDIR /app
COPY --from=build /workspace/app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
