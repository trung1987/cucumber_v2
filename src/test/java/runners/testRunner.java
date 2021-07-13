package runners;


import commons.basedTest;
import cucumber.api.CucumberOptions;



@CucumberOptions(
		features = "src/test/java/features",
		glue = "steps",
		plugin = {
		"pretty","com.cucumber.listener.ExtentCucumberFormatter:my_report/report.html",
		"html:target/cucumber-reports","pretty:target/cucumber-pretty.txt", "json:target/cucumber-3.json",
		"junit:target/cucumber-3.xml"}
		)

public class testRunner extends basedTest{
	
}