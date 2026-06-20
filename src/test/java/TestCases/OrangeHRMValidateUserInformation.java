package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utility.BaseTest;
import Utility.ConfigPropertiesReader;
import Utility.DataproviderManager;
import pages.OrangeHRMHomePage;
import pages.OrangeHRMLeavePage;
import pages.OrangeHRMLoginPage;
import pages.OrangeHRMUserInfo;
import Utility.ExcelUtily;

public class OrangeHRMValidateUserInformation extends BaseTest{
	String username;
	String password;
	
@BeforeClass
	public void OrangeHRMLogin() throws InterruptedException {
   
	OrangeHRMLoginPage login= new OrangeHRMLoginPage(driver);
	ExcelUtily excel = new ExcelUtily(ConfigPropertiesReader.getProperty("DataSheet_QA"), "Sheet1");
	username = excel.getUsername("OrangeHRMValidateUserInformation");
	password = excel.getPassword("OrangeHRMValidateUserInformation");
	login.loginOrangeHRM(username, password);
	Thread.sleep(3000);
	}

@AfterClass
public void LogOutOrangeHRM()
{
	OrangeHRMLoginPage login= new OrangeHRMLoginPage(driver);
	login.logOut();
}

@Test(dataProvider = "UserData", dataProviderClass = DataproviderManager.class)
public void validateUserInformation(Object[] data) throws InterruptedException {
	OrangeHRMUserInfo userInfo= new OrangeHRMUserInfo(driver);
	userInfo.validateUserInfo(data);
}


@Test(dataProvider = "UserData", dataProviderClass = DataproviderManager.class, priority=1)
public void validateUserInformationAfterUpdate(Object[] data) throws InterruptedException {
	OrangeHRMUserInfo userInfo= new OrangeHRMUserInfo(driver);
	userInfo.updateUserInfo(data);
	//System.out.println(data);
	//userInfo.ValidateUserInfoAfterUpdate(data);
}
}

