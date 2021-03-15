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


    @Test
    public void newKindergartenGroupCreationTest() {

        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);
        EducationSpecialistKindergartenListPage educationSpecialistKindergartenListPage = new EducationSpecialistKindergartenListPage(driver);
        EducationSpecialistKindergartenGroupsListPage educationSpecialistKindergartenGroupsListPage = new EducationSpecialistKindergartenGroupsListPage(driver);
        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        loginPage.clickLoginButton();
        wait = new WebDriverWait(driver, 10);
        WebElement successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        educationSpecialistPage.clickKindergartenListButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        educationSpecialistKindergartenListPage.clickGroupsButton();
        educationSpecialistKindergartenGroupsListPage.clickGroupCreateButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        Select ageGroupsDropdown = new Select(driver.findElement(By.id("ageRange")));
        ageGroupsDropdown.selectByIndex(8);
        String childrenAmount = "3";
        educationSpecialistKindergartenGroupsListPage.enterChildrenAmount(childrenAmount);

        educationSpecialistKindergartenGroupsListPage.clickGroupSaveButton();

        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[3]")));

        String actualKindergartenGroupCreationText = educationSpecialistKindergartenGroupsListPage.findSuccessfulGroupCreationText();
        assertEquals(actualKindergartenGroupCreationText, "Darželio grupė sėkmingai išsaugota", "Text is not as expected: ");

        educationSpecialistKindergartenGroupsListPage.clickBackToGroupListButton();

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
        EducationSpecialistAgeGroupCreationPage educationSpecialistAgeGroupCreationPage = new EducationSpecialistAgeGroupCreationPage(driver);
        EducationSpecialistKindergartenGroupsListPage educationSpecialistKindergartenGroupsListPage = new EducationSpecialistKindergartenGroupsListPage(driver);
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

        educationSpecialistKindergartenGroupsListPage.clickGroupCreateButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        Select ageGroupsDropdown = new Select(driver.findElement(By.id("ageRange")));
        ageGroupsDropdown.selectByIndex(8);
        String childrenAmount = "3";
        educationSpecialistKindergartenGroupsListPage.enterChildrenAmount(childrenAmount);

        educationSpecialistKindergartenGroupsListPage.clickGroupSaveButton();

        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/div[2]/div[2]/div/form/div[4]")));

        String actualKindergartenGroupCreationText = educationSpecialistKindergartenGroupsListPage.findUnsuccessfulSameGroupCreationText();
        assertEquals(actualKindergartenGroupCreationText, "Toks amžiaus intervalas jau išsaugotas kitoje grupėje", "Text is not as expected: ");

        educationSpecialistKindergartenGroupsListPage.clickBackToGroupListButton();

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
        EducationSpecialistAgeGroupCreationPage educationSpecialistAgeGroupCreationPage = new EducationSpecialistAgeGroupCreationPage(driver);
        EducationSpecialistKindergartenGroupsListPage educationSpecialistKindergartenGroupsListPage = new EducationSpecialistKindergartenGroupsListPage(driver);
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

        educationSpecialistKindergartenGroupsListPage.clickAddChildrenToGroupButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        String childrenAmount = "4";
        educationSpecialistKindergartenGroupsListPage.clearChildrenAmount();
        educationSpecialistKindergartenGroupsListPage.enterChildrenAmount(childrenAmount);

        educationSpecialistKindergartenGroupsListPage.clickGroupSaveButton();

        wait = new WebDriverWait(driver, 10);
        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[3]")));

        String actualKindergartenGroupCreationText = educationSpecialistKindergartenGroupsListPage.findSuccessfulChildrenAmountUpdateText();
        assertEquals(actualKindergartenGroupCreationText, "Grupė sėkmingai atnaujinta", "Text is not as expected: ");

        educationSpecialistKindergartenGroupsListPage.clickBackToGroupListButton();

        successfulText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        educationSpecialistPage.clickSpecialistLogoutButton();


    }
}
