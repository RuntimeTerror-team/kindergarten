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

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DistrictCreationTest extends BaseTest {


    @Test(groups = "smoke, regression")
    public void districtCreationTest() throws IOException, InterruptedException {
        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdminLoginLogoutTest adminLoginLogoutTest = new AdminLoginLogoutTest();
        AdminDistrictAdministrationPage adminDistrictAdministrationPage = new AdminDistrictAdministrationPage(driver);
        adminLoginLogoutTest.adminLoginTest();
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        adminPage.clickDistrictAdministrationButton();
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        String districtName = "Karoliniškių rajonas";
        adminDistrictAdministrationPage.enterDistrictName(districtName);
        adminDistrictAdministrationPage.clickAddDistrictButton();
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/form/span")));
        String actualSuccessfulDistrictCreationText = adminDistrictAdministrationPage.findSuccessfulDistrictCreationText();
        assertEquals(actualSuccessfulDistrictCreationText, "Rajonas sėkmingai sukurtas", "Text is not as expected: ");
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
        adminLoginLogoutTest.adminLoginTest();
        wait = new WebDriverWait(driver, 3);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        adminPage.clickDistrictAdministrationButton();
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        String newDistrict = "Antakalnis";
        adminDistrictAdministrationPage.clickFirstDistrictInTheListToEdit();
        adminDistrictAdministrationPage.clearDistrictName();
        adminDistrictAdministrationPage.enterNewDistrictName(newDistrict);

        adminDistrictAdministrationPage.clickToSaveNewDistrictName();
        wait = new WebDriverWait(driver, 5);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]/div/table/thead/tr/th[2]")));

        String actualSuccessfulNewDistrictSave = adminDistrictAdministrationPage.findSuccessfulNewDistrictNameSaveText();
        assertEquals(actualSuccessfulNewDistrictSave, newDistrict, "Text is not as expected: ");

        adminPage.clickAdminLogoutButton();
    }
}

