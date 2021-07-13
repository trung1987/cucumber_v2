package actions;

import org.openqa.selenium.WebDriver;

import commons.basedObject;
import interfaces.loginUI;

public class homeActions extends basedObject {
	WebDriver driverlocal;
	
	public homeActions (WebDriver driver)
	{
		driverlocal= driver;
	}
	
	public void verifylogin(String username)
	{
		System.out.println("verify");
	}
	
	
	
}
