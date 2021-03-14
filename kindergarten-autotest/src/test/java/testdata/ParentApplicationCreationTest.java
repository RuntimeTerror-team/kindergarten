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



public class ParentApplicationCreationTest extends BaseTest {


    @Test
    public void parentApplicationCreationTest()  {


        LoginPage loginPage = new LoginPage(driver);

        ParentPage parentPage = new ParentPage(driver);
        ParentApplicationFillUpPage parentApplicationFillUpPage = new ParentApplicationFillUpPage(driver);
        ParentApplicationsPage parentApplicationsPage = new ParentApplicationsPage(driver);

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
        parentPage.clickApplicationFillUp();

        String childName = "Jonas";
        String childSurname = "Petrauskas";
        String childId = "50704120012";
        String childStreet = "Kalvarijų g. 44";
        String childCity = "Vilnius";

        parentApplicationFillUpPage.enterChildName(childName);
        parentApplicationFillUpPage.enterChildSurname(childSurname);
        parentApplicationFillUpPage.enterChildId(childId);
        parentApplicationFillUpPage.enterChildStreet(childStreet);
        parentApplicationFillUpPage.enterChildCity(childCity);
        parentApplicationFillUpPage.clickChildSaveButton();

        String newParentName = "Petras Jonas";
        String newParentSurname = "Petrauskas-Jonaitis";
        String newParentAddress = "Antakalnio g.34-1";
        String newParentPostalCode = "12346";

        parentApplicationFillUpPage.clickParentDataEditButton();
        parentApplicationFillUpPage.clearOldParentName();
        parentApplicationFillUpPage.enterNewParentName(newParentName);
        parentApplicationFillUpPage.enterNewParentSurname(newParentSurname);
        parentApplicationFillUpPage.enterNewParentAddress(newParentAddress);
        parentApplicationFillUpPage.enterNewParentPostalCode(newParentPostalCode);

        parentApplicationFillUpPage.clickParentDataSaveButton();
        parentApplicationFillUpPage.clickSecondParentAddButton();

        String secondParentName = "Jonė";
        String secondParentSurname = "Petrauskė";
        String secondParentId = "98765432109";
        String secondParentPhoneNo = "+3706060606";
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

        Select kindergartenDropdown = new Select(driver.findElement(By.name("kindergartens")));
        kindergartenDropdown.selectByIndex(1);
        kindergartenDropdown.selectByIndex(2);
        kindergartenDropdown.selectByIndex(3);

        parentApplicationFillUpPage.clickCheckBoxNumberOne();
        parentApplicationFillUpPage.clickCheckBoxNumberTwo();
        parentApplicationFillUpPage.clickCheckBoxNumberThree();
        parentApplicationFillUpPage.clickCheckBoxNumberFour();

        parentApplicationFillUpPage.clickApplicationSubmitButton();

        WebElement applicationPageTitle = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));


        String actualChildName = parentApplicationsPage.findThirdApplicationChildNameText();
        assertEquals(actualChildName, childName + " " + childSurname, "Text is not as expected: ");

        String actualStatusText = parentApplicationsPage.findThirdChildApplicationStatusText();
        assertEquals(actualChildName, "Pateiktas", "Text is not as expected: ");

        parentPage.clickParentLogoutButton();

    }
}
