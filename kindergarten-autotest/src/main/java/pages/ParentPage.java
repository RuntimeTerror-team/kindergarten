package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ParentPage extends AbstractPage {

	// text
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div[2]/div/div/h1")
	public WebElement parentpageMainpageTitle;

	@FindBy (xpath = "//p[@class='lead']/strong")
	public WebElement getParentName;
	// input
//	@FindBy(id = "firstname")
//	public WebElement inputFirstName;
//
//	@FindBy(id = "lastname")
//	public WebElement inputLastName;
//
//	@FindBy(id = "personalCode")
//	public WebElement inputPersonalCode;
//	@FindBy(id = "address")
//	public WebElement inputAddress;
//	@FindBy(id = "postalCode")
//	public WebElement inputPostalCode;
//	@FindBy(id = "phoneNo")
//	public WebElement inputPhoneNo;
//	@FindBy(id = "email")
//	public WebElement inputEmail;

// button
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/div/div[2]/button")
	public WebElement buttonParentLogout;

	public ParentPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String findParentpageMainpageTitle() {
		return parentpageMainpageTitle.getText();
	}
	public String findParentName() {
		return getParentName.getText();
	}

//	public void enterFirstName(String firstName) {
//		inputFirstName.sendKeys(firstName);
//	}
//
//	public void enterLastName(String lastName) {
//		inputLastName.sendKeys(lastName);
//	}
//
//	public void enterPersonalCode(String personalCode) {
//		inputPersonalCode.sendKeys(personalCode);
//	}
//	public void enterAddress(String address) {
//		inputAddress.sendKeys(address);
//	}
//	public void enterPostalCode(String postalCode) {
//		inputPostalCode.sendKeys(postalCode);
//	}
//	public void enterPhoneNo(String phoneNo) {
//		inputPhoneNo.sendKeys(phoneNo);
//	}
//	public void enterEmail(String email) {
//		inputEmail.sendKeys(email);
//	}
//
	public void clickParentLogoutButton() {
		buttonParentLogout.click();
	}
}
