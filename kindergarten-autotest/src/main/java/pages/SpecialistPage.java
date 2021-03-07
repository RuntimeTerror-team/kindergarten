package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpecialistPage extends AbstractPage {

    // text
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div[1]/div/div[2]/p/strong")
    public WebElement specialistLoginText;

    // button
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div[1]/div/div[2]/button")
    public WebElement buttonSpecialistLogout;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div/div[2]/div/nav/a[2]")
	public  WebElement buttonAgeGroupCreation;

    public SpecialistPage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    public String findSpecialistLoginText() {
        return specialistLoginText.getText();
    }

    public void clickSpecialistLogoutButton() {
        buttonSpecialistLogout.click();
    }
public void clickAgeGroupCreationButton(){
    	buttonAgeGroupCreation.click();
}
}
