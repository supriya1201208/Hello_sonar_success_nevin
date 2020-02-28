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
	    
	stage('SonarQube analysis') {
    	    def scannerHome = tool 'SONAR_SCANNER';
            withSonarQubeEnv('SONAR_SERVER') { // If you have configured more than one global server connection, you can specify its name
                sh "${scannerHome}/bin/sonar-scanner"
	    }	    
         }
		
	stage("TestQualityGate") {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }   	
    }	    
}
