pipeline {
    agent any

    tools {
        // Use the names configured in Jenkins Global Tool Configuration
        jdk 'JDK21'           // Java 21
        maven 'maven3'        // Maven installation
    }

    environment {
        // Ensure MAVEN_HOME is set correctly (Windows)
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

        stage('Build Microservices') {
            steps {
                script {
                    def services = ['accounts', 'cards', 'loans']
                    for (svc in services) {
                        dir(svc) {
                            echo "Building ${svc}..."
                            bat 'mvn clean install -DskipTests'
                        }
                    }
                }
            }
        }

        stage('Run Unit Tests') {
            steps {
                script {
                    def services = ['accounts', 'cards', 'loans']
                    for (svc in services) {
                        dir(svc) {
                            echo "Running tests for ${svc}..."
                            bat 'mvn test'
                        }
                    }
                }
            }
        }

        stage('SonarCloud Analysis') {
            steps {
                withSonarQubeEnv('MySonarCloudServer') {
                    script {
                        def services = ['accounts', 'cards', 'loans']
                        for (svc in services) {
                            dir(svc) {
                                echo "Running SonarCloud analysis for ${svc}..."
                                bat "mvn sonar:sonar -Dsonar.projectKey=ELMEHDIFATHI_${svc} -Dsonar.organization=ELMEHDIFATHI"
                            }
                        }
                    }
                }
            }
        }

        stage('Package Microservices') {
            steps {
                script {
                    def services = ['accounts', 'cards', 'loans']
                    for (svc in services) {
                        dir(svc) {
                            echo "Packaging ${svc}..."
                            bat 'mvn package -DskipTests'
                        }
                    }
                }
            }
        }
    }

    post {
        success {
            echo "🎉 All microservices built and analyzed successfully!"
        }
        failure {
            echo "❌ Build failed!"
        }
    }
}
