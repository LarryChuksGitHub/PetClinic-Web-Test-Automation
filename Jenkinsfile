pipeline {

	agent any

	triggers {

		cron('0 2 * * *')
	}

	parameters {

		choice(
			name: 'PLATFORM',
			choices: [
				'android',
				'ios'
			]
		)
	}

	stages {

		stage('Checkout') {

			steps {

				git branch: 'feat/',
				url: 'https://'
			}
		}

		stage('Build') {

			steps {

				sh './gradlew clean'
			}
		}

		stage('Execute Tests') {

			steps {

				sh """
                ./gradlew test \
                -Dplatform=${PLATFORM}
                """
			}
		}

		stage('Publish Report') {

			steps {

				publishHTML([
					reportDir: 'reports',
					reportFiles: 'cucumber.html',
					reportName: 'Report'
				])
			}
		}
	}

	post {

		always {

			junit '**/test-results/**/*.xml'
			archiveArtifacts 'reports/**/*'
		}
	}
}
