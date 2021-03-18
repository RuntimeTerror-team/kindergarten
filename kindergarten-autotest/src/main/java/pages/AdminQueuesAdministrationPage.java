package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminQueuesAdministrationPage extends AbstractPage {

    // buttons

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/button")
    public WebElement buttonToOpenNewQueue;


    public AdminQueuesAdministrationPage(WebDriver driver) {
        super(driver);
    }


    // buttons

    public void clickToOpenNewQueueButton() {
        buttonToOpenNewQueue.click();
    }

}