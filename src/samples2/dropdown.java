package samples2;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class dropdown {
    
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
		driver.get("http://toolsqa.com/automation-practice-form/");		
		
		Select dropdown =new Select( driver.findElement(By.xpath("//*[@id=\"continents\"]")));
        List<WebElement> List=dropdown.getOptions();
        
        
		System.out.println("continents selector is "+dropdown.isMultiple());
		dropdown.selectByIndex(1);
//		String selected=dropdown.selectByIndex(2);
		 System.out.println(List.get(0).getText());
		Thread.sleep(5000);
//		dropdown.selectByValue("Asia");
//		System.out.println(List.get(1).getText());
//		Thread.sleep(5000);
		dropdown.selectByVisibleText("Europe");
		System.out.println(List.get(1).getText());
		Thread.sleep(5000);
		
		
		Select dropdown2 =new Select( driver.findElement(By.xpath("//*[@id=\"selenium_commands\"]")));
        List<WebElement> List2=dropdown2.getOptions();
		System.out.println("selenium_commands selector is "+dropdown.isMultiple());
        //multiple select
		dropdown2.selectByIndex(1);
		System.out.println("select "+List2.get(0).getText());
		Thread.sleep(5000);
//		dropdown2.selectByValue("Browser Commands");
//		System.out.println(List2.get(1).getText());
//		Thread.sleep(5000);
		dropdown2.selectByVisibleText("Switch Commands");
		System.out.println("select "+List2.get(2).getText());
		Thread.sleep(5000);
		
		//deselect 
		dropdown2.selectByIndex(1);
		System.out.println("deselect "+List2.get(0).getText());
		Thread.sleep(5000);
//		dropdown2.selectByValue("Browser Commands");
//		System.out.println(List2.get(1).getText());
//		Thread.sleep(5000);
		dropdown2.selectByVisibleText("Switch Commands");
		System.out.println("deselect "+List2.get(2).getText());
		Thread.sleep(5000);

            
        }
        
 
    
    @AfterMethod
		public void tearDown() {
    	System.out.println("After Method");
    	driver.quit();
    }
}