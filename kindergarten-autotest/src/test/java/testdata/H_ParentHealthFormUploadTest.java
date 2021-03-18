package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;
/** The tests are testing application of Kindergarten Information System
 *
 * @author Runtime Terror Team
 * @version 1.0
 *
 */

public class H_ParentHealthFormUploadTest extends BaseTest {


    @Test(groups = "regression")
    public void parentHealthFormUploadTest()  {

        LoginPage loginPage = new LoginPage(driver);
        ParentPage parentPage = new ParentPage(driver);
        ParentApplicationFillUpPage parentApplicationFillUpPage = new ParentApplicationFillUpPage(driver);
        ParentApplicationsPage parentApplicationsPage = new ParentApplicationsPage(driver);
        ParentHealthFormsPage parentHealthFormsPage = new ParentHealthFormsPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));

        String parentUsername = "PetrasPetrauskas1";
        String parentPassword = "PetrasPetrauskas1";

        loginPage.enterUsername(parentUsername);
        loginPage.enterPassword(parentPassword);
        /**
         * Child's guardian logs in
         *
         */
        loginPage.clickLoginButton();
        WebElement parentNameTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));
        parentPage.clickHealthForms();
        WebElement parentHealthFormTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        /**
         * Child's guardian selects the child for which
         * he would like to upload the health form
         *
         */
        Select childSelectDropdown = new Select(driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div[2]/div[1]/div/div[1]/select")));
        childSelectDropdown.selectByIndex(1);


        String healthFileName = "Sveikatos pazyma 2021_01_26.pdf";
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        driver.findElement(By.id("fileToUpload")).sendKeys(absolutePath + '\\' + healthFileName);


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"#fileToUpload\").type =\"\";");


        wait = new WebDriverWait(driver, 10);
        WebElement applicationPageTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[1]/span/input")));
/**
 * Child's guardian saves uploaded health form
 *
 */
        parentHealthFormsPage.clickHealthFormSaveButton();


        applicationPageTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr/td[4]/button")));
/**
 * Child's guardian downloads health form to PC
 *
 */
        parentHealthFormsPage.clickFileDownloadToPCButton();

        String actualHealthFormFileName = parentHealthFormsPage.findHealthFormFileName();

        assertEquals(actualHealthFormFileName, healthFileName, "Text is not as expected: ");
/**
 * Child's guardian logs out
 *
 */
        parentPage.clickParentLogoutButton();

    }
}
