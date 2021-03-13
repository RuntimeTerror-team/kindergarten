package testdata;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;

import basetest.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.LoginPage;
import utilities.FileReaderUtils;

public class AdminTest extends BaseTest {

    @Test (groups = "smoke, regression")
    public void adminLoginTest() throws IOException {
        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        String loginText = loginPage.checkLoginPageTitleName();
        assertEquals(loginText, "Vaikų darželių informacinė sistema", "Text is not as expected: ");


        List<String> testdata = FileReaderUtils.getTestData("src/test/resources/TestData_Admin_Login.txt");

        String username = testdata.get(0);
        String password = testdata.get(1);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement adminAccountText = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")));

//		String adminLoginURL = "http://akademijait.vtmc.lt:8181/kindergarten/admin";
//		assertEquals(adminLoginURL, driver.getCurrentUrl());
        String actualResult = adminPage.findAdminLoginText();
        assertEquals(actualResult, adminAccountText.getText(), "Text is not as expected: ");
     //   adminPage.clickAdminLogoutButton();
    }

    @Test
    public void adminLoginLogout() throws IOException {
        AdminPage adminPage = new AdminPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        adminLoginTest();
        adminPage.clickAdminLogoutButton();
    }
}
