package com.sangchual.test.automation.rbc.loan.quote;

import com.sangchual.test.automation.rbc.HomePageLoader;
import com.sangchual.test.automation.rbc.PersonalHomePage;
import com.sangchual.test.automation.rbc.RBC;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class PersonalLoansHomePageTest {
    WebDriver webDriver ;
    PersonalHomePage personalHomePage;
    PersonalLoansHomePage personalLoansHomePage;

    @BeforeClass
    public void setUpClass() throws Exception {
        webDriver = HomePageLoader.load();
        personalHomePage = new PersonalHomePage(webDriver);
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
        personalLoansHomePage = personalHomePage.navigateToLoans();
        assertEquals(RBC.TITLE_PERSONAL_LOAN, personalLoansHomePage.getTitle());
    }

    @Test(dependsOnMethods="testNavigateToLoans")
    public void testCalculate() throws Exception {
        personalLoansHomePage.selectBorrowReason("car");
        personalLoansHomePage.setLoansParamsters("0", "42000", "25000",
                "2000", "500", "0.37", "3", "bi_weekly");
        assertEquals("$186.94", personalLoansHomePage.getResult());
    }

}