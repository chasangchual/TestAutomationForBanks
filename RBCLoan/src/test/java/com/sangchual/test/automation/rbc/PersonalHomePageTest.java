package com.sangchual.test.automation.rbc;

import com.sangchual.test.automation.rbc.loan.quote.PersonalLoansHomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class PersonalHomePageTest {
    WebDriver webDriver ;

    @BeforeClass
    public void setUpClass() {
        webDriver = HomePageLoader.load();
    }

    @AfterClass
    public void tearDownClass() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriver.close();
    }

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
        webDriver.manage().deleteAllCookies();
    }

    @Test
    public void testNavigateToLoans() throws Exception {
        PersonalHomePage personalHomePage = new PersonalHomePage(webDriver);
        assertEquals(RBC.TITLE_PERSONAL_HOME, personalHomePage.getTitle());
        PersonalLoansHomePage personalLoansHomePage = personalHomePage.navigateToLoans();
    }
}