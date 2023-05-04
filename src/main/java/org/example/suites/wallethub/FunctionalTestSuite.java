package org.example.suites.wallethub;

import org.example.common.WebdriverFactory;
import org.example.pages.wallethub.CompanyProfilePage;
import org.example.pages.wallethub.LoginPage;
import org.example.pages.wallethub.ReviewConfirmationPage;
import org.example.pages.wallethub.UserProfilePage;
import org.example.suites.AbstractTestSuite;
import org.example.tests.AbstractTestCase;
import org.example.tests.wallethub.PostReviewTestCase;
import org.slf4j.Logger;

import java.util.HashMap;

public class FunctionalTestSuite extends AbstractTestSuite {

    public FunctionalTestSuite(Logger logger) {
        super(logger);
        this.registerTestCase(new PostReviewTestCase(this.testDependencies));
    }

    @Override
    protected void beforeAll() {
        this.testDependencies.pages = new HashMap<>();
        this.testDependencies.pages.put(LoginPage.class.getSimpleName(), new LoginPage(this.testDependencies));
        this.testDependencies.pages.put(CompanyProfilePage.class.getSimpleName(), new CompanyProfilePage(this.testDependencies));
        this.testDependencies.pages.put(UserProfilePage.class.getSimpleName(), new UserProfilePage(this.testDependencies));
        this.testDependencies.pages.put(ReviewConfirmationPage.class.getSimpleName(), new ReviewConfirmationPage(this.testDependencies));
    }

    @Override
    protected void beforeEach(AbstractTestCase testCase) {
        this.testDependencies.webDriver = WebdriverFactory.getAngularWebdriver();
    }

    @Override
    protected void afterEach(AbstractTestCase testCase) {
        super.afterEach(testCase);
        this.testDependencies.webDriver.close();
    }

}
