package org.example.suites;

import org.apache.commons.io.FileUtils;
import org.example.common.TestDependencies;
import org.example.tests.AbstractTestCase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

abstract public class AbstractTestSuite {

    protected Logger logger;

    protected TestDependencies testDependencies = new TestDependencies();

    private final ArrayList<AbstractTestCase> testCases = new ArrayList<>();

    public AbstractTestSuite(Logger logger) {
        this.logger = logger;
    }

    protected void beforeAll() {}

    protected void afterAll() {}

    protected void beforeEach(AbstractTestCase testCase) {}

    protected void afterEach(AbstractTestCase testCase) {
        if (testCase.hasFailed | testCase.hasFaulted) {
            File screenshot = ((TakesScreenshot) this.testDependencies.webDriver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshot, new File("logs/" + testCase.getClass().getSimpleName() + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void run() {
        int total = 0;
        int passed = 0;
        int failed = 0;
        int faulted = 0;

        this.logger.info("Running test suite: " + this.getClass().getName());
        this.beforeAll();
        for (AbstractTestCase testCase: testCases) {
            total++;

            this.beforeEach(testCase);
            testCase.run();
            this.afterEach(testCase);

            String testCaseStatus;
            if (testCase.hasFaulted) {
                faulted++;
                testCaseStatus = "ERROR";
            } else if (testCase.hasFailed) {
                failed++;
                testCaseStatus = "FAIL";
            } else {
                passed++;
                testCaseStatus = "PASS";
            }
            this.logger.info("Test " + testCase.getClass().getName() + " " + testCaseStatus);

            if (testCase.hasFaulted | testCase.hasFailed) {
                this.logger.warn(testCase.errorMessage);
            }
        }
        this.afterAll();
        this.logger.info(String.format(
                "Finished running %s tests of which %s passed, %s failed and %s faulted.",
                total, passed, failed, faulted
        ));
    }

    public void registerTestCase(AbstractTestCase testCase) {
        this.testCases.add(testCase);
    }

}
