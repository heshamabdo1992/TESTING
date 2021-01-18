package samples4;

import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;


public class Alerts {
	 public static WebDriver driver;
		
		@BeforeMethod									
		public void setup(){
		System.out.println("@BeforeMethod");
		driver= new ChromeDriver();
        driver.manage().window().maximize();
	       
	 }	
		 private static String urlWithAccount(String url) {
		     String username = "admin";
		     String password = "admin";

		     String newUrl = url.replace("https://", 
		                                 "https://" + 
		                                 username + 
		                                 ":" + 
		                                 password + 
		                                 "@");

		     return newUrl;
		  }
		
		//////////sample for windows authentication
//		@Test
		public static void Alerts_testrun2 () throws Exception{
			System.out.println("@test:TestCase2");

		//	driver.get("https://the-internet.herokuapp.com/basic_auth");	
			
			driver.get(urlWithAccount("https://the-internet.herokuapp.com/basic_auth"));
			
			//Windows Auth popup
			// Switching to Alert        
	       // Alert alertt = driver.switchTo().alert();
	        //driver.findElement(By.partialLinkText("name")).sendKeys("admin");
	       // driver.findElement(By.partialLinkText("Password")).sendKeys("admin");

	     //   alertt.accept();
	     //   driver.switchTo().defaultContent();
	        
	        WebElement success=driver.findElement(By.xpath("//*[@id=\"content\"]/div/p"));
	        System.out.println(success.getText());
	        Thread.sleep(5000);
		}
		
		
		/////////////sample for Alerts
		@Test
		public static void Alerts_testrun () throws Exception{
			System.out.println("@test:TestCase1");
			//driver.get("https://the-internet.herokuapp.com/basic_auth");	
			driver.get("https://the-internet.herokuapp.com/javascript_alerts");	

			///========= Find Element by (XPath) ============================================		
			WebElement JS_Alert=driver.findElement(By.xpath("//*[@id=\"content\"]/div//li[1]/button"));
			WebElement JS_confirm=driver.findElement(By.xpath("//*[@id=\"content\"]/div//li[2]/button"));
			WebElement JS_prompt=driver.findElement(By.xpath("//*[@id=\"content\"]/div//li[3]/button"));
			WebElement Result= driver.findElement(By.xpath("//*[@id=\"result\"]"));
			
			//Test java Alert message
			JS_Alert.click();
			// Switching to Alert        
	        Alert alert = driver.switchTo().alert();	
	     // Capturing alert message.    
	        String alertMessage= driver.switchTo().alert().getText();
	        Assert.assertEquals(alertMessage, "I am a JS Alert");
	     // Displaying alert message		
	        System.out.println(alertMessage);	
	        Thread.sleep(2000);	
	        // Accepting alert		
	        alert.accept();	
	        System.out.println(Result.getText());
	        
	        
	        ///////////////////////////////////////////////////////////////////////////
			//Test java Alert confirm
	        JS_confirm.click();
			// Switching to Alert        
	        Alert alert2 = driver.switchTo().alert();	
	     // Capturing alert message.    
	        String alertMessage2= driver.switchTo().alert().getText();
	        Assert.assertEquals(alertMessage2, "I am a JS Confirm");
	     // Displaying alert message		
	        System.out.println(alertMessage2);	
	        Thread.sleep(2000);	
	        // Accepting alert		
	        alert2.accept();
	        System.out.println(Result.getText());

	        JS_confirm.click();
	        driver.switchTo().alert();
		     // Capturing alert message.    
	        alertMessage2= driver.switchTo().alert().getText();
	        Assert.assertEquals(alertMessage2, "I am a JS Confirm");
		     // Displaying alert message		
	        System.out.println(alertMessage2);
	        Thread.sleep(2000);
	        // Cancel alert		
	        alert2.dismiss();
	        System.out.println(Result.getText());
///////////////////////////////////////////////////////////////////////////////////////

	        
			//Test java Alert prompt
	        JS_prompt.click();
			// Switching to Alert        
	        Alert alert3 = driver.switchTo().alert();	
	     // Capturing alert message.    
	        String alertMessage3= driver.switchTo().alert().getText();
	        Assert.assertEquals(alertMessage3, "I am a JS prompt");
	     // Displaying alert message		
	        System.out.println(alertMessage3);	
	        Thread.sleep(2000);
	        //Sendkeys
	        alert3.sendKeys("TESTING");
	        // Accepting alert		
	        alert3.accept();	
	        System.out.println(Result.getText());

	        JS_prompt.click();
	        driver.switchTo().alert();
		     // Capturing alert message.    
	        alertMessage3= driver.switchTo().alert().getText();
	        Assert.assertEquals(alertMessage3, "I am a JS prompt");
	        // Displaying alert message		
	        System.out.println(alertMessage3);
	        Thread.sleep(2000);
	        //Sendkeys
	        alert3.sendKeys("TESTING");
	        // Cancel alert		
	        alert3.dismiss();
	        System.out.println(Result.getText());

	        
	        
	        
		}
		
		@AfterMethod									
		public static void TearDown(){
			System.out.println("@AfterMethod");
			driver.quit();
		}

}
