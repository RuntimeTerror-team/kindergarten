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
import static org.testng.Assert.assertTrue;


public class ParentPrimaryInfoTest extends BaseTest {


    @Test(groups = "smoke")
    public void parentCreationTest() throws IOException {

        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SpecialistPage specialistPage = new SpecialistPage(driver);
        AdminTest adminTest = new AdminTest();
        ParentPrimaryInputPage parentPrimaryInputPage = new ParentPrimaryInputPage(driver);
        ParentPage parentPage =new ParentPage(driver);

        adminTest.adminLoginTest();

        String parentFirstName = "Petras";
        String parentLastName = "Petrauskas";

        adminPage.enterFirstName(parentFirstName);
        adminPage.enterLastName(parentLastName);

        Select roleDropdown = new Select(driver.findElement(By.id("inputGroupSelect01")));
        roleDropdown.selectByIndex(0);

        adminPage.clickSaveAccountButton();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/div/div/div/div")));

        String actualSuccessfulAccountSave = adminPage.findSuccesfulAccountSaveText();
        assertTrue(actualSuccessfulAccountSave.contains(parentFirstName+parentLastName), "Text is not as expected: ");

       adminPage.clickAdminLogoutButton();

               WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        String parentUsename = parentFirstName + parentLastName + "17";
        String parentPassword = parentFirstName + parentLastName + "17";

        loginPage.enterUsername(parentUsename);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();
        WebElement parentPrimaryInfoInputTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/h1")));

        String actualResultParentPrimaryInfoInputTitle = parentPrimaryInputPage.findPrimaryInfoInputTitle();
        assertEquals(actualResultParentPrimaryInfoInputTitle, "Pirminių duomenų anketa", "Text is not as expected: ");

        parentPrimaryInputPage.enterFirstName(parentFirstName);
        parentPrimaryInputPage.enterLastName(parentLastName);

        String personalCode = "37112220010";
        String address = "Antakalnio g. 34-12";
        String postalCode = "12345";
        String phoneNo = "60606060";
        String email = "petras@petrauskas.lt";

        parentPrimaryInputPage.enterPersonalCode(personalCode);
        parentPrimaryInputPage.enterAddress(address);

        Select townDropdown = new Select(driver.findElement(By.id("city")));
        townDropdown.selectByIndex(1);

        parentPrimaryInputPage.enterPostalCode(postalCode);
        parentPrimaryInputPage.enterPhoneNo(phoneNo);
        parentPrimaryInputPage.enterEmail(email);

        parentPrimaryInputPage.clickParentPrimaryInputSaveButton();


//        WebDriverWait wait1 = new WebDriverWait(driver, 30);
//        WebElement parentpageMainpageText = wait1.until(
//                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/h1")));
//
        String actualURL = "http://akademijait.vtmc.lt:8181/kindergarten/guardian/applications";
               assertEquals(actualURL, driver.getCurrentUrl(), "Text is not as expected: ");



    }
}
