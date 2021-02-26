package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.LoginPage;
import pages.ParentPrimaryInputPage;
import pages.SpecialistPage;
import utilities.FileReaderUtils;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class ParentTest extends BaseTest {


    @Test
    public void parentCreationTest() throws IOException {

        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SpecialistPage specialistPage = new SpecialistPage(driver);
        AdminTest adminTest = new AdminTest();
        ParentPrimaryInputPage parentPrimaryInputPage = new ParentPrimaryInputPage(driver);

        adminTest.adminLoginTest();

        String parentFirstName = "Petras";
        String parentLastName = "Petrauskas";

        adminPage.enterFirstName(parentFirstName);
        adminPage.enterLastName(parentLastName);

        Select roleDropdown = new Select(driver.findElement(By.id("inputGroupSelect01")));
        roleDropdown.selectByIndex(0);

        adminPage.clickSaveAccountButton();

        adminPage.clickAdminLogoutButton();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        String parentUsename = parentFirstName + parentLastName + "1";
        String parentPassword = parentFirstName + parentLastName + "1";

        loginPage.enterUsername(parentUsename);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();
        WebElement parentPrimaryInfoInputTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/h1")));

        String actualResultParentPrimaryInfoInputTitle = parentPrimaryInputPage.findPrimaryInfoInputTitle();
        assertEquals(actualResultParentPrimaryInfoInputTitle, parentPrimaryInfoInputTitle.getText(), "Text is not as expected: ");

        parentPrimaryInputPage.enterFirstName(parentFirstName);
        parentPrimaryInputPage.enterLastName(parentLastName);

        String personalCode = "37112220025";
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

//        String actualSuccessfulSaveResult = specialistPage.findSpecialistLoginText();
//        assertEquals(actualSuccessfulSaveResult, specialistLoginText.getText(), "Text is not as expected: ");



    }
}
