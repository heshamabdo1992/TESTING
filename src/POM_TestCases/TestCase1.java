package POM_TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCase1 {

    static WebDriver driver;
    POM_Admin_Pages.LoginPage objLogin;

    POM_Admin_Pages.HomePage objHomePage;
    
    @BeforeMethod
    public void setup(){
    	System.out.println("@BeforeMethod");
    	driver= new ChromeDriver();						  
    	driver.manage().window().maximize();
	    driver.get("http://192.168.5.179/pxadmin");
	    }
	 
	 @Test
	    public void testcase1(){
	     //Create Login Page object
		 objLogin=new POM_Admin_Pages.LoginPage(driver);
		 objLogin.Login("Admintest", "Admin1234");
		 
	     //Create HomePage Page object
		 objHomePage=new POM_Admin_Pages.HomePage(driver);
		//Verify home page
		System.out.println(objHomePage.GetFullUserName());
		objHomePage.Customerbtn_click();
	 }
	//=========================================================
	// Calculator using DDT test frame work"Data Driven test" 
	//>>Use the same method using data provider<<
	//=========================================================
		
	// ==== Create TestNG (DataProvider)=====================
	 @DataProvider (name= "My data provider")
		public Object[][] dataProvider(){
			String [][] data={
					{"AdminTest","Admin1234","AdminTest"},
					{"AdminT","Admin1234","AdminT"},
					};
			return data;
		}
	// ==== Fill data using TestNG (DataProvider)============	
	 @Test(dataProvider= "My data provider")
	    public void testcase2(String username,String Password,String Fullname){
	     //Create Login Page object
		 objLogin=new POM_Admin_Pages.LoginPage(driver);
		 objLogin.Login(username, Password);
		 
	     //Create HomePage Page object
		 objHomePage=new POM_Admin_Pages.HomePage(driver);
		 
		//Verify user name
		 objHomePage.assertusername(Fullname);
		System.out.println(objHomePage.GetFullUserName());
		objHomePage.Customerbtn_click();
	 }
	 

	 
		@AfterMethod									
		public static void TearDown(){
			System.err.println("@AfterMethod");
			driver.quit();
		}
	 
}
