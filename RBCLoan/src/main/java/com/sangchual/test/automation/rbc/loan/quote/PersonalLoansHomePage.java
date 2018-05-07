package com.sangchual.test.automation.rbc.loan.quote;

import com.sangchual.test.automation.WebPageObject;
import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.net.MalformedURLException;

public class PersonalLoansHomePage extends WebPageObject {
    @FindBy(id="br") private WebElement borrowingReasonElement;
    @FindBy(id="lp-calc") private WebElement linkToCalculatorElement;

    public PersonalLoansHomePage(WebDriver driver) throws MalformedURLException {
        super("http://www.rbcroyalbank.com/personal-loans/index.html", driver);
    }

    public void selectBorrowReason(@NonNull final String reason) {
        Select dropdownBorrowReasonElement = new Select(borrowingReasonElement);
        dropdownBorrowReasonElement.selectByValue(reason);
        linkToCalculatorElement.click();
    }

    public void setLoansParamsters(@NonNull final String vehicleAge, @NonNull final String vehiclePrice,
                                    @NonNull final String vehicleDownPayment, @NonNull final String vehicleTradeIn,
                                    @NonNull final String vehicleCashIncentive, @NonNull final String interest,
                                    @NonNull final String periodIndex, @NonNull final String loanPaymentFrequency) {
        WebElement vehicleAgeElement = getVisibleElement(By.id("vehicle-age"));
        Select dropdownVehicleAgeElement = new Select(vehicleAgeElement);
        dropdownVehicleAgeElement.selectByValue(vehicleAge);

        WebElement vehiclePriceBtnElement = getClickableElement(By.id("vehicle-price-btn"));
        vehiclePriceBtnElement.click();

        WebElement vehiclePriceElement = getVisibleElement(By.id("vehicle-price"));
        vehiclePriceElement.clear();
        vehiclePriceElement.sendKeys(vehiclePrice);
        vehiclePriceElement.sendKeys(Keys.ENTER);

        WebElement vehicleDownPaymentElement = getVisibleElement(By.id("vehicle-down-payment"));
        vehicleDownPaymentElement.clear();
        vehicleDownPaymentElement.sendKeys(vehicleDownPayment);
        vehicleDownPaymentElement.sendKeys(Keys.ENTER);

        WebElement vehicleTradeInElement = getVisibleElement(By.id("vehicle-trade-in"));
        vehicleTradeInElement.clear();
        vehicleTradeInElement.sendKeys(vehicleTradeIn);
        vehicleTradeInElement.sendKeys(Keys.ENTER);

        WebElement vehicleCashIncentiveElement = getVisibleElement(By.id("vehicle-cash-incentive"));
        vehicleCashIncentiveElement.clear();
        vehicleCashIncentiveElement.sendKeys(vehicleCashIncentive);
        vehicleCashIncentiveElement.sendKeys(Keys.ENTER);

        vehiclePriceElement.click();

        WebElement interestElement = getVisibleElement(By.id("interest"));
        interestElement.clear();
        interestElement.sendKeys(interest);
        interestElement.sendKeys(Keys.ENTER);

        WebElement loanPaymentPeriodCalcElement = getVisibleElement(By.id("loan-payment-period-calc"));
        Select dropdownloanPaymentPeriodCalcElement= new Select(loanPaymentPeriodCalcElement);
        dropdownloanPaymentPeriodCalcElement.selectByValue(periodIndex);

        WebElement loanPaymentFrequencyElement = getVisibleElement(By.id("loan-payment-frequency"));
        Select dropdownloanPaymentFrequencyElement = new Select(loanPaymentFrequencyElement);
        dropdownloanPaymentFrequencyElement .selectByValue(loanPaymentFrequency);

        WebElement getResultsBtnElement = getClickableElement(By.cssSelector("button[class='rds-btn rds-primary calculate-button rds-fl rds-mobile-hidden']"));
        getResultsBtnElement.click();
    }

    public String getResult() {
        return getVisibleElement(By.id("loan-payment-cost")).getText();
    }
}