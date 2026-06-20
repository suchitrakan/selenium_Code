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


public class OrangeHRMUserInfo {

    private WebDriver driver;
    TestUtility testutility = new TestUtility();

    // Constructor initializes elements with PageFactory
    public OrangeHRMUserInfo(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page elements
    
    
    @FindBy(xpath = "//span[text()='My Info']")
    private WebElement myInfoLink;
    
    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@name='middleName']")
    private WebElement middleName;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastName;
    
    
    @FindBy(xpath = "//label[text()='Employee Id']//parent::div//following-sibling::div")
    private WebElement employeeId;
    
    @FindBy(xpath = "//label[text()='Other Id']//parent::div//following-sibling::div")
    private WebElement otherId;
    
    @FindBy(xpath = "//label[contains(text(),'Driver')]//parent::div//following-sibling::div")
    private WebElement driverLicenceNumber;
    
    @FindBy(xpath = "//label[contains(text(),'License Expiry Date')]//parent::div//following-sibling::div")
    private WebElement licenseExpiryDate;
    

    @FindBy(xpath = "//label[contains(text(),'Nationality')]//parent::div//following-sibling::div")
    private WebElement nationality;
    
    @FindBy(xpath = "//label[contains(text(),'Marital')]//parent::div//following-sibling::div")
    private WebElement maritalStatus;
    
    @FindBy(xpath = "//label[contains(text(),'Date of Birth')]//parent::div//following-sibling::div")
    private WebElement dateOfBirth;
    
    @FindBy(xpath = "//p[text()=' * Required']//following-sibling::button")
    private WebElement saveButton;

    // Page actions
    public void validateUserInfo(Object[] details) throws InterruptedException {
    	testutility.waitForElementToBeClickable(driver, myInfoLink);
    	myInfoLink.click();
    	Thread.sleep(3000);
    	Assert.assertTrue(firstName.getText().equalsIgnoreCase((String)details[0]));
    	Assert.assertTrue(middleName.getText().equalsIgnoreCase((String)details[1]));
    	Assert.assertTrue(lastName.getText().equalsIgnoreCase((String)details[2]));
    	Assert.assertTrue(employeeId.getText().equalsIgnoreCase((String)details[3]));
    	Assert.assertTrue(otherId.getText().equalsIgnoreCase((String)details[4]));
    	Assert.assertTrue(driverLicenceNumber.getText().equalsIgnoreCase((String)details[5]));
    	Assert.assertTrue(licenseExpiryDate.getText().equalsIgnoreCase((String)details[6]));
    	Assert.assertTrue(nationality.getText().equalsIgnoreCase((String)details[7]));
    	Assert.assertTrue(maritalStatus.getText().equalsIgnoreCase((String)details[8]));
    	Assert.assertTrue(dateOfBirth.getText().equalsIgnoreCase((String)details[9]));
    	ExtentReportListener.logPass("Employee Details are validated successfully");
   		
    }
    
    public void updateUserInfo(Object[] details) throws InterruptedException {
    	
    	Thread.sleep(3000);
    	firstName.sendKeys((String)details[1]);
    	System.out.println((String)details[1]);
    	lastName.sendKeys((String)details[3]);
    	testutility.JavaScriptExecutorClick(driver, saveButton);
    	ExtentReportListener.logPass("User Details Saved");	
    }
    
    public void ValidateUserInfoAfterUpdate(Object[] details) throws InterruptedException
    {
    	Thread.sleep(4000);

    	System.out.println(firstName.getText());
    	System.out.println(lastName.getText());
    	Assert.assertTrue(firstName.getText().contains((String)details[1]));
    	Assert.assertTrue(lastName.getText().contains((String)details[3]));
    	ExtentReportListener.logPass("User Details Verified");
    	   		
    	    }
    }
    	

