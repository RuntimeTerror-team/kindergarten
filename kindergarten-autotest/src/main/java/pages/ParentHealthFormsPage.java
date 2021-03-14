package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentHealthFormsPage extends AbstractPage {

    // text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")
    public WebElement healthFormsTitleText;

    @FindBy (xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div[2]")
    public WebElement warningThereAreNoHealthFormsUploaded;

    // button
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[3]/button")
    public WebElement buttonHealthFormSave;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div[1]/div/div[2]/form/div[1]/span/label")
    public WebElement buttonSearchToUpload;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr/td[4]/button")
    public WebElement buttonFileDownloadToPC;

    // input



    public ParentHealthFormsPage(WebDriver driver) {
        super(driver);
    }

    // text
    public String findHealthFormsTitleText() {
        return healthFormsTitleText.getText();
    }

    public String findWarningThereAreNoHealthFormsUploadedText() {
        return warningThereAreNoHealthFormsUploaded.getText();
    }

    // button
    public void clickHealthFormSaveButton() {
        buttonHealthFormSave.click();
    }

    public void clickSearchToUploadButton() {
        buttonSearchToUpload.click();
    }

    public void clickFileDownloadToPCButton() {
        buttonFileDownloadToPC.click();
    }


    // input

}

