pipeline {
    
    agent any

    environment
    {
        PATH="/opt/apache-maven-3.6.3/bin:$PATH"
    }
	
    stages {
    	stage('GIT checkout'){
		steps{
			checkout changelog: false, poll: false, 
			scm: [$class: 'GitSCM', branches: [[name: '*/master']], 
			doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/nevin-cleetus/hello.git']]]
		}
	}
	
	stage('build') {
		steps {
			echo 'Maven build'
			sh 'mvn clean package sonar:sonar -Dsonar.host.url=http://13.235.242.47:9000/sonar -Dsonar.login=b8b8454c3e04c859e60c838bc80c91e0125365f2'
		}
	}
	    
	stage('SONAR_SERVER') {	
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                        waitForQualityGate abortPipeline: true
                }
           }
        }    	 	
    }	    
}
