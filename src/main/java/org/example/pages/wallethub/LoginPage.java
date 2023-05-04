package org.example.pages.wallethub;

import org.example.common.TestDependencies;
import org.example.fixtures.WallethubFixtures;
import org.example.pages.AbstractLoginPage;
import org.openqa.selenium.By;

public class LoginPage extends AbstractLoginPage {

    public LoginPage(TestDependencies testDependencies) {
        super(
                WallethubFixtures.baseUrl + "/join/login",
                testDependencies,
                By.id("email"),
                By.id("password"),
                By.xpath("//button[contains(.,'Login')]")
        );
    }

}
