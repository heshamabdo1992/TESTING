package samples2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class hover2 {
    
	public static WebDriver driver;
    WebDriverWait wait;
	
    @BeforeMethod

	public void setup() throws Exception{

	    	System.out.println("Before Method - Chrome");
			driver = new ChromeDriver();
	        driver.manage().window().maximize();

	}
	
	@Test    
    public void Run()throws InterruptedException{
    	System.out.println("Test");
    	 driver.get("https://the-internet.herokuapp.com/hovers");
    	 
    	 // Type Name in the FirstName text box      
    	  WebElement menu= driver.findElement(By.xpath("//*[@id=\"menu\"]"));
    	  WebElement enable= driver.findElement(By.xpath("//*[@id=\"ui-id-4\"]/a"));
    	  WebElement PDF = driver.findElement(By.xpath("//*[@id=\"ui-id-5\"]"));
          menu.click();
          enable.click();
          PDF.click();
   	     Thread.sleep(1000);
System.out.println(PDF.getText());
    	 
    	    Assert.assertTrue(PDF.getText().contains(""));
    	    Thread.sleep(2000);

        }
        
 
    
    @AfterMethod
		public void tearDown() {
    	System.out.println("After Method");
    	driver.quit();
    }
}