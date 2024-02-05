pipeline {
    agent any

    tools {
        maven 'Maven' // Ensure Maven is configured in Global Tool Configuration
    }

    triggers {
        cron('H */4 * * *') // Triggers every 4 hours
    }

    parameters {
        // Define any parameters for the pipeline
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                git branch: 'main', credentialsId: 'qestit', url: 'https://github.com/qestitmavericks/QesteniumX.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building...'
                sh 'mvn clean test'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests with full debug logging...'
                sh 'mvn -X test' // -X enables full debug logging
            }
            post {
                always {
                    // Generate and archive test reports
                    junit '**/target/surefire-reports/*.xml'
                    archiveArtifacts artifacts: '**/target/surefire-reports/*.html', fingerprint: true
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs() // Cleanup the workspace after the build
            echo 'Pipeline execution complete.'
        }
        success {
            echo 'Build was successful!'
            // Implement notifications for success, e.g., email, Slack
        }
        failure {
            echo 'Build failed.'
            // Implement notifications for failure, e.g., email, Slack
        }
    }
}
