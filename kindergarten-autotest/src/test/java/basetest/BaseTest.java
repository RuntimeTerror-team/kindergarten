package basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {
	protected static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass()  {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("http://akademijait.vtmc.lt:8181/kindergarten/");
	}


	@AfterClass
	public static void tearDown() {
		driver.manage().deleteAllCookies();
		// driver.close();
	}

}
