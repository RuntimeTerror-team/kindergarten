package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EducationSpecialistApplicationListPage extends AbstractPage {

    // text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")
    public WebElement applicationListTitleText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/h6")
    public WebElement warningWhenQueueIsActiveText;

    // button
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/div[2]/button")
    public WebElement buttonApplicationsResort;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/div[1]/form/button")
    public WebElement buttonSearch;
    @FindBy(xpath = "/html/body/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr[1]/td[9]/button")
    public WebElement buttonHealthFormFileDownload;
    //atsaukti prasyma

//    @FindBy(xpath = "/html/body/div/div/div/div[2]/div[2]/div/div[1]/div/table/tbody/tr/td[5]/button")
//    public WebElement buttonQueueChange;


    // input
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div[1]/div[1]/form/input")
    public WebElement inputSearchField;

    public EducationSpecialistApplicationListPage(WebDriver driver) {
        super(driver);
    }

    // text
    public String findApplicationListTitleText() {
        return applicationListTitleText.getText();
    }

    public String findWarningWhenQueueIsActiveText() {
        return warningWhenQueueIsActiveText.getText();
    }


    // button
    public void clickApplicationResortButton() {
        buttonApplicationsResort.click();
    }

    public void clickSearchButton() {
        buttonSearch.click();
    }

    public void clickHealthFormFileDownloadButton() {
        buttonHealthFormFileDownload.click();
    }

    // input
    public void enterSearchInput(String input) {
        inputSearchField.sendKeys(input);
    }


}

