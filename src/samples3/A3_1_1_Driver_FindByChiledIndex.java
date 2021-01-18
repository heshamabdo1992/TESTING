package samples3;

/* Find Element Mechanism */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class A3_1_1_Driver_FindByChiledIndex {

 public static WebDriver driver;
	
	@BeforeMethod									
	public void setup(){
	System.err.println("@BeforeMethod");
	driver= new ChromeDriver();						  
       
 }	

//::Reference:: ***********"check reference document in project"***********

	@Test											
	public static void FindElement_Approachs () throws Exception{
		System.err.println("@test:TestCase1");
		driver.get("https://html-cleaner.com/features/replace-html-table-tags-with-divs/");		

///========= Find Element by (XPath) ============================================
		
//::Note:: 
//Link have to be visible before use it. 	
		
		driver.findElement(By.xpath("//*[@class='rTableRow'][2]//*[@class='rTableCell'][3]"));
		
		
//========= Find Element by (CssSelector) ============================================

//::Note:: 
//Find element by CSS is faster than XPath.
		driver.findElement(By.cssSelector(".rTableRow:nth-child(2)>.rTableCell:nth-child(3)"));

}
	@AfterMethod									
	public static void TearDown(){
		System.err.println("@AfterMethod");
		driver.quit();
	}
}
