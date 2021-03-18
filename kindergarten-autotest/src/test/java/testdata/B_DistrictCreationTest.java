package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.AdminDistrictAdministrationPage;
import pages.LoginPage;
import utilities.FileReaderUtils;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;


/** The tests are testing application of Kindergarten Information System
 *
 * @author Runtime Terror Team
 * @version 1.0
 *
 */
public class B_DistrictCreationTest extends BaseTest {


    @Test(groups = "regression")
    public void districtCreationTest() throws IOException, InterruptedException {
        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdminDistrictAdministrationPage adminDistrictAdministrationPage = new AdminDistrictAdministrationPage(driver);
        List<String> testdata = FileReaderUtils.getTestData("src/test/resources/TestData_Admin_Login.txt");
/**
 * Admin logs in with data from file.
 */
        String username = testdata.get(0);
        String password = testdata.get(1);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));
/**
 * Admin clicks the button to administrate the districts
 */
        adminPage.clickDistrictAdministrationButton();
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        /**
         * Admin creates the district
         */
        String districtName = "Antakalnis";
        adminDistrictAdministrationPage.enterDistrictName(districtName);
        adminDistrictAdministrationPage.clickAddDistrictButton();
        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]")));
Thread.sleep(3000);
        String actualSuccessfulDistrictCreationText = adminDistrictAdministrationPage.findSuccessfulDistrictCreationText();
        assertEquals(actualSuccessfulDistrictCreationText, "Rajonas sėkmingai sukurtas", "Text is not as expected: ");
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        /**
         * Admin logs out.
         */
        adminPage.clickAdminLogoutButton();

    }

    @Test
    public void districtEditionTest() throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdminLoginLogoutTest adminLoginLogoutTest = new AdminLoginLogoutTest();
        AdminDistrictAdministrationPage adminDistrictAdministrationPage = new AdminDistrictAdministrationPage(driver);
        adminLoginLogoutTest.adminLoginLogoutTest();
        wait = new WebDriverWait(driver, 5);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        adminPage.clickDistrictAdministrationButton();
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        String newDistrict = "Antakalnio rajonas";
        adminDistrictAdministrationPage.clickFirstDistrictInTheListToEdit();
        adminDistrictAdministrationPage.clearDistrictName();
        adminDistrictAdministrationPage.enterNewDistrictName(newDistrict);

        adminDistrictAdministrationPage.clickToSaveNewDistrictName();
        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div[2]/div/div[3]/div/table/tbody/tr[1]/td[1]")));

        String actualSuccessfulNewDistrictSave = adminDistrictAdministrationPage.findSuccessfulNewDistrictNameSaveText();
        assertEquals(actualSuccessfulNewDistrictSave, newDistrict, "Text is not as expected: ");

        adminPage.clickAdminLogoutButton();
    }
}

