package sample_test;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class profile_chrome {
	 public static WebDriver driver;
		
		@BeforeMethod									
		public void setup(){
		System.out.println("@BeforeMethod");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--user-data-dir=C:\\Users\\Hesham\\AppData\\Local\\Google\\Chrome\\User Data");
		//options.addArguments("chrome.switches", "--disable-extensions");
		//options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		options.addArguments("--start-maximized");
		driver= new ChromeDriver(options);
     //driver.manage().window().maximize();
	       
	 }	

		@Test
		public static void profile_chrome_run () throws Exception{
			System.out.println("@test:TestCase1");

			driver.get("https://accounts.google.com/signin");
			
		
	        Thread.sleep(5000);
		}
		
		
		
		@AfterMethod									
		public static void TearDown(){
			System.out.println("@AfterMethod");
		//	driver.quit();
		}
}
