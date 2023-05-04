package org.example.tests.wallethub;

import com.github.javafaker.Faker;
import org.example.common.TestDependencies;
import org.example.common.WebdriverWaits;
import org.example.fixtures.WallethubFixtures;
import org.example.pages.wallethub.CompanyProfilePage;
import org.example.pages.wallethub.LoginPage;
import org.example.pages.wallethub.ReviewConfirmationPage;
import org.example.pages.wallethub.UserProfilePage;
import org.example.tests.AbstractTestCase;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByChained;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;
import static org.example.common.VisualTesting.calculateImageMatchPercentage;

public class PostReviewTestCase extends AbstractTestCase {

    public PostReviewTestCase(TestDependencies testDependencies) {
        super("" +
                "Given we are on the Test Insurance Company's profile page" +
                "When we post a new 4 star review containing 200 characters" +
                "Then the new review should be visible on the company's profile page and the poster's profile page",
                testDependencies
        );
    }

    public static void assertThatStarIsLit(WebElement starWebElement) {
        BufferedImage actualImage;
        BufferedImage expectedImage;
        try {
            actualImage = ImageIO.read(starWebElement.getScreenshotAs(OutputType.FILE));
            expectedImage = ImageIO.read(WallethubFixtures.reviewStarLitImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        float imageMatchPercentage = calculateImageMatchPercentage(actualImage, expectedImage, 0.3f);
        assertThat(imageMatchPercentage).isEqualTo(1.0f);
    }

    public static void assertThatStarIsUnlit(WebElement starWebElement) {
        BufferedImage actualImage;
        BufferedImage expectedImage;
        try {
            actualImage = ImageIO.read(starWebElement.getScreenshotAs(OutputType.FILE));
            expectedImage = ImageIO.read(WallethubFixtures.reviewStarUnlitImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        float imageMatchPercentage = calculateImageMatchPercentage(actualImage, expectedImage, 0.3f);
        assertThat(imageMatchPercentage).isEqualTo(1.0f);
    }

    private void checkStarHoverFunctionality() {
        WebDriver webDriver = this.testDependencies.webDriver;
        CompanyProfilePage companyProfilePage = (CompanyProfilePage) this.testDependencies.pages.get(CompanyProfilePage.class.getSimpleName());

        // Prepare review stars selectors
        By reviewStars = new ByChained(
                companyProfilePage.reviewsSection,
                companyProfilePage.reviewActionContainer,
                companyProfilePage.reviewStarComponent
        );
        By reviewStarsStar1 = new ByChained(reviewStars, companyProfilePage.reviewStarComponentStar1);
        By reviewStarsStar2 = new ByChained(reviewStars, companyProfilePage.reviewStarComponentStar2);
        By reviewStarsStar3 = new ByChained(reviewStars, companyProfilePage.reviewStarComponentStar3);
        By reviewStarsStar4 = new ByChained(reviewStars, companyProfilePage.reviewStarComponentStar4);
        By reviewStarsStar5 = new ByChained(reviewStars, companyProfilePage.reviewStarComponentStar5);

        // Bring reviewStars into view
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true)", webDriver.findElement(reviewStars));

        // Check that all stars are unlit by default
        assertThatStarIsUnlit(webDriver.findElement(reviewStarsStar1));
        assertThatStarIsUnlit(webDriver.findElement(reviewStarsStar2));
        assertThatStarIsUnlit(webDriver.findElement(reviewStarsStar3));
        assertThatStarIsUnlit(webDriver.findElement(reviewStarsStar4));
        assertThatStarIsUnlit(webDriver.findElement(reviewStarsStar5));

        // Check that the first star becomes lit upon hover and all others remain unlit
        new Actions(webDriver).moveToElement(webDriver.findElement(reviewStarsStar1)).perform();
        assertThatStarIsLit(webDriver.findElement(reviewStarsStar1));
        assertThatStarIsUnlit(webDriver.findElement(reviewStarsStar2));
        assertThatStarIsUnlit(webDriver.findElement(reviewStarsStar3));
        assertThatStarIsUnlit(webDriver.findElement(reviewStarsStar4));
        assertThatStarIsUnlit(webDriver.findElement(reviewStarsStar5));

        // Check that the first and second stars become lit upon hovering the second star, while the others remain unlit
        new Actions(webDriver).moveToElement(webDriver.findElement(reviewStarsStar2)).perform();
        assertThatStarIsLit(webDriver.findElement(reviewStarsStar1));
        assertThatStarIsLit(webDriver.findElement(reviewStarsStar2));
        assertThatStarIsUnlit(webDriver.findElement(reviewStarsStar3));
        assertThatStarIsUnlit(webDriver.findElement(reviewStarsStar4));
        assertThatStarIsUnlit(webDriver.findElement(reviewStarsStar5));

        // Check that the first, second and third stars become lit upon hovering the third star, while the others remain unlit
        new Actions(webDriver).moveToElement(webDriver.findElement(reviewStarsStar3)).perform();
        assertThatStarIsLit(webDriver.findElement(reviewStarsStar1));
        assertThatStarIsLit(webDriver.findElement(reviewStarsStar2));
        assertThatStarIsLit(webDriver.findElement(reviewStarsStar3));
        assertThatStarIsUnlit(webDriver.findElement(reviewStarsStar4));
        assertThatStarIsUnlit(webDriver.findElement(reviewStarsStar5));

        // Check that the first, second, third and fourth stars become lit upon hovering the fourth star, while the last remains unlit
        new Actions(webDriver).moveToElement(webDriver.findElement(reviewStarsStar4)).perform();
        assertThatStarIsLit(webDriver.findElement(reviewStarsStar1));
        assertThatStarIsLit(webDriver.findElement(reviewStarsStar2));
        assertThatStarIsLit(webDriver.findElement(reviewStarsStar3));
        assertThatStarIsLit(webDriver.findElement(reviewStarsStar4));
        assertThatStarIsUnlit(webDriver.findElement(reviewStarsStar5));

        // Check that all stars become lit upon hovering the fifth star
        new Actions(webDriver).moveToElement(webDriver.findElement(reviewStarsStar5)).perform();
        assertThatStarIsLit(webDriver.findElement(reviewStarsStar1));
        assertThatStarIsLit(webDriver.findElement(reviewStarsStar2));
        assertThatStarIsLit(webDriver.findElement(reviewStarsStar3));
        assertThatStarIsLit(webDriver.findElement(reviewStarsStar4));
        assertThatStarIsLit(webDriver.findElement(reviewStarsStar5));
    }

    private void checkPostNewReviewFunctionality(String reviewMessage) {
        WebDriver webDriver = this.testDependencies.webDriver;
        CompanyProfilePage companyProfilePage = (CompanyProfilePage) this.testDependencies.pages.get(CompanyProfilePage.class.getSimpleName());

        By reviewStars = new ByChained(
                companyProfilePage.reviewsSection,
                companyProfilePage.reviewActionContainer,
                companyProfilePage.reviewStarComponent
        );
        By reviewStarsStar4 = new ByChained(reviewStars, companyProfilePage.reviewStarComponentStar4);

        // Post new review
        webDriver.findElement(reviewStarsStar4).click();
        webDriver.findElement(companyProfilePage.selectDropdown).click();
        webDriver.findElement(companyProfilePage.healthInsuranceOption).click();
        webDriver.findElement(companyProfilePage.reviewTextbox).sendKeys(reviewMessage);
        webDriver.findElement(companyProfilePage.reviewSubmitButton).click();

        // Check review confirmation
        ReviewConfirmationPage reviewConfirmationPage = (ReviewConfirmationPage) this.testDependencies.pages.get(ReviewConfirmationPage.class.getSimpleName());
        WebdriverWaits.waitForElementBy(webDriver, reviewConfirmationPage.reviewConfirmationMessage);
        assertThat(webDriver.findElement(reviewConfirmationPage.reviewConfirmationMessage).isDisplayed()).isTrue();
    }

    private void checkUserProfileForRecommendation() {
        WebDriver webDriver = this.testDependencies.webDriver;
        UserProfilePage userProfilePage = (UserProfilePage) this.testDependencies.pages.get(UserProfilePage.class.getSimpleName());
        By reviewedCompanyInRecommendations = new ByChained(
                userProfilePage.recommendationsSection,
                By.xpath("//*[text()='" + WallethubFixtures.companyName + "']")
        );

        userProfilePage.load();
        Boolean reviewedCompanyInRecommendationsIsDisplayed = webDriver.findElement(reviewedCompanyInRecommendations).isDisplayed();
        assertThat(reviewedCompanyInRecommendationsIsDisplayed).isTrue();
    }

    private void checkCompanyProfileForReview(String reviewMessage) {
        WebDriver webDriver = this.testDependencies.webDriver;
        CompanyProfilePage companyProfilePage = (CompanyProfilePage) this.testDependencies.pages.get(CompanyProfilePage.class.getSimpleName());
        By reviewMessageInReviewSection = new ByChained(
                companyProfilePage.reviewsSection,
                By.xpath("//*[text()='" + reviewMessage + "']")
        );

        companyProfilePage.load();
        Boolean reviewMessageInReviewSectionIsDisplayed = webDriver.findElement(reviewMessageInReviewSection).isDisplayed();
        assertThat(reviewMessageInReviewSectionIsDisplayed).isTrue();
    }

    @Override
    protected void script() {
        String reviewMessage = Faker.instance().lorem().characters(200, 300);

        checkStarHoverFunctionality();
        checkPostNewReviewFunctionality(reviewMessage);
        checkUserProfileForRecommendation();
        checkCompanyProfileForReview(reviewMessage);
    }

    @Override
    protected void before() {
        // Login test user
        LoginPage loginPage = (LoginPage) this.testDependencies.pages.get(LoginPage.class.getSimpleName());
        loginPage.load();
        loginPage.login(WallethubFixtures.userEmail, WallethubFixtures.userPassword);

        // Navigate to test page
        CompanyProfilePage companyProfilePage = (CompanyProfilePage) this.testDependencies.pages.get(CompanyProfilePage.class.getSimpleName());
        companyProfilePage.load();
    }

}
