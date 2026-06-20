package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utility.BaseTest;
import Utility.ConfigPropertiesReader;
import pages.OrangeHRMHomePage;
import pages.OrangeHRMLoginPage;
import Utility.ExcelUtily;

public class OrangeHRMValidateUser extends BaseTest{
	String username;
	String password;
	
	
@BeforeClass
	public void OrangeHRMLogin() throws InterruptedException {
   
	OrangeHRMLoginPage login= new OrangeHRMLoginPage(driver);
	ExcelUtily excel = new ExcelUtily(ConfigPropertiesReader.getProperty("DataSheet_QA"), "Sheet1");
	username = excel.getUsername("OrangeHRMValidateUser");
	password = excel.getPassword("OrangeHRMValidateUser");
	login.loginOrangeHRM(username, password);
	Thread.sleep(3000);
	}

@AfterClass
public void LogOutOrangeHRM()
{
	OrangeHRMLoginPage login= new OrangeHRMLoginPage(driver);
	login.logOut();
}

@Test
public void ValidateUserSearch() throws InterruptedException {
	OrangeHRMHomePage homePage= new OrangeHRMHomePage(driver);
	homePage.searchForUser(username);
	Thread.sleep(3000);
}
}

