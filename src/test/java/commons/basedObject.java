package commons;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class basedObject {

	//Web Browser
	protected void openURL(WebDriver driver, String url) {
		driver.get(url);
	}
	
	protected String getTitle(WebDriver driver) {
		return driver.getTitle();
	}
	 
	protected String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	protected void goBack(WebDriver driver) {
		driver.navigate().back();
	}
	
	protected void goForward(WebDriver driver) {
		driver.navigate().forward();
	}
	
	protected void pressRefresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	
	//acceptAlert
	protected void acceptAlert(WebDriver driver) {
		//driver.switchTo().alert().accept();
		Alert alert = driver.switchTo().alert();
		alert.accept(); 
	}
	
	//cancelAlert
	protected void cancelAlert(WebDriver driver) {
		//driver.switchTo().alert().dismiss();
		Alert alert = driver.switchTo().alert();
		alert.dismiss(); 
	}
	
	//sendkeyToAlert
	protected void sendkeyToAlert(WebDriver driver, String input) {
		//driver.switchTo().alert().sendKeys(input);
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(input);
		alert.accept();
	}
	
	//Click to element by Selenium API
	protected void clickOnElement(WebDriver driver, String xpath) {
		//WebElement element = driver.findElement(By.xpath(xpath));
		WebElement element = waitForControlClickAble(driver, xpath);		//New
		element.click();
	}
	
	//Click element by JS
	protected void clickOnElementByJS(WebDriver driver, String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	//Click to element by Action
	protected void clickOnElementByAction(WebDriver driver, String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		Actions action = new Actions(driver);
		action.moveToElement(element).click()  //move co hay ko cung duoc nhung phai co click
		.build()
		.perform();
	}
		
	//clearElement
	protected void clearElement(WebDriver driver, String xpath) {
		WebElement element = waitForControlPresence(driver, xpath);		//New
		element.clear();
	}	
		
	
	//sendkeyToElement
	protected void SenkeytoElement(WebDriver driver, String xpath, String key) {
		//WebElement ele= driver.findElement(By.xpath(xpath));
		WebElement element= waitForControlPresence(driver, xpath);
		element.clear();
		element.sendKeys(key);
	}
	
	//-selectItemInHtmlDropDown  -> <select /> : selectIndex, selectByvalue,selectbyVisibleText
	protected void selectItemInHtmlDropDownByIndex(WebDriver driver, String xpath, int index) {
		//Select element = new Select(driver.findElement(By.xpath(xpath)));
		Select element = new Select(waitForAlertPresence(driver, xpath));		//New
		element.selectByIndex(index);
	}
	
	//selectByvalue
	protected void getSelectedItemInHtmlDropDownByValue(WebDriver driver, String xpath, String value) {
		//Select element = new Select(driver.findElement(By.xpath(xpath)));
		WebElement element = waitForControlPresence(driver, xpath);
		Select ele= new Select(element);
		ele.selectByValue(value);
	}
	
	//selectbyVisibleText
	protected void getSelectedItemInHtmlDropDownByValueByVisivleText(WebDriver driver, String xpath, String text) {
		//Select element = new Select(driver.findElement(By.xpath(xpath)));
		Select element = new Select(waitForAlertPresence(driver, xpath));		//New
		element.selectByVisibleText(text);
	}
	
	//-getSelectedItemInHtmlDropDown  -> string 
	protected String getSelectedItemInHtmlDropDownText(WebDriver driver, String xpath) {
		Select value = new Select(driver.findElement(By.xpath(xpath)));
		String result = value.getFirstSelectedOption().getText();
		return result;
	}
	
	//Get text element
	protected String getTextElement(WebDriver driver, String xpath) {
		//WebElement element = driver.findElement(By.xpath(xpath));
		WebElement element = waitForControlPresence(driver, xpath);		//New
		return element.getText();
	}
	
	//-checkTheCheckBox ->click 
	protected void checkTheCheckBox(WebDriver driver, String xpath) {
		Boolean select = driver.findElement(By.xpath(xpath)).isSelected();
		if(select == false) {
			WebElement element = driver.findElement(By.xpath(xpath));
			element.click();
		}
		
	}
	
	
	//-uncheckTheCheckBox -> click + if (isSelected)
	protected void uncheckTheCheckBox(WebDriver driver, String xpath) {
		Boolean select = driver.findElement(By.xpath(xpath)).isSelected();
		if(select == true) {
			WebElement element = driver.findElement(By.xpath(xpath));
			element.click();
		}
	}
	
	
	//-isControlDisplayed  -> boolean 
	protected Boolean isControlDisplayed(WebDriver driver, String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		Boolean display;
		display = element.isDisplayed();
		return display;
	}
	
	//-isControlSelected -> boolean 
	protected Boolean isControlSelected(WebDriver driver, String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		Boolean display = element.isSelected();
		return display;
	}
	
	//-isControlEnabled -> Boolean 
	protected Boolean isControlEnabled(WebDriver driver, String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		Boolean display = element.isEnabled();
		return display;
	}

	
	//Scroll to bottom by JS
	public void scrollByJSToBottom(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
		
	//Scroll to bottom by: Action ?
	public void scrollByActionToBottom(WebDriver driver, String xpath) {
		Actions action = new Actions(driver);
		while (driver.findElement(By.xpath(xpath)).isDisplayed() == false) {
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		}
	}
	
	//iFrame
	public void switchToiFrame(WebDriver driver, String iframeName) {
		driver.switchTo().frame(iframeName);
	}
	
	//Switch to main
	public void switchToMainFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	//SwitchTabByIndex
	public void SwitchTabByIndex(WebDriver driver, int index) {
		ArrayList<String> list = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(list.get(index));
	}
	
	//Upload file by sendkey <———
	public void uploadBySendkey(WebDriver driver, String xpath, String path, String fileName, String xpathUploadButton) throws InterruptedException {
		path = System.getProperty("user.dir") + "/libs/"+ fileName;
		WebElement uploadEle = driver.findElement(By.xpath(xpath));
		uploadEle.sendKeys(path);
		driver.findElement(By.xpath(xpathUploadButton)).click();
	}
	
	//Unpload file by robot <———
	public void uploadFileByRobot(WebDriver driver, String xpathFileInputButton, String fileName, String xpathUploadButton) throws InterruptedException, AWTException {
		WebElement upload = driver.findElement(By.cssSelector(xpathFileInputButton));
		String fileName1 = System.getProperty("user.dir") + "/libs/"+ fileName;
		
		StringSelection select = new StringSelection(fileName1);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		upload.click();
		Thread.sleep(5000);
		Robot robot = new Robot(); 
		Thread.sleep(5000);
		//This launches java applet, so we are using cmd+tab to shift the focus 
		//robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_TAB);
		//robot.keyRelease(KeyEvent.VK_META);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.delay(500); 
		Thread.sleep(5000);
		//Open Goto window 
		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_G);
		robot.keyRelease(KeyEvent.VK_META);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_G);
		Thread.sleep(5000);
		//Paste the clipboard value 
		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_META);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(5000);
		//Press Enter key to close the Goto window and Upload window 
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(500); 
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		driver.findElement(By.xpath(xpathUploadButton)).click();
	}
	
	//Drag and drop by Action
	protected void dragNDropByAction(WebDriver driver, String xpathSource, String xpathtarget ) {
		WebElement source = driver.findElement(By.xpath(xpathSource));
		WebElement target = driver.findElement(By.xpath(xpathtarget));
		
		Actions action = new Actions(driver);
		action.moveToElement(source).clickAndHold(source).moveToElement(target).release(target)
		.build()
		.perform();
	}
	
	//Get attribute 
	protected String getAttributeElement(WebDriver driver, String xpath, String attributeName) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element.getAttribute(attributeName);
	}
	
	//coutElementNumber
	protected int coutElementNumber(WebDriver driver, String xpath) {
		List<WebElement> allElement = driver.findElement(By.xpath(xpath));
		int elementCount = allElement.size();
		return elementCount;
	}
	
	//Verify
	protected boolean verifyEqual(String expect, String actual) {
		
		/*
		//C1
		if (actual.equalsIgnoreCase(expect)) {
			return true;
		}
		return false;
		*/
		
		//C2
		try {
			assertEquals(actual, expect);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	protected boolean verifyTrue(WebDriver driver, String xpath) {	//Dung de kiem tra element co hien thi hay khong
		return isControlDisplayed(driver, xpath);
	}

	//waitForControlClickAble
	public WebElement waitForControlClickAble(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 3);
		By locator = By.xpath(xpath);
	 	wait.until(ExpectedConditions.elementToBeClickable(locator));	//Cho den khi nao co the click duoc
	 	return driver.findElement(locator);
	}
	
	public WebElement waitForControlClickAble2(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 3);
	 	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));	
	 	return driver.findElement(By.xpath(xpath));		//Cho den khi nao co the click duoc
	}
	
	//waitForControlPresence 
	public WebElement waitForControlPresence(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 3);
		By locator = By.xpath(xpath);
	 	wait.until(ExpectedConditions.presenceOfElementLocated(locator));	
	 	return driver.findElement(locator);
	}
	
	public WebElement waitForControlPresence2(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 3);
	 	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));	
	 	return driver.findElement(By.xpath(xpath));
	}
	
	//waitForAlertPresence 
	public WebElement waitForAlertPresence(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 3);
		By locator = By.xpath(xpath);
	 	wait.until(ExpectedConditions.alertIsPresent());	
	 	return driver.findElement(locator);
	}
	
	public WebElement waitForAlertPresence2(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 3);
	 	wait.until(ExpectedConditions.alertIsPresent());	
	 	return driver.findElement(By.xpath(xpath));
	}
	
	//waitForVisibilityOfElement
	public WebElement visibilityOfElementLocated(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 3);
		By locator = By.xpath(xpath);
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));	
	 	return driver.findElement(locator);
	}
	
	public WebElement visibilityOfElementLocated2(WebDriver driver, String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, 3);
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	 	return driver.findElement(By.xpath(xpath));
	}
	
	//Fluent wait
	
	public WebElement presenceOfTheElementID(WebDriver driver, String xpath) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
				
		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return  driver.findElement(By.xpath(xpath));	
			}
		});
		
		/*//Ex:
		WebElement ele = presenceOfTheElementID(driver, "//android.widget.TextView[@resource-id='com.instagram.android:id/button_text']");
		ele.click();*/
	}
	
}
