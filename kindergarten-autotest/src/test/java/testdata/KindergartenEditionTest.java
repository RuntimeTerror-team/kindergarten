package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertEquals;
/** The tests are testing application of Kindergarten Information System
 *
 * @author Runtime Terror Team
 * @version 1.0
 *
 */

public class KindergartenEditionTest extends BaseTest {

    @Test
    public void kindergartenEditionTest()  {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);
        EducationSpecialistAgeGroupCreationPage educationSpecialistAgeGroupCreationPage = new EducationSpecialistAgeGroupCreationPage(driver);
        EducationSpecialistKindergartenListPage educationSpecialistKindergartenListPage = new EducationSpecialistKindergartenListPage(driver);
        EducationSpecialistKindergartenEditPage educationSpecialistKindergartenEditPage = new EducationSpecialistKindergartenEditPage(driver);

        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        /**
         * Education specialist logs in
         */
        loginPage.clickLoginButton();

        wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        educationSpecialistPage.clickKindergartenListButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
/**
 * Education specialist clicks the kindergarten information preview button
 */
        educationSpecialistKindergartenListPage.clickKindergartenInformationReviewButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
/**
 * Education specialist clicks the kindergarten information edit button
 */
        educationSpecialistKindergartenEditPage.clickKindergartenInformationEditButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        String newKindergartenName = "Darželis Pušaitė";
        String newKindergartenPhoneNumber = "60606060";
        String newKindergartenEmail = "pusaite@pusaite.lt";

        educationSpecialistKindergartenEditPage.clearKindergartenName();
        educationSpecialistKindergartenEditPage.enterKindergartenName(newKindergartenName);

        educationSpecialistKindergartenEditPage.clearKindergartenPhoneNumber();
        educationSpecialistKindergartenEditPage.enterKindergartenPhoneNumber(newKindergartenPhoneNumber);

        educationSpecialistKindergartenEditPage.clearKindergartenEmail();
        educationSpecialistKindergartenEditPage.enterKindergartenEmail(newKindergartenEmail);
/**
 * Education specialist saves the updated kindergarten's information
 */
        educationSpecialistKindergartenEditPage.clickKindergartenUpdateSaveButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div[2]/div/form/div[10]")));

        String actualNewKindergartenUpdateSaveText = educationSpecialistKindergartenEditPage.getSuccessfulKindergartenUpdateSaveText();
        assertEquals(actualNewKindergartenUpdateSaveText, "Darželio informacija sėkmingai atnaujinta", "Text is not as expected: ");

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/button[1]")));
        /**
         * Education specialist goes back to the kindergarten list
         */
        educationSpecialistKindergartenListPage.clickBackToKindergartenListButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
/**
 * Education specialist logs out
 */

        educationSpecialistPage.clickSpecialistLogoutButton();
    }
}
