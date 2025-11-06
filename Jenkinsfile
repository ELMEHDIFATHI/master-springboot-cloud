pipeline {
    agent any

    tools {
        maven 'maven3' // Must match your Jenkins Maven installation name
        jdk 'JDK21'    // Must match your Jenkins JDK installation name
    }

    environment {
        MAVEN_HOME = "C:\\Users\\user\\Downloads\\apache-maven-3.9.11-bin\\apache-maven-3.9.11"
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

        stage('SonarCloud Analysis') {
            steps {
                withCredentials([string(credentialsId: 'SONARCLOUD_TOKEN', variable: 'SONAR_TOKEN')]) {
                    dir('accounts') {
                        bat "mvn sonar:sonar -Dsonar.projectKey=ELMEHDIFATHI_accounts -Dsonar.organization=ELMEHDIFATHI -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=${SONAR_TOKEN}"
                    }
                    dir('cards') {
                        bat "mvn sonar:sonar -Dsonar.projectKey=ELMEHDIFATHI_cards -Dsonar.organization=ELMEHDIFATHI -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=${SONAR_TOKEN}"
                    }
                    dir('loans') {
                        bat "mvn sonar:sonar -Dsonar.projectKey=ELMEHDIFATHI_loans -Dsonar.organization=ELMEHDIFATHI -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=${SONAR_TOKEN}"
                    }
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
