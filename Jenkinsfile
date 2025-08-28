pipeline {
    agent {
        // Use a Docker container with Maven and JDK 17.
        // This ensures a clean and consistent build environment.
        docker {
            image 'maven:3.9-eclipse-temurin-17'
            // Cache the local Maven repository to speed up subsequent builds.
            args '-v $HOME/.m2:/root/.m2'
        }
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the repository.
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // Run the Maven clean install lifecycle.
                // This will compile the code, run tests, and package the application.
                sh 'mvn clean install'
            }
        }
    }

    post {
        // The 'always' block runs regardless of the pipeline's success or failure.
        always {
            // Archive the TestNG XML results for reporting.
            // This requires the 'TestNG Results' plugin in Jenkins.
            testng testResults: 'target/surefire-reports/testng-results.xml', allowMissingResults: true

            // Publish the generated HTML report.
            // This requires the 'HTML Publisher' plugin in Jenkins.
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/surefire-reports/Suite',
                reportFiles: 'Regression.html',
                reportName: 'Regression Test Report'
            ])
        }
    }
}