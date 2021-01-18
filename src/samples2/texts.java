package samples2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class texts {
    
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
    	 driver.get("http://toolsqa.wpengine.com/Automation-practice-form/");
    	 
    	 // Type Name in the FirstName text box      
    	 
    	 WebElement fname= driver.findElement(By.name("firstname"));
    	 fname.clear(); 
    	 fname.sendKeys("Hesham"); 
    	 
    	 //Type LastName in the LastName text box

    	 WebElement lname= driver.findElement(By.name("lastname"));
    	 lname.clear();
    	 lname.sendKeys("Abdo"); 

    	 
    	 // Click on the Submit button
    	 WebElement submitbtk =driver.findElement(By.id("submit"));
    	 submitbtk.click();
    	 Thread.sleep(10000);

        }
        
 
    
    @AfterMethod
		public void tearDown() {
    	System.out.println("After Method");
    	driver.quit();
    }
}