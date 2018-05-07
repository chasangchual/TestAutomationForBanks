package com.sangchual.test.automation.rbc.loan.quote;

import com.sangchual.test.automation.rbc.RBC;
import com.sangchual.test.automation.WebDriverHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class CarLoanCalculator {
    WebDriver webDriver;

    @BeforeClass
    private void setUpClass() {
        WebDriverHelper.setDriverPath();

        // create a FireFox driver
        webDriver = WebDriverHelper.getFireFoxDriver();
        // open the RBC online banking home page
        webDriver.get(RBC.URL_PERSONAL_HOME);
        // maximize the browser window
        webDriver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        // close the browser
        webDriver.close();
    }

    @Test
    public void navigateToLoanPage() {
        WebElement element = getClickableElement(webDriver, By.linkText("Loans"));
        element.click();
        // validate whether it opened the correct page by comparing the page title
        assertEquals(RBC.TITLE_PERSONAL_LOAN, webDriver.getTitle());
    }

    @Test(dependsOnMethods="navigateToLoanPage")
    public void navigateToCalculator() {
        WebElement borrowingReason = getVisibleElement(webDriver, By.id("br"));
        Select dropdownBorrowReason = new Select(borrowingReason);
        dropdownBorrowReason.selectByValue("car");
        
        WebElement linkToCalculator = getClickableElement(webDriver, By.id("lp-calc"));
        linkToCalculator.click();
    }

    @Test(dependsOnMethods="navigateToCalculator")
    public void enterVehicleAgeAndPrice() {
        WebElement vehicleAge = getVisibleElement(webDriver, By.id("vehicle-age"));
        Select dropdownVehicleAge = new Select(vehicleAge);
        dropdownVehicleAge.selectByValue("0");

        WebElement vehiclePriceBtn = getClickableElement(webDriver, By.id("vehicle-price-btn"));
        vehiclePriceBtn.click();

        WebElement vehiclePrice = getVisibleElement(webDriver, By.id("vehicle-price"));
        vehiclePrice.clear();
        vehiclePrice.sendKeys("42000");
        vehiclePrice.sendKeys(Keys.ENTER);

        WebElement vehicleDownPayment = getVisibleElement(webDriver, By.id("vehicle-down-payment"));
        vehicleDownPayment.clear();
        vehicleDownPayment.sendKeys("25000");
        vehicleDownPayment.sendKeys(Keys.ENTER);

        WebElement vehicleTradeIn = getVisibleElement(webDriver, By.id("vehicle-trade-in"));
        vehicleTradeIn.clear();
        vehicleTradeIn.sendKeys("2000");
        vehicleTradeIn.sendKeys(Keys.ENTER);

        WebElement vehicleCashIncentive = getVisibleElement(webDriver, By.id("vehicle-cash-incentive"));
        vehicleCashIncentive.clear();
        vehicleCashIncentive.sendKeys("500");
        vehicleCashIncentive.sendKeys(Keys.ENTER);

        vehiclePriceBtn.click();
    }

    @Test(dependsOnMethods="enterVehicleAgeAndPrice")
    public void getResult() {

        WebElement interest = getVisibleElement(webDriver, By.id("interest"));
        interest.clear();
        interest.sendKeys("0.37");
        interest.sendKeys(Keys.ENTER);

        WebElement loanPaymentPeriodCalc = getVisibleElement(webDriver, By.id("loan-payment-period-calc"));
        Select dropdownloanPaymentPeriodCalc= new Select(loanPaymentPeriodCalc);
        dropdownloanPaymentPeriodCalc.selectByValue("3");

        WebElement loanPaymentFrequency = getVisibleElement(webDriver, By.id("loan-payment-frequency"));
        Select dropdownloanPaymentFrequency= new Select(loanPaymentFrequency);
        dropdownloanPaymentFrequency.selectByValue("bi_weekly");
        ////////////////////////////////////////////////////////////////////////////////////////
        WebElement getResultsBtn = getClickableElement(webDriver, By.cssSelector("button[class='rds-btn rds-primary calculate-button rds-fl rds-mobile-hidden']"));
        getResultsBtn.click();
    }

    @Test(dependsOnMethods="getResult")
    public void validateResult() {
        WebElement result = getVisibleElement(webDriver, By.id("loan-payment-cost"));
        assertEquals("$186.94", result.getText());
    }

    private static WebElement getVisibleElement(WebDriver webDriver, By by) throws NoSuchElementException {
        WebElement linkToLoans = null;
        int tries = 0 ;

        // create a wait handler
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        // wain until the a tag with the specified link text is visible
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

        return element;
    }

    private static WebElement getClickableElement(WebDriver webDriver, By by) throws NoSuchElementException {
        WebElement linkToLoans = null;
        int tries = 0 ;

        // create a wait handler
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        // wain until the a tag with the specified link text is clickable
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));

        return element;
    }

    private static WebElement getPresentElement(WebDriver webDriver, By by) throws NoSuchElementException {
        WebElement linkToLoans = null;
        int tries = 0 ;

        // create a wait handler
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        // wain until the a tag with the specified link text is clickable
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

        return element;
    }

    private static void moveTo(WebDriver webDriver, WebElement element) throws NoSuchElementException {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element);
        actions.perform();
    }
}
