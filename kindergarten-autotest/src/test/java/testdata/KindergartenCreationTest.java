package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.EducationSpecialistPage;
import pages.KindergartenListPage;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;


public class KindergartenCreationTest extends BaseTest {


    @Test(groups = "smoke, regression")
    public void newKindergartenCreationTest() {


        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);
        KindergartenListPage kindergartenListPage = new KindergartenListPage(driver);
        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        loginPage.clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        educationSpecialistPage.clickKindergartenListButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        kindergartenListPage.clickAddKindergartenButton();
        String kindergartenName = "Mažas Nykštukas";
        String kindergartenCode = "987654322";
        String kindergartenAddress = "Bokšto g. 10";
        String kindergartenPostCode = "67859";
        String kindergartenPhoneNo = "67859123";
        String kindergartenEmail = "nykstukas@nykstukas.lt";
        String kindergartenWebsite = "www.nykstukas.lt";
        kindergartenListPage.enterKindergartenName(kindergartenName);

        kindergartenListPage.enterKindergartenCode(kindergartenCode);
        kindergartenListPage.enterKindergartenAddress(kindergartenAddress);

        Select districtDropdown = new Select(driver.findElement(By.name("district")));
        districtDropdown.selectByIndex(1);
        kindergartenListPage.enterKindergartenPostalCode(kindergartenPostCode);
        kindergartenListPage.enterKindergartenPhoneNo(kindergartenPhoneNo);
        kindergartenListPage.enterKindergartenEmail(kindergartenEmail);
        kindergartenListPage.enterKindergartenWebsite(kindergartenWebsite);
        kindergartenListPage.clickKindergartenSaveButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[11]")));

        String actualKindergartenCreationText = kindergartenListPage.getSuccessfulKindergartenSaveText();
        assertEquals(actualKindergartenCreationText, "Darželis sėkmingai sukurtas", "Text is not as expected: ");
        kindergartenListPage.clickBackToKindergartenListButton();
        educationSpecialistPage.clickSpecialistLogoutButton();
    }

}
