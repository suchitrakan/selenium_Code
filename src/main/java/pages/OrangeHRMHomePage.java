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

import Utility.TestUtility;
import ExtentReports.ExtentReportListener;


public class OrangeHRMHomePage {

    private WebDriver driver;
    TestUtility testutility = new TestUtility();

    // Constructor initializes elements with PageFactory
    public OrangeHRMHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page elements
    @FindBy(xpath = "//span[text()='Admin']")
    private WebElement adminLink;

    @FindBy(xpath = "(//button[contains(@class,'oxd-icon-button')]//following::i[contains(@class,'oxd-icon bi-caret-down-fill')])[2]")
    private WebElement expandSystemUser;

    @FindBy(xpath = "//div[contains(@class,'oxd-form-row')]//child::input[contains(@class,'input--active')]")
    private WebElement enterUserName;
    
    @FindBy(xpath = "//button[text()=' Search ']")
    private WebElement searchButton;
    
    @FindBy(xpath = "//div[contains(@class,'table-row')]")
    private WebElement userRecord;
    
    
  
    

    // Page actions
    public void searchForUser(String username) {
    	testutility.waitForElementToBeClickable(driver, adminLink);
    	adminLink.click();
    	testutility.waitForElementToBeClickable(driver, expandSystemUser);
    	expandSystemUser.click();
    	enterUserName.sendKeys(username);
    	searchButton.click();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    	WebElement element = wait.until(ExpectedConditions.visibilityOf(userRecord));
    	ExtentReportListener.logPass("User Found Successfully");
   		
    }
}
    	

