package org.example.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;


/**
 * Reference:
 * https://github.com/selenide/selenide/issues/525
 */
public class AngularWebdriverEventListener extends AbstractWebDriverEventListener {

    @Override
    public void afterNavigateTo(String url, WebDriver webDriver) {
        WebdriverWaits.waitForAngular(webDriver);
    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {
        WebdriverWaits.waitForAngular(webDriver);
    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {
        WebdriverWaits.waitForAngular(webDriver);
    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {
        WebdriverWaits.waitForAngular(webDriver);
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver webDriver) {
        WebdriverWaits.waitForAngular(webDriver);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver webDriver) {
        WebdriverWaits.waitForAngular(webDriver);
    }

    public void afterClickOn(WebElement element, WebDriver webDriver) {
        WebdriverWaits.waitForAngular(webDriver);
    }

}
