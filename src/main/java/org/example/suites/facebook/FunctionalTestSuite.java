package org.example.suites.facebook;

import org.example.common.WebdriverFactory;
import org.example.pages.facebook.HomePage;
import org.example.pages.facebook.LoginPage;
import org.example.suites.AbstractTestSuite;
import org.example.tests.AbstractTestCase;
import org.example.tests.facebook.PostHelloWorldTestCase;
import org.slf4j.Logger;

import java.util.HashMap;

/**
 * This test suite makes use of Facebook's services and is subject to it's Terms-of-Service.
 * Currently <a href="https://www.facebook.com/legal/terms">Facebook's (ToS)</a> states:
 * "You may not access or collect data from our Products using automated means
 * (without our prior permission) or attempt to access data that you do not have
 * permission to access. We also reserve all of our rights against text and data mining."
 */
public class FunctionalTestSuite extends AbstractTestSuite {

    public FunctionalTestSuite(Logger logger) {
        super(logger);
        this.registerTestCase(new PostHelloWorldTestCase(this.testDependencies));
    }

    @Override
    protected void beforeAll() {
        this.testDependencies.pages = new HashMap<>();
        this.testDependencies.pages.put(HomePage.class.getSimpleName(), new HomePage(this.testDependencies));
        this.testDependencies.pages.put(LoginPage.class.getSimpleName(), new LoginPage(this.testDependencies));
    }

    @Override
    protected void beforeEach(AbstractTestCase testCase) {
        this.testDependencies.webDriver = WebdriverFactory.getFirefoxWebdriver();
    }

    @Override
    protected void afterEach(AbstractTestCase testCase) {
        super.afterEach(testCase);
        this.testDependencies.webDriver.close();
    }

}
