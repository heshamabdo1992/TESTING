package samples;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Send_parameter {
	
	 static WebDriver  driver;
	    
	    
	    @BeforeMethod
	    public void SetUp()   {
	          System.out.println("BeforeMethod");

		    driver = new ChromeDriver();  //for local check
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	    }
	    

	    @Test
	    @Parameters({"email1","pass1"})
	    public void Run(@Optional("Abc") String email1,String pass1){
	    	
	        System.out.println("Test");
	        System.out.println(email1+" "+pass1);

			driver.get("https://www.facebook.com/");		
			
			WebElement emailField = driver.findElement(By.id("email"));
			emailField.sendKeys(email1);
			
			WebElement passField=driver.findElement(By.id("pass")) ;
			passField.sendKeys(pass1);
			
			driver.findElement(By.id("loginbutton")).click();
	    
	    }
	    
	    @AfterMethod
	    public void closeFirefox(){
	        System.out.println("AfterMethod");

	        driver.quit();
	    }
	    
	    

}
