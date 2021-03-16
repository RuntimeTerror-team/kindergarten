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


public class ParentHealthFormUploadTest extends BaseTest {


    @Test
    public void parentHealthFormUploadTest() throws InterruptedException {


        LoginPage loginPage = new LoginPage(driver);

        ParentPage parentPage = new ParentPage(driver);
        ParentApplicationFillUpPage parentApplicationFillUpPage = new ParentApplicationFillUpPage(driver);
        ParentApplicationsPage parentApplicationsPage = new ParentApplicationsPage(driver);
        ParentHealthFormsPage parentHealthFormsPage = new ParentHealthFormsPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));

        String parentUsername = "EdgarasBujonauskas1";
        String parentPassword = "EdgarasBujonauskas1";

        loginPage.enterUsername(parentUsername);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();
        WebElement parentNameTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));
        parentPage.clickHealthForms();
        WebElement parentHealthFormTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
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

        parentHealthFormsPage.clickHealthFormSaveButton();


        applicationPageTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr/td[4]/button")));

        parentHealthFormsPage.clickFileDownloadToPCButton();

        String actualHealthFormFileName = parentHealthFormsPage.findHealthFormFileName();

        assertEquals(actualHealthFormFileName, healthFileName, "Text is not as expected: ");

   //     parentPage.clickParentLogoutButton();

    }
}
