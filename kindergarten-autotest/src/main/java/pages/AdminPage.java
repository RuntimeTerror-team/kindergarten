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
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/form/div[4]/button")
    public WebElement buttonSaveAccount;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[1]/button")
    public WebElement buttonAdminLogout;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/nav/a[2]")
    public WebElement buttonDistrictAdministration;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/nav/a[2]")
    public WebElement buttonAccountsAdministration;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/nav/a[3]")
    public WebElement buttonPermissionsAllowance;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/nav/a[4]")
    public WebElement buttonQueuesAdministration;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/nav/a[5]")
    public WebElement buttonLogging;

    //text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")
    public WebElement adminAccountText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]")
    public WebElement successfulAccountSave;

    public AdminPage(WebDriver driver) {
        super(driver);

    }

    // input
    public void enterFirstName(String firstName) {
        inputFirstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        inputLastName.sendKeys(lastName);
    }

    // buttons
    public void clickSaveAccountButton() {
        buttonSaveAccount.click();
    }

    public void clickAdminLogoutButton() {
        buttonAdminLogout.click();
    }

    public void clickDistrictAdministrationButton() {
        buttonDistrictAdministration.click();
    }

    public void clickAccountsAdministrationButton() {
        buttonAccountsAdministration.click();
    }

    public void clickPermissionAllowanceButton() {
        buttonPermissionsAllowance.click();
    }

    public void clickQueuesAdministrationButton() {
        buttonQueuesAdministration.click();
    }

    public void clickLoggingButton() {
        buttonLogging.click();
    }

    //text
    public String findAdminLoginText() {
        return adminAccountText.getText();
    }

    public String findSuccessfulAccountSaveText() {
        return successfulAccountSave.getText();
    }
}