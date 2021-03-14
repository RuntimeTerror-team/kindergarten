package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentApplicationsPage extends AbstractPage {

    // text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/h1/strong")
    public WebElement applicationsTitleText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[1]")
    public WebElement firstApplicationChildNameText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/table/tbody/tr[2]/td[1]")
    public WebElement secondApplicationChildNameText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/table/tbody/tr[3]/td[1]")
    public WebElement thirdApplicationChildNameText;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[3]")
    public WebElement firstChildApplicationStatusText;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/table/tbody/tr[2]/td[3]")
    public WebElement secondChildApplicationStatusText;
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/table/tbody/tr[3]/td[3]")
    public WebElement thirdChildApplicationStatusText;

    // button

    // input



    public ParentApplicationsPage(WebDriver driver) {
        super(driver);
    }

    // text
    public String findApplicationsTitleText() {
        return applicationsTitleText.getText();
    }

    public String findFirstApplicationChildNameText() {
        return firstApplicationChildNameText.getText();
    }
    public String findSecondApplicationChildNameText() {
        return secondApplicationChildNameText.getText();
            }
    public String findThirdApplicationChildNameText() {
        return thirdApplicationChildNameText.getText();
    }

    public String findFirstChildApplicationStatusText() {
        return firstChildApplicationStatusText.getText();
    }
    public String findSecondChildApplicationStatusText() {
        return secondChildApplicationStatusText.getText();
    }
    public String findThirdChildApplicationStatusText() {
        return thirdChildApplicationStatusText.getText();
    }
    // button


    // input

}

