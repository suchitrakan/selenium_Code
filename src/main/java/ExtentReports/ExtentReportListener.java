package ExtentReports;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import Utility.BaseTest;
import Utility.ScreenshotUtility;



public class ExtentReportListener implements ITestListener {
	 private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	    @Override
	    public void onTestStart(ITestResult result) {
	        ExtentTest extentTest = BaseTest.getExtent().createTest(result.getMethod().getMethodName());
	        test.set(extentTest);
	        logPass("Starting test: " + result.getMethod().getMethodName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        logPass("Test Passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	    	 String screenshotPath = null;
			 try {
				 screenshotPath = ScreenshotUtility.captureScreenshot(result.getMethod().getMethodName());
			 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
	         //System.out.println(screenshotPath);
	    	 //logFail("Test Failed: " + result.getThrowable());

				
				  if (test.get() != null && screenshotPath != null) {
				  test.get().fail(result.getMethod().getMethodName()+"is failed at this step",
				  MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
				 	             
	       	         }
	    }
	    

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        logFail("Test Skipped: " + result.getThrowable());
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        // Flushing handled in BaseTest
	    }

	    // 👉 Static methods for POM classes
	    public static void logPass(String message) {
	        BaseTest.getLogger().info(message);
	        if (test.get() != null) {
	            test.get().pass(message);
	            
	        }
	    }

	    public static void logFail(String message) {
	        BaseTest.getLogger().error(message);
	        if (test.get() != null) {
	            test.get().fail(message);
	        }
	    }

	    public static ExtentTest getTest() {
	        return test.get();
	    }
	}

