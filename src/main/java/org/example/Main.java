package org.example;

import ch.qos.logback.classic.Logger;
import org.example.suites.AbstractTestSuite;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Logger logger = (Logger) LoggerFactory.getLogger("main");

        ArrayList<AbstractTestSuite> testSuites = new ArrayList<>();
//        Uncomment the following line to enable Facebook testing, prior to doing so please ensure you have read Facebook's Terms-of-Service.
//        testSuites.add(new org.example.suites.facebook.FunctionalTestSuite(logger));
        testSuites.add(new org.example.suites.wallethub.FunctionalTestSuite(logger));

        for (AbstractTestSuite testSuit: testSuites) {
            testSuit.run();
        }

    }

}
