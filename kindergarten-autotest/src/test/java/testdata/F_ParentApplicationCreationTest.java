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

public class F_ParentApplicationCreationTest extends BaseTest {


    @Test(groups = "regression")
    public void parentApplicationCreationTest() throws InterruptedException, IOException {

        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ParentApplicationFillUpPage parentApplicationFillUpPage = new ParentApplicationFillUpPage(driver);
        ParentApplicationsPage parentApplicationsPage = new ParentApplicationsPage(driver);
        AdminQueuesAdministrationPage adminQueuesAdministrationPage = new AdminQueuesAdministrationPage(driver);
        ParentPrimaryInputPage parentPrimaryInputPage = new ParentPrimaryInputPage(driver);
        ParentPage parentPage = new ParentPage(driver);

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
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement adminLoginText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        adminPage.clickQueuesAdministrationButton();

        WebElement queuePageText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        /**
         * Admin opens the new queue for application
         *
         */
        adminQueuesAdministrationPage.clickToOpenNewQueueButton();
        /**
         * Admin logs out
         *
         */
        adminPage.clickAdminLogoutButton();

        wait = new WebDriverWait(driver, 10);
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
        parentPage.clickApplicationFillUp();

        String childName = "Alana";
        String childSurname = "Petrauskaitė";
        String childId = "60704120035";
        String childStreet = "Kalvarijų g. 44";
        String childCity = "Vilnius";
        /**
         * Child's guardian fills up the application to the kindergarten
         *
         */
        parentApplicationFillUpPage.enterChildName(childName);
        parentApplicationFillUpPage.enterChildSurname(childSurname);
        parentApplicationFillUpPage.enterChildId(childId);
        parentApplicationFillUpPage.enterChildStreet(childStreet);
        parentApplicationFillUpPage.enterChildCity(childCity);
        parentApplicationFillUpPage.clickChildSaveButton();

        String newParentAddress = "Antakalnio g.34-1";

        parentApplicationFillUpPage.clickParentDataEditButton();
        parentApplicationFillUpPage.clearOldParentAddress();
        parentApplicationFillUpPage.enterNewParentAddress(newParentAddress);

        parentApplicationFillUpPage.clickParentDataSaveButton();
        WebElement buttonEditText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[2]/div/div[1]/div[9]/button")));
/**
 * Child's guardian adds the second child's guardian information
 *
 */
        parentApplicationFillUpPage.clickSecondParentAddButton();

        String secondParentName = "Jonė";
        String secondParentSurname = "Petrauskė";
        String secondParentId = "98765432145";
        String secondParentPhoneNo = "+37060606060";
        String secondParentAddress = "Kalvarijų g. 44";
        String secondParentCity = "Vilnius";
        String secondParentPostalCode = "12347";
        String secondParentEmail = "jone@petrauskas.lt";

        parentApplicationFillUpPage.enterSecondParentName(secondParentName);
        parentApplicationFillUpPage.enterSecondParentSurname(secondParentSurname);
        parentApplicationFillUpPage.enterSecondParentId(secondParentId);
        parentApplicationFillUpPage.enterSecondParentPhoneNo(secondParentPhoneNo);
        parentApplicationFillUpPage.enterSecondParentAddress(secondParentAddress);
        parentApplicationFillUpPage.enterSecondParentCity(secondParentCity);
        parentApplicationFillUpPage.enterSecondParentPostalCode(secondParentPostalCode);
        parentApplicationFillUpPage.enterSecondParentEmail(secondParentEmail);
        parentApplicationFillUpPage.clickSecondParentDataSaveButton();

        parentApplicationFillUpPage.clickNextPageButton();
/**
 * Child's guardian chooses the preferred kindergarten
 *
 */
        Select kindergartenDropdown = new Select(driver.findElement(By.name("kindergartens")));
        kindergartenDropdown.selectByIndex(1);
/**
 * Child's guardian adds additional information
 *
 */
        parentApplicationFillUpPage.clickCheckBoxNumberOne();
        parentApplicationFillUpPage.clickCheckBoxNumberTwo();
        parentApplicationFillUpPage.clickCheckBoxNumberThree();
        parentApplicationFillUpPage.clickCheckBoxNumberFour();
/**
 * Child's guardian submits the application to kindergarten
 *
 */
        parentApplicationFillUpPage.clickApplicationSubmitButton();
        wait = new WebDriverWait(driver, 10);
        WebElement applicationPageTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));


        parentPage.clickApplicationsButton();
        applicationPageTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        Thread.sleep(3000);
        String actualChildName = parentApplicationsPage.findFirstApplicationChildNameText();
/**
 * Child's guardian application assertion with child's name
 * and surname and with the status of application
 * in application list
 *
 */
        assertEquals(actualChildName, childName + " " + childSurname, "Text is not as expected: ");
        applicationPageTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        Thread.sleep(3000);
        String actualStatusText = parentApplicationsPage.findFirstChildApplicationStatusText();
        assertEquals(actualStatusText, "Pateiktas", "Text is not as expected: ");
        applicationPageTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));
        /**
         * Child's guardian logs out
         *
         */
        parentPage.clickParentLogoutButton();

    }
}
