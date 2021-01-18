package samples4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class iframes {
	 public static WebDriver driver;
		
		@BeforeMethod									
		public void setup(){
		System.out.println("@BeforeMethod");
		driver= new ChromeDriver();
       driver.manage().window().maximize();
	       
	 }	
		///////////////////////////////////////////////////
		//sample for iframe
	//	@Test
		public static void iframe_run () throws Exception{
			System.out.println("@test:TestCase1");
			driver.get("https://the-internet.herokuapp.com/iframe");
			driver.getWindowHandles();			// switch to the iframe through ID or name
			driver.switchTo().frame("mce_0_ifr");
			
			WebElement Text=driver.findElement(By.xpath("//*[@id=\"tinymce\"]"));
			Text.clear();
			Text.sendKeys("TESSST");
			Thread.sleep(5000);

		}
		
		
		////////////////////////////////////////////////////
		//////////////2nd sample for iframe
	//	@Test
		public static void iframes_run () throws Exception{
			System.out.println("@test:TestCase2");
			 driver.get("http://demo.guru99.com/test/guru99home/"); 
		       // navigates to the page consisting an iframe
		       driver.switchTo().frame("a077aa5e"); //switching the frame by ID
				System.out.println("********We are switch to the iframe*******");
	     		driver.findElement(By.xpath("html/body/a/img")).click();
	  		    //Clicks the iframe
	       
	  			System.out.println("*********We are done***************");

		}
		
		/////////////////////////////////////////////////////////////////////////////
		//sample for frame 
	//	@Test
		public static void frame_run () throws Exception{
			System.out.println("@test:TestCase3");
			driver.get("https://the-internet.herokuapp.com/nested_frames");
			Thread.sleep(2000);

         // switch to the frame through frame name
         driver.switchTo().frame("frame-top");
         driver.switchTo().frame("frame-left");

          WebElement frame1=driver.findElement(By.xpath("/html/body"));
          System.out.println(frame1.getText());

          //switching back from the frame
          driver.switchTo().defaultContent(); 
          driver.switchTo().frame("frame-top");
          driver.switchTo().frame("frame-middle");

          WebElement frame2=driver.findElement(By.xpath("/html/body"));
          System.out.println(frame2.getText());

          Thread.sleep(3000);	
		}
		
		/////////////////////////////////////////////////////////////////////////////
		//Loop on frame outer and inner
		//@Test
		public static void frames_run () throws Exception{
			System.out.println("@test:TestCase4");
			driver.get("https://the-internet.herokuapp.com/nested_frames");
			Thread.sleep(2000);
			
			int outersize = driver.findElements(By.tagName("frame")).size();
		    System.out.println("Total outer Frames --" + outersize);
		    
		    for (int j=0;j< outersize;j++)
		    {
		 // prints the total number of frames 
			driver.switchTo().frame(j); // Switching the Outer Frame    		
		    System.out.println ("Switched to outer frame"+j);
		  //Printing the text in outer frame
			int innersize = driver.findElements(By.tagName("frame")).size();
		    // prints the total number of frames inside outer frame
			System.out.println("Total inner Frames --" + innersize);
			if(innersize!=0) {
			for(int i=0;i<innersize;i++) {
			driver.switchTo().frame(i); // Switching to innerframe
		    System.out.println ("Switched to inner frame"+i);
			System.out.println(driver.findElement(By.xpath("/html/body")).getText());
			//Printing the text in inner frame
			driver.switchTo().defaultContent();
			driver.switchTo().frame(j); 
			}
			driver.switchTo().defaultContent();

		    }
		    else
		    {
			    System.out.println ("Switched to outer frame"+j);
				System.out.println(driver.findElement(By.xpath("/html/body")).getText());
				driver.switchTo().defaultContent();
		    }
			
		    }
////////////////////////////////////////////////
		    ///////////////////
		    /*
			// switch to the frame through frame name
			driver.switchTo().frame("frame-top");
			driver.switchTo().frame("frame-left");
			
			WebElement frame1=driver.findElement(By.xpath("/html/body"));
			System.out.println(frame1.getText());
			
			//switching back from the frame
			driver.switchTo().defaultContent(); 
			driver.switchTo().frame("frame-top");
			driver.switchTo().frame("frame-middle");
			
			WebElement frame2=driver.findElement(By.xpath("/html/body"));
			System.out.println(frame2.getText());
			*/
			Thread.sleep(3000);

		}

		
		
		
		
		@AfterMethod									
		public static void TearDown(){
			System.out.println("@AfterMethod");
			driver.quit();
		}

}
