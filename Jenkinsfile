#!/usr/bin/env groovy
node{
	stage('Checkout'){
		checkout scm
	}
	stage('Run Jar For Selenium Test'){
		bat 'cd C:\\WINDOWS\\system32\\config\\systemprofile\\AppData\Local\\Jenkins.jenkins\\workspace\\Computer Database Test\\'
		bat 'java -jar src\\test\\resources\\executionjar\\sample.jar'	
	}
	
	stage('Notify Status To Github'){
		githubNotify description: 'Notify status to github', status: ${currentBuild.currentResult}
	}
}