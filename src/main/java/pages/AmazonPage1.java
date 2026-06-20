package pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.TestUtility;
import ExtentReports.ExtentReportListener;


public class AmazonPage1 {

    private WebDriver driver;
    TestUtility testUtility = new TestUtility();

    // Constructor initializes elements with PageFactory
    public AmazonPage1(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page elements
    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    private WebElement searchBox;

    @FindBy(xpath = "//input[contains(@id,'search-submit')]")
    private WebElement searchButton;

    @FindBy(xpath = "//span[contains(text(),'Apple iPhone 16')]")
    private WebElement iphone16;
    
    @FindBy(xpath = "//input[@title='Add to Shopping Cart']//following-sibling::span[text()='Add to cart']")
    private WebElement AddToCart;

    // Page actions
    public void enterSearchKey(String username) {
    	searchBox.sendKeys(username); 
    	ExtentReportListener.logPass("Search Key Entered");
    	}

    public void clickOnSearch() {
    	searchButton.click();
    	ExtentReportListener.logPass("Search Button Clicked");
    	
    }

    public void clickOnProductToSelect() {
    	//testUtility.ScrollIntoView(driver,iphone16);
    	
    	//testUtility.waitForElementToBeClickable(driver,iphone16);
    	iphone16.click();
    	ExtentReportListener.logPass("Product Clicked");;
    }
    
    public void ClickOnAddToCart()
    {
    	try {
    	testUtility.ScrollIntoView(driver,AddToCart);
    	AddToCart.click();
    }
    	catch(NoSuchElementException e)
    	{
    		ExtentReportListener.logFail("Add to Cart is not Present");
    	}
    		
    	}
    
    

   
}
