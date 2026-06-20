package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.TestUtility;
import ExtentReports.ExtentReportListener;


public class OrangeHRMLoginPage {

    private WebDriver driver;
    TestUtility testUtility = new TestUtility();

    // Constructor initializes elements with PageFactory
    public OrangeHRMLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page elements
    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameE;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordE;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;
    
    @FindBy(xpath = "//i[contains(@class,'userdropdown-icon')]")
    private WebElement userDropDown;
    
    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logOut;
    

    // Page actions
    public void loginOrangeHRM(String username,String password) {
    	usernameE.sendKeys(username);
    	passwordE.sendKeys(password);
    	loginButton.click();
    	
    	ExtentReportListener.logPass("Login Successful");
 
    		
    }
    
    public void logOut() {
    	userDropDown.click();
    	logOut.click();
    	
    	ExtentReportListener.logPass("Logout Successful");
 
    		
    }
}
    	

