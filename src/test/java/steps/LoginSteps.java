package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.cucumber.listener.Reporter;

import actions.homeActions;
import actions.loginActions;
import commons.ManageDriver;
import commons.basedTest;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps extends basedTest{
	//WebDriver driver;
	loginActions login;
	homeActions home;
	
	@Before  //cucumber 
	public void before(Scenario scenario) {
	 
	}
	
	@Given("^I am on the URL \"([^\"]*)\"$")
	public void i_am_on_the_URL(String arg1) throws Throwable {
	
	    // Write code here that turns the phrase above into concrete actions
	
		//driver.get(arg1);
	
		System.out.println("Enter URL");
		login = ManageDriver.GoToLogin(driver);
		login.gotoURL(arg1);
	}

	/*@When("^I fill in \"([^\"]*)\" with \"([^\"]*)\"$")
	public void i_fill_in_with(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		arg1 = "//input[@name='uid']";	//?????????????????????????????????????
		//arg2 = "mngr202876";
		driver.findElement(By.xpath(arg1)).sendKeys(arg2);	
		
		
		arg1 = "//input[@name='password']";	
		//arg2 = "AvEbygy";
		driver.findElement(By.xpath(arg1)).sendKeys(arg2);	
		
		System.out.println("Enter Username/Password");
	    throw new PendingException();
	}
	
*/
	@When("^I fill in Username with \"([^\"]*)\"$")
	public void i_fill_in_Username_with(String arg1) throws Throwable {
		login.enterUserName(arg1);
		
		//driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(arg1);
	}
	
	@When("^I fill in Password with \"([^\"]*)\"$")
	public void i_fill_in_Password_with(String arg1) throws Throwable {
		login.enterPass(arg1);
		//driver.findElement(By.xpath("//input[@name='password']")).sendKeys(arg1);
		Reporter.addScreenCaptureFromPath(TakeScreenShot(driver));
	}

	@When("^I click on the \"([^\"]*)\" button$")
	public void i_click_on_the_button(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		home = login.clickLogin(arg1);
		/*String xpath= "//input[@name='" + arg1
				+ "']";
		driver.findElement(By.xpath(xpath)).click(); //?????????????????????????????????????
*/		System.out.println("Click Login button");
	}

	@Then("^I should see \"([^\"]*)\" message$")
	public void i_should_see_message(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement verifyText= driver.findElement(By.xpath("//marquee[@class='heading3']"));
		Reporter.addScreenCaptureFromPath(TakeScreenShot(driver));
		Assert.assertEquals(verifyText.getText(), arg1);
		System.out.println("Login successful");
		home.verifylogin("aaa");
	}

	@Then("^I should see alert message$")
	public void i_should_see_alert_message() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		login.acceptAlert();
		System.out.println("aaaaaa");
	}

}
