package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EducationSpecialistQueuesAdministrationPage extends AbstractPage {

    // text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")
    public WebElement queuesAdministrationTitleText;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/div/table/tbody/tr/td[4]")
    public WebElement statusActiveText;
@FindBy(xpath = "/html/body/div/div/div/div[2]/div[2]/div/div[1]/div/table/tbody/tr/td[4]")
public WebElement statusSuspendedText;

    // button
    @FindBy(xpath = "/html/body/div/div/div/div[2]/div[2]/div/div[1]/div/table/tbody/tr/td[5]/button")
    public WebElement buttonQueueChange;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div[2]/div/div[1]/div/table/tbody/tr/td[5]/button[1]")
    public WebElement buttonCancelRegistrationStopDate;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div[2]/div/div[1]/div/table/tbody/tr/td[5]/button[2]")
    public WebElement buttonSaveRegistrationStopDate;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div[2]/div/div[1]/div/table/tbody/tr/td[3]/button")
    public WebElement buttonQueueClose;

    // input
    @FindBy(xpath = "//*[@id=\"registrationClosingDt\"]")
    public WebElement inputRegistrationStopDateAndTime;

    public EducationSpecialistQueuesAdministrationPage(WebDriver driver) {
        super(driver);
    }

    // text
    public String findQueuesAdministrationTitleText() {
        return queuesAdministrationTitleText.getText();
    }

    public String findStatusActiveText() {
        return statusActiveText.getText();
    }

    public String findStatusSuspendedText() {
        return statusSuspendedText.getText();
    }
    // button
    public void clickQueueChangeButton() {
        buttonQueueChange.click();
    }

    public void clickCancelRegistrationStopDateButton() {
        buttonCancelRegistrationStopDate.click();
    }
    public void clickSaveRegistrationStopDateButton() {
        buttonSaveRegistrationStopDate.click();
    }

    public void clickQueueCloseButton() {
        buttonQueueClose.click();
    }

    // input
    public void enterRegistrationStopDateAndTime(String dateAndTime) {
        inputRegistrationStopDateAndTime.sendKeys(dateAndTime);
    }


}

