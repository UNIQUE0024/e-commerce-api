pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }
    
    environment {
        APP_NAME = 'ecommerce-api'
        VERSION = '1.0.0'
    }
    
    stages {
        stage('🔍 Checkout') {
            steps {
                echo 'Checking out code from Git...'
                checkout scm
            }
        }
        
        stage('🔨 Build') {
            steps {
                echo 'Compiling application...'
                sh 'mvn clean compile'
            }
        }
        
        stage('🧪 Unit Tests') {
            steps {
                echo 'Running unit tests...'
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('📦 Package') {
            steps {
                echo 'Packaging application...'
                sh 'mvn package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: '**/target/*.jar',
                                   fingerprint: true
                }
            }
        }
        
        stage('🚀 Deploy') {
            steps {
                echo 'Deploying to production server...'
                sh '''
                    echo "Deployment would happen here"
                    echo "JAR file location: target/${APP_NAME}-${VERSION}.jar"
                '''
            }
        }
    }
    
    post {
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed!'
        }
        always {
            cleanWs()
        }
    }
}
