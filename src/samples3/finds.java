package samples3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class finds {
	
	 public static WebDriver driver;
	 @BeforeMethod									
		public void setup(){
		System.out.println("@BeforeMethod");
		driver= new ChromeDriver();						  
        driver.manage().window().maximize();

	 }	
	 
		@AfterMethod									
		public static void TearDown(){
			System.out.println("@AfterMethod");
			driver.quit();
		}
		
		//::Reference:: ***********"check reference document in project"***********

		/*::Note::
		 * 1- We must to get search result (1 of 1) when check find element value in web page.
		 * 2- Mind set (1- find by unified attribute, 2- find using its root directory).
		 * 
		 * #### Find element using attribute "value" -is a visible value- Not valid to test all WebPage multi-Language ####
		*/	
		
		@Test											
		public static void Run () throws Exception{
			System.out.println("Run");
			driver.get("https://www.linkedin.com/");		
	//========== Find Element by (ID) ==============================================
 
			driver.findElement(By.id("reg-firstname")).sendKeys("heshamabdo");
			
	//========= Find Element by (Name) =============================================

			driver.findElement(By.name("lastName")).sendKeys("lastName");	 			 		
			
	//========= Find Element by (XPath) ============================================
			
	//::Note:: 
	//Link have to be visible before use it. 	
			
			Thread.sleep(1000);
//			driver.navigate().to("https://www.instagram.com/");		

			//XPATH structure: driver.findElement(By.xpath("xpath[tag vale]+/+xpath[tag vale]+/+xpath[tag vale]"));
			driver.findElement(By.xpath("//html[@class='os-win']//section[@class='form-body']/input[@value='Join now']")).click();
			// = to the following
			driver.findElement(By.xpath("//html[@class='os-win']//section[@class='form-body']/input[@id='registration-submit']")).click();

		//It = to following: USING PATH shortcut "//"
			driver.findElement(By.xpath("//input[@id='registration-submit']")).click();     
			driver.findElement(By.xpath("//input[@value='Join now']")).getText();
			
		//It = to following: USING TYPE shortcut "*"		
			driver.findElement(By.xpath("//*[@id='registration-submit']")).getText();

		//When ***not attribute "VALUE"*** USING shortcut "." for visible value
			driver.findElement(By.xpath("//a[.='Sign Up']")).getText();
				
		//XPath contain both of 2 tags value
			driver.findElement(By.xpath("//input[@value='Join now' and @id='registration-submit']")).getText();
			
		//XPath contain 1 of 2 tags value
//			driver.findElement(By.xpath("//input[@value='Join now' or @class='registration submit-button' ]")).click();
			driver.findElement(By.xpath("//input[@value='Join now' or @value='انضم الآن' ]")).getText();

	//::Note:: Can be used For multi-Language ** Need check syntax.		
//			driver.findElement(By.xpath("//input[@value='Log In' or @value='ÏÎæá']")).click();	
		
			// === Performing Partial Match ====
			//Find element START WITH 'u'       ******* some times not work **********
			driver.findElement(By.xpath("//input[starts-with(@value,'Join')]")).getText(); 

			//Find element CONTAIN '0'
										//Type[contains(@attribute,'value')] 
			driver.findElement(By.xpath("//input[contains(@id,'ion-su')]")).getText(); 

			
			//Find element END WITH '2' 		******* not work **********
//			driver.findElement(By.xpath("//input[ends-with(@value,'now')]")).click();
			
			
	//========= Find Element by (CssSelector) ============================================

	/*::Note:: 
	 * 1- Find element by CSS is faster than XPath. 
	*/		
			Thread.sleep(2000);
			
		// === Using class tag with CSS ===
			//CSS selector structure: driver.findElement(By.cssSelector("[css tag]+ space+>+[css tag]+ space+>+[css tag]"));
			driver.findElement(By.cssSelector("[class='main background lazy-loaded show-join   ']>[class='reg-form']>[class='form-body']>[class='registration submit-button']"));
			
		    //It = to following: USING DIRECTORY shortcut "space"
			driver.findElement(By.cssSelector("[class='global-wrapper artdeco-a'] [class='registration submit-button']")).getText();
			
			//driver.findElement(By.cssSelector("[class=form-body] [class=reg-password]")).getText();

			driver.findElement(By.cssSelector("[id='regForm'] [name='session_password']")).getText();
			
			
			
			driver.findElement(By.cssSelector("[class='alert-content']")).getText();
			driver.findElement(By.cssSelector("[class='dismiss dismiss-alert']")).getText();

			//It = to following: using CLASS shortcut "."
			// We start value with "." and replace space "."
			driver.findElement(By.cssSelector(".main.background.lazy-loaded.show-join>.reg-form>.form-body>.registration.submit-button")).getText();  	//Find by (CssSelector). 
			String errMSG2 = driver.findElement(By.cssSelector(".reg-form > .reg-alert > .wrapper> .message > .alert-content")).getText();  	//Find (error Message) by (CssSelector). 

			System.out.println(errMSG2);
			
			
			driver.findElement(By.cssSelector("[class='global-wrapper artdeco-a'] [class='registration submit-button']")).getText();
			
			//It = to following: USING CLASS shortcut "." and DIRECTORY shortcut "Space"
			driver.findElement(By.cssSelector(".global-wrapper.artdeco-a .registration.submit-button ")).getText();

		// === Using ID tag with CSS ===
			driver.findElement(By.cssSelector("[id='reg-password']")).getText();

			//It = to following: USING ID shortcut "#"
			driver.findElement(By.cssSelector("#reg-password")).getText(); 
			
		// === Performing Partial Match ====
			//Find element START WITH '_4rbf'
			driver.findElement(By.cssSelector("input[class^='reg-p']")).getText(); 
/////////////////////////////////////////////
			//Find element CONTAIN 'g-p'
			driver.findElement(By.cssSelector("input[class*='g-p']")).getText(); 
//			driver.findElement(By.cssSelector("input:contains('g-p)")).getText(); 
			
			//Find element END WITH 'password'
			driver.findElement(By.cssSelector("input[class$='email error']")).getText(); 
			driver.findElement(By.cssSelector("input[class$='submit-button'][id^='registration']")).getText(); 

			//When ***not attribute "VALUE"*** USING shortcut "." for visible value
//			driver.findElement(By.cssSelector("a:contains('Sign Up')")).click();
			
			
			//Css contain both of 2 tags value
			driver.findElement(By.cssSelector("[value='Join now'][id='registration-submit']")).getText();
			
		   //Css contain 1 of 2 tags value
			driver.findElement(By.cssSelector("[value='Join now'],[value='انضم الآن']")).getText();
			
			//find element using "Not"
			driver.findElement(By.cssSelector("input[name$='password']:NOT([class='login-password'])")).getText();
			
			

			String errMSG3 = driver.findElement(By.cssSelector("span[class='alert-content']")).getText();

			System.out.println(errMSG3);	 		// Print error message
			Assert.assertEquals("Please enter your email address",errMSG3 ,"Error message note as expected");
			
	 }

	//==============Find element by (linktext) / (partialLinkText) ================= 	
		@Test
		public void Find_linkText () throws Exception{

			driver.get("http://toolsqa.com/automation-practice-form/"); // Use_(get)function_of_new_instance_to_send_URL

			
					driver.findElement(By.name("firstname")).sendKeys("firstname");
					driver.findElement(By.name("lastname")).sendKeys("lastname");
					driver.findElement(By.id("submit")).click();

					Thread.sleep(5000);

			// submit
			driver.findElement(By.cssSelector(".control-group #submit")).click();
	// ===================================================
	// ::Note:: Preferred when link test is available (for 1 language and other language tested in smoke test)

	//Find element by linktext.
			driver.findElement(By.linkText("Selenium Automation Hybrid Framework")).click();

	//Find element by (partialLinkText)in case of link have space before and after link value. using "Ranorex Selocity" chrome tool.
			driver.findElement(By.partialLinkText("Download")).click();

	// ===================================================		
		
	}

}
