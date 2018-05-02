package com.sangchual.test.automation.rbc.loan.quote;

import com.sangchual.test.automation.WebDriverHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CarLoanCalculator {
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
    @Test
    public void testCarLoanCalculator() {
        ////////////////////////////////////////////////////////////////////////////////////////
        WebElement borrowingReason = getElement(webDriver, By.id("br"));
        Select dropdownBorrowReason= new Select(borrowingReason);
        dropdownBorrowReason.selectByValue("car");

        WebElement borrowingArmout = getElement(webDriver, By.id("am"));
        borrowingArmout.sendKeys("12000");

        WebElement linkToQuote = getElement(webDriver, By.id("lp-calc"));
        linkToQuote.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("0");
        WebElement vehicleAge = getElement(webDriver, By.id("vehicle-age"));
        System.out.println("1");
        Select dropdownVehicleAge = new Select(vehicleAge);
        System.out.println("2");
        dropdownVehicleAge.selectByValue("0");
        System.out.println("3");

        WebElement vehiclePriceBtn = getElement(webDriver, By.id("vehicle-price-btn"));
        vehiclePriceBtn.click();

        WebElement vehiclePrice = getElement(webDriver, By.id("vehicle-price"));
        vehiclePrice.clear();
        vehiclePrice.sendKeys("42000");
        vehiclePrice.sendKeys(Keys.ENTER);

        WebElement vehicleDownPayment = getElement(webDriver, By.id("vehicle-down-payment"));
        vehicleDownPayment.clear();
        vehicleDownPayment.sendKeys("25000");
        vehicleDownPayment.sendKeys(Keys.ENTER);

        WebElement vehicleTradeIn = getElement(webDriver, By.id("vehicle-trade-in"));
        vehicleTradeIn.clear();
        vehicleTradeIn.sendKeys("2000");
        vehicleTradeIn.sendKeys(Keys.ENTER);

        WebElement vehicleCashIncentive = getElement(webDriver, By.id("vehicle-cash-incentive"));
        vehicleCashIncentive.clear();
        vehicleCashIncentive.sendKeys("500");
        vehicleCashIncentive.sendKeys(Keys.ENTER);

        vehiclePriceBtn.click();

        WebElement interest = getElement(webDriver, By.id("interest"));
        interest.clear();
        interest.sendKeys("0.37");
        interest.sendKeys(Keys.ENTER);

        WebElement loanPaymentPeriodCalc = getElement(webDriver, By.id("loan-payment-period-calc"));
        Select dropdownloanPaymentPeriodCalc= new Select(loanPaymentPeriodCalc);
        dropdownloanPaymentPeriodCalc.selectByValue("3");

        WebElement loanPaymentFrequency = getElement(webDriver, By.id("loan-payment-frequency"));
        Select dropdownloanPaymentFrequency= new Select(loanPaymentFrequency);
        dropdownloanPaymentFrequency.selectByValue("bi_weekly");
        ////////////////////////////////////////////////////////////////////////////////////////
        WebElement getResultsBtn = getElement(webDriver, By.cssSelector("button[class='rds-btn rds-primary calculate-button rds-fl rds-mobile-hidden']"));
        getResultsBtn.click();
    }

    private static WebElement getElement(WebDriver driver, By by) throws NoSuchElementException {
        WebElement linkToLoans = null;
        int tries = 0 ;

        while(linkToLoans == null) {
            try {
                linkToLoans = driver.findElement(by);
            } catch(NoSuchElementException e) {
                if(tries < 3) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e1) {
                        // do nothing
                    }
                    tries ++;
                } else {
                    throw e;
                }
            }
        }

        return linkToLoans;
    }
}
