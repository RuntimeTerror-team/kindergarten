package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;


import org.openqa.selenium.support.ui.Select;

import org.testng.annotations.Test;

import pages.AgeGroupCreationPage;
import pages.LoginPage;
import pages.SpecialistPage;


import static org.testng.Assert.assertEquals;


public class AgeGroupCreationTest extends BaseTest {


    @Test
    public void ageGroupCreationTest() throws InterruptedException {


        LoginPage loginPage = new LoginPage(driver);
        SpecialistPage specialistPage = new SpecialistPage(driver);
        AgeGroupCreationPage ageGroupCreationPage = new AgeGroupCreationPage(driver);

        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        loginPage.clickLoginButton();
        Thread.sleep(3000);

        specialistPage.clickAgeGroupCreationButton();

        Select AgeFromDropdown = new Select(driver.findElement(By.name("fromAge")));
        AgeFromDropdown.selectByIndex(1);
        Thread.sleep(2000);
        Select AgeToDropdown = new Select(driver.findElement(By.name("toAge")));
        AgeToDropdown.selectByIndex(3);
        ageGroupCreationPage.clickAgeGroupCreateButton();
        Thread.sleep(2000);
        String actualResultAgeGroupCreationText = ageGroupCreationPage.findSuccesfulAgeGroupCreationText();
        assertEquals(actualResultAgeGroupCreationText, "Grupės intervalas sėkmingai išsaugotas", "Text is not as expected: ");
        Thread.sleep(5000);

    }

    @Test
    public void sameAgeGroupCreationTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        SpecialistPage specialistPage = new SpecialistPage(driver);
        AgeGroupCreationPage ageGroupCreationPage = new AgeGroupCreationPage(driver);

        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        loginPage.clickLoginButton();
        Thread.sleep(3000);

        specialistPage.clickAgeGroupCreationButton();
        Select AgeFromDropdown = new Select(driver.findElement(By.name("fromAge")));
        AgeFromDropdown.selectByIndex(1);
        Thread.sleep(2000);
        Select AgeToDropdown = new Select(driver.findElement(By.name("toAge")));
        AgeToDropdown.selectByIndex(3);
        ageGroupCreationPage.clickAgeGroupCreateButton();
        Thread.sleep(2000);
        String actualResultAgeGroupCreationText = ageGroupCreationPage.findUnsuccesfulSameAgeGroupCreationText();
        assertEquals(actualResultAgeGroupCreationText, "Toks amžiaus intervalas jau yra įrašytas", "Text is not as expected: ");

//        specialistPage.clickSpecialistLogoutButton();


    }
}
