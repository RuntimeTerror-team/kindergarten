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


public class SpecialistTest extends BaseTest {



    @Test
    public void specialistCreationTest() throws IOException {

        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SpecialistPage specialistPage = new SpecialistPage(driver);
        AdminTest adminTest = new AdminTest();

       adminTest.adminLoginTest();

        String specialistFirstName = "Vardas";
        String specialistLastName = "Pavardė";

        adminPage.enterFirstName(specialistFirstName);
        adminPage.enterLastName(specialistLastName);

        Select roleDropdown = new Select(driver.findElement(By.id("inputGroupSelect01")));
        roleDropdown.selectByIndex(1);

        adminPage.clickSaveAccountButton();

              adminPage.clickAdminLogoutButton();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        loginPage.clickLoginButton();
        WebElement specialistLoginText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div[1]/div/div[2]/p/strong")));

        String actualResultSpec = specialistPage.findSpecialistLoginText();
        assertEquals(actualResultSpec, specialistLoginText.getText(), "Text is not as expected: ");
        specialistPage.clickSpecialistLogoutButton();


    }
}
