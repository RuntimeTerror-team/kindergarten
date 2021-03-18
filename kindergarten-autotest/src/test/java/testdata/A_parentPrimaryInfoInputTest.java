package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.*;
import utilities.FileReaderUtils;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * The tests are testing application of Kindergarten Information System
 *
 * @author Runtime Terror Team
 * @version 1.0
 */

public class A_parentPrimaryInfoInputTest extends BaseTest {


    @Test(groups = "regression")
    public void parentPrimaryInfoInputTest() throws IOException, InterruptedException {
        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ParentPrimaryInputPage parentPrimaryInputPage = new ParentPrimaryInputPage(driver);
        ParentPage parentPage = new ParentPage(driver);
/**
 * Admin logs in with data from file.
 */
        List<String> testdata = FileReaderUtils.getTestData("src/test/resources/TestData_Admin_Login.txt");

        String username = testdata.get(0);
        String password = testdata.get(1);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        WebDriverWait wait = new WebDriverWait(driver, 5);

        WebElement specialistLoginText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
/**
 * Admin creates child's guardian user's account.
 */
        String parentFirstName = "Petras";
        String parentLastName = "Petrauskas";

        adminPage.enterFirstName(parentFirstName);
        adminPage.enterLastName(parentLastName);

        Select roleDropdown = new Select(driver.findElement(By.id("inputGroupSelect01")));
        roleDropdown.selectByIndex(0);
/**
 * Admin saves child's guardian user's account.
 */
        adminPage.clickSaveAccountButton();
        /**
         * Wait for element presence
         */
        wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]")));
        Thread.sleep(3000);
        adminPage.clickAdminLogoutButton();

        /**
         *  Child's guardian logs in with the primary password which is as username.
         */
        Thread.sleep(3000);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        String parentUsername = parentFirstName + parentLastName + "1";
        String parentPassword = parentFirstName + parentLastName + "1";

        loginPage.enterUsername(parentUsername);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();
        /**
         *  Child's guardian fills up the primary data form and saves it.
         */
        WebElement parentPrimaryInfoInputTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        String actualResultParentPrimaryInfoInputTitle = parentPrimaryInputPage.findPrimaryInfoInputTitle();
        assertEquals(actualResultParentPrimaryInfoInputTitle, "Pirminių duomenų anketa", "Text is not as expected: ");

        parentPrimaryInputPage.enterFirstName(parentFirstName);
        parentPrimaryInputPage.enterLastName(parentLastName);
/** personalCode,
 * address,
 * postalCode,
 * phoneNo,
 * email fields
 * */
        String personalCode = "37112220080";
        String address = "Antakalnio g. 34-12";
        String postalCode = "12345";
        String phoneNo = "60606060";
        String email = "petras@petrauskas.lt";

        parentPrimaryInputPage.enterPersonalCode(personalCode);
        parentPrimaryInputPage.enterAddress(address);
        /**
         *  Select value from dropdown
         */
        Select townDropdown = new Select(driver.findElement(By.id("city")));
        townDropdown.selectByIndex(1);

        parentPrimaryInputPage.enterPostalCode(postalCode);
        parentPrimaryInputPage.enterPhoneNo(phoneNo);
        parentPrimaryInputPage.enterEmail(email);

        parentPrimaryInputPage.clickParentPrimaryInputSaveButton();
        WebElement parentPrimaryPageTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        /**
         *  Child's guardian logs out.
         */
        parentPage.clickParentLogoutButton();
        Thread.sleep(3000);
        loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
/**
 *  Child's guardian logs in to personal account and
 *  on the top left corner aside the button "Atsijungti" sees his name and surname.
 *
 */
        loginPage.enterUsername(parentUsername);
        loginPage.enterPassword(parentPassword);
        Thread.sleep(3000);
        /**
         *  Child's guardian logs in.
         */
        loginPage.clickLoginButton();
        Thread.sleep(3000);
        wait = new WebDriverWait(driver, 10);
        parentPrimaryPageTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        Thread.sleep(3000);
        String actualParentName = parentPage.findParentName();
        /**
         *  Assert name and surname of child's guardian
         */
        assertEquals(actualParentName, parentFirstName.toUpperCase() + " " + parentLastName.toUpperCase(), "Text is not as expected: ");
        /**
         *  Child's guardian logs out.
         */
        Thread.sleep(3000);
        parentPage.clickParentLogoutButton();

    }
}
