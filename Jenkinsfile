pipeline {
    
    agent any

    environment
    {
        PATH="/opt/apache-maven-3.6.3/bin:$PATH"
    }
	
	  stages {
    
		    stage('GIT checkout'){
			      checkout changelog: false, poll: false, 
			      scm: [$class: 'GitSCM', branches: [[name: '*/master']], 
			      doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/nevin-cleetus/hello.git']]]
		    }
		
		    stage ('Build') {
			      steps {
				       echo 'This is a minimal pipeline.'
               sh "mvn clean package"
			      }
		     }
         
         
		     stage('SonarQube analysis') {
			       withSonarQubeEnv('SONAR_SERVER') {
				     def scannerHome = tool 'SONAR_SCANER';
				     sh "${scannerHome}/bin/sonar-scanner sonar.java.binaries=**/target/classes "
				}
		}	
}


