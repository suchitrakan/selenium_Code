package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigPropertiesReader {

    private static Properties properties = new Properties();

    static {
        try {
            // Load properties file
        	String env="st";
            FileInputStream fis = new FileInputStream("src/main/resources/" + env + "_config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    // Method to get property by key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
