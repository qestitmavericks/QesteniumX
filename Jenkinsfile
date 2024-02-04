pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                git branch: 'main', credentialsId: 'QesteniumXToken', url: 'https://github.com/qestitmavericks/QesteniumX.git'
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
        }
    }

    post {
        always {
            echo 'Pipeline execution complete.'
        }
        success {
            echo 'Build was successful!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
