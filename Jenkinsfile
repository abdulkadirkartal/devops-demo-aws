pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {
        stage('Build Maven') {
            steps {
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/abdulkadirkartal/devops-demo']])
                bat 'mvn clean install'
            }
        }

        stage('Unit Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Docker Image') {
            steps {
                bat 'docker build -t kadirkartal/my-application:latest .'
            }
        }

        stage('Docker Image to DockerHub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        bat 'echo docker login -u kadirkartal -p ${DockerHub}'
                        bat 'docker push kadirkartal/my-application:latest'
                    }

                }
            }
        }


        stage('Deploy to Kubernetes') {
            steps {
                kubernetesDeploy(configs: 'deployment-service.yml', kubeconfigId: 'kubernetes')
            }
        }


        stage('Docker Image to Clean') {
            steps {
                bat 'docker image prune -f'
            }
        }
    }
}
