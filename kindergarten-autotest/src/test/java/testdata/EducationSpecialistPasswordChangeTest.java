package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertEquals;

public class EducationSpecialistPasswordChangeTest extends BaseTest {

    @Test
    public void educationSpecialistPasswordChangeTest() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);
        AgeGroupCreationPage ageGroupCreationPage = new AgeGroupCreationPage(driver);
        KindergartenListPage kindergartenListPage = new KindergartenListPage(driver);
        EducationSpecialistMyAccountPage educationSpecialistMyAccountPage = new EducationSpecialistMyAccountPage(driver);
        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        loginPage.clickLoginButton();

        wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        educationSpecialistPage.clickMyAccountButton();
        String newPassword = "Sviet7spec";
        educationSpecialistMyAccountPage.enterOldPassword(specialistPassword);
        educationSpecialistMyAccountPage.enterNewPassword(newPassword);
        educationSpecialistMyAccountPage.enterNewPasswordConfirm(newPassword);
        educationSpecialistMyAccountPage.clickNewPasswordSaveButton();
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));


        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        educationSpecialistPage.clickSpecialistLogoutButton();
        wait = new WebDriverWait(driver, 5);
        loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));

        specialistUsername = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(newPassword);
        loginPage.clickLoginButton();

        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        String actualResultSpecialistTitleText = educationSpecialistPage.findSpecialistLoginText();
        assertEquals(actualResultSpecialistTitleText, "ŠVIETIMO SPECIALISTAS", "Text is not as expected: ");




    }
}
