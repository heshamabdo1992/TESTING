package samples2;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class radio {
    
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
		
		List<WebElement> rdBtn_Sex = driver.findElements(By.name("sex"));
		boolean bValue = false;
		bValue = rdBtn_Sex.get(0).isSelected();
				if(bValue == true){
					rdBtn_Sex.get(1).click();
					System.out.println(rdBtn_Sex.get(1).getAttribute("value"));

				}else{
					rdBtn_Sex.get(0).click();
					
					System.out.println(rdBtn_Sex.get(0).getAttribute("value"));

				}
				
				WebElement rdBtn_Exp = driver.findElement(By.id("exp-3"));
				rdBtn_Exp.click();
				System.out.println(rdBtn_Exp.getAttribute("value"));
                Thread.sleep(10000);
            
        }
        
 
    
    @AfterMethod
		public void tearDown() {
    	System.out.println("After Method");
    	driver.quit();
    }
}