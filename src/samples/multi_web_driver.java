package samples;

//import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class multi_web_driver {
    
	public static WebDriver driver;
    WebDriverWait wait;

   
   
    @BeforeMethod
	@Parameters("browser")  
	public void setup(String browser) throws Exception{
    	browser="chrome";
		//Check if parameter passed from TestNG is 'firefox'
		if(browser.equalsIgnoreCase("firefox")){
		//create firefox instance
			//System.setProperty("webdriver.gecko.driver", "C:\\webdriver\\geckodriver.exe");
	    	System.out.println("Before Method - firefox");
			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();
		}
		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
			//set path to chromedriver.exe
//			System.setProperty("webdriver.chrome.driver",".\\chromedriver.exe");
			//create chrome instance
	    	System.out.println("Before Method - chrome");
			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();
		}
		//Check if parameter passed as 'Edge'
				else if(browser.equalsIgnoreCase("InternetExplorer")){
					//set path to Edge.exe
//					System.setProperty("webdriver.ie.driver", "C:\\webdriver\\IEDriverServer.exe");
					//create Edge instance
			    	System.out.println("Before Method - InternetExplorer");
					WebDriverManager.iedriver().setup();

					driver = new InternetExplorerDriver();
				}
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
	//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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