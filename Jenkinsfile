pipeline {
    agent any

    tools {
    maven 'maven3'
    jdk 'JDK21'
}

    environment {

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
    withSonarQubeEnv('MySonarCloudServer') {
      bat 'mvn sonar:sonar -Dsonar.projectKey=ELMEHDIFATHI_master-springboot-cloud -Dsonar.organization=ELMEHDIFATHI'
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
