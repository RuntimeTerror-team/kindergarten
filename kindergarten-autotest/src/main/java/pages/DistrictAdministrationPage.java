package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DistrictAdministrationPage extends AbstractPage {

    // input
    @FindBy(id = "inputDistrict")
    public WebElement inputDistrictName;


    // buttons
    @FindBy(xpath = "//*[@id=\"districtAdministrationComponent\"]/form/div[1]/div/button")
    public WebElement buttonAddDistrict;

    //text
    @FindBy(xpath = "//*[@id=\"districtAdministrationComponent\"]/div[1]/span")
    public WebElement succesfulDistrictCreationText;


    public DistrictAdministrationPage(WebDriver driver) {
        super(driver);

    }

    public void enterDistrictName(String districtName) {
        inputDistrictName.sendKeys(districtName);
    }


    public void clickAddDistrictButton() {
        buttonAddDistrict.click();

    }

    public String findSuccesfulDistrictCreationText() {
        return succesfulDistrictCreationText.getText();
    }
}
