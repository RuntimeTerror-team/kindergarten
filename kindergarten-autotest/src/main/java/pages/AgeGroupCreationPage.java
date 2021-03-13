package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AgeGroupCreationPage extends AbstractPage {

    // input

    // buttons
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/form/div[4]/button")
    public WebElement buttonAgeGroupCreate;

    //text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/form/div[3]")
    public WebElement successfulAgeGroupCreationText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/form/div[3]")
    public WebElement unsuccessfulSameAgeCreationText;

    public AgeGroupCreationPage(WebDriver driver) {
        super(driver);

    }

    public void clickAgeGroupCreateButton() {
        buttonAgeGroupCreate.click();
    }

    public String findSuccessfulAgeGroupCreationText() {
        return successfulAgeGroupCreationText.getText();
    }

    public String findUnsuccessfulSameAgeGroupCreationText() {
        return unsuccessfulSameAgeCreationText.getText();
    }
}