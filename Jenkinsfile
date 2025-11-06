pipeline {
	agent any

    environment {
		SONAR_TOKEN = credentials('SONAR_TOKEN')
        JAVA_HOME = "C:\\Program Files\\Java\\jdk-21"
        PATH = "${env.JAVA_HOME}\\bin;C:\\apache-maven\\bin;${env.PATH}"
    }

    stages {
		stage('Checkout') {
			steps {
				echo '🔄 Secure Checkout from GitHub'
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

        stage('Build') {
			steps {
				bat 'mvn clean install -DskipTests'
            }
        }
    }

    post {
		failure {
			echo '❌ CI failed! Check console logs'
        }
        success {
			echo '✅ Build Success!'
        }
    }
}
