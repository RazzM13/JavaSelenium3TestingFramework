package org.example.pages.facebook;

import org.example.common.TestDependencies;
import org.example.fixtures.FacebookFixtures;

import org.example.pages.AbstractLoginPage;
import org.openqa.selenium.By;


public class LoginPage extends AbstractLoginPage {

    public By allowAllCookiesButton = By.xpath("//button[contains(text(), '" + FacebookFixtures.allowCookiesButtonText + "')]");

    public LoginPage(TestDependencies testDependencies) {
        super(
                FacebookFixtures.baseUrl + "/login",
                testDependencies,
                By.id("email"),
                By.id("pass"),
                By.id("loginbutton")
        );
    }

    public void acceptCookies() {
        this.testDependencies.webDriver.findElement(this.allowAllCookiesButton).click();
    }

    @Override
    public void load() {
        super.load();
        this.acceptCookies();
    }

}
