pipeline {
    agent any

    environment {
        // Java & Maven paths if needed (Windows)
        MAVEN_HOME = "C:\\Program Files\\Apache\\maven\\apache-maven-3.9.3"
        PATH = "${env.MAVEN_HOME}\\bin;${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "✅ Checking out develop branch"
                checkout([
                    $class: 'GitSCM', 
                    branches: [[name: 'develop']], 
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

        stage('Build Cards') {
            steps {
                dir('cards') {
                    bat 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Build Loans') {
            steps {
                dir('loans') {
                    bat 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Run Unit Tests') {
            steps {
                dir('accounts') { bat 'mvn test' }
                dir('cards') { bat 'mvn test' }
                dir('loans') { bat 'mvn test' }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('MySonarQubeServer') {
                    dir('accounts') { bat 'mvn sonar:sonar' }
                    dir('cards') { bat 'mvn sonar:sonar' }
                    dir('loans') { bat 'mvn sonar:sonar' }
                }
            }
        }

        stage('Package') {
            steps {
                dir('accounts') { bat 'mvn package -DskipTests' }
                dir('cards') { bat 'mvn package -DskipTests' }
                dir('loans') { bat 'mvn package -DskipTests' }
            }
        }
    }

    post {
        success {
            echo "🎉 Build completed successfully!"
        }
        failure {
            echo "❌ Build failed!"
        }
    }
}
