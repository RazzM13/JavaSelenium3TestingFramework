package org.example.pages.wallethub;

import org.example.common.TestDependencies;
import org.example.fixtures.WallethubFixtures;
import org.example.pages.AbstractPage;
import org.openqa.selenium.By;

public class UserProfilePage extends AbstractPage {

    public By recommendationsSection = By.xpath("//section[contains(., 'Recommendations')]");

    public UserProfilePage(TestDependencies testDependencies) {
        super(WallethubFixtures.baseUrl + "/profile/" + WallethubFixtures.userId, testDependencies);
    }

}
