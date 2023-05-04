package org.example.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;

public class WebdriverFactory {

    public static WebDriver getFirefoxWebdriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.setLogLevel(FirefoxDriverLogLevel.INFO);
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "logs/browser.log");

        return new FirefoxDriver(
                (new GeckoDriverService.Builder() {
                    @Override
                    protected File findDefaultExecutable() {
                        if (new File("/snap/bin/geckodriver").exists()) {
                            return new File("/snap/bin/geckodriver") {
                                @Override
                                public String getCanonicalPath() {
                                    return this.getAbsolutePath();
                                }
                            };
                        } else {
                            return super.findDefaultExecutable();
                        }
                    }
                }).build(), options
        );
    }

    public static WebDriver getAngularWebdriver() {
        WebDriver webDriver = WebdriverFactory.getFirefoxWebdriver();
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(webDriver);
        eventFiringWebDriver.register(new AngularWebdriverEventListener());
        return eventFiringWebDriver;
    }

}
