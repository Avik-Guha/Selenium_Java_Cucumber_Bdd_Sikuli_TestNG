# Selenium_Java_Cucumber_BDD_Sikuli_JUnit

This is an Web Automation Testing framework using:

	Selenium
	Java
	Cucumber BDD
	log4js
	Sikuli
	Maven
	TestNG

## Installation

Prerequisite:

	Java
	Editor
	Maven

Extensions in VS Code:

	Cucumber Eclipse Plugin
	Eclipse XML Editors and Tools
	JSON Editor Plugin
	Markdown Text Editor

All set!!! Just download the Project folder...

## Framework Structure

	src/test/java
	
		global --> contains Base class for the execution
		
		hooks --> contains Hooks class file
		
		objects --> contains object locators
		
		pages --> contains Business Components
		
		runner --> contains test runner file (@CucumberOptions parameters)
		
		steps --> contains Step Definition files
		
		utility --> Contains custom utilities developed in the framework and are used globally across the framework
		
		      CommonFunctions --> contains custom methods to perform Selenium checks/actions
	
	src/test/resources
	
		features --> contains cucumber feature files
		
		sikuli_Images --> contains images for Sikuli actions
		
		TestData --> this folder contains test data files (json files preferred)
		
		log4j2.properties --> properties file for log4j2
		
		extent.xml, extent.properties --> configuration file for Extent Reports
	
	logs --> execution logs is generated here by log4j2
	
	target --> html report gets generated
	
		extent report --> inside ExtentReport folder
		
		cucumber report --> inside cucumberReports folder
	
	pom.xml --> contains dependencies and other maven information
	
	testng.xml --> TestNG configuration file

## Usage

	1. Download the Project folder
	
	2. Compile
	
	3. Open Command Prompt, navigate to Project folder. Use below Maven command to run test:
	
		To run:
		
			mvn clean verify

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## Contributors

Avik Guha

## License

Licensed under the [MIT License](LICENSE).
