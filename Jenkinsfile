pipeline {
	agent any

    environment {
		// Jenkins credentials for SonarCloud and GitHub
        SONAR_TOKEN = credentials('SONAR_TOKEN')         // Secret text in Jenkins credentials
        GITHUB_TOKEN = credentials('GITHUB_TOKEN')
  GITHUB_CREDENTIALS = credentials('GITHUB_CREDENTIALS')// Secret text in Jenkins credentials
// Secret text in Jenkins credentials

        JAVA_HOME = "C:\\Program Files\\Java\\jdk-21"     // Adjust to your JDK path
        PATH = "${env.JAVA_HOME}\\bin;C:\\apache-maven\\bin;${env.PATH}"
    }

    stages {
		stage('Checkout') {
			steps {
				echo '🔄 Checking out code from GitHub securely'
                git url: 'https://github.com/ELMEHDIFATHI/master-springboot-cloud.git',
                    credentialsId: 'GITHUB_CREDENTIALS'
            }
        }

        stage('Build & Test Microservices') {
			parallel {
				stage('Accounts Service') {
					steps {
						echo '🏗️ Building Accounts Service'
                        bat 'mvn -f accounts-service/pom.xml clean install'
                        echo '🔬 Running SonarCloud Analysis for Accounts'
                        bat """mvn -f accounts-service/pom.xml verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar ^
                            -Dsonar.projectKey=ELMEHDIFATHI_master-springboot-cloud ^
                            -Dsonar.organization=elmehdifathi ^
                            -Dsonar.host.url=https://sonarcloud.io ^
                            -Dsonar.login=%SONAR_TOKEN%"""
                    }
                }

                stage('Cards Service') {
					steps {
						echo '🏗️ Building Cards Service'
                        bat 'mvn -f cards-service/pom.xml clean install'
                        echo '🔬 Running SonarCloud Analysis for Cards'
                        bat """mvn -f cards-service/pom.xml verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar ^
                            -Dsonar.projectKey=ELMEHDIFATHI_master-springboot-cloud ^
                            -Dsonar.organization=elmehdifathi ^
                            -Dsonar.host.url=https://sonarcloud.io ^
                            -Dsonar.login=%SONAR_TOKEN%"""
                    }
                }

                stage('Loans Service') {
					steps {
						echo '🏗️ Building Loans Service'
                        bat 'mvn -f loans-service/pom.xml clean install'
                        echo '🔬 Running SonarCloud Analysis for Loans'
                        bat """mvn -f loans-service/pom.xml verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar ^
                            -Dsonar.projectKey=ELMEHDIFATHI_master-springboot-cloud ^
                            -Dsonar.organization=elmehdifathi ^
                            -Dsonar.host.url=https://sonarcloud.io ^
                            -Dsonar.login=%SONAR_TOKEN%"""
                    }
                }
            }
        }
    }

    post {
		success {
			echo '✅ All microservices built and analyzed successfully!'
        }
        failure {
			echo '❌ CI failed. Check logs for details ,.'
        }
    }
}
