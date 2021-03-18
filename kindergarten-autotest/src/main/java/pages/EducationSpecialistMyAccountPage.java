package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EducationSpecialistMyAccountPage extends AbstractPage {


    // button
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/div/div[1]/form/div[4]/button")
    public WebElement buttonNewPasswordSave;

    // input
    @FindBy(id = "oldPassword")
    public WebElement inputOldPassword;

    @FindBy(id = "password")
    public WebElement inputNewPassword;

    @FindBy(id = "password2")
    public WebElement inputConfirmNewPassword;

    public EducationSpecialistMyAccountPage(WebDriver driver) {
        super(driver);
    }


    // button
    public void clickNewPasswordSaveButton() {
        buttonNewPasswordSave.click();
    }

    // input
    public void enterOldPassword(String oldPassword) {
        inputOldPassword.sendKeys(oldPassword);
    }

    public void enterNewPassword(String newPassword) {
        inputNewPassword.sendKeys(newPassword);
    }

    public void enterNewPasswordConfirm(String newPassword) {
        inputConfirmNewPassword.sendKeys(newPassword);
    }
}

