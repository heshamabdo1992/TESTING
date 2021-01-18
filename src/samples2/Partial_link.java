package samples2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Partial_link {
    
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
    	 driver.get("http://toolsqa.com/Automation-practice-form/");
    	 
    	 // Click on "Partial Link Text" link
    	 WebElement Partiallink= driver.findElement(By.partialLinkText("Partial"));
    	 Partiallink.click();
    	 System.out.println("Partial Link Test Pass");
    	 
//    	 // Convert element in to a string 
//    	 String sClass = driver.findElements(By.tagName("button")).toString();
//    	 System.out.println(sClass);
    	 
    	 // Click on "Link Text" link
    	 WebElement linktext=driver.findElement(By.linkText("Link Test"));
    	 linktext.click();
    	 System.out.println("Link Test Pass");
    	 Thread.sleep(10000);

        }
        
 
    
    @AfterMethod
		public void tearDown() {
    	System.out.println("After Method");
    	driver.quit();
    }
}