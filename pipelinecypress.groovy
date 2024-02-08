pipeline {
    agent any

    stages {
        stage('GIT CLONE') {
            steps {
                git branch: 'main', credentialsId: '76a4d053-abcf-42c6-9b6d-92926f7d99c0', url: 'https://github.com/m1ndhub/Cypress-demo-project.git'
            }
        }
        stage('BUILD TEST') {
            steps {
                sh 'docker build -t test-cypress .'
            }
        }
        stage('RUN TEST') {
            steps {
                ansiColor('xterm') {
                    sh 'docker run -t test-cypress'
                }
            }
        }
        stage('Detect SERP changed') {
            steps {
                script {
                    try {
                        build job: 'cypress'
                    } catch (err) {
                        echo "Error occurred: $err"
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed.'
        }
    }
}
