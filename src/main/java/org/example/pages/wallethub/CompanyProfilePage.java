package org.example.pages.wallethub;

import org.example.common.TestDependencies;
import org.example.fixtures.WallethubFixtures;
import org.example.pages.AbstractPage;
import org.openqa.selenium.By;

public class CompanyProfilePage extends AbstractPage {

    public By reviewsSection = By.id("reviews-section");
    public By reviewActionContainer = By.className("review-action");
    public By reviewStarComponent = By.tagName("review-star");
    public By reviewStarComponentStar1 = By.xpath("//*[@role='radio'][1]");
    public By reviewStarComponentStar2 = By.xpath("//*[@role='radio'][2]");
    public By reviewStarComponentStar3 = By.xpath("//*[@role='radio'][3]");
    public By reviewStarComponentStar4 = By.xpath("//*[@role='radio'][4]");
    public By reviewStarComponentStar5 = By.xpath("//*[@role='radio'][5]");
    public By selectDropdown = By.xpath("//*[@role='button' and text()='Select...']");
    public By healthInsuranceOption = By.xpath("//*[@role='option' and text()='Health Insurance']");
    public By reviewTextbox = By.xpath("//*[@placeholder='Write your review...']");
    public By reviewSubmitButton = By.xpath("//*[@role='button' and text()=' Submit ']");

    public CompanyProfilePage(TestDependencies testDependencies) {
        super(WallethubFixtures.baseUrl + "/profile/" + WallethubFixtures.companyId, testDependencies);
    }

}
