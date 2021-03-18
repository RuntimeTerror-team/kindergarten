package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentHealthFormsPage extends AbstractPage {

    // text

    @FindBy(xpath = "/html/body/div/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr/td[3]")
    public WebElement healthFormFileName;

    // button
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/button")
    public WebElement buttonHealthFormSave;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr/td[4]/button")
    public WebElement buttonFileDownloadToPC;


    public ParentHealthFormsPage(WebDriver driver) {
        super(driver);
    }


//text

    public String findHealthFormFileName() {
        return healthFormFileName.getText();
    }

    // button
    public void clickHealthFormSaveButton() {
        buttonHealthFormSave.click();
    }


    public void clickFileDownloadToPCButton() {
        buttonFileDownloadToPC.click();
    }


}

