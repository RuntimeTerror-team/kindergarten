package testdata;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.assertEquals;
import basetest.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.LoginPage;
import pages.SpecialistPage;
import utilities.FileReaderUtils;
import utilities.WaitUtils;

public class SpecialistTest extends BaseTest {



    @Test
    public void testFromTestdataFileSpecialistCreate() throws IOException, InterruptedException {

        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SpecialistPage specialistPage = new SpecialistPage(driver);
        WaitUtils waitUtils = new WaitUtils();

        List<String> adminTestdata = FileReaderUtils.getTestData("src/test/resources/TestData_Admin_Login.txt");
        String adminUsername = adminTestdata.get(0);
        String adminPassword = adminTestdata.get(1);
        loginPage.enterUsername(adminUsername);
        loginPage.enterPassword(adminPassword);
        loginPage.clickLoginButton();
        //Thread.sleep(2000);
        waitUtils.verifyAdminLoginText(driver);
        String specialistFirstName = "Vardas";
        String specialistLastName = "Pavardė";

        adminPage.enterFirstName(specialistFirstName);
        adminPage.enterLastName(specialistLastName);

        Select roleDropdown = new Select(driver.findElement(By.id("inputGroupSelect01")));
        roleDropdown.selectByIndex(1);

        adminPage.clickSaveAccountButton();
        //	Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/div/div[2]/p/strong")));

        //waitUtils.verifyAdminLoginText(driver);

        adminPage.clickAdminLogoutButton();

        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";
        //Thread.sleep(2000);
        waitUtils.verifyAdminLoginText(driver);
        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        loginPage.clickLoginButton();
        //Thread.sleep(2000);
        waitUtils.verifyAdminLoginText(driver);
        String actualResultSpec = specialistPage.findSpecialistLoginText();
        assertEquals("Text is not as expected: ", actualResultSpec, "Švietimo specialistas");
        specialistPage.clickSpecialistLogoutButton();


    }
}
