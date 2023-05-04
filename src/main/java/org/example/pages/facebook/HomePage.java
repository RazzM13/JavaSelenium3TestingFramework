package org.example.pages.facebook;

import org.example.common.TestDependencies;
import org.example.fixtures.FacebookFixtures;

import org.example.pages.AbstractPage;
import org.openqa.selenium.By;


public class HomePage extends AbstractPage {

    public By postCreateButton = By.xpath("//*[@role='button' and contains(., \"" + FacebookFixtures.whatsOnYourMindButtonText + "\")]");
    public By postContentTextbox = By.xpath("//*[@role='textbox' and contains(., \"" + FacebookFixtures.whatsOnYourMindButtonText + "\"')]");
    public By postSubmitButton = By.xpath("//*[@role='button' and contains(., '" + FacebookFixtures.postButtonText + "')]");
    public By postFeed = By.xpath("//*[@role='feed' ]");

    public HomePage(TestDependencies testDependencies) {
        super(FacebookFixtures.baseUrl + '/', testDependencies);
    }

    public void postStatusMessage(String message) {
        this.testDependencies.webDriver.findElement(this.postCreateButton).click();
        this.testDependencies.webDriver.findElement(this.postContentTextbox).sendKeys(message);
        this.testDependencies.webDriver.findElement(this.postSubmitButton).click();
    }

}
