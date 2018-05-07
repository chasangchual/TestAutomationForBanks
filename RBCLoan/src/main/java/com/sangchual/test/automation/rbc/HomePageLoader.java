package com.sangchual.test.automation.rbc;

import com.sangchual.test.automation.WebDriverHelper;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class HomePageLoader {
    public static WebDriver load() {
        WebDriver driver = WebDriverHelper.newFireFoxDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(RBC.URL_PERSONAL_HOME);
        return driver;
    }
}
