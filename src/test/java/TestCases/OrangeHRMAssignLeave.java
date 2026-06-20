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
import pages.OrangeHRMLeavePage;
import pages.OrangeHRMLoginPage;
import Utility.ExcelUtily;

public class OrangeHRMAssignLeave extends BaseTest{
	String username;
	String password;
	
@BeforeClass
	public void OrangeHRMLogin() throws InterruptedException {
   
	OrangeHRMLoginPage login= new OrangeHRMLoginPage(driver);
	ExcelUtily excel = new ExcelUtily(ConfigPropertiesReader.getProperty("DataSheet_QA"), "Sheet1");
	username = excel.getUsername("OrangeHRMAssignLeave");
	password = excel.getPassword("OrangeHRMAssignLeave");
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
public void ValidateAssignLeave() throws InterruptedException {
	OrangeHRMLeavePage homePage= new OrangeHRMLeavePage(driver);
	homePage.validateLeavesRecord();
}
}

