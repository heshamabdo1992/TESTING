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

public class checkbox {
    
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
		
		// STep 5: Check the Check Box 'Automation Tester' for category 'Profession'( Use Value attribute to match the selection)
				// Find the Check Box or radio button element by Name
				List<WebElement> chkBx_Profession = driver.findElements(By.name("profession"));
				
				// This will tell you the number of Check Boxes are present
				int iSize = chkBx_Profession.size();
				
				// Start the loop from first Check Box to last Check Box
				for(int i=0; i < iSize ; i++ )
				{
					// Store the Check Box name to the string variable, using 'Value' attribute
					String sValue = chkBx_Profession.get(i).getAttribute("value");
					
					// Select the Check Box it the value of the Check Box is same what you are looking for
//					if (sValue.equalsIgnoreCase("Automation Tester")){
						chkBx_Profession.get(i).click();
						System.out.println(sValue);
					// This will take the execution out of for loop
//					break;
//					}
				}
				// Step 6: Check the Check Box 'Selenium IDE' for category 'Automation Tool' (Use cssSelector)
				WebElement oCheck_Box = driver.findElement(By.cssSelector("input[value='Selenium IDE']"));
				oCheck_Box.click();
//				System.out.println(oCheck_Box.getText());

                Thread.sleep(10000);

        }
        
 
    
    @AfterMethod
		public void tearDown() {
    	System.out.println("After Method");
    	driver.quit();
    }
}