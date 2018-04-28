package com.sangchual.test.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverHelper {
    private static String firefoxDriverPath = "G:\\Project\\WebDriver\\geckodriver.exe";
    private static WebDriver webDriverFireFox = null ;

    public static void setDriverPath() {
        // to specify FireFox driver path in the system environment
        System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
    }

    public static WebDriver getFireFoxDriver() {
        if(webDriverFireFox == null) {
            webDriverFireFox = new FirefoxDriver();
        }
        return webDriverFireFox;
    }

    public static WebDriver newFireFoxDriver() {
        return new FirefoxDriver();
    }
}
