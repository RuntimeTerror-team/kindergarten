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

/** The tests are testing application of Kindergarten Information System
 *
 * @author Runtime Terror Team
 * @version 1.0
 *
 */

public class ParentLoginLogoutTest extends BaseTest {


    @Test(groups = "smoke")
    public void parentLoginTest() throws IOException {

        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        ParentPrimaryInputPage parentPrimaryInputPage = new ParentPrimaryInputPage(driver);

        ParentPage parentPage = new ParentPage(driver);

        List<String> testdata = FileReaderUtils.getTestData("src/test/resources/TestData_Admin_Login.txt");

        String username = testdata.get(0);
        String password = testdata.get(1);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        /**
         * Admin logs in
         */
        loginPage.clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, 5);

        WebElement specialistLoginText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        String parentFirstName = "Nijolė";
        String parentLastName = "Grybienė";
/**
 * Admin creates child's guardian user
 */
        adminPage.enterFirstName(parentFirstName);
        adminPage.enterLastName(parentLastName);

        Select roleDropdown = new Select(driver.findElement(By.id("inputGroupSelect01")));
        roleDropdown.selectByIndex(0);

        adminPage.clickSaveAccountButton();

        wait = new WebDriverWait(driver, 5);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div[2]/div/div[2]")));

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        /**
         * Admin logs out
         */
        adminPage.clickAdminLogoutButton();

        wait = new WebDriverWait(driver, 5);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        String parentUsename = parentFirstName + parentLastName + "1";
        String parentPassword = parentFirstName + parentLastName + "1";

        loginPage.enterUsername(parentUsename);
        loginPage.enterPassword(parentPassword);
        /**
         * Child's guardian logs in
         */
        loginPage.clickLoginButton();
        WebElement parentPrimaryInfoInputTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        String actualResultParentPrimaryInfoInputTitle = parentPrimaryInputPage.findPrimaryInfoInputTitle();
        assertEquals(actualResultParentPrimaryInfoInputTitle, "Pirminių duomenų anketa", "Text is not as expected: ");
/**
 * Child's guardian logs out
 */
        parentPage.clickParentLogoutButton();
    }


}
