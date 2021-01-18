package samples3;

/* Find Element Mechanism */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FindByChiledIndex {

 public static WebDriver driver;
	
	@BeforeMethod									
	public void setup(){
	System.out.println("@BeforeMethod");
	driver= new ChromeDriver();						  
	 driver.manage().window().maximize();
 }	

//::Reference:: ***********"check reference document in project"***********

	@Test											
	public static void FindElement_Approachs () throws Exception{
		System.out.println("Run");
		driver.get("https://the-internet.herokuapp.com/tables");		

///========= Find Element by (XPath) ============================================
		
//::Note:: 
//Link have to be visible before use it. 	
		
//		driver.findElement(By.xpath("//*[@class='rTableRow'][2]//*[@class='rTableCell'][3]"));
		driver.findElement(By.xpath("//*[@id='table1']//tr[1]/td[2]"));

		
//========= Find Element by (CssSelector) ============================================

//::Note:: 
//Find element by CSS is faster than XPath.
//		driver.findElement(By.cssSelector(".rTableRow:nth-child(2)>.rTableCell:nth-child(3)"));
		driver.findElement(By.cssSelector("#table1  tr:nth-child(1) > td:nth-child(2)"));

		//next element
		driver.findElement(By.cssSelector("#table1  tr:nth-child(1)+tr > td:nth-child(2) +td"));
		//next element
		driver.findElement(By.cssSelector("#table1  tr:nth-child(1)+tr > td:nth-child(2) +td"));
		//first element
		//You can use “Tag:first-of-type”. It will select the first tag element.
		driver.findElement(By.cssSelector("#table1>tbody> tr:first-of-type td:first-of-type"));
		//first element 
		//You can use “*last-of-type”. It will select the last child of parent tag.
		driver.findElement(By.cssSelector("#table1>tbody> *:first-of-type td:first-of-type"));
		//last element
		//You can use “Tag:last-of-type”. It will select the last tag element.
		driver.findElement(By.cssSelector("#table1>tbody> tr:last-of-type td:last-of-type"));
}
	@AfterMethod									
	public static void TearDown(){
		System.err.println("@AfterMethod");
		driver.quit();
	}
}
