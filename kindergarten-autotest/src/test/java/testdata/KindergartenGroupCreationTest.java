package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertEquals;


public class KindergartenGroupCreationTest extends BaseTest {


    @Test(groups = "smoke, regression")
    public void newKindergartenGroupCreationTest() {


        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);
        KindergartenListPage kindergartenListPage = new KindergartenListPage(driver);
        KindergartenGroupsListPage kindergartenGroupsListPage = new KindergartenGroupsListPage(driver);
        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        loginPage.clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        educationSpecialistPage.clickKindergartenListButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        kindergartenListPage.clickGroupsButton();
        kindergartenGroupsListPage.clickGroupCreateButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        Select ageGroupsDropdown = new Select(driver.findElement(By.id("ageRange")));
        ageGroupsDropdown.selectByIndex(5);
        String childrenAmount = "3";
        kindergartenGroupsListPage.enterChildrenAmount(childrenAmount);

        kindergartenGroupsListPage.clickGroupSaveButton();

        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[3]")));

        String actualKindergartenGroupCreationText = kindergartenGroupsListPage.findSuccessfulGroupCreationText();
        assertEquals(actualKindergartenGroupCreationText, "Darželio grupė sėkmingai išsaugota", "Text is not as expected: ");

        kindergartenGroupsListPage.clickBackToGroupListButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        educationSpecialistPage.clickSpecialistLogoutButton();
    }

    @Test
    public void sameGroupCreationTest() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);
        AgeGroupCreationPage ageGroupCreationPage = new AgeGroupCreationPage(driver);
        KindergartenGroupsListPage kindergartenGroupsListPage = new KindergartenGroupsListPage(driver);
        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        loginPage.clickLoginButton();

        wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        educationSpecialistPage.clickKindergartenGroupsButton();
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        kindergartenGroupsListPage.clickGroupCreateButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        Select ageGroupsDropdown = new Select(driver.findElement(By.id("ageRange")));
        ageGroupsDropdown.selectByIndex(3);
        String childrenAmount = "3";
        kindergartenGroupsListPage.enterChildrenAmount(childrenAmount);

        kindergartenGroupsListPage.clickGroupSaveButton();

        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[4]")));

        String actualKindergartenGroupCreationText = kindergartenGroupsListPage.findUnsuccessfulSameGroupCreationText();
        assertEquals(actualKindergartenGroupCreationText, "Toks amžiaus intervalas jau išsaugotas kitoje grupėje", "Text is not as expected: ");

        kindergartenGroupsListPage.clickBackToGroupListButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        educationSpecialistPage.clickSpecialistLogoutButton();


    }

    @Test
    public void addChildrenToGroupTest() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);
        AgeGroupCreationPage ageGroupCreationPage = new AgeGroupCreationPage(driver);
        KindergartenGroupsListPage kindergartenGroupsListPage = new KindergartenGroupsListPage(driver);
        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        loginPage.clickLoginButton();

        wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        educationSpecialistPage.clickKindergartenGroupsButton();
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        kindergartenGroupsListPage.clickAddChildrenToGroupButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        String childrenAmount = "11";
        kindergartenGroupsListPage.clearChildrenAmount();
        kindergartenGroupsListPage.enterChildrenAmount(childrenAmount);

        kindergartenGroupsListPage.clickGroupSaveButton();

        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[3]")));

        String actualKindergartenGroupCreationText = kindergartenGroupsListPage.findSuccessfulChildrenAmountUpdateText();
        assertEquals(actualKindergartenGroupCreationText, "Grupė sėkmingai atnaujinta", "Text is not as expected: ");

        kindergartenGroupsListPage.clickBackToGroupListButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        educationSpecialistPage.clickSpecialistLogoutButton();


    }
}
