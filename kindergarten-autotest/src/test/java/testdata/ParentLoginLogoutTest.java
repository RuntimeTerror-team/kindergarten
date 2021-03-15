package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class ParentLoginLogoutTest extends BaseTest {


    @Test(groups = "smoke, regression")
    public void parentLoginTest() throws IOException {

        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);
        AdminLoginLogoutTest adminLoginLogoutTest = new AdminLoginLogoutTest();
        ParentPrimaryInputPage parentPrimaryInputPage = new ParentPrimaryInputPage(driver);
        ParentPage parentPage = new ParentPage(driver);

        adminLoginLogoutTest.adminLoginTest();

        String parentFirstName = "Nijolė";
        String parentLastName = "Grybienė";

        adminPage.enterFirstName(parentFirstName);
        adminPage.enterLastName(parentLastName);

        Select roleDropdown = new Select(driver.findElement(By.id("inputGroupSelect01")));
        roleDropdown.selectByIndex(0);

        adminPage.clickSaveAccountButton();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]")));

        String actualSuccessfulAccountSave = adminPage.findSuccessfulAccountSaveText();
        assertTrue(actualSuccessfulAccountSave.contains(parentFirstName+parentLastName), "Text is not as expected: ");

        adminPage.clickAdminLogoutButton();
        wait = new WebDriverWait(driver, 5);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        String parentUsename = parentFirstName + parentLastName + "3";
        String parentPassword = parentFirstName + parentLastName + "3";

        loginPage.enterUsername(parentUsename);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();
        WebElement parentPrimaryInfoInputTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        String actualResultParentPrimaryInfoInputTitle = parentPrimaryInputPage.findPrimaryInfoInputTitle();
        assertEquals(actualResultParentPrimaryInfoInputTitle, "Pirminių duomenų anketa", "Text is not as expected: ");

        parentPage.clickParentLogoutButton();
    }

    @Test

    public void parentLoginTest1() throws IOException {
        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);
        AdminLoginLogoutTest adminLoginLogoutTest = new AdminLoginLogoutTest();
        ParentPrimaryInputPage parentPrimaryInputPage = new ParentPrimaryInputPage(driver);
        ParentPage parentPage = new ParentPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"loginForm\"]/h3[1]")));
        adminLoginLogoutTest.adminLoginTest();

        String parentFirstName = "Alan";
        String parentLastName = "Wu";
        adminPage.enterFirstName(parentFirstName);
        adminPage.enterLastName(parentLastName);
        Select roleDropdown = new Select(driver.findElement(By.id("inputGroupSelect01")));
        roleDropdown.selectByIndex(0);

        adminPage.clickSaveAccountButton();

      wait = new WebDriverWait(driver, 10);
      successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]")));

        String actualSuccessfulAccountSave = adminPage.findSuccessfulAccountSaveText();
        assertTrue(actualSuccessfulAccountSave.contains(parentFirstName + parentLastName), "Text is not as expected: ");

        adminPage.clickAdminLogoutButton();

        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        String parentUsename = parentFirstName + parentLastName + "02";
        String parentPassword = parentFirstName + parentLastName + "02";

        loginPage.enterUsername(parentUsename);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();
        WebElement parentPrimaryInfoInputTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        String actualResultParentPrimaryInfoInputTitle = parentPrimaryInputPage.findPrimaryInfoInputTitle();
        assertEquals(actualResultParentPrimaryInfoInputTitle, "Pirminių duomenų anketa", "Text is not as expected: ");
        parentPage.clickParentLogoutButton();
    }
}
