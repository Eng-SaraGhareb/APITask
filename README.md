# API Test Automation Project

This project contains automated tests for a sample API. It uses Java, Maven, TestNG, and REST Assured.

## Project Structure

```
.
├── Dockerfile
├── Jenkinsfile
├── pom.xml
├── runtest.xml
├── .github
│   └── workflows
│       └── maven.yml
├── src
│   ├── main
│   │   └── java
│   │       ├── ApiBase
│   │       │   └── RequestApi.java
│   │       ├── APIs
│   │       │   └── BooksModule.java
│   │       └── URLs
│   │           └── URLs.java
│   └── test
│       └── java
│           └── com
│               └── example
│                   ├── Delete.java
│                   ├── Get.java
│                   ├── GetId.java
│                   ├── Post.java
│                   └── Put.java
└── target
```

*   `pom.xml`: The Project Object Model (POM) file for Maven. It defines the project's dependencies, plugins, and build settings.
*   `Dockerfile`: Defines the steps to build a Docker image for this project. The image is configured to run the API tests.
*   `Jenkinsfile`: A Groovy script that defines a Jenkins pipeline for continuous integration.
*   `.github/workflows/maven.yml`: A GitHub Actions workflow file that automates running the tests.
*   `runtest.xml`: A TestNG suite XML file that defines which tests to run.
*   `src/main/java`: Contains the main source code for the API testing framework.
*   `src/test/java`: Contains the test classes.
*   `target/`: This directory is created by Maven when the project is built. It contains the compiled code, test reports, and the packaged JAR file.

## Prerequisites

Before you begin, ensure you have the following installed:
*   Java Development Kit (JDK) 17
*   Apache Maven
*   Docker

## How to Run

### Running Tests with Maven

You can run the tests using Maven from the command line.

*   **Run all tests:**
    ```bash
    mvn clean install
    ```
    This command will clean the project, compile the code, and run all the tests defined in `runtest.xml`.

*   **Run only the tests:**
    ```bash
    mvn test
    ```

*   **Run a specific test file:**
    To run a single test class, use the `-Dtest` parameter. For example, to run the `Get.java` tests:
    ```bash
    mvn test -Dtest=com.example.Get
    ```

### Running with Docker

You can also run the tests inside a Docker container.

1.  **Build the Docker image:**
    ```bash
    docker build -t api-tests .
    ```

2.  **Run the tests in a container:**
    ```bash
    docker run api-tests
    ```
    This will start a container and execute the `mvn test` command as defined in the `Dockerfile`.

### Continuous Integration

This project is configured to run the tests automatically in a CI environment.

*   **Jenkins**:
    The `Jenkinsfile` in the root of the project defines a pipeline that can be used to run the tests on a Jenkins server. The pipeline will:
    1.  Checkout the code.
    2.  Build the project and run tests using `mvn clean install`.
    3.  Publish the test reports.

*   **GitHub Actions**:
    The `.github/workflows/maven.yml` file sets up a GitHub Actions workflow that is triggered on every push or pull request to the `main` or `master` branches. It also runs on a daily schedule. This workflow will:
    1.  Checkout the code.
    2.  Set up Java 17.
    3.  Run the tests using `mvn test`.
    4.  Upload the test reports as artifacts.
