package org.example.pages;

import java.net.MalformedURLException;
import java.net.URL;

import org.example.common.TestDependencies;


abstract public class AbstractPage {

    public URL url;
    public TestDependencies testDependencies;

    public AbstractPage(String url, TestDependencies testDependencies) {

        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        this.testDependencies = testDependencies;
    }

    public void load() {
        this.testDependencies.webDriver.navigate().to(this.url);
    }

}
