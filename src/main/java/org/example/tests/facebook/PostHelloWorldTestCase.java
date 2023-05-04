package org.example.tests.facebook;

import org.example.common.TestDependencies;
import org.example.fixtures.FacebookFixtures;
import org.example.pages.facebook.HomePage;
import org.example.pages.facebook.LoginPage;
import org.example.tests.AbstractTestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;
import static org.assertj.core.api.Assertions.*;

public class PostHelloWorldTestCase extends AbstractTestCase {

    public PostHelloWorldTestCase(TestDependencies testDependencies) {
        super("" +
                "Given we are on the homepage" +
                "When we post a new status message" +
                "Then the new post should be visible",
                testDependencies
        );
    }

    @Override
    protected void script() {
        HomePage homePage = (HomePage) this.testDependencies.pages.get(HomePage.class.getSimpleName());
        homePage.load();
        homePage.postStatusMessage(FacebookFixtures.helloWorldMessage);
        By hellWorldPost = new ByChained(
                homePage.postFeed,
                By.xpath("//*[contains(text(), '" + FacebookFixtures.helloWorldMessage + "')]")
        );
        Boolean hellWorldPostIsDisplayed = this.testDependencies.webDriver.findElement(hellWorldPost).isDisplayed();
        assertThat(hellWorldPostIsDisplayed).isTrue();
    }

    @Override
    protected void before() {
        LoginPage loginPage = (LoginPage) this.testDependencies.pages.get(LoginPage.class.getSimpleName());
        loginPage.load();
        loginPage.login(FacebookFixtures.username, FacebookFixtures.password);
    }

}
