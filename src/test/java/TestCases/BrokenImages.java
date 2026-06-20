package TestCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

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

public class BrokenImages extends BaseTest{
	
	


@Test(priority=1)
public void fileUpload() throws InterruptedException {
	/*n
	 * URL_Amended=https://admin:admin123@the-internet.herokuapp.com/basic_auth
	 * URL_Actual=https://the-internet.herokuapp.com/basic_auth
	 * 
	 */	
	logger.info("Broken Images Test Started");
	driver.get("https://the-internet.herokuapp.com/broken_images");
	Thread.sleep(3000);
	List<WebElement> allImages=driver.findElements(By.tagName("img"));
	System.out.println("Total Images " + allImages.size());
	int m=0, n=0;
	for(WebElement image:allImages)
	{
		String src=image.getAttribute("src");
		try {
			
			HttpURLConnection connection= (HttpURLConnection) new URL(src).openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int ResponseCode=connection.getResponseCode();
			
			if(ResponseCode==200)
			{
				
				
				m++;
			}
			
			else
			{
				
				
				n++;
			}
			
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}
	
	System.out.println("Valid Images are " + m);
	System.out.println("InValid Images are " + n);
	System.out.println(System.getProperty("env"));
	
	driver.quit();	
}


}


