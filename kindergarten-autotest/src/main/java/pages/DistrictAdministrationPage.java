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
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/form/div[1]/div/button")
    public WebElement buttonSaveDistrict;

    @FindBy(xpath = "//*[@id=\"1\"]")
    public WebElement firstDistrictInTheListEditButton;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr[1]/td[2]/button")
    public WebElement saveNewDistrictNameButton;

    //text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/form/span")
    public WebElement successfulDistrictCreationText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr[1]/td[1]")
    public WebElement getSuccessfulNewDistrictNameText;

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

        buttonSaveDistrict.click();
    }

    public void clickFirstDistrictInTheListToEdit() {

        firstDistrictInTheListEditButton.click();
    }

    public void clickToSaveNewDistrictName() {

        saveNewDistrictNameButton.click();
    }

    public String findSuccessfulDistrictCreationText() {

        return successfulDistrictCreationText.getText();
    }
    public String findSuccessfulNewDistrictNameSaveText() {

        return getSuccessfulNewDistrictNameText.getText();
    }
}