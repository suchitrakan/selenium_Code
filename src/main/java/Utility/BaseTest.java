package Utility;

import java.util.concurrent.TimeUnit;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.OrangeHRMLoginPage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    
    
    @BeforeSuite()
    @Parameters("Report Name")
    public void setupReport(  @Optional("ExtentReport") String ReportName) {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/Reports/"+ReportName+".html");
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Execution Report");
        spark.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "QA Engineer");
        extent.setSystemInfo("Environment", "QA");
    }
    
    
    
    @AfterSuite
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    @BeforeClass
    @Parameters("browser") // Browser parameter from TestNG XML
    public void setUp( @Optional("chrome") String browser) {
    	
        // Initialize driver with browser parameter
        DriverManager.initDriver(browser);
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        System.out.println(ConfigPropertiesReader.getProperty("url_"+System.getProperty("env")));
        driver.get(ConfigPropertiesReader.getProperty("url_"+System.getProperty("env")));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
    }

    @AfterClass
    public void tearDown() {
        // Quit driver after each test
    	DriverManager.quitDriver();
    }
    
    public static ExtentReports getExtent() {
        return extent;
    }
    
    public static Logger getLogger() {
        return logger;
    }
}
