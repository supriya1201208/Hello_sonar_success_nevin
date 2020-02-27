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
		
	stage('build && SonarQube analysis') {

		steps {
			echo 'This is a minimal pipeline.'
			sh 'mvn clean package sonar:sonar -Dsonar.projectKey=MyHello -Dsonar.host.url=http://13.235.242.47:9000/sonar -Dsonar.login=d4b7a84c8da51ff1a211392a5e99344a9e0384e7'
		}
	}
    }	    
}
