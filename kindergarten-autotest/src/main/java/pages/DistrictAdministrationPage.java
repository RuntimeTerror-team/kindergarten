package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DistrictAdministrationPage extends AbstractPage {

    // input
    @FindBy(id = "inputDistrict")
    public WebElement inputDistrictName;

    @FindBy(name = "districtTitle")
    public WebElement inputDistrictNameToEdit;
    // buttons
    @FindBy(xpath = "//*[@id=\"districtAdministrationComponent\"]/form/div[1]/div/button")
    public WebElement buttonAddDistrict;

    @FindBy(id = "1")
    public WebElement firstDistrictInTheList;
    @FindBy(xpath = "//*[@id=\"districtTable\"]/tbody/tr[1]/td/form/div[1]/div/button")
    public WebElement saveNewDistrictNameButton;
    //text
    @FindBy(xpath = "//*[@id=\"districtAdministrationComponent\"]/div[1]/span")
    public WebElement succesfulDistrictCreationText;
    @FindBy(xpath = "//*[@id=\"1\"]")
    public WebElement districtName;


    public DistrictAdministrationPage(WebDriver driver) {
        super(driver);

    }

    public void enterDistrictName(String districtName) {
        inputDistrictName.sendKeys(districtName);
    }

    public void clearDistrictName() {
        inputDistrictNameToEdit.clear();
    }

    public void enterNewDistrictName(String districtName) {
        inputDistrictNameToEdit.sendKeys(districtName);
    }

    public void clickAddDistrictButton() {
        buttonAddDistrict.click();
    }

    public void clickFirstDistrictInTheListToEdit() {
        firstDistrictInTheList.click();
    }

    public void clickToSaveNewDistrictName() {
        saveNewDistrictNameButton.click();
    }

    public String findSuccesfulDistrictCreationText() {
        return succesfulDistrictCreationText.getText();
    }

    public String findNewDistrictName() {
        return districtName.getText();
    }
}