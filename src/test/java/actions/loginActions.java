package actions;

import org.openqa.selenium.WebDriver;

import commons.ManageDriver;
import commons.basedObject;
import interfaces.loginUI;

public class loginActions extends basedObject {
	WebDriver driverlocal;
	
	public loginActions (WebDriver driver)
	{
		driverlocal= driver;
	}
	
	public void gotoURL(String url)
	{
		openURL(driverlocal, url);
	}
	
	public void enterUserName(String username)
	{
		SenkeytoElement(driverlocal, loginUI.username, username);
	}
	
	public void enterPass(String pass)
	{
		SenkeytoElement(driverlocal, loginUI.pass, pass);
	}
	
	public homeActions clickLogin(String name)
	{
		String xpath = String.format(loginUI.btnLogin, name);
		System.out.println(xpath);
		clickOnElement(driverlocal, xpath);
		return ManageDriver.GoToHomepage(driverlocal);
	}
	
	public void acceptAlert()
	{
		acceptAlert(driverlocal);
	}
	
}
