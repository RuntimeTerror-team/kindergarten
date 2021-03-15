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


public class ParentPrimaryInfoInputTest extends BaseTest {


    @Test
    public void parentCreationTest() throws IOException {

        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);
        AdminLoginLogoutTest adminLoginLogoutTest = new AdminLoginLogoutTest();
        ParentPrimaryInputPage parentPrimaryInputPage = new ParentPrimaryInputPage(driver);
        ParentPage parentPage = new ParentPage(driver);

        adminLoginLogoutTest.adminLoginTest();

        String parentFirstName = "Petras";
        String parentLastName = "Petrauskas";

        adminPage.enterFirstName(parentFirstName);
        adminPage.enterLastName(parentLastName);

        Select roleDropdown = new Select(driver.findElement(By.id("inputGroupSelect01")));
        roleDropdown.selectByIndex(0);

        adminPage.clickSaveAccountButton();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]")));

        String actualSuccessfulAccountSave = adminPage.findSuccessfulAccountSaveText();
        assertTrue(actualSuccessfulAccountSave.contains(parentFirstName + parentLastName), "Text is not as expected: ");

        adminPage.clickAdminLogoutButton();

        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        String parentUsername = parentFirstName + parentLastName + "4";
        String parentPassword = parentFirstName + parentLastName + "4";

        loginPage.enterUsername(parentUsername);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();
        WebElement parentPrimaryInfoInputTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        String actualResultParentPrimaryInfoInputTitle = parentPrimaryInputPage.findPrimaryInfoInputTitle();
        assertEquals(actualResultParentPrimaryInfoInputTitle, "Pirminių duomenų anketa", "Text is not as expected: ");

        parentPrimaryInputPage.enterFirstName(parentFirstName);
        parentPrimaryInputPage.enterLastName(parentLastName);

        String personalCode = "37112220015";
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
        WebElement parentPrimaryPageTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        parentPage.clickParentLogoutButton();

        loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));

        loginPage.enterUsername(parentUsername);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();
        wait = new WebDriverWait(driver, 10);
        parentPrimaryPageTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        String actualParentName = parentPage.findParentName();
        assertEquals(actualParentName, parentFirstName.toUpperCase() + " " + parentLastName.toUpperCase(), "Text is not as expected: ");
        parentPage.clickParentLogoutButton();

        loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));

        adminLoginLogoutTest.adminLoginTest();

        parentFirstName = "Marija";
        parentLastName = "Pūkienė";

        adminPage.enterFirstName(parentFirstName);
        adminPage.enterLastName(parentLastName);

        roleDropdown = new Select(driver.findElement(By.id("inputGroupSelect01")));
        roleDropdown.selectByIndex(0);

        adminPage.clickSaveAccountButton();
        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]")));

        actualSuccessfulAccountSave = adminPage.findSuccessfulAccountSaveText();
        assertTrue(actualSuccessfulAccountSave.contains(parentFirstName + parentLastName), "Text is not as expected: ");

        adminPage.clickAdminLogoutButton();

        loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        parentUsername = parentFirstName + parentLastName + "3";
        parentPassword = parentFirstName + parentLastName + "3";

        loginPage.enterUsername(parentUsername);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();
        parentPrimaryInfoInputTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        actualResultParentPrimaryInfoInputTitle = parentPrimaryInputPage.findPrimaryInfoInputTitle();
        assertEquals(actualResultParentPrimaryInfoInputTitle, "Pirminių duomenų anketa", "Text is not as expected: ");

        parentPrimaryInputPage.enterFirstName(parentFirstName);
        parentPrimaryInputPage.enterLastName(parentLastName);

        personalCode = "47112220022";
        address = "Antakalnio g. 34-1";
        postalCode = "12345";
        phoneNo = "60606060";
        email = "marija@pukiene.lt";

        parentPrimaryInputPage.enterPersonalCode(personalCode);
        parentPrimaryInputPage.enterAddress(address);

        townDropdown = new Select(driver.findElement(By.id("city")));
        townDropdown.selectByIndex(1);

        parentPrimaryInputPage.enterPostalCode(postalCode);
        parentPrimaryInputPage.enterPhoneNo(phoneNo);
        parentPrimaryInputPage.enterEmail(email);

        parentPrimaryInputPage.clickParentPrimaryInputSaveButton();

        parentPrimaryPageTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        parentPage.clickParentLogoutButton();

        loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));

        loginPage.enterUsername(parentUsername);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();

        parentPrimaryInfoInputTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        actualParentName = parentPage.findParentName();
        assertEquals(actualParentName, parentFirstName.toUpperCase() + " " + parentLastName.toUpperCase(), "Text is not as expected: ");
        parentPage.clickParentLogoutButton();

        loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));

        adminLoginLogoutTest.adminLoginTest();
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        parentFirstName = "Alana";
        parentLastName = "Wu";

        adminPage.enterFirstName(parentFirstName);
        adminPage.enterLastName(parentLastName);

        roleDropdown = new Select(driver.findElement(By.id("inputGroupSelect01")));
        roleDropdown.selectByIndex(0);

        adminPage.clickSaveAccountButton();
        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]")));

        actualSuccessfulAccountSave = adminPage.findSuccessfulAccountSaveText();
        assertTrue(actualSuccessfulAccountSave.contains(parentFirstName + parentLastName), "Text is not as expected: ");

        adminPage.clickAdminLogoutButton();

        loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        parentUsername = parentFirstName + parentLastName + "2";
        parentPassword = parentFirstName + parentLastName + "2";

        loginPage.enterUsername(parentUsername);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();

        parentPrimaryInfoInputTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        actualResultParentPrimaryInfoInputTitle = parentPrimaryInputPage.findPrimaryInfoInputTitle();
        assertEquals(actualResultParentPrimaryInfoInputTitle, "Pirminių duomenų anketa", "Text is not as expected: ");
        parentFirstName = "Alana Marija";
        parentLastName = "Juodyte-Wu"
        ;
        parentPrimaryInputPage.enterFirstName(parentFirstName);
        parentPrimaryInputPage.enterLastName(parentLastName);

        personalCode = "09876543210";
        address = "Antakalnio g. 34-1";
        postalCode = "12345";
        phoneNo = "60606060";
        email = "alana@wu.lt";

        parentPrimaryInputPage.enterPersonalCode(personalCode);
        parentPrimaryInputPage.enterAddress(address);

        townDropdown = new Select(driver.findElement(By.id("city")));
        townDropdown.selectByIndex(1);

        parentPrimaryInputPage.enterPostalCode(postalCode);
        parentPrimaryInputPage.enterPhoneNo(phoneNo);
        parentPrimaryInputPage.enterEmail(email);

        parentPrimaryInputPage.clickParentPrimaryInputSaveButton();

        parentPrimaryPageTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        parentPage.clickParentLogoutButton();

        loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));

        loginPage.enterUsername(parentUsername);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();

        parentPrimaryInfoInputTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        actualParentName = parentPage.findParentName();
        assertEquals(actualParentName, parentFirstName.toUpperCase() + " " + parentLastName.toUpperCase(), "Text is not as expected: ");
        parentPage.clickParentLogoutButton();

    }
}
