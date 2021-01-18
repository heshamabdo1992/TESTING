package samples2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class hoverfacebook {
    
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
    	 driver.get("https://www.facebook.com/login/device-based/regular/login/?login_attempt=1&lwv=100");
    	 
    	 // Type Name in the FirstName text box      
//    	    Actions act = new Actions(driver);
    	  WebElement email= driver.findElement(By.xpath("//*[@id=\"email\"]"));
    	  email.sendKeys("000");
//    	  WebElement pass=driver.findElement(By.xpath("//*[@id=\"pass\"]"));
    	  WebElement login=driver.findElement(By.xpath("//*[@id=\"loginbutton\"]"));
    	  login.click();
//    	  act.clickAndHold().moveToElement(email);
  	       Thread.sleep(3000);

//    	  act.moveToElement(email).build().perform(); 	

//    	   ToolTip1.clickAndHold(email).perform();
   	       Thread.sleep(1000);

    	   WebElement ToolTipText = driver.findElement(By.xpath("//*[@id=\"globalContainer\"]/div[3]/div/div"));
//     	  act.moveToElement(ToolTipText).build().perform(); 	
  	       Thread.sleep(1000); 
     	  Assert.assertTrue(ToolTipText.getText().contains("The email address or phone number that you've entered doesn't match any account. "));
    	    Thread.sleep(2000);
    	    System.out.println("Tooltip value is: " + ToolTipText.getText());
 
         	 Thread.sleep(10000);

        }
        
 
    
    @AfterMethod
		public void tearDown() {
    	System.out.println("After Method");
    	driver.quit();
    }
}