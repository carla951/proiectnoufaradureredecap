package tests;

import static org.testng.Assert.assertEquals;


import java.io.IOException;


import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.LoggedUser;
import pages.LoginPage;

public class LoginTest extends BaseTest{
	
	@Parameters({"user", "pass"})
	@Test
	public void testValidLogin(String username, String password) throws IOException {
	
		
		
		LoginPage loginPage = homePage.navigateToLogin();
		
		loginPage.setUsername(username);
		loginPage.setPassword(password);
		
		LoggedUser loggedUser = loginPage.clickSubmitButton();
		assertEquals(loggedUser.readUserName(), "Test User6");
		
	
	}
}
		
	
			
			
	
		


	
