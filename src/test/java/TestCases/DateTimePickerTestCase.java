package TestCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.DateTimePicker;
import pages.OrangeHRMLoginPage;
import Utility.BaseTest;
import Utility.DriverManager;
import Utility.ExcelUtily;

public class DateTimePickerTestCase extends BaseTest{
	
	


@Test()
public void testBasicAuthUsingURL() throws InterruptedException {
	/*n
	 * URL_Amended=https://admin:admin123@the-internet.herokuapp.com/basic_auth
	 * URL_Actual=https://the-internet.herokuapp.com/basic_auth
	 * 
	 */	
	
	driver.get("https://www.tutorialspoint.com/selenium/practice/date-picker.php");
	Thread.sleep(4000);
	DateTimePicker dp= new DateTimePicker(driver);
	dp.SelectDateTime("2024-02-19 10:59");
	dp.dateTimevalue();
	Thread.sleep(4000);
}
}

