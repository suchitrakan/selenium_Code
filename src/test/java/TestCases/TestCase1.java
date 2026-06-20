package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Utility.BaseTest;
import pages.AmazonPage1;

public class TestCase1 extends BaseTest{
	
@Test
	public void TestCase1() throws InterruptedException {
    AmazonPage1 amazon= new AmazonPage1(driver);
	amazon.enterSearchKey("iPhone 16");
	amazon.clickOnSearch();
	Thread.sleep(3000);
	amazon.clickOnProductToSelect();
	amazon.ClickOnAddToCart();
	}

}
