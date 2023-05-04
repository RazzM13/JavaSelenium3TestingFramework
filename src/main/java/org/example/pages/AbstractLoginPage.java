package org.example.pages;

import org.example.common.TestDependencies;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.*;

public class AbstractLoginPage extends AbstractPage {

    public By usernameTextbox;
    public By passwordTextbox;
    public By loginButton;

    public AbstractLoginPage(String url, TestDependencies testDependencies, By usernameTextbox, By passwordTextbox, By loginButton) {
        super(url, testDependencies);
        this.usernameTextbox = usernameTextbox;
        this.passwordTextbox = passwordTextbox;
        this.loginButton = loginButton;
    }

    public boolean isLoggedIn() {
        return !this.testDependencies.webDriver.getCurrentUrl().equals(this.url.toString());
    }

    public void login(String username, String password) {
        assertThat(this.isLoggedIn()).isFalse();
        this.testDependencies.webDriver.findElement(this.usernameTextbox).sendKeys(username);
        this.testDependencies.webDriver.findElement(this.passwordTextbox).sendKeys(password);
        this.testDependencies.webDriver.findElement(this.loginButton).click();
        assertThat(this.isLoggedIn()).isTrue();
    }

}
