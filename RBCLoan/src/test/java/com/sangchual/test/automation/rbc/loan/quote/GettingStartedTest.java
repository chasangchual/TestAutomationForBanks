package com.sangchual.test.automation.rbc.loan.quote;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GettingStartedTest {
    String URL_PERSONAL_HOME = "https://www.rbcroyalbank.com/personal.html";
    String TITLE_PERSONAL_HOME = "Personal Banking - RBC Royal Bank";
    String TITLE_PERSONAL_LOAN = "Lines of Credit and Loans - RBC Royal Bank";

    String firefoxDriverPath = "G:\\Project\\WebDriver\\geckodriver.exe";
    WebDriver webDriver;

    @BeforeClass
    private void setUpClass() {
        // to specify FireFox driver path in the system environment
        System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
    }

    @BeforeMethod
    public void setUp() {
        // create a FireFox driver
        webDriver = new FirefoxDriver();
        // open the RBC online banking home page
        webDriver.get(URL_PERSONAL_HOME);
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
        assertEquals(TITLE_PERSONAL_HOME, webDriver.getTitle());
    }

    @Test
    public void testNavigateToLoanPage() {
        // get an a tag with the specified link text
        WebElement linkToLoans = webDriver.findElement(By.linkText("Loans"));
        // click the link
        linkToLoans.click();
        // validate whether it opened the correct page by comparing the page title
        assertEquals(TITLE_PERSONAL_LOAN, webDriver.getTitle());
    }

    @Test
    public void testWaitAndNavigateToLoanPage() {
        // create a wait handler
        WebDriverWait wait=new WebDriverWait(webDriver, 20);
        // wain until the a tag with the specified link text is clickable and get the element
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Loans")));
        element.click();
        // validate whether it opened the correct page by comparing the page title
        assertEquals(TITLE_PERSONAL_LOAN, webDriver.getTitle());
    }
}
