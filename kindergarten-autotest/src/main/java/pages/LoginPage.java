package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    // input
    @FindBy(id = "username")
    public WebElement inputUsername;

    @FindBy(id = "password")
    public WebElement inputPassword;

    // buttons
    @FindBy(id = "loginButton")
    public WebElement buttonLogin;

    // text
    @FindBy(xpath = "//*[@id=\"loginForm\"]/h3[1]")
    public WebElement loginTitleText;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        inputUsername.sendKeys(username);
    }

    public void enterPassword(String password) {
        inputPassword.sendKeys(password);
    }

    public void clickLoginButton() {
        buttonLogin.click();
    }

    public String checkLoginPageTitleName() {
        return loginTitleText.getText();
    }

}
