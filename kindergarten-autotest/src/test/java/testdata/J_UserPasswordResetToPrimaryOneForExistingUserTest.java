package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

public class J_UserPasswordResetToPrimaryOneForExistingUserTest extends BaseTest {


    @Test(groups = "regression")
    public void UserPasswordResetToPrimaryOneForExistingUserTest() throws IOException, InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        ParentPage parentPage = new ParentPage(driver);
        ParentMyAccountPage parentMyAccountPage = new ParentMyAccountPage(driver);
        AdminPage adminPage = new AdminPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));

        String guardianFirstName = "Petras";
        String guardianLastName = "Petrauskas";

        List<String> testdata = FileReaderUtils.getTestData("src/test/resources/TestData_Admin_Login.txt");

        String username = testdata.get(0);
        String password = testdata.get(1);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        /**
         * Admin logs in
         *
         */
        loginPage.clickLoginButton();

        wait = new WebDriverWait(driver, 5);
        WebElement applicationText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
/**
 * Admin resets password to primary one by clicking the button
 */
        adminPage.clickPasswordResetButton();

        WebElement alertText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div/div/div[1]")));
        adminPage.clickAlertCloseButton();

        WebElement adminText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));
        /**
         * Admin logs out
         *
         */
        adminPage.clickAdminLogoutButton();
        loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        username = "PetrasPetrauskas1";
        password = "PetrasPetrauskas1";

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
/**
 * Child's guardian logs in with reset password to primary one
 *
 */
        loginPage.clickLoginButton();
        WebElement guardianText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

       Thread.sleep(2000);
        String actualParentName = parentPage.findParentName();
        assertEquals(actualParentName, guardianFirstName.toUpperCase() + " " + guardianLastName.toUpperCase(), "Text is not as expected: ");
        Thread.sleep(2000);
        /**
         * Child's guardian logs out
         *
         */
        parentPage.clickParentLogoutButton();


    }
}
