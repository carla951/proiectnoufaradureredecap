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
	
	//@Parameters({"url"})
	@BeforeClass
	public void setup() {

	//	System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("--headless");
		
		
		

		driver = new ChromeDriver(options);
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

