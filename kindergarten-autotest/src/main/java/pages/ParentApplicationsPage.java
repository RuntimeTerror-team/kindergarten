package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentApplicationsPage extends AbstractPage {

    // text

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[1]")
    public WebElement firstApplicationChildNameText;

     @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div[2]/div/div/table/tbody/tr[1]/td[3]")
    public WebElement firstChildApplicationStatusText;


    public ParentApplicationsPage(WebDriver driver) {
        super(driver);
    }

    // text


    public String findFirstApplicationChildNameText() {
        return firstApplicationChildNameText.getText();
    }

    public String findFirstChildApplicationStatusText() {
        return firstChildApplicationStatusText.getText();
    }


}

