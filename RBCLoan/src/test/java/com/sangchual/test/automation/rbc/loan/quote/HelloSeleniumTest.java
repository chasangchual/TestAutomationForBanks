package com.sangchual.test.automation.rbc.loan.quote;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HelloSeleniumTest {
    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testMain() throws InterruptedException {
        String firefoxDriverPath = "G:\\Project\\WebDriver\\geckodriver.exe";
        String URL_PERSONAL_HOME = "https://www.rbcroyalbank.com/personal.html";
        WebDriver webDriver;

        System.setProperty("webdriver.gecko.driver", firefoxDriverPath);

        // create a FireFox driver
        webDriver = new FirefoxDriver();
        // open the RBC online banking home page
        webDriver.get(URL_PERSONAL_HOME);
        // maximize the browser window
        webDriver.manage().window().maximize();
        assertEquals("Personal Banking - RBC Royal Bank", webDriver.getTitle());
        Thread.sleep(5000);
        webDriver.close();
    }
}