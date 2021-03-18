package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.EducationSpecialistPage;
import pages.EducationSpecialistKindergartenListPage;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;

/**
 * The tests are testing application of Kindergarten Information System
 *
 * @author Runtime Terror Team
 * @version 1.0
 */
public class D_KindergartenCreationTest extends BaseTest {


    @Test(groups = "regression")
    public void kindergartenCreationTest() throws InterruptedException {


        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);
        EducationSpecialistKindergartenListPage educationSpecialistKindergartenListPage = new EducationSpecialistKindergartenListPage(driver);
        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        /**
         * Education specialist logs in
         *
         */
        loginPage.clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        educationSpecialistPage.clickKindergartenListButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        educationSpecialistKindergartenListPage.clickAddKindergartenButton();
        String kindergartenName = "Lopšelis-darželis Laputė";
        String kindergartenCode = "123454329";
        String kindergartenAddress = "Antakalnio g. 123";
        String kindergartenPostCode = "12345";
        String kindergartenPhoneNo = "60606060";
        String kindergartenEmail = "lape@lape.lt";
        String kindergartenWebsite = "www.lape.lt";

        educationSpecialistKindergartenListPage.enterKindergartenName(kindergartenName);
        educationSpecialistKindergartenListPage.enterKindergartenCode(kindergartenCode);
        educationSpecialistKindergartenListPage.enterKindergartenAddress(kindergartenAddress);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[4]/label")));

        Select districtDropdown = new Select(driver.findElement(By.name("district")));
        districtDropdown.selectByIndex(1);
        educationSpecialistKindergartenListPage.enterKindergartenPostalCode(kindergartenPostCode);
        educationSpecialistKindergartenListPage.enterKindergartenPhoneNo(kindergartenPhoneNo);
        educationSpecialistKindergartenListPage.enterKindergartenEmail(kindergartenEmail);
        educationSpecialistKindergartenListPage.enterKindergartenWebsite(kindergartenWebsite);
        /**
         * Education specialist saves the created kindergarten
         *
         */
        educationSpecialistKindergartenListPage.clickKindergartenSaveButton();
        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div[2]/div/form/div[11]")));
        Thread.sleep(3000);
        String actualKindergartenCreationText = educationSpecialistKindergartenListPage.getSuccessfulKindergartenSaveText();
        assertEquals(actualKindergartenCreationText, "Darželis sėkmingai sukurtas", "Text is not as expected: ");
        Thread.sleep(3000);
        /**
         * Education specialist goes back to kindergarten list
         *
         */
        educationSpecialistKindergartenListPage.clickBackToKindergartenListButton();

        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        Thread.sleep(3000);
        /**
         * Education specialist logs out
         *
         */
        educationSpecialistPage.clickSpecialistLogoutButton();
    }

}
