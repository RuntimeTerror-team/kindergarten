package testdata;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.DistrictAdministrationPage;
import pages.LoginPage;
import utilities.FileReaderUtils;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DistrictTest extends BaseTest {


    @Test(groups = "smoke")
    public void districtCreationTest() throws IOException, InterruptedException {
        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdminTest adminTest = new AdminTest();
        DistrictAdministrationPage districtAdministrationPage = new DistrictAdministrationPage(driver);
        adminTest.adminLoginTest();
        Thread.sleep(2000);
        adminPage.clickDistrictAdministrationButton();
        String districtName = "Pilaitė";
        districtAdministrationPage.enterDistrictName(districtName);
        districtAdministrationPage.clickAddDistrictButton();
        Thread.sleep(2000);
        String actualSuccessfulDistrictCreationText = districtAdministrationPage.findSuccesfulDistrictCreationText();
        assertEquals(actualSuccessfulDistrictCreationText, "Rajonas sėkmingai sukurtas", "Text is not as expected: ");

//        String createdDistrictName= driver.findElement(By.className("btn btn-link")).getText();
//        List<WebElement> district = driver
//                .findElements(By.cssSelector("body > table.table.table-hover >tbody:nth-child(2) >tr"));
//
//        for (int i = 0; i < district.size(); i++) {
//            String tempName = district.get(i).findElement(By.cssSelector("td:nth-child(2)")).getText();
//            if (tempName.equals(createdDistrictName)) {
//                district.get(i).findElement(By.linkText("createdDistrictName")).click();
//                break;
//            }
//        }


        String newDistrict = "Antakalnis";
        districtAdministrationPage.clickFirstDistrictInTheListToEdit();
        districtAdministrationPage.clearDistrictName();
        districtAdministrationPage.enterNewDistrictName(newDistrict);
        districtAdministrationPage.clickToSaveNewDistrictName();
        Thread.sleep(2000);
        String actualSuccessfulNewDistrictSave = districtAdministrationPage.findNewDistrictName();
        assertEquals(actualSuccessfulNewDistrictSave, newDistrict, "Text is not as expected: ");


    }
}

