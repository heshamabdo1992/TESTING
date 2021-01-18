package careLogin;
import org.junit.After;
import org.junit.Before;
//import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.junit.DataProvider;

public class FirstTestNGScript {	
	WebDriver driver;
	WebDriverWait wait;
	//@DataProvider(name = "Accounts")
	
	/*public Object[][] Users() {

	String[][] Data = { { "sally.sedky@paxerahealth.com", "test1234" }};
	return Data;
	}*/

	@Before
	public void setup(){
		//System.setProperty("webdriver.chrome.driver", "D:\\Eclipse Run Automation\\Chrome\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
   	 	chromeOptions.addArguments("--headless");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30, 1000);
		driver.manage().window().maximize();
		driver.get("https://alb.carepassportqa.com/patients/Login.aspx");
	}

	@Test//(dataProvider = "Accounts")
	
	public void login_testcase() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id='txt_UserName']")).clear();
		driver.findElement(By.xpath("//*[@id='txt_UserName']")).sendKeys("sally.sedky@paxerahealth.com");
		driver.findElement(By.xpath("//*[@id='txt_Password_login']")).clear();
		driver.findElement(By.xpath("//*[@id='txt_Password_login']")).sendKeys("test1234");

		driver.findElement(By.xpath("//*[@id='LoginButton']")).click();
		Thread.sleep(3000);
		driver.quit();
//		String Phone = driver.findElement(By.xpath("//*[@id='tr_phone']/td[3]")).getText();
//		if (Phone == "201007373359")
//		{
//			System.err.println(Phone);
//			System.err.println("Test Case Success");
//		}
//		else 
//		{
//			System.err.println(Phone);
//			System.err.println("Test Case Failed");
//		}
//			
		
		//Assert.assertTrue(Phone.contains(""));
		//System.out.println("Test Case Success");
		
//		Thread.sleep(3000);
	}

	@After
	public void quit() {
		driver.quit();
	}
}
