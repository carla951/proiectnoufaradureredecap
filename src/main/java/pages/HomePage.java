package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage {
	private WebDriver driver;
	
	//locatori 

	private By loginLink = By.linkText("Login");

	
	@SuppressWarnings("unused")
	private By contactLink = By.linkText("CONTACTS");
	
	public By linkComingSoon = By.linkText("COMING SOON");
	
	public By searchIcon = By.cssSelector("button[class*='search_submit']");
	
	public By searchField = By.className("search_field");
	
	public By copyrightLink = By.linkText("KeyTraining");


	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
		
	// metode specifice locatorilor
		
	public LoginPage navigateToLogin() {
		driver.findElement(loginLink).click();
		return new LoginPage(driver); }
	

}
	
	
	
