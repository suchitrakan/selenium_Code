package TestCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class MouseHovers extends BaseTest{
	
	


@Test(priority=1)
public void testActionsMouseHovers() throws InterruptedException {
	/*n
	 * URL_Amended=https://admin:admin123@the-internet.herokuapp.com/basic_auth
	 * URL_Actual=https://the-internet.herokuapp.com/basic_auth
	 * 
	 */	
	logger.info("Test Started");
	driver.get("https://the-internet.herokuapp.com/hovers");
	Thread.sleep(5000);
	Actions actions= new Actions(driver);
	WebElement image1=driver.findElement(By.xpath("//div[@class='example']//child::div//child::img"));
	WebElement profile1=driver.findElement(By.xpath("//a[text()='View profile']"));
	
	actions.moveToElement(image1).build().perform();
	logger.info("Hover Completed");
	logger.warn("Hover Completed with Warn");
	logger.error("Hover Completed with Error");
	logger.debug("Hover Completed with Debug");
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(3));
	wait.until(ExpectedConditions.visibilityOf(profile1));
	profile1.click();
	Assert.assertTrue(false);
	Thread.sleep(4000);
	WebElement Header=driver.findElement(By.xpath("//h1"));
	Assert.assertEquals(Header.getText(),"Not Found");
	logger.info("Test Successful");
	driver.quit();
		
}

@Test(priority=2)
public void testKeyPresses() throws InterruptedException {
	/*n
	 * URL_Amended=https://admin:admin123@the-internet.herokuapp.com/basic_auth
	 * URL_Actual=https://the-internet.herokuapp.com/basic_auth
	 * 
	 */	
	logger.info("Test Key Press Started");
	DriverManager.initDriver("chrome");
    driver = DriverManager.getDriver();
    driver.manage().window().maximize();
	driver.get("https://the-internet.herokuapp.com/key_presses");
	Thread.sleep(3000);
	Actions actions= new Actions(driver);
	actions.sendKeys("R").perform();
	WebElement message= driver.findElement(By.xpath("//form//following-sibling::p"));
	Assert.assertEquals(message.getText(),"You entered: R");
	actions.keyDown(Keys.SHIFT).perform();
	WebElement message1= driver.findElement(By.xpath("//form//following-sibling::p"));
	Assert.assertEquals(message1.getText(),"You entered: SHIFT");
	driver.quit();
	
}
}


