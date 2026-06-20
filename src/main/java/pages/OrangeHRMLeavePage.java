package pages;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import Utility.TestUtility;
import ExtentReports.ExtentReportListener;


public class OrangeHRMLeavePage {

    private WebDriver driver;
    TestUtility testutility = new TestUtility();

    // Constructor initializes elements with PageFactory
    public OrangeHRMLeavePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page elements
    @FindBy(xpath = "//span[text()='Leave']")
    private WebElement leaveLink;

    @FindBy(xpath = "//a[text()='Assign Leave ']")
    private WebElement assignLeave;

    @FindBy(xpath = "//div[contains(@class,'text-input')]//child::input")
    private WebElement enterEmployeeName;
    
    
    @FindBy(xpath="//label[text()='Leave Type']//parent::div//following-sibling::div//child::i")
    private WebElement selectLeaveType;

    @FindBy(xpath="//div[contains(@class,'oxd-table-row')]")
    private WebElement leaveRecord;

    
  
    

    // Page actions
    public void validateLeavesRecord() throws InterruptedException {
    	testutility.waitForElementToBeClickable(driver, leaveLink);
    	leaveLink.click();
    	try {
    	testutility.waitForVisibilityOfElement(driver, leaveRecord);
    	ExtentReportListener.logPass("Leave Records Found Successfully");
    	}
    	catch(Exception e)
    	{
    		ExtentReportListener.logFail("No Leave Records Found");
    		Assert.fail("Failing test because Leave Records were not found");
    	}
    	
    	
   		
    }
}
    	

