pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage('Checkout Code') {
            steps {
                script {
                    checkout scmGit(
                        branches: [[name: '*/main']],
                        extensions: [],
                        userRemoteConfigs: [[url: 'https://github.com/HariMedarametla1243/Jenkins-Project']]
                    )
                }
            }
        }

        stage('Build with Maven') {
            steps {
                script {
                    // Navigate to the project directory and build with Maven
                    bat '''
                    cd RestAPI-with-Mysql-with-JPA-Demo
                    mvn clean install
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Change to the project directory where Dockerfile exists
                    bat '''
                    cd RestAPI-with-Mysql-with-JPA-Demo
                    docker build -t devops-integration .
                    '''
                }
            }
        }
    }
}
