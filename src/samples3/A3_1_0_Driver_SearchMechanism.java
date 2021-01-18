package samples3;

/* Find Element Mechanism */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class A3_1_0_Driver_SearchMechanism {

 public static WebDriver driver;
	
	@BeforeMethod									
	public void setup(){
	System.err.println("@BeforeMethod");
	driver= new ChromeDriver();						  
       
 }	

//::Reference:: ***********"check reference document in project"***********

/*::Note::
 * 1- We must to get search result (1 of 1) when check find element value in web page.
 * 2- Mind set (1- find by unified attribute, 2- find using its root directory).
 * 
 * #### Find element using attribute "value" -is a visible value- Not valid to test all WebPage multi-Language ####
*/	
	@Test											
	public static void FindElement_Approachs () throws Exception{
		System.err.println("@test:TestCase1");
		driver.get("https://www.facebook.com/");		
//========== Find Element by (ID) ==============================================
		//  
		driver.findElement(By.id("email")).sendKeys("ahmed_ocp1@hotmail.com");
		
//========= Find Element by (Name) =============================================

		driver.findElement(By.name("pass")).sendKeys("123456789");	 			 		
		
//========= Find Element by (XPath) ============================================
		
//::Note:: 
//Link have to be visible before use it. 	
		
		Thread.sleep(1000);
		
		//XPATH structure: driver.findElement(By.xpath("xpath[tag vale]+/+xpath[tag vale]+/+xpath[tag vale]"));
		driver.findElement(By.xpath("//html[@id='facebook']//label[@id='loginbutton']/input[@value='Log In']")).click();
		// = to the following
		driver.findElement(By.xpath("//html[@id='facebook']//label[@id='loginbutton']/input[@id='u_0_2']")).click();

	//It = to following: USING PATH shortcut "//"
		driver.findElement(By.xpath("//input[@value='Log In']")).click();     
		driver.findElement(By.xpath("//html[@id='facebook']//input[@id='u_0_2']")).click();
		
	//It = to following: USING TYPE shortcut "*"		
		driver.findElement(By.xpath("//*[@value='Log In']")).click();

	//When ***not attribute "VALUE"*** USING shortcut "." for visible value
		driver.findElement(By.xpath("//a[.='Log In']")).click();
			
	//XPath contain both of 2 tags value
		driver.findElement(By.xpath("//input[@value='Log In'and @type='submit']")).click();
		
	//XPath contain 1 of 2 tags value
		driver.findElement(By.xpath("//input[@value='Log In' or @type='submit']")).click();
//::Note:: Can be used For multi-Language ** Need check syntax.		
//		driver.findElement(By.xpath("//input[@value='Log In' or @value='ÏÎæá']")).click();	
	
		// === Performing Partial Match ====
		//Find element START WITH 'u'       ******* some times not work **********
		driver.findElement(By.xpath("//input[starts-with(@value,'Log')]")).click(); 

		//Find element CONTAIN '0'
									//Type[contains(@attribute,'value')]
		driver.findElement(By.xpath("//input[contains(@tabindex,'4')]")).click(); 
		
		
		//Find element END WITH '2' 		******* not work **********
//		driver.findElement(By.xpath("//input[ends-with(@value,'In')]")).click();
		
		
//========= Find Element by (CssSelector) ============================================

/*::Note:: 
 * 1- Find element by CSS is faster than XPath. 
*/		
		Thread.sleep(2000);
		
	// === Using class tag with CSS ===
		//CSS selector structure: driver.findElement(By.cssSelector("[css tag]+ space+>+[css tag]+ space+>+[css tag]"));
		driver.findElement(By.cssSelector("[id='u_0_g'] > div > [class='_51lp _1gb4']"));
		
	    //It = to following: USING DIRECTORY shortcut "space"
		driver.findElement(By.className("[id='u_0_g'] [class='_51lp _1gb4']"));
		
		driver.findElement(By.cssSelector("[class='nav-left'] [class='nav-search-facade']"));
		driver.findElement(By.cssSelector("[class='_45ks'] [class='_51lp _1gb4']"));
		
		
		
		String errMSG = driver.findElement(By.cssSelector("[class='_4rbf _53ij']")).getText();

		//It = to following: using CLASS shortcut "."
		// We start value with "." and replace space "."
		String errMSG2 = driver.findElement(By.cssSelector("._4rbf._53ij")).getText();  	//Find (error Message) by (CssSelector). 
		System.out.println(errMSG2);
		
		
		driver.findElement(By.cssSelector("[class='uiContextualLayerPositioner _572t uiLayer'] [class='_4rbf _53ij']"));
		
		//It = to following: USING CLASS shortcut "." and DIRECTORY shortcut "Space"
		driver.findElement(By.cssSelector(".uiContextualLayerPositioner._572t.uiLayer ._4rbf._53ij"));

	// === Using ID tag with CSS ===
		driver.findElement(By.cssSelector("[id=u_0_3]")).getText();

		//It = to following: USING ID shortcut "#"
		driver.findElement(By.cssSelector("#u_0_3")).getText(); 
		
	// === Performing Partial Match ====
		//Find element START WITH '_4rbf'
		driver.findElement(By.cssSelector("div[class^='_4rbf']")).getText(); 

		//Find element CONTAIN 'rbf'
		driver.findElement(By.cssSelector("div[class*='rbf']")).getText(); 
		
		//Find element END WITH '53ij'
		driver.findElement(By.cssSelector("div[class$='53ij']")).getText(); 

		
		System.out.println(errMSG);												 		// Print error message
		Assert.assertEquals("The password that you've entered is incorrect. Forgotten password?",errMSG ,"Error message note as expected");
		
 }

//==============Find element by (linktext) / (partialLinkText) ================= 	
	@Test
	public void Find_linkText () throws Exception{

		driver.get("https://demos1.softaculous.com/WordPress/wp-login.php"); // Use_(get)function_of_new_instance_to_send_URL

		// 1- Login using admin/ pass
				driver.findElement(By.id("user_login")).sendKeys("admin");
				driver.findElement(By.id("user_pass")).sendKeys("pass");
				driver.findElement(By.id("wp-submit")).click();

				Thread.sleep(5000);

		// 2-Click Post tab in menu
		driver.findElement(By.cssSelector("#menu-posts .wp-menu-name")).click();
// ===================================================
// ::Note:: Preferred when link test is available (for 1 language and other language tested in smoke test)

//Find element by linktext in sub menu.
		driver.findElement(By.linkText("Add New")).click();

//Find element by (partialLinkText)in case of link have space before and after link value. using "Ranorex Selocity" chrome tool.
		driver.findElement(By.partialLinkText("Add New")).click();

// ===================================================		
	
}
	@AfterMethod									
	public static void TearDown(){
		System.err.println("@AfterMethod");
		driver.quit();
	}
}
