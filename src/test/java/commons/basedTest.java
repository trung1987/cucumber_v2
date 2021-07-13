package commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;

public class basedTest extends AbstractTestNGCucumberTests {
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter; // tao file html report , nen de o beforeTest , ko dc de o
													// beforeclass
	public static ExtentReports extent;
	@Parameters({ "browser" })
	@BeforeTest
	public void openBrowser(String browser) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/my_report/Automation.xml"); 
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter); // save data len html report
		
		switch (browser) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			break;
		}

		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@AfterClass
//	public void saveReport() {
//		extent.flush();
//	}
	
	@AfterMethod 	//Report
	public void saveReport() throws Exception {
		String imagePath = TakeScreenShot(driver);
		Reporter.addScreenCaptureFromPath(imagePath);
	}
	
	
	//For take screenshot
	public static String TakeScreenShot(WebDriver driver) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/my_report/" + "demoGuruAtt_" + dateName + ".png";
		File destFile = new File(destination);
		FileUtils.copyFile(srcFile, destFile);
		return  "demoGuruAtt_" + dateName + ".png";
	}
		

}
