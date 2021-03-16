package basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public abstract class BaseTest {
	protected static WebDriver driver;


	@BeforeTest(alwaysRun = true)
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
	}

	@BeforeMethod(alwaysRun = true)
	public static void setUpBeforeClass()  {


		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("http://akademijait.vtmc.lt:8181/kindergarten/");
	}


	@AfterMethod(alwaysRun = true)
	public static void tearDown() {
		driver.manage().deleteAllCookies();
		// driver.close();
	}

}
