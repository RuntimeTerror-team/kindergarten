package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.*;
/** The tests are testing application of Kindergarten Information System
 *
 * @author Runtime Terror Team
 * @version 1.0
 *
 */
import static org.testng.Assert.assertEquals;

public class G_EducationSpecialistPasswordChangeTest extends BaseTest {

    @Test (groups = "regression")
    public void educationSpecialistPasswordChangeTest() throws InterruptedException {
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        Thread.sleep(3000);
        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);
        EducationSpecialistAgeGroupCreationPage educationSpecialistAgeGroupCreationPage = new EducationSpecialistAgeGroupCreationPage(driver);
        EducationSpecialistKindergartenListPage educationSpecialistKindergartenListPage = new EducationSpecialistKindergartenListPage(driver);
        EducationSpecialistMyAccountPage educationSpecialistMyAccountPage = new EducationSpecialistMyAccountPage(driver);
        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        /**
         * Education specialist logs in
         *
         */
        loginPage.clickLoginButton();

        wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        educationSpecialistPage.clickMyAccountButton();
        String newPassword = "Sviet7spec";
        educationSpecialistMyAccountPage.enterOldPassword(specialistPassword);
        educationSpecialistMyAccountPage.enterNewPassword(newPassword);
        educationSpecialistMyAccountPage.enterNewPasswordConfirm(newPassword);
        /**
         * Education specialist saves the new password
         *
         */
        educationSpecialistMyAccountPage.clickNewPasswordSaveButton();
        Thread.sleep(3000);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        Thread.sleep(3000);
        /**
         * Education specialist logs out
         *
         */
        educationSpecialistPage.clickSpecialistLogoutButton();

        Thread.sleep(3000);
        wait = new WebDriverWait(driver, 10);
        loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));

        specialistUsername = "ŠvietimoSpecialistas1";
        Thread.sleep(3000);
        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(newPassword);

        /**
         * Education specialist logs in the new password
         *
         */
        loginPage.clickLoginButton();
        Thread.sleep(3000);
        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        Thread.sleep(3000);
        String actualResultSpecialistTitleText = educationSpecialistPage.findSpecialistLoginText();
        assertEquals(actualResultSpecialistTitleText, "ŠVIETIMO SPECIALISTAS", "Text is not as expected: ");
        Thread.sleep(3000);
        /**
         * Education specialist logs out
         *
         */
        educationSpecialistPage.clickSpecialistLogoutButton();


    }
}
