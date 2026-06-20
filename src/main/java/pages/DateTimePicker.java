package pages;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.TestUtility;

public class DateTimePicker {
	
	 private WebDriver driver;
	    TestUtility testUtility = new TestUtility();

	    // Constructor initializes elements with PageFactory
	    public DateTimePicker(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    // Page elements
	   
	    
	    @FindBy(xpath = "//div[text()='Select Date']//parent::div//child::div[@id='datepicker']//following::input[contains(@id,'datetimepicker1')]")
	    private WebElement dateTimeInputSelectorFirst;
	    
	    @FindBy(xpath = "//input[@id='datetimepicker1']")
	    private WebElement dateTimeInput;
	    
	  
	    
	    @FindBy(xpath="//input[@aria-label='Year']//following-sibling::span[@class='arrowUp']")
	    private WebElement yearScrollUp;
	    
	    @FindBy(xpath="//input[@aria-label='Year']//following-sibling::span[@class='arrowDown']")
	    private WebElement yearScrollDown;
	    
	    
	    @FindBy(xpath="//select[@aria-label='Month']")
	    private WebElement clickMonth;
	    
	    @FindBy(xpath="//select[@aria-label='Month']//following::option")
	    private List<WebElement> allMonths;
	    
	    @FindBy(xpath="//div[@class='flatpickr-days']//child::div[@class='dayContainer']//following::span[@class='flatpickr-day']")
	    private List<WebElement> allDays;
	    
	    @FindBy(xpath="//input[contains(@class,'flatpickr-hour')]")
	    private WebElement hourInput;
	    
	    @FindBy(xpath="//input[contains(@class,'flatpickr-minute')]")
	    private WebElement minuteInput;
	    
	    @FindBy(xpath="//span[@title='Click to toggle']")
	    private WebElement AmOrPMToggle;
	    
	    public void dateTimevalue()
	    {
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	String text = (String) js.executeScript("return arguments[0].textContent;", dateTimeInput);
	    	System.out.println("Value is" + text);
	    	
	    }
	    
	    public void SelectDateTime(String DateTime) throws InterruptedException
	    {
	    	
	    	String[] dateTime=DateTime.split(" ");
	        String[] dateComponent=dateTime[0].split("-");
	        String[] timeComponent=dateTime[1].split(":");
	    	dateTimeInputSelectorFirst.click();
	    	int yearDiff=Integer.parseInt(dateComponent[0]) - Calendar.getInstance().get(Calendar.YEAR);
	    	if(yearDiff!=0)
	    	{
	    		if(yearDiff>0)
	    		{
	    			for(int i=0;i<yearDiff;i++)
	    			{
	    				yearScrollUp.click();
	    			}
	    		}
	    		
	    		if(yearDiff<0)
	    		{
	    			for(int i=0;i<yearDiff*(-1);i++)
	    			{
	    				yearScrollDown.click();
	    			}
	    		}
	    		
	    	}
	    	clickMonth.click();
	    	
	    	String monthInWords=findMonth(dateComponent[1]);
	    	for(WebElement month:allMonths)
	    	{
	    		
	    		if(month.getText().equalsIgnoreCase(monthInWords))
	    				{
	    			      month.click();
	    			
	    				}
	    	}
	    	
	    	int dayToSelect = Integer.parseInt(dateComponent[2], 10);
	    	for (int i = 0; i 
	    			< allDays.size(); i++) {
	    	    List<WebElement> days = driver.findElements(
	    	        By.xpath("//div[@class='flatpickr-days']//div[@class='dayContainer']//span[@class='flatpickr-day']")
	    	    );
	    	    WebElement day = days.get(i);
	    	    if (day.getText().trim().equals(String.valueOf(dayToSelect))) {
	    	        day.click();
	    	        break;
	    	    }
	    	}
	    	
	    	selectHour(timeComponent[0]);
	    	minuteInput.sendKeys(timeComponent[1]);
	    	minuteInput.sendKeys(Keys.TAB);
	    	Thread.sleep(4000);
	    	selectAMOrPM(timeComponent[0]);
	    	
	    }
	    
	    public void selectAMOrPM(String hour)
	    {
	    	int hourInt=Integer.parseInt(hour);
	    	String AM_PM=AmOrPMToggle.getText();
	    	/*if(hourInt>12 && hourInt<25)
	    	{
	    		if(AM_PM.equalsIgnoreCase("AM"));
	    		{
	    		  System.out.println(AM_PM);
	    		  AmOrPMToggle.click();
	    		}
	    	}*/
	    	
	    	if(hourInt<=12)
	    	{
	    		
	    		
	    		AmOrPMToggle.click();
	    	}
	    	
	    }
	    
	    
	    
	    
	    public void selectHour(String hour)
	    {
	    int hourInt=Integer.parseInt(hour);
	    	if(hourInt>12 && hourInt<25)
	    	{
	    		switch (hourInt) {
	    	    case 13:
	    	    hourInput.sendKeys("01");
	    	    break;
	    	    case 14:
	    		hourInput.sendKeys("02");
	    		break;
	    	    case 15:
		        hourInput.sendKeys("03");
		        break;
	    	    case 16:
		    	hourInput.sendKeys("04");
		    	break;
	    	    case 17:
		    	hourInput.sendKeys("05");
		    	break;
	    	    case 18:
	    	    hourInput.sendKeys("06");
	    	    break;
	    	    case 19:
		    	hourInput.sendKeys("07");
		    	break;
	    	    case 20:
		    	hourInput.sendKeys("08");
		    	break;
	    	    case 21:
		    	hourInput.sendKeys("09");
		    	break;
	    	    case 22:
		    	hourInput.sendKeys("10");
		    	break;
	    	    case 23:
			    hourInput.sendKeys("11");
			    break;
	    	    case 24:
			    hourInput.sendKeys("24");
	    	    default:
		    	System.out.println("Invalid hour");	    		
	    	}
	    	}
	    	else
	    	{
	    		hourInput.sendKeys(hour);
	    	}
	    	
	    }
	    public String findMonth(String monthInNumber)
	    {
	    	  switch (monthInNumber) {
	    	    case "01":
	    	      return "January";
	    	    case "02":
	    	      return "February";
	    	    case "03":
	    	      return "March";
	    	    case "04":
	    	      return "April";
	    	    case "05":
	    	      return "May";
	    	    case "06":
	    	      return "June";
	    	    case "07":
	    	      return "July";
	    	    case "08":
	    	      return "August";
	    	    case "09":
	    	      return "September";
	    	    case "10":
	    	      return "October";
	    	    case "11":
	    	      return "November";
	    	    case "12":
	    	      return "December";
	    	    default:
	    	      return "Invalid month";
	    	      
	    	  }
	    	
	    	
	    }


}
