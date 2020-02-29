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
	    steps{	
                withSonarQubeEnv('SONAR_SERVER') {
                   sh 'mvn clean package sonar:sonar -Dsonar.projectKey=MyHello -Dsonar.host.url=http://13.235.242.47:9000/sonar -Dsonar.login=d4b7a84c8da51ff1a211392a5e99344a9e0384e7'
	        }
	    }	    
        }
	stage("Quality Gate"){
	     steps{	
                 timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
                     steps{	
			 def qg = waitForQualityGate() 
                         if (qg.status != 'OK') {
                             error "Pipeline aborted due to quality gate failure: ${qg.status}"
                         }
		     }		     
                 }
	     }		     
        }	
	
    }	    
}
