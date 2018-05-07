package com.sangchual.test.automation.rbc;

import com.sangchual.test.automation.WebPageObject;
import com.sangchual.test.automation.rbc.loan.quote.PersonalLoansHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.net.MalformedURLException;

public class PersonalHomePage extends WebPageObject {
    @FindBy(how = How.LINK_TEXT, using = "Loans")
    @CacheLookup // we know that element is always present on the page.
    private WebElement linkToLoans;

    public PersonalHomePage(WebDriver driver) throws MalformedURLException {
        super(driver.getCurrentUrl(), driver);
    }

    public PersonalLoansHomePage navigateToLoans() throws MalformedURLException {
        linkToLoans.click();
        return new PersonalLoansHomePage(this.driver);
    }
}