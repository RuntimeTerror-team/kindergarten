package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KindergartenGroupsListPage extends AbstractPage {

    // input
    @FindBy(xpath = "//*[@id=\"childrenCount\"]")
    public WebElement inputChildrenCount;
    // buttons
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/a[1]")
    public WebElement buttonGroupCreate;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/a[2]")
    public WebElement buttonBackToKindergartenList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[5]/button[1]")
    public WebElement buttonBackToGroupList;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr/td[3]/a")
    public WebElement buttonChangeAmount;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[5]/button[2]")
    public WebElement buttonGroupSave;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/div/table/tbody/tr[1]/td[3]/a")
    public WebElement buttonAddChildrenToGroup;

    //text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[3]")
    public WebElement successfulGroupCreationText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[4]")
    public WebElement unsuccessfulSameGroupCreationText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")
    public WebElement groupListText;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")
    public WebElement createGroupText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/form/div[3]")
    public WebElement successfulChildrenAmountUpdateText;

    public KindergartenGroupsListPage(WebDriver driver) {
        super(driver);

    }

    public void enterChildrenAmount(String childrenAmount) {

        inputChildrenCount.sendKeys(childrenAmount);
    }

    public void clickGroupCreateButton() {

        buttonGroupCreate.click();
    }

    public void clickBackToKindergartenListButton() {

        buttonBackToKindergartenList.click();
    }

    public void clickBackToGroupListButton() {

        buttonBackToGroupList.click();
    }

    public void clickChangeAmountButton() {

        buttonChangeAmount.click();

    }

    public void clickGroupSaveButton() {

        buttonGroupSave.click();
    }

    public void clickAddChildrenToGroupButton() {

        buttonAddChildrenToGroup.click();
    }

    public void clearChildrenAmount() {

        inputChildrenCount.clear();
    }

    public String findSuccessfulGroupCreationText() {

        return successfulGroupCreationText.getText();
    }

    public String findUnsuccessfulSameGroupCreationText() {

        return unsuccessfulSameGroupCreationText.getText();
    }

    public String findSuccessfulChildrenAmountUpdateText() {

        return successfulChildrenAmountUpdateText.getText();
    }

}