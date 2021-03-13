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


public class ParentPrimaryInfoTest extends BaseTest {


    @Test(groups = "smoke")
    public void parentCreationTest() throws IOException, InterruptedException {

        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);
        AdminTest adminTest = new AdminTest();
        ParentPrimaryInputPage parentPrimaryInputPage = new ParentPrimaryInputPage(driver);
        ParentPage parentPage = new ParentPage(driver);

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

        String actualSuccessfulAccountSave = adminPage.findSuccessfulAccountSaveText();
        assertTrue(actualSuccessfulAccountSave.contains(parentFirstName + parentLastName), "Text is not as expected: ");

        adminPage.clickAdminLogoutButton();

        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        String parentUsename = parentFirstName + parentLastName + "7";
        String parentPassword = parentFirstName + parentLastName + "7";

        loginPage.enterUsername(parentUsename);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();
        WebElement parentPrimaryInfoInputTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/h1")));

        String actualResultParentPrimaryInfoInputTitle = parentPrimaryInputPage.findPrimaryInfoInputTitle();
        assertEquals(actualResultParentPrimaryInfoInputTitle, "Pirminių duomenų anketa", "Text is not as expected: ");

        parentPrimaryInputPage.enterFirstName(parentFirstName);
        parentPrimaryInputPage.enterLastName(parentLastName);

        String personalCode = "37112220028";
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
        Thread.sleep(10000);
        parentPage.clickParentLogoutButton();

        Thread.sleep(2000);

        loginPage.enterUsername(parentUsename);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();
        Thread.sleep(5000);
        String actualParentName = parentPage.findParentName();
        assertEquals(actualParentName, parentFirstName + " " + parentLastName, "Text is not as expected: ");
        parentPage.clickParentLogoutButton();

        Thread.sleep(2000);

        adminTest.adminLoginTest();

        parentFirstName = "Marija";
        parentLastName = "Pūkienė";

        adminPage.enterFirstName(parentFirstName);
        adminPage.enterLastName(parentLastName);

        roleDropdown = new Select(driver.findElement(By.id("inputGroupSelect01")));
        roleDropdown.selectByIndex(0);

        adminPage.clickSaveAccountButton();
        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/div/div/div/div")));

        actualSuccessfulAccountSave = adminPage.findSuccessfulAccountSaveText();
        assertTrue(actualSuccessfulAccountSave.contains(parentFirstName + parentLastName), "Text is not as expected: ");

        adminPage.clickAdminLogoutButton();

        loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        parentUsename = parentFirstName + parentLastName + "1";
        parentPassword = parentFirstName + parentLastName + "1";

        loginPage.enterUsername(parentUsename);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();
        parentPrimaryInfoInputTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/h1")));

        actualResultParentPrimaryInfoInputTitle = parentPrimaryInputPage.findPrimaryInfoInputTitle();
        assertEquals(actualResultParentPrimaryInfoInputTitle, "Pirminių duomenų anketa", "Text is not as expected: ");

        parentPrimaryInputPage.enterFirstName(parentFirstName);
        parentPrimaryInputPage.enterLastName(parentLastName);

        personalCode = "47112220025";
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

        Thread.sleep(10000);
        parentPage.clickParentLogoutButton();

        Thread.sleep(2000);

        loginPage.enterUsername(parentUsename);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();
        Thread.sleep(5000);
        actualParentName = parentPage.findParentName();
        assertEquals(actualParentName, parentFirstName + " " + parentLastName, "Text is not as expected: ");
        parentPage.clickParentLogoutButton();
        Thread.sleep(5000);
        adminTest.adminLoginTest();

        parentFirstName = "Alana";
        parentLastName = "Wu";

        adminPage.enterFirstName(parentFirstName);
        adminPage.enterLastName(parentLastName);

        roleDropdown = new Select(driver.findElement(By.id("inputGroupSelect01")));
        roleDropdown.selectByIndex(0);

        adminPage.clickSaveAccountButton();
        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/div/div/div/div")));

        actualSuccessfulAccountSave = adminPage.findSuccessfulAccountSaveText();
        assertTrue(actualSuccessfulAccountSave.contains(parentFirstName + parentLastName), "Text is not as expected: ");

        adminPage.clickAdminLogoutButton();

        loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        parentUsename = parentFirstName + parentLastName + "1";
        parentPassword = parentFirstName + parentLastName + "1";

        loginPage.enterUsername(parentUsename);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();
        Thread.sleep(5000);
        parentPrimaryInfoInputTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/h1")));

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

        Thread.sleep(10000);
        parentPage.clickParentLogoutButton();

        Thread.sleep(2000);

        loginPage.enterUsername(parentUsename);
        loginPage.enterPassword(parentPassword);
        loginPage.clickLoginButton();
        Thread.sleep(5000);
        actualParentName = parentPage.findParentName();
        assertEquals(actualParentName, parentFirstName + " " + parentLastName, "Text is not as expected: ");
        parentPage.clickParentLogoutButton();

    }
}
