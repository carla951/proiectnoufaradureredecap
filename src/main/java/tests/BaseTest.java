package tests;



import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;

import pages.HomePage;

public class BaseTest {

	WebDriver driver;
	public HomePage homePage;
	String browser = System.getProperty("browser");
	
	
	
	
	
	//@Parameters({"url"})
	@BeforeClass
	public void setup() {

	//	System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("--headless");
		
		//Firefox Options
		FirefoxBinary fixerfoxBinary = new FirefoxBinary();
		fixerfoxBinary.addCommandLineOptions("--headless");
		FirefoxOptions firefoxOPtions =  new FirefoxOptions();
		firefoxOPtions.setBinary(fixerfoxBinary);
		
		if(browser !="" & browser !=null) {
			if(browser.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
			}
			
			else if(browser.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver(firefoxOPtions);
			}
		}
		else {
			driver = new ChromeDriver(options);
		}
		

		
		//maximize the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(6000, TimeUnit.MILLISECONDS);
		driver.manage().window().setSize(new Dimension(1440, 900));
		driver.get("https://keybooks.ro/");
		//driver.get("https://the-internet.herokuapp.com/upload");
		
		homePage = new HomePage(driver);
		
		
	}
		@AfterMethod	
		public void recordFailure(ITestResult result) {
			if(ITestResult.FAILURE == result.getStatus()) {
				TakesScreenshot poza = (TakesScreenshot)driver;
				File picture = poza.getScreenshotAs(OutputType.FILE);
				
				try {
					Files.move(picture,new File("screenshots/" + result.getName() + ".png"));
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
	
		@AfterClass
		public void tearDown() {
		if(driver != null) {
			driver.quit();
	
	
	
}
		}
		
}

