package samples2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class hover {
    
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
    	    Actions action = new Actions(driver);
    	    WebElement photo1= driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/img"));
    	  
    	   action.clickAndHold(photo1).perform();
   	       Thread.sleep(1000);

    	   WebElement ToolTipText = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/h5"));
    	    Assert.assertEquals(ToolTipText.getText(), "name: user1");
    	    System.out.println("Tooltip value is: " + ToolTipText.getText());
    	  
       	   WebElement link = driver.findElement(By.xpath("//*[@id=\"content\"]//div[1]/div/a"));
   	       Assert.assertEquals(link.getAttribute("href"), "https://the-internet.herokuapp.com/users/1");
   	       System.out.println("link value is: " + link.getAttribute("href"));
    	    
    	 //Type LastName in the LastName text box

//    	 WebElement lname= driver.findElement(By.name("lastname"));
//    	 lname.clear();
//    	 lname.sendKeys("Abdo"); 
//
//    	 
//    	 // Click on the Submit button
//    	 WebElement submitbtk =driver.findElement(By.id("submit"));
//    	 submitbtk.click();
//    	 Thread.sleep(10000);

        }
        
 
    
    @AfterMethod
		public void tearDown() {
    	System.out.println("After Method");
    	driver.quit();
    }
}