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
        String kindergartenName = "Lopšelis-darželis Lapė";
        String kindergartenCode = "123454331";
        String kindergartenAddress = "Antakalnio g. 123";
        String kindergartenPostCode = "12345";
        String kindergartenPhoneNo = "60606060";
        String kindergartenEmail = "lape@lape.lt";
        String kindergartenWebsite = "www.lape.lt";

        kindergartenListPage.enterKindergartenName(kindergartenName);
        kindergartenListPage.enterKindergartenCode(kindergartenCode);
        kindergartenListPage.enterKindergartenAddress(kindergartenAddress);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[4]/label")));

        Select districtDropdown = new Select(driver.findElement(By.name("district")));
        districtDropdown.selectByIndex(1);
        kindergartenListPage.enterKindergartenPostalCode(kindergartenPostCode);
        kindergartenListPage.enterKindergartenPhoneNo(kindergartenPhoneNo);
        kindergartenListPage.enterKindergartenEmail(kindergartenEmail);
        kindergartenListPage.enterKindergartenWebsite(kindergartenWebsite);
        kindergartenListPage.clickKindergartenSaveButton();
        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div[2]/div/form/div[11]")));

        String actualKindergartenCreationText = kindergartenListPage.getSuccessfulKindergartenSaveText();
        assertEquals(actualKindergartenCreationText, "Darželis sėkmingai sukurtas", "Text is not as expected: ");

        kindergartenListPage.clickBackToKindergartenListButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        kindergartenListPage.clickAddKindergartenButton();
        kindergartenName = "Lopšelis-darželis Voverytė";
        kindergartenCode = "1234568";
        kindergartenAddress = "Antakalnio g. 123";
        kindergartenPostCode = "12345";
        kindergartenPhoneNo = "60606060";
        kindergartenEmail = "voveryte@voveryte.lt";
        kindergartenWebsite = "www.voveryte.lt";
        kindergartenListPage.enterKindergartenName(kindergartenName);

        kindergartenListPage.enterKindergartenCode(kindergartenCode);
        kindergartenListPage.enterKindergartenAddress(kindergartenAddress);

        districtDropdown = new Select(driver.findElement(By.name("district")));
        districtDropdown.selectByIndex(1);
        kindergartenListPage.enterKindergartenPostalCode(kindergartenPostCode);
        kindergartenListPage.enterKindergartenPhoneNo(kindergartenPhoneNo);
        kindergartenListPage.enterKindergartenEmail(kindergartenEmail);
        kindergartenListPage.enterKindergartenWebsite(kindergartenWebsite);
        kindergartenListPage.clickKindergartenSaveButton();
        wait = new WebDriverWait(driver, 5);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div[2]/div/form/div[11]")));

        actualKindergartenCreationText = kindergartenListPage.getSuccessfulKindergartenSaveText();
        assertEquals(actualKindergartenCreationText, "Darželis sėkmingai sukurtas", "Text is not as expected: ");

        kindergartenListPage.clickBackToKindergartenListButton();
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        educationSpecialistPage.clickSpecialistLogoutButton();
    }

}
