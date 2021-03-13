package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EducationSpecialistPage extends AbstractPage {

    // text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[2]/p/strong")
    public WebElement specialistLoginText;

    // button
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[1]/div/nav/ul/li[1]/button")
    public WebElement buttonSpecialistLogout;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/nav/a[2]")
    public WebElement buttonAgeGroupCreation;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/nav/a[1]")
    public WebElement buttonKindergartenList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a[1]")
    public WebElement buttonKindergartenPreview;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a[2]")
    public WebElement buttonKindergartenGroups;

    public EducationSpecialistPage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    public String findSpecialistLoginText() {

        return specialistLoginText.getText();
    }

    public void clickSpecialistLogoutButton() {

        buttonSpecialistLogout.click();
    }

    public void clickAgeGroupCreationButton() {

        buttonAgeGroupCreation.click();
    }

    public void clickKindergartenListButton() {
        buttonKindergartenList.click();
    }
    public void clickKindergartenPreviewButton() {
        buttonKindergartenPreview.click();
    }
    public void clickKindergartenGroupsButton() {
        buttonKindergartenGroups.click();
    }
}

