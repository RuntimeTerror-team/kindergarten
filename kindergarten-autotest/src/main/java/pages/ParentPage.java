package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentPage extends AbstractPage {

    // text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div/div/h1")
    public WebElement parentpageMainpageTitle;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")
    public WebElement getParentName;

    // button
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[1]/button")
    public WebElement buttonParentLogout;

    @FindBy (xpath = "//*[@id=\"root\"]/div/div/div[1]/nav/a[4]")
    public WebElement buttonMyAccount;

    public ParentPage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    public String findParentpageMainpageTitle() {

        return parentpageMainpageTitle.getText();
    }

    public String findParentName() {

        return getParentName.getText();
    }

    public void clickParentLogoutButton() {

        buttonParentLogout.click();
    }
    public void clickMyAccountButton() {

        buttonMyAccount.click();
    }
}
