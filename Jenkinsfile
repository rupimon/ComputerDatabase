#!/usr/bin/env groovy
node{
	stage('Checkout'){
		checkout scm
	}
	stage('Run Jar For Selenium Test'){
		echp ${WORKSPACE}
		cd ${WORKSPACE}/src/test/resources/executionjar/
		sh "java -jar sample.jar"
		//sh "java -jar ${WORKSPACE}/src/test/resources/executionjar/sample.jar"
		//java target/classes/sample/Mainprog.class
		
	}
	stage('Push Generated HTML Report to Github'){
		sh "git add *"
		sh "git commit -m 'Generated HTML report'"
		sh "git push"
	}
	stage('Notify Status To Github'){
		githubNotify description: 'Notify status to github', status: ${currentBuild.currentResult}
	}
}