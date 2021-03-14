package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentMyAccountPage extends AbstractPage {

    // text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")
    public WebElement myAccountText;

    // button
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/div/div[1]/form/div[4]/button")
    public WebElement buttonNewPasswordSave;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]/div/div[1]/div[9]/button")
    public WebElement buttonDataEdit;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]/div/div[1]/div[9]/button")
    public WebElement buttonDataSave;

    // input
    @FindBy(id = "oldPassword")
    public WebElement inputOldPassword;

    @FindBy(id = "password")
    public WebElement inputNewPassword;

    @FindBy(id = "password2")
    public WebElement inputConfirmNewPassword;

    @FindBy(name = "guardianName")
    public WebElement inputNewParentName;

    @FindBy(name = "guardianSurname")
    public WebElement inputNewParentSurname;

    @FindBy(name = "guardianPhone")
    public WebElement inputNewParentPhoneNo;

    @FindBy(name = "guardianAddress")
    public WebElement inputNewParentAddress;

    @FindBy(name = "guardianCity")
    public WebElement inputNewParentCity;

    @FindBy(name = "guardianPostalCode")
    public WebElement inputNewParentPostalCode;

    @FindBy(name = "guardianEmail")
    public WebElement inputNewParentEmail;

    public ParentMyAccountPage(WebDriver driver) {
        super(driver);
    }

    // text
    public String findMyAccountText() {
        return myAccountText.getText();
    }

    // button
    public void clickNewPasswordSaveButton() {
        buttonNewPasswordSave.click();
    }

    public void clickDataEditButton() {
        buttonDataEdit.click();
    }

    public void clickDataSaveButton() {
        buttonDataSave.click();
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

    public void enterNewParentName(String newName) {
        inputNewParentName.sendKeys(newName);
    }

    public void enterNewParentSurname(String newSurname) {
        inputNewParentName.sendKeys(newSurname);
    }

    public void enterNewParentPhoneNo(String newPhoneNo) {
        inputNewParentPhoneNo.sendKeys(newPhoneNo);
    }

    public void enterNewParentAddress(String newAddress) {
        inputNewParentAddress.sendKeys(newAddress);
    }

    public void enterNewParentCity(String newCity) {
        inputNewParentCity.sendKeys(newCity);
    }

    public void enterNewParentPostalCode(String newPostalCode) {
        inputNewParentPostalCode.sendKeys(newPostalCode);
    }

    public void enterNewParentEmail(String newEmail) {
        inputNewParentEmail.sendKeys(newEmail);
    }
}

