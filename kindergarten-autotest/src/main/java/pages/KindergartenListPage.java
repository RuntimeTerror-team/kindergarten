package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KindergartenListPage extends AbstractPage {

    // text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[11]")
    public WebElement successfulKindergartenSaveText;

    // button

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/a")
    public WebElement buttonAddKindergarten;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[12]/button[2]")
    public WebElement buttonKindergartenSave;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[12]/button[1]")
    public WebElement buttonBackToKindergartenList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a[2]")

public WebElement buttonGroups;

    //input
    @FindBy(id = "title")
    public WebElement inputKindergartenName;

    @FindBy(id = "companyCode")
    public WebElement inputKindergartenCode;

    @FindBy(id = "address")
    public WebElement inputKindergartenAddress;

    @FindBy(id = "postalCode")
    public WebElement inputKindergartenPostalCode;

    @FindBy(id = "phoneNo")
    public WebElement inputKindergartenPhoneNo;

    @FindBy(id = "email")
    public WebElement inputKindergartenEmail;

    @FindBy(id = "website")
    public WebElement inputKindergartenWebsite;


    public KindergartenListPage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }


    public void clickAddKindergartenButton() {

        buttonAddKindergarten.click();
    }

    public void clickKindergartenSaveButton() {

        buttonKindergartenSave.click();
    }

    public void clickBackToKindergartenListButton() {

        buttonBackToKindergartenList.click();
    }

    public void clickGroupsButton() {

        buttonGroups.click();
    }

    public void enterKindergartenName(String kindergartenName) {

        inputKindergartenName.sendKeys(kindergartenName);
    }

    public void enterKindergartenCode(String kindergartenCode) {

        inputKindergartenCode.sendKeys(kindergartenCode);
    }

    public void enterKindergartenAddress(String kindergartenAddress) {
        inputKindergartenAddress.sendKeys(kindergartenAddress);
    }

    public void enterKindergartenPostalCode(String kindergartenPostalCode) {
        inputKindergartenPostalCode.sendKeys(kindergartenPostalCode);
    }

    public void enterKindergartenPhoneNo(String kindergartenPhoneNo) {
        inputKindergartenPhoneNo.sendKeys(kindergartenPhoneNo);
    }

    public void enterKindergartenEmail(String kindergartenEmail) {

        inputKindergartenEmail.sendKeys(kindergartenEmail);
    }

    public void enterKindergartenWebsite(String kindergartenWebsite) {
        inputKindergartenWebsite.sendKeys(kindergartenWebsite);
    }

    public String getSuccessfulKindergartenSaveText() {
        return successfulKindergartenSaveText.getText();
    }
}

