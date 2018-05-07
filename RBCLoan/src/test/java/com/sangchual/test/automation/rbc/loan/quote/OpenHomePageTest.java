package com.sangchual.test.automation.rbc.loan.quote;

import com.sangchual.test.automation.rbc.RBC;
import com.sangchual.test.automation.WebDriverHelper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OpenHomePageTest {
    WebDriver webDriver;

    @BeforeClass
    private void setUpClass() {
        WebDriverHelper.setDriverPath();
    }

    @BeforeMethod
    public void setUp() {
        // create a FireFox driver
        webDriver = WebDriverHelper.getFireFoxDriver();
        // open the RBC online banking home page
        webDriver.get(RBC.URL_PERSONAL_HOME);
        // maximize the browser window
        webDriver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        // close the browser
        webDriver.close();
    }

    @Test
    public void testOpenHomePage() {
        // validate whether it opened the correct page by comparing the page title
        assertEquals(RBC.TITLE_PERSONAL_HOME, webDriver.getTitle());
    }
}
