package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.EducationSpecialistAgeGroupCreationPage;
import pages.EducationSpecialistPage;
import pages.EducationSpecialistKindergartenListPage;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;


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
        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        loginPage.clickLoginButton();

        wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        educationSpecialistPage.clickKindergartenListButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));


        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/form/div[3]")));
        String actualResultAgeGroupCreationText = educationSpecialistAgeGroupCreationPage.findUnsuccessfulSameAgeGroupCreationText();
        assertEquals(actualResultAgeGroupCreationText, "Toks amžiaus intervalas jau yra įrašytas", "Text is not as expected: ");

        educationSpecialistPage.clickSpecialistLogoutButton();


    }
}
