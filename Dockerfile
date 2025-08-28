# Stage 1: Build the Java application
# Use a Maven base image to build the project and download dependencies
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml to download dependencies first for caching
COPY pom.xml .

# Download project dependencies
RUN mvn dependency:go-offline

# Copy the rest of the project source code
COPY src ./src

# Build the project, skipping tests in this stage
RUN mvn package -DskipTests

# --- Stage 2: Create a lightweight runtime image ---
# Use a lightweight OpenJDK image with Java only
FROM openjdk:17-jdk-slim

# Set the working directory for the final image
WORKDIR /app

# Copy the built JAR file from the 'build' stage
COPY --from=build /app/target/*.jar rest-assured-tests.jar

# Define the command to run the tests using Maven
# This command assumes you have a TestNG XML suite to run your tests
# The 'rest-assured-tests.jar' is not executed directly.
# Instead, Maven is used to run the tests from the command line.
CMD ["mvn", "test", "-Dsurefire.suiteXmlFiles=runtest.xml"]