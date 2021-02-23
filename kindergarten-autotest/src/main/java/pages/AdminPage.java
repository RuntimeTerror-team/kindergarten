package pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends AbstractPage {

    // input
    @FindBy(id = "firstname")
    public WebElement inputFirstName;

    @FindBy(id = "lastname")
    public WebElement inputLastName;

    // buttons
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div[2]/div/div/div/form/button")
    public WebElement buttonSaveAccount;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div[1]/div/div[2]/button")
    public WebElement buttonAdminLogout;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div[1]/div/div[2]/p/strong")
    public WebElement adminAccountText;


    public AdminPage(WebDriver driver) {
        super(driver);

    }

    public void enterFirstName(String firstName) {
        inputFirstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        inputLastName.sendKeys(lastName);
    }

    public void clickSaveAccountButton() {
        buttonSaveAccount.click();

    }

    public String findAdminLoginText() {
        return adminAccountText.getText();
    }

    public void clickAdminLogoutButton() {
        buttonAdminLogout.click();

    }

}