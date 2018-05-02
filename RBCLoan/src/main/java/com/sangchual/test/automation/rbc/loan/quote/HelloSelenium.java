package com.sangchual.test.automation.rbc.loan.quote;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelloSelenium {
    public static void main(String[] args) throws InterruptedException {
        String firefoxDriverPath = "G:\\Project\\WebDriver\\geckodriver.exe";
        String URL_PERSONAL_HOME = "https://www.rbcroyalbank.com/personal.html";
        WebDriver webDriver;

        System.setProperty("webdriver.gecko.driver", firefoxDriverPath);

        System.setProperty("webdriver.gecko.driver", "G:\\Project\\WebDriver\\geckodriver.exe");

        // create a FireFox driver
        webDriver = new FirefoxDriver();
        // open the RBC online banking home page
        webDriver.get(URL_PERSONAL_HOME);
        // maximize the browser window
        webDriver.manage().window().maximize();

        System.out.println(webDriver.getTitle());
        Thread.sleep(5000);
        webDriver.close();
    }
}
