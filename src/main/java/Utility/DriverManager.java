package Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {

    // ThreadLocal to keep WebDriver instance thread-safe
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

   
    // Initialize driver based on browser parameter
    public static void initDriver(String browser) {
    	
    	
        if (browser == null) {
            throw new IllegalArgumentException("Browser parameter cannot be null");
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options= new ChromeOptions();
                HashMap<String,Object> chromeprefs= new HashMap<>();
                chromeprefs.put("download.default_directory", System.getProperty("user.dir")+ "\\src\\test\\resources\\fileDownload\\");
                chromeprefs.put("download.prompt_for_download", false);
                options.setExperimentalOption("prefs", chromeprefs);
                driver.set(new ChromeDriver(options));
                break;

            case "firefox":
                     WebDriverManager.firefoxdriver().setup();
                     driver.set(new FirefoxDriver());
                break;

            case "edge":
            	System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/msedgedriver.exe");
                
                //WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    // Get driver instance
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Quit driver and remove from ThreadLocal
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
