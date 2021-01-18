package samples5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class data_provider_using_fixed_values {
	 public static WebDriver driver;
		
		@BeforeMethod									
		public void setup(){
		System.out.println("@BeforeMethod");
		driver= new ChromeDriver();
       driver.manage().window().maximize();
	       
	 }	
		  @DataProvider(name = "login") 
		  public static Object[][] credentials() {
		 
		        return new Object[][] { { "nada", "Nada1234" }, { "usdemo", "Usdemo1234" }};
		 
		  }
		  
		@Test(dataProvider = "login")
		public static void data_provider_run (String sUsername, String sPassword) throws Exception{
			System.out.println("@test:TestCase1");

			driver.get("https://paxerahealth.us/pxadmin");	
	        
	        WebElement username_field=driver.findElement(By.xpath("//*[@id=\"txt_UserName\"]"));
	        
	        WebElement password_field=driver.findElement(By.xpath("//*[@id=\"txt_Password\"]"));

	        WebElement login_btn=driver.findElement(By.xpath("//*[@id=\"btn_Login\"]"));
	        username_field.clear();
	        password_field.clear();
	        username_field.sendKeys(sUsername);
	        password_field.sendKeys(sPassword);

	        login_btn.click();
	       // Thread.sleep(2000);

	        WebElement profile=driver.findElement(By.xpath("//*[@id=\"lbl_admin_FullName\"]"));
	        Assert.assertTrue(profile.isDisplayed());
	       // Assert.assertEquals("Lakshay Sharma", sValue);
	       // Assert.assertTrue(profile.getText().equalsIgnoreCase("nada"));;
		//   Assert.assertTrue(profile.getText().contains("nada"));;
	        System.out.println(profile.getText());
	       // Thread.sleep(3000);
		}
		
		
		
		@AfterMethod									
		public static void TearDown(){
			System.out.println("@AfterMethod");
			driver.quit();
		}
}
