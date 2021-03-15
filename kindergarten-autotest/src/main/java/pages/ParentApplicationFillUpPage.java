package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentApplicationFillUpPage extends AbstractPage {

    // text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")
    public WebElement applicationFillUpText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[5]/div/div/h3")
    public WebElement kindergartenOptionsTitleText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[6]/div/h3")
    public WebElement additionalInformationTitleText;

    // button
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[1]/div/div[1]/div[6]/button")
    public WebElement buttonChildSave;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[2]/div/div[1]/div[9]/button")
    public WebElement buttonParentDataEdit;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div[2]/div/form/div[2]/div/div[1]/div[9]/button")
    public WebElement buttonParentDataSave;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[3]/div/div/button")
    public WebElement buttonSecondParentAdd;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[4]/div/button")
    public WebElement buttonNextPage;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[3]/div/div[1]/div[9]/button")
    public WebElement buttonSecondParentDataSave;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[8]/button[1]")
    public WebElement buttonBackToApplicationFirstPage;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[8]/button[2]")
    public WebElement buttonApplicationSubmit;

    // input
    @FindBy(name = "childName")
    public WebElement inputChildName;

    @FindBy(name = "childSurname")
    public WebElement inputChildSurname;

    @FindBy(name = "childId")
    public WebElement inputChildId;

    @FindBy(name = "childStreet")
    public WebElement inputChildStreet;

    @FindBy(name = "childCity")
    public WebElement inputChildCity;

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

    @FindBy(name = "secondGuardianName")
    public WebElement inputSecondParentName;

    @FindBy(name = "secondGuardianSurname")
    public WebElement inputSecondParentSurname;

    @FindBy(name = "secondGuardianId")
    public WebElement inputSecondParentId;

    @FindBy(name = "secondGuardianPhone")
    public WebElement inputSecondParentPhoneNo;

    @FindBy(name = "secondGuardianAddress")
    public WebElement inputSecondParentAddress;

    @FindBy(name = "secondGuardianCity")
    public WebElement inputSecondParentCity;

    @FindBy(name = "secondGuardianPostalCode")
    public WebElement inputSecondParentPostalCode;

    @FindBy(name = "secondGuardianEmail")
    public WebElement inputSecondParentEmail;

    //check-boxes
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[6]/div/div/div[1]/div/label/span")
    public WebElement checkBoxNumberOne;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[6]/div/div/div[2]/div/label/span")
    public WebElement checkBoxNumberTwo;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[6]/div/div/div[3]/div/label/span")
    public WebElement checkBoxNumberThree;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[6]/div/div/div[4]/div/label/span")
    public WebElement checkBoxNumberFour;


    public ParentApplicationFillUpPage(WebDriver driver) {
        super(driver);
    }

    // text
    public String findMyAccountText() {
        return applicationFillUpText.getText();
    }

    public String findKindergartenOptionsTitleText() {
        return kindergartenOptionsTitleText.getText();
    }

    public String findAdditionalInformationTitleText() {
        return additionalInformationTitleText.getText();
    }

    // button
    public void clickChildSaveButton() {
        buttonChildSave.click();
    }

    public void clickParentDataEditButton() {
        buttonParentDataEdit.click();
    }
    public void clickParentDataSaveButton() {
        buttonParentDataSave.click();
    }

    public void clickSecondParentAddButton() {
        buttonSecondParentAdd.click();
    }

    public void clickNextPageButton() {
        buttonNextPage.click();
    }

    public void clickSecondParentDataSaveButton() {
        buttonSecondParentDataSave.click();
    }

    public void clickBackToApplicationFirstPageButton() {
        buttonBackToApplicationFirstPage.click();
    }

    public void clickApplicationSubmitButton() {
        buttonApplicationSubmit.click();
    }

    // input
    public void enterChildName(String childName) {
        inputChildName.sendKeys(childName);
    }

    public void enterChildSurname(String childSurname) {
        inputChildSurname.sendKeys(childSurname);
    }

    public void enterChildId(String childId) {
        inputChildId.sendKeys(childId);
    }

    public void enterChildStreet(String childStreet) {
        inputChildStreet.sendKeys(childStreet);
    }

    public void enterChildCity(String childCity) {
        inputChildCity.sendKeys(childCity);
    }

       public void enterSecondParentName(String secondParentName) {
        inputSecondParentName.sendKeys(secondParentName);
    }

    public void enterSecondParentSurname(String secondParentSurname) {
        inputSecondParentSurname.sendKeys(secondParentSurname);
    }

    public void enterSecondParentId(String secondParentId) {
        inputSecondParentId.sendKeys(secondParentId);
    }

    public void enterSecondParentPhoneNo(String secondParentPhoneNo) {
        inputSecondParentPhoneNo.sendKeys(secondParentPhoneNo);
    }

    public void enterSecondParentAddress(String secondParentAddress) {
        inputSecondParentAddress.sendKeys(secondParentAddress);
    }

    public void enterSecondParentCity(String secondParentCity) {
        inputSecondParentCity.sendKeys(secondParentCity);
    }

    public void enterSecondParentPostalCode(String secondParentPostalCode) {
        inputSecondParentPostalCode.sendKeys(secondParentPostalCode);
    }

    public void enterSecondParentEmail(String secondParentEmail) {
        inputSecondParentEmail.sendKeys(secondParentEmail);
    }

    public void enterNewParentName(String newName) {
        inputNewParentName.sendKeys(newName);
    }
    public void clearOldParentName() {
        inputNewParentName.clear();
    }

    public void enterNewParentSurname(String newSurname) {
        inputNewParentSurname.sendKeys(newSurname);
    }
    public void clearOldParentSurname() {
        inputNewParentSurname.clear();
    }

    public void enterNewParentPhoneNo(String newPhoneNo) {
        inputNewParentPhoneNo.sendKeys(newPhoneNo);
    }

    public void clearOldParentPhoneNo() {
        inputNewParentPhoneNo.clear();
    }

    public void enterNewParentAddress(String newAddress) {
        inputNewParentAddress.sendKeys(newAddress);
    }
    public void clearOldParentAddress() {
        inputNewParentAddress.clear();
    }

    public void enterNewParentCity(String newCity) {
        inputNewParentCity.sendKeys(newCity);
    }
    public void clearOldParentCity() {
        inputNewParentCity.clear();
    }

    public void enterNewParentPostalCode(String newPostalCode) {
        inputNewParentPostalCode.sendKeys(newPostalCode);
    }
    public void clearOldParentPostalCode() {
        inputNewParentPostalCode.clear();
    }
    public void enterNewParentEmail(String newEmail) {
        inputNewParentEmail.sendKeys(newEmail);
    }
    public void clearOldParentEmail() {
        inputNewParentEmail.clear();
    }

    //check-boxes
    public void clickCheckBoxNumberOne() {
        checkBoxNumberOne.click();
    }

    public void clickCheckBoxNumberTwo() {
        checkBoxNumberTwo.click();
    }

    public void clickCheckBoxNumberThree() {
        checkBoxNumberThree.click();
    }

    public void clickCheckBoxNumberFour() {
        checkBoxNumberFour.click();
    }
}

