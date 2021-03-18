package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentPage extends AbstractPage {

    // text
    @FindBy(xpath = "/html/body/div/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")
    public WebElement parentpageMainpageTitle;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")
    public WebElement getParentName;

    // button
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[1]/button")
    public WebElement buttonParentLogout;

    @FindBy (xpath = "//*[@id=\"root\"]/div/div/div[1]/nav/a[4]")
    public WebElement buttonMyAccount;

    @FindBy (xpath = "//*[@id=\"root\"]/div/div/div[1]/nav/a[2]")
    public WebElement buttonApplicationFillUp;

    @FindBy (xpath = "//*[@id=\"root\"]/div/div/div[1]/nav/a[3]")
    public WebElement buttonHealthForms;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/nav/a[1]")
    public WebElement buttonApplications;

    public ParentPage(WebDriver driver) {
        super(driver);
           }

    // text
    public String findParentpageMainpageTitle() {
        return parentpageMainpageTitle.getText();
    }

    public String findParentName() {
        return getParentName.getText();
    }

    // button
    public void clickParentLogoutButton() {
        buttonParentLogout.click();
    }

    public void clickApplicationFillUp() {
        buttonApplicationFillUp.click();
    }

    public void clickHealthForms() {
        buttonHealthForms.click();
    }

    public void clickMyAccountButton() {
        buttonMyAccount.click();
    }
    public void clickApplicationsButton() {
        buttonApplications.click();
    }
}
