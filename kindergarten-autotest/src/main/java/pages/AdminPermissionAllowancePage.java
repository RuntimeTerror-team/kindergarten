package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPermissionAllowancePage extends AbstractPage {

    // input


    // buttons
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/table/tbody/td[3]/button")
    public WebElement buttonGivePermission;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/table/tbody/td[3]/button")
    public WebElement buttonNotGivePermission;


    //text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")
    public WebElement permissionAllowanceTitleText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/table/tbody/td[2]/span")
    public WebElement permissionNotGivenText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/table/tbody/td[2]/span")
    public WebElement permissionGivenText;

    public AdminPermissionAllowancePage(WebDriver driver) {
        super(driver);
    }

    // input

    // buttons
    public void clickGivePermissionButton() {
        buttonGivePermission.click();
    }

    public void clickNotGivePermissionButton() {
        buttonNotGivePermission.click();
    }


    //text
    public String findPermissionAllowanceTitleText() {
        return permissionAllowanceTitleText.getText();
    }

    public String findPermissionNotGivenText() {
        return permissionNotGivenText.getText();
    }

    public String findPermissionGivenText() {
        return permissionGivenText.getText();
    }
}