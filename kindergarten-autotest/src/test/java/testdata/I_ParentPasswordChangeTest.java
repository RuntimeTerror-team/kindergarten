package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertEquals;

/**
 * The tests are testing application of Kindergarten Information System
 *
 * @author Runtime Terror Team
 * @version 1.0
 */
public class I_ParentPasswordChangeTest extends BaseTest {

    @Test(groups = "regression")
    public void parentPasswordChangeTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        LoginPage loginPage = new LoginPage(driver);

        ParentPage parentPage = new ParentPage(driver);

        ParentMyAccountPage parentMyAccountPage = new ParentMyAccountPage(driver);

        String parentUsername = "PetrasPetrauskas1";
        String parentPassword = "PetrasPetrauskas1";

        loginPage.enterUsername(parentUsername);
        loginPage.enterPassword(parentPassword);
        /**
         * Child's guardian logs in
         *
         */
        loginPage.clickLoginButton();

        wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        parentPage.clickMyAccountButton();
        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        String newPassword = "A7jonasp";
        parentMyAccountPage.enterOldPassword(parentPassword);
        parentMyAccountPage.enterNewPassword(newPassword);
        parentMyAccountPage.enterNewPasswordConfirm(newPassword);
        /**
         * Child's guardian saves the new password
         *
         */
        parentMyAccountPage.clickNewPasswordSaveButton();
        Thread.sleep(3000);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        Thread.sleep(3000);
        /**
         * Child's guardian logs out
         *
         */
        parentPage.clickParentLogoutButton();

        wait = new WebDriverWait(driver, 10);
        loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        Thread.sleep(3000);
        parentUsername = "PetrasPetrauskas1";

        loginPage.enterUsername(parentUsername);
        loginPage.enterPassword(newPassword);
        /**
         * Child's guardian logs with the new password
         *
         */
        loginPage.clickLoginButton();
        Thread.sleep(3000);
        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));
        Thread.sleep(3000);
        String actualResultParentTitleText = parentPage.findParentpageMainpageTitle();
        assertEquals(actualResultParentTitleText, "PETRAS PETRAUSKAS", "Text is not as expected: ");
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        Thread.sleep(3000);
        /**
         * Child's guardian logs out
         *
         */
        parentPage.clickParentLogoutButton();

    }
}
