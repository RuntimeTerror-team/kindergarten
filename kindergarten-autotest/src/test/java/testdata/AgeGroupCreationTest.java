package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pages.EducationSpecialistAgeGroupCreationPage;
import pages.LoginPage;
import pages.EducationSpecialistPage;


import static org.testng.Assert.assertEquals;


public class AgeGroupCreationTest extends BaseTest {


    @Test(groups = "smoke, regression")
    public void ageGroupCreationTest()  {


        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);
        EducationSpecialistAgeGroupCreationPage educationSpecialistAgeGroupCreationPage = new EducationSpecialistAgeGroupCreationPage(driver);

        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        loginPage.clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        educationSpecialistPage.clickAgeGroupCreationButton();
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));


        Select AgeFromDropdown = new Select(driver.findElement(By.name("fromAge")));
        AgeFromDropdown.selectByIndex(4);
        Select AgeToDropdown = new Select(driver.findElement(By.name("toAge")));
        AgeToDropdown.selectByIndex(4);
        educationSpecialistAgeGroupCreationPage.clickAgeGroupCreateButton();
        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/form/div[3]")));

        String actualResultAgeGroupCreationText = educationSpecialistAgeGroupCreationPage.findSuccessfulAgeGroupCreationText();
        assertEquals(actualResultAgeGroupCreationText, "Grupės intervalas sėkmingai išsaugotas", "Text is not as expected: ");

        educationSpecialistPage.clickSpecialistLogoutButton();
    }

    @Test
    public void sameAgeGroupCreationTest() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);
        EducationSpecialistAgeGroupCreationPage educationSpecialistAgeGroupCreationPage = new EducationSpecialistAgeGroupCreationPage(driver);

        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        loginPage.clickLoginButton();

        wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        educationSpecialistPage.clickAgeGroupCreationButton();
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        Select AgeFromDropdown = new Select(driver.findElement(By.name("fromAge")));
        AgeFromDropdown.selectByIndex(4);

        Select AgeToDropdown = new Select(driver.findElement(By.name("toAge")));
        AgeToDropdown.selectByIndex(4);
        educationSpecialistAgeGroupCreationPage.clickAgeGroupCreateButton();
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/form/div[3]")));

        String actualResultAgeGroupCreationText = educationSpecialistAgeGroupCreationPage.findUnsuccessfulSameAgeGroupCreationText();
        assertEquals(actualResultAgeGroupCreationText, "Toks amžiaus intervalas jau yra įrašytas", "Text is not as expected: ");

        educationSpecialistPage.clickSpecialistLogoutButton();


    }
}
