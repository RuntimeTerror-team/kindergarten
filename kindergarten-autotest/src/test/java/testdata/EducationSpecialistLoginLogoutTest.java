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
import pages.EducationSpecialistPage;
import utilities.FileReaderUtils;
/** The tests are testing application of Kindergarten Information System
 *
 * @author Runtime Terror Team
 * @version 1.0
 *
 */

public class EducationSpecialistLoginLogoutTest extends BaseTest {


    @Test(groups = "smoke")
    public void educationSpecialistCreationTest() throws IOException, InterruptedException {

        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        EducationSpecialistPage educationSpecialistPage = new EducationSpecialistPage(driver);

        String loginText = loginPage.checkLoginPageTitleName();
        assertEquals(loginText, "Vaikų darželių informacinė sistema", "Text is not as expected: ");


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

        WebElement specialistLoginText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")));

        String specialistFirstName = "Jonas";
        String specialistLastName = "Jonaitis";

        adminPage.enterFirstName(specialistFirstName);
        adminPage.enterLastName(specialistLastName);

        Select roleDropdown = new Select(driver.findElement(By.id("inputGroupSelect01")));
        roleDropdown.selectByIndex(1);
        /**
         * Admin creates Education specialist
         *
         */
        adminPage.clickSaveAccountButton();
        /**
         * Admin logs out
         *
         */
        adminPage.clickAdminLogoutButton();
        wait = new WebDriverWait(driver, 5);
        WebElement loginh1 = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("loginh1")));
        String specialistUsername = "ŠvietimoSpecialistas1";
        String specialistPassword = "ŠvietimoSpecialistas1";

        loginPage.enterUsername(specialistUsername);
        loginPage.enterPassword(specialistPassword);
        /**
         * Education specialist logs in
         *
         */
        loginPage.clickLoginButton();
        specialistLoginText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

        String actualResultSpec = educationSpecialistPage.findSpecialistLoginText();
        assertEquals(actualResultSpec, specialistLoginText.getText(), "Text is not as expected: ");
       Thread.sleep(2000);
        /**
         * Education specialist logs out
         *
         */
        educationSpecialistPage.clickSpecialistLogoutButton();


    }
}
