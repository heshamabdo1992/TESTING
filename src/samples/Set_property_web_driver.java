package samples;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Set_property_web_driver {
    
	public static WebDriver driver;
    WebDriverWait wait;
	
    @BeforeMethod

	public void setup() throws Exception{


		//create firefox instance
//			System.setProperty("webdriver.gecko.driver", "C:\\Firefox\\geckodriver.exe");
	    	System.out.println("Before Method - firefox");
			driver = new FirefoxDriver();
	        driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test    
    public void Run()throws InterruptedException{
    	System.out.println("Test");
		driver.get("https://www.facebook.com/");		
		
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("01004827411");
		
		WebElement passField=driver.findElement(By.id("pass")) ;
		passField.sendKeys("01004827411");
		
		driver.findElement(By.id("loginbutton")).click();
            
        }
        
 
    
    @AfterMethod
		public void tearDown() {
    	System.out.println("After Method");
    	driver.quit();
    }
}