package org.example.pages.wallethub;

import org.example.common.TestDependencies;
import org.example.fixtures.WallethubFixtures;
import org.example.pages.AbstractPage;
import org.openqa.selenium.By;

public class ReviewConfirmationPage extends AbstractPage {

    public By reviewConfirmationMessage = By.xpath("//*[text()='Your review has been posted.']");

    public ReviewConfirmationPage(TestDependencies testDependencies) {
        super(WallethubFixtures.baseUrl + "/confirm-review", testDependencies);
    }

    @Override
    public void load() {
        throw new UnsupportedOperationException("This page does not support direct loading!");
    }

}
