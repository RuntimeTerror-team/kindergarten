package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AgeGroupCreationPage extends AbstractPage {

    // input

    // buttons
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div/div/div/div/form/div[3]/button")
    public WebElement buttonAgeGroupCreate;

    //text
    @FindBy(xpath = "///*[@id=\"root\"]/div/div/div[2]/div/div/div/div/form/div[5]/span")
    public WebElement succesfulAgeGroupCreationText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div/div/div/div/form/div[5]/span")
    public WebElement unsuccessfulSameAgeCreationText;

    public AgeGroupCreationPage(WebDriver driver) {
        super(driver);

    }

    public void clickAgeGroupCreateButton() {
        buttonAgeGroupCreate.click();
    }

    public String findSuccesfulAgeGroupCreationText() {
        return succesfulAgeGroupCreationText.getText();
    }
    public String findUnsuccesfulSameAgeGroupCreationText() {
        return unsuccessfulSameAgeCreationText.getText();
    }
}