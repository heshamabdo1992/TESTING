package POM_TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CPTESTCASE {
	
	static WebDriver driver;
	POM_CPPatientPortalPages.LoginPatientPortalPage objLogin;
	
	@BeforeMethod
    public void setup(){
    	System.err.println("@BeforeMethod");
    	driver= new ChromeDriver();						  
    	driver.manage().window().maximize();
	    driver.get("https://carepassport.net/patients");
	    }
	
	// ==== Create TestNG (DataProvider)=====================
		 @DataProvider (name= "My data provider")
			public Object[][] dataProvider(){
				String [][] data={
						{"heshamabdo7007@gmail.com","Test1234","N"}
						,
						{"spider_zee200@yahoo.com","01004827411qwer","F"}
						,
						{"heshamabdo7007@gmail.com","123456789asdf","G"}
						};
				return data;
			}
		// ==== Fill data using TestNG (DataProvider)============	
		 @Test(dataProvider= "My data provider")
		    public void testcase2(String username,String Password,String type) throws InterruptedException{
		     //Create Login Page object
			 objLogin=new POM_CPPatientPortalPages.LoginPatientPortalPage(driver);
			 objLogin.Login(username, Password, type);

		 }
	
	@AfterMethod									
	public static void TearDown(){
		System.err.println("@AfterMethod");
		driver.quit();
	}
}
