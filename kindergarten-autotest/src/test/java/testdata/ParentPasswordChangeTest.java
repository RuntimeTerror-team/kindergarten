package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertEquals;

public class ParentPasswordChangeTest extends BaseTest {

    @Test
    public void parentPasswordChangeTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        LoginPage loginPage = new LoginPage(driver);

        ParentPage parentPage = new ParentPage(driver);

        ParentMyAccountPage parentMyAccountPage = new ParentMyAccountPage(driver);

        String parentUsername = "EdgarasBujonauskas1";
        String parentPassword = "EdgarasBujonauskas1";

        loginPage.enterUsername(parentUsername);
        loginPage.enterPassword(parentPassword);
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
        parentMyAccountPage.clickNewPasswordSaveButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));


        parentPage.clickParentLogoutButton();

        Thread.sleep(2000);
        wait = new WebDriverWait(driver, 10);
        loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));

        parentUsername = "EdgarasBujonauskas1";

        loginPage.enterUsername(parentUsername);
        loginPage.enterPassword(newPassword);
        loginPage.clickLoginButton();

        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        String actualResultParentTitleText = parentPage.findParentpageMainpageTitle();
        assertEquals(actualResultParentTitleText, "EDGARAS BUJONAUSKAS", "Text is not as expected: ");


    }
}
