package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminQueuesAdministrationPage extends AbstractPage {

    // input


    // buttons
//    sukurti nauja eile mygtukas
//    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/table/tbody/td[3]/button")
//    public WebElement buttonGivePermission;


    //text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")
    public WebElement queuesAdministrationTitleText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]")
    public WebElement warningWhenActiveText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr/td[4]")
    public WebElement activeQueueText;

    public AdminQueuesAdministrationPage(WebDriver driver) {
        super(driver);
    }

    // input

    // buttons
    //PRADETI NAUJA EILE
//    public void clickGivePermissionButton() {
//        buttonGivePermission.click();
//    }



    //text
    public String findQueuesAdministrationTitleText() {
        return queuesAdministrationTitleText.getText();
    }

    public String findWarningWhenActiveText() {
        return warningWhenActiveText.getText();
    }

    public String findActiveQueueText() {
        return activeQueueText.getText();
    }
}