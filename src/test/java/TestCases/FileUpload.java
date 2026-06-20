package TestCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
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

import ExtentReports.ExtentReportListener;
import pages.OrangeHRMLoginPage;
import Utility.BaseTest;
import Utility.DriverManager;
import Utility.ExcelUtily;

public class FileUpload extends BaseTest{
	
	


@Test(priority=1)
public void fileUpload() throws InterruptedException {
	/*n
	 * URL_Amended=https://admin:admin123@the-internet.herokuapp.com/basic_auth
	 * URL_Actual=https://the-internet.herokuapp.com/basic_auth
	 * 
	 */	
	logger.info("File Upload Test Started");
	driver.get("https://the-internet.herokuapp.com/upload");
	Thread.sleep(3000);
	WebElement fileUpload=driver.findElement(By.xpath("//input[@id='file-upload']"));
	String path= System.getProperty("user.dir")+ "/src/test/resources/fileUpload/";
	fileUpload.sendKeys(path+"/fileUpload1.png");
	WebElement uploadButton=driver.findElement(By.xpath("//input[@id='file-submit']"));
	Thread.sleep(5000);
	ExtentReportListener.logPass("File Uploaded Successfully");
	uploadButton.click();
	Thread.sleep(3000);
	ExtentReportListener.logPass("Upload Button clicked");
	WebElement message=driver.findElement(By.xpath("//h3"));
	Assert.assertEquals(message.getText(),"File Uploaded!");
	ExtentReportListener.logPass("Uploaded File can be seen");
	driver.quit();	
}

@Test(priority=2)
public void fileUploadUsingRobot() throws InterruptedException, AWTException {
	/*n
	 * URL_Amended=https://admin:admin123@the-internet.herokuapp.com/basic_auth
	 * URL_Actual=https://the-internet.herokuapp.com/basic_auth
	 * 
	 */	
	DriverManager.initDriver("chrome");
    driver = DriverManager.getDriver();
    driver.manage().window().maximize();
	driver.get("https://the-internet.herokuapp.com/upload");
	Thread.sleep(3000);
	logger.info("Test File Upload with Robot Started");
	driver.findElement(By.xpath("//div[@id='drag-drop-upload']")).click();
    // Copy file path to clipboard
    StringSelection filePath = new StringSelection(System.getProperty("user.dir")+ "\\src\\test\\resources\\fileUpload\\fileUpload1.png");
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
    
    // Use Robot to paste path and press Enter
    Robot robot = new Robot();
    robot.delay(1000);

    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    Thread.sleep(4000);
    WebElement fileU1=driver.findElement(By.xpath("//div[contains(@class,'image-preview')]//child::div[@class='dz-filename']//child::span"));
    Assert.assertEquals(fileU1.getText(),"fileUpload1.png");
	ExtentReportListener.logPass("Uploaded File through Robot can be seen");
	driver.quit();
	
}

@Test(priority=3)
public void fileDownload() throws InterruptedException, AWTException {
	/*n
	 * URL_Amended=https://admin:admin123@the-internet.herokuapp.com/basic_auth
	 * URL_Actual=https://the-internet.herokuapp.com/basic_auth
	 * 
	 */	
	DriverManager.initDriver("chrome");
    driver = DriverManager.getDriver();
    driver.manage().window().maximize();
	driver.get("https://the-internet.herokuapp.com/download");
	Thread.sleep(3000);
	logger.info("Test File Download Started");
	driver.findElement(By.xpath("//a[text()='signature.pdf']")).click();
    // Copy file path to clipboard
	String downloadPath = System.getProperty("user.dir") + "\\src\\test\\resources\\fileDownload\\";
	File downloaded = waitForAnyDownload(downloadPath, 30);

	Assert.assertTrue(downloaded.exists(), "Downloaded file not found!");
	logger.info("File downloaded successfully: " + downloaded.getAbsolutePath());
	 File dir1 = new File(downloadPath);
	 File[] files = dir1.listFiles();
	 for(File f1: files)
	 {
		 
		 f1.delete();
	 }
	
    driver.quit();
}


public File waitForAnyDownload(String downloadDir, int timeoutSeconds) throws InterruptedException {
	 File dir = new File(downloadDir);
	    int waited = 0;
	    while (waited < timeoutSeconds) {
	        File[] files = dir.listFiles();
	        if (files != null) {
	            for (File f : files) {
	                if (!f.getName().endsWith(".crdownload") && !f.getName().endsWith(".tmp") && f.length() > 0) {
	                    return f; // completed file
	                }
	            }
	        }
	        Thread.sleep(1000);
	        waited++;
	    }
	    throw new RuntimeException("Download not completed within timeout");
}
}


