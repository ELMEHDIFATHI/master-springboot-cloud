pipeline {
    agent any

    environment {
        JAVA_HOME = "C:\\Program Files\\Java\\jdk-21"
        PATH = "${env.JAVA_HOME}\\bin;C:\\apache-maven\\bin;${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "✅ Checking out develop branch"
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/develop']],
                    userRemoteConfigs: [[
                        url: 'https://github.com/ELMEHDIFATHI/master-springboot-cloud.git',
                        credentialsId: 'GITHUB_CREDENTIALS'
                    ]]
                ])
            }
        }

        stage('Build Accounts') {
            steps {
                dir('accounts') {
                    bat 'mvn clean install -DskipTests'
                }
            }
        }
    }

    post {
        success {
            echo "✅ Jenkins test successful!"
        }
        failure {
            echo "❌ Jenkins test failed!"
        }
    }
}
