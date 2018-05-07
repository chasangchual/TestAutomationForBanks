package com.sangchual.test.automation.rbc.loan.quote;

import com.sangchual.test.automation.rbc.RBC;
import com.sangchual.test.automation.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class NavigateToLoanTest {
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
    public void testNavigateToLoanPage() {
        // get an a tag with the specified link text
        WebElement linkToLoans = webDriver.findElement(By.linkText("Loans"));
        // click the link
        linkToLoans.click();
        // validate whether it opened the correct page by comparing the page title
        assertEquals(RBC.TITLE_PERSONAL_LOAN, webDriver.getTitle());
    }

    @Test
    public void testWaitAndNavigateToLoanPage() {
        // create a wait handler
        WebDriverWait wait=new WebDriverWait(webDriver, 20);
        // wain until the a tag with the specified link text is clickable and get the element
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Loans")));
        element.click();
        // validate whether it opened the correct page by comparing the page title
        assertEquals(RBC.TITLE_PERSONAL_LOAN, webDriver.getTitle());
    }
}
