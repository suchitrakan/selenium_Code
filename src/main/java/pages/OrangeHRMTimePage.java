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


public class OrangeHRMTimePage {

    private WebDriver driver;
    TestUtility testutility = new TestUtility();

    // Constructor initializes elements with PageFactory
    public OrangeHRMTimePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page elements
    @FindBy(xpath = "//span[text()='Time']")
    private WebElement timeLink;

    @FindBy(xpath = "//div[contains(@class,'text-input')]//following-sibling::input")
    private WebElement enterEmployeeName;

    @FindBy(xpath = "//p[text()=' * Required']//parent::div//following-sibling::button")
    private WebElement clickViewButton;
    
    
    @FindBy(xpath = "//p[text()=' * Required']//parent::div//following-sibling::button")
    private WebElement invalidMessage;
    
    

    // Page actions
    public void validateEmployeeNameErrorMessageOnTimePage(String username) throws InterruptedException {
    	testutility.waitForElementToBeClickable(driver, timeLink);
    	timeLink.click();
    	enterEmployeeName.sendKeys(username);
    	clickViewButton.click();
    	try {
    	testutility.waitForVisibilityOfElement(driver, invalidMessage);
    	ExtentReportListener.logPass("Employee Name is validated successfully");
    	}
    	catch(Exception e)
    	{
    		ExtentReportListener.logFail("No Error Message found");
    		Assert.fail("Failing test because Error message was not found");
    	}
    	
    	
   		
    }
}
    	

