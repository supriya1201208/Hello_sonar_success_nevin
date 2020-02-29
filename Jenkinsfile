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
			sh 'mvn clean package' 
		}
	}
	    
	stage('SONAR_SERVER') {	
            environment {
                scannerHome = tool 'SONAR_SCANNER_ID'
            }    
	    steps {
                withSonarQubeEnv('SONAR_SERVER') {
		   sh 'mvn clean package' 
		   echo 'Maven build'	
		   sh "${scannerHome}/bin/sonar-scanner"
		}        
		timeout(time: 10, unit: 'MINUTES') {
                        waitForQualityGate abortPipeline: true
                }
           }
        }    	 	
    }	    
}
