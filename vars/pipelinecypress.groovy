pipeline {
    agent any

     
        stages('GIT CLONE') {
            steps {
                git branch: 'main', credentialsId: '76a4d053-abcf-42c6-9b6d-92926f7d99c0', url: 'https://github.com/m1ndhub/Cypress-demo-project.git'
            }
        }
        stages('BUILD TEST') {
            steps {
                sh 'docker build -t test-cypress .'
            }
        }
        stages('RUN TEST') {
            steps {
                ansiColor('xterm') {
                    sh 'docker run -t test-cypress'
                }
            }
        }
    

    post {
        always {
            echo 'Pipeline completed.'
        }
    }
}
