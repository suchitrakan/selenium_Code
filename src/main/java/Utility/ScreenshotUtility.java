package Utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {
	
	static WebDriver driver = DriverManager.getDriver();
	public static String captureScreenshot(String screenshotName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";

        // Save screenshot inside test-output/Report/screenshots
        String folderPath = System.getProperty("user.dir") + "/test-output/Reports/screenshots/";
        String fullPath = folderPath + fileName;

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(fullPath);
		FileUtils.copyFile(srcFile, destFile);
		return fullPath; // return file path for attaching to Extent Report
    }

}
