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


import pages.OrangeHRMLoginPage;
import Utility.BaseTest;
import Utility.DriverManager;
import Utility.ExcelUtily;

public class BasicAuthTestCase extends BaseTest{
	
	


@Test(priority=1)
public void testBasicAuthUsingURL() throws InterruptedException {
	/*n
	 * URL_Amended=https://admin:admin123@the-internet.herokuapp.com/basic_auth
	 * URL_Actual=https://the-internet.herokuapp.com/basic_auth
	 * 
	 */	
	DriverManager.initDriver("chrome");
    driver = DriverManager.getDriver();
    driver.manage().window().maximize();
	driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
	WebElement Success= driver.findElement(By.xpath("//p[normalize-space(text())='Congratulations! You must have the proper credentials.']"));
	Assert.assertEquals(Success.getText().trim(), "Congratulations! You must have the proper credentials.");
	driver.quit();
	
}

@Test(priority=0)
public void testBasicAuthUsingRobot() throws InterruptedException, AWTException {
	/*
	 * URL_Amended=https://admin:admin123@the-internet.herokuapp.com/basic_auth
	 * URL_Actual=https://the-internet.herokuapp.com/basic_auth
	 */	
	driver.get("https://the-internet.herokuapp.com/basic_auth");
	Thread.sleep(3000);
	Robot robot = new Robot();
    robot.setAutoDelay(100);

    // Type username: "admin"
    typeText(robot, "admin");

    // Press TAB to move to password field
    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_TAB);

    // Type password: replace with your actual password
    typeText(robot, "admin");

    // Press ENTER to submitdmin	
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
	WebElement Success= driver.findElement(By.xpath("//p[normalize-space(text())='Congratulations! You must have the proper credentials.']"));
	Assert.assertEquals(Success.getText().trim(), "Congratulations! You must have the proper credentials.");
	driver.quit();
	Thread.sleep(4000);
}


private void typeText(Robot robot, String text) {
    for (char c : text.toCharArray()) {
        int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
        if (KeyEvent.CHAR_UNDEFINED == keyCode) {
            throw new RuntimeException(
                "Key code not found for character '" + c + "'");
        }
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
    }
}

@Test(priority=2)
public void testBasicAuthUsingAutoIT() throws InterruptedException {
	/*n
	 * URL_Amended=https://admin:admin123@the-internet.herokuapp.com/basic_auth
	 * URL_Actual=https://the-internet.herokuapp.com/basic_auth
	 * 
	 */	
	
	  DriverManager.initDriver("chrome"); driver = DriverManager.getDriver();
	  driver.manage().window().maximize();
	 
	
	  DriverManager.initDriver("chrome"); driver = DriverManager.getDriver();
	  driver.manage().window().maximize();
	 
	driver.get("https://the-internet.herokuapp.com/basic_auth");
	Thread.sleep(3000);	
	
	  try { // Run AutoIt script to handle authentication popup
	  Runtime.getRuntime().exec("src/test/resources/basicauthScript.exe"); }
	  catch(Exception e) { e.printStackTrace(); }
	  Thread.sleep(4000);
	 
	WebElement Success= driver.findElement(By.xpath("//p[normalize-space(text())='Congratulations! You must have the proper credentials.']"));
	Assert.assertEquals(Success.getText().trim(), "Congratulations! You must have the proper credentials.");
	driver.quit();
	
}
}

//the-internet.herokuapp.com/basic_auth - Google Chrome
