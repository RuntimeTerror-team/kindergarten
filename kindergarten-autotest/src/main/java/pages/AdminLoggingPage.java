package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoggingPage extends AbstractPage {

    // input

    // buttons

    //text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")
    public WebElement loggingTitleText;

    public AdminLoggingPage(WebDriver driver) {
        super(driver);
    }

    // input

    // buttons

    //text
    public String findLoggingTitleText() {
        return loggingTitleText.getText();
    }
}