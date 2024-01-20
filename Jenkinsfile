pipeline {
    agent any
    stages {
        stage('Build Jar') {
            steps {
                sh "sh build.sh"
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t=anubhavgupta11/selenium-docker:latest .'
            }
        }
        stage('Push image to DockerHub') {
             environment {
                DOCKER_HUB = credentials('Docker_creds')
            }
             steps {
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh "docker tag anubhavgupta11/selenium-docker:latest anubhavgupta11/selenium-docker:${env.BUILD_NUMBER}"
                sh "docker push anubhavgupta11/selenium-docker:${env.BUILD_NUMBER}"
                sh "docker tag anubhavgupta11/selenium-docker:${env.BUILD_NUMBER} anubhavgupta11/selenium-docker:latest"
                sh 'docker push anubhavgupta11/selenium-docker'
            }
        }
    }
    post{
        always{
            sh "docker logout"
            sh "docker system prune -f"
            sh "docker rmi anubhavgupta11/selenium-docker:${env.BUILD_NUMBER}"
            sh "docker images"
        }
    }
}