package com.sangchual.test.automation;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class WebPageObject extends PageObject {
    @Getter
    protected final URL url;
    @Getter
    protected String title;

    public WebPageObject(String url, WebDriver driver) throws MalformedURLException {
        this(new URL(url), driver);
    }

    public WebPageObject(URL url, WebDriver driver) {
        super(driver);
        this.url = url;
        this.title = driver.getTitle();
    }

    protected WebElement getVisibleElement(By by) throws NoSuchElementException {
        return WebPageObject.getVisibleElement(this.driver, by) ;
    }

    protected WebElement getClickableElement(By by) throws NoSuchElementException {
        return WebPageObject.getClickableElement(this.driver, by) ;
    }

    protected WebElement getPresentElement(By by) throws NoSuchElementException {
        return WebPageObject.getPresentElement(this.driver, by) ;
    }

    protected void moveTo(WebElement element) throws NoSuchElementException {
        WebPageObject.moveTo(this.driver, element) ;
    }

    public static WebElement getVisibleElement(WebDriver webDriver, By by) throws NoSuchElementException {
        WebElement linkToLoans = null;
        int tries = 0 ;

        // create a wait handler
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        // wain until the a tag with the specified link text is visible
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

        return element;
    }

    public static WebElement getClickableElement(WebDriver webDriver, By by) throws NoSuchElementException {
        WebElement linkToLoans = null;
        int tries = 0 ;

        // create a wait handler
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        // wain until the a tag with the specified link text is clickable
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));

        return element;
    }

    public static WebElement getPresentElement(WebDriver webDriver, By by) throws NoSuchElementException {
        WebElement linkToLoans = null;
        int tries = 0 ;

        // create a wait handler
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        // wain until the a tag with the specified link text is clickable
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

        return element;
    }

    public static void moveTo(WebDriver webDriver, WebElement element) throws NoSuchElementException {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element);
        actions.perform();
    }
}