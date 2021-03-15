package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EducationSpecialistKindergartenEditPage extends AbstractPage {

    // text
    @FindBy(xpath = "/html/body/div/div/div/div[2]/div[2]/div/form/div[10]")
    public WebElement successfulKindergartenUpdateSaveText;

    @FindBy (xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")
    public WebElement kindergartenInformationTitleText;

    @FindBy (xpath = "/html/body/div/div/div/div[2]/div[2]/div/form/div[1]/input")
    public WebElement newKindergartenNameText;

    @FindBy (xpath = "//*[@id=\"phoneNumber\"]")
    public WebElement newKindergartenPhoneNumberText;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div[2]/div/form/div[8]/input")
    public WebElement newKindergartenEmailText;
    // button

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div[2]/div/div/button[2]")
    public WebElement buttonKindergartenUpdateSave;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div[2]/div/div/button[1]")
    public WebElement buttonBackToKindergartenList;

    @FindBy (xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/button[2]")
    public WebElement buttonKindergartenInformationEdit;

    //input
    @FindBy(id = "title")
    public WebElement inputKindergartenName;

    @FindBy(id = "address")
    public WebElement inputKindergartenAddress;

    @FindBy(id = "postalCode")
    public WebElement inputKindergartenPostalCode;

    @FindBy(id = "phoneNumber")
    public WebElement inputKindergartenPhoneNumber;

    @FindBy(id = "email")
    public WebElement inputKindergartenEmail;

    @FindBy(id = "website")
    public WebElement inputKindergartenWebsite;

    public EducationSpecialistKindergartenEditPage(WebDriver driver) {
        super(driver);
    }

    // text
    public String getSuccessfulKindergartenUpdateSaveText() {
        return successfulKindergartenUpdateSaveText.getText();
    }
    public String getKindergartenInformationTitleText() {
        return kindergartenInformationTitleText.getText();
    }
    public String getNewKindergartenNameText() {
        return newKindergartenNameText.getText();
    }
    public String getNewKindergartenPhoneName() {
        return newKindergartenPhoneNumberText.getText();
    }

    public String getNewKindergartenEmail() {
        return newKindergartenEmailText.getText();
    }
    // button
    public void clickKindergartenUpdateSaveButton() {
        buttonKindergartenUpdateSave.click();
    }

    public void clickBackToKindergartenListButton() {
        buttonBackToKindergartenList.click();
    }

    public void clickKindergartenInformationEditButton() {
        buttonKindergartenInformationEdit.click();
    }


    //input
    public void enterKindergartenName(String kindergartenName) {
        inputKindergartenName.sendKeys(kindergartenName);
    }
    public void clearKindergartenName() {
        inputKindergartenName.clear();
    }
     public void enterKindergartenAddress(String kindergartenAddress) {
        inputKindergartenAddress.sendKeys(kindergartenAddress);
    }
    public void clearKindergartenAddress() {
        inputKindergartenAddress.clear();
    }
    public void enterKindergartenPostalCode(String kindergartenPostalCode) {
        inputKindergartenPostalCode.sendKeys(kindergartenPostalCode);
    }
    public void clearKindergartenPostalCode() {
        inputKindergartenPostalCode.clear();
    }
    public void enterKindergartenPhoneNumber(String kindergartenPhoneNumber) {
        inputKindergartenPhoneNumber.sendKeys(kindergartenPhoneNumber);
    }
    public void clearKindergartenPhoneNumber() {
        inputKindergartenPhoneNumber.clear();
    }
    public void enterKindergartenEmail(String kindergartenEmail) {
        inputKindergartenEmail.sendKeys(kindergartenEmail);
    }
    public void clearKindergartenEmail() {
        inputKindergartenEmail.clear();
    }
    public void enterKindergartenWebsite(String kindergartenWebsite) {
        inputKindergartenWebsite.sendKeys(kindergartenWebsite);
    }
    public void clearKindergartenWebsite() {
        inputKindergartenWebsite.clear();
    }
}

