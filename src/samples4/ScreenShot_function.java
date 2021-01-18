package samples4;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
//import java.awt.image.RasterFormatException;
import java.io.File;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import java.awt.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.Color;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenShot_function {
	 public static WebDriver driver;
	 static JavascriptExecutor js;

		@BeforeMethod									
		public void setup(){
		System.out.println("@BeforeMethod");
		driver= new ChromeDriver();
      driver.manage().window().maximize();
	       
	 }	
		
		@Test
		public static void ScreenShot_run () throws Exception{ 
			System.out.println("@test:TestCase1");
			 driver.get("http://demo.guru99.com/test/guru99home/");  
			// takeSnapShot(driver, "G://testGurr99home.png") ; 
			// usingAShot("G://testGurr99home3.png") ; 
			// usingrobot("G://testGurr99home5.png");
			// WebElement Element1=driver.findElement(By.xpath("//*[@id=\"rt-feature\"]/div/div[1]/div/div/div/div[1]/div[3]/div/div/div/div/div[1]/div/div/h4/a"));			
			 WebElement Element2=driver.findElement(By.xpath("//*[@id=\"rt-maintop\"]/div/div[1]/div/div/div/div/div[2]/div/img"));

			 //takeSnapShotforelement(driver, "G://testGurr99home2.png",Element1); 
			 //To scroll to element2
			 /*
			 Actions actions = new Actions(driver);
			 actions.moveToElement(Element);
			 actions.perform();
			*/
			 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", Element2);
			 ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", Element2);
			// usingAShotforelement( "G://testGurr99home4.png",Element2) ; 
			 usingAShotforele( "G://testGurr99home6.png",Element2);
			 Thread.sleep(3000);
			 
		}
		
		//Taking screen shot Viewable Area’s Screenshot
		 public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
		        //Convert web driver object to TakeScreenshot
		        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		        //Call getScreenshotAs method to create image file
		        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		        //Move image file to new destination
	            File DestFile=new File(fileWithPath);
	            //Copy file at destination
	            FileUtils.copyFile(SrcFile, DestFile);

		    }
		 
		 //using AShot class Viewable Area’s Screenshot
		 public static void usingAShot(String entirePageImagepath) throws Exception{
			    File entirePageImageFile = new File(entirePageImagepath );
			 //Take Screenshot of entire page by AShot
		        Screenshot entirePageScreenShot = new AShot().
		                shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
		        //Write Screenshot to a file
		        ImageIO.write(entirePageScreenShot.getImage(),"PNG", entirePageImageFile);
		    }
		 
		//Taking screen shot for Element
		 public static void takeSnapShotforelement(WebDriver webdriver,
				 String fileWithPath,WebElement ele) throws Exception{
		        //Convert web driver object to TakeScreenshot
		        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		        //Call getScreenshotAs method to create image file
		        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		     
		      //Used selenium getSize() method to get height and width of element.
		        //Retrieve width of element.
		        int ImageWidth = ele.getSize().getWidth();
		        //Retrieve height of element.
		        int ImageHeight = ele.getSize().getHeight();  
		   
		        //Used selenium Point class to get x y coordinates of Image element.
		        //get location(x y coordinates) of the element.
			     // Get the location of element on the page
			     Point point = ele.getLocation();
			    // Get width and height of the element
		        int xcord = point.getX();
		        int ycord = point.getY();

		        System.out.println(xcord+"  "+ycord);
		        System.out.println(ImageWidth+"   "+ImageHeight);
		        
		        BufferedImage fullimage = ImageIO.read(SrcFile);

		        // Crop element from viewable area's screenshot to get element's screenshot
		        BufferedImage dest = fullimage.getSubimage(xcord, ycord, ImageWidth, ImageHeight);
		        ImageIO.write(dest, "png", SrcFile);   
		        // Copy the element screenshot to disk
		        //Move image file to new destination
	            File DestFile=new File(fileWithPath);
	            //Copy file at destination
	            FileUtils.copyFile(SrcFile, DestFile);

		    }
		 
		 //using AShot for specific element
		 public static void usingAShotforelement(String filepath,WebElement ele) throws Exception{
			    File ImageFile = new File(filepath);
			 //Take Screenshot of entire page by AShot
		        Screenshot entirePageScreenShot = new AShot().
		                shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
		       
		        
			      //Used selenium getSize() method to get height and width of element.
		        //Retrieve width of element.
		        int ImageWidth = ele.getSize().getWidth();
		        //Retrieve height of element.
		        int ImageHeight = ele.getSize().getHeight();  
		   
		        //Used selenium Point class to get x y coordinates of Image element.
		        //get location(x y coordinates) of the element.
			     // Get the location of element on the page
			     Point point = ele.getLocation();
			    // Get width and height of the element
		        int xcord = point.getX();
		        int ycord = point.getY();

		        BufferedImage dest =  
		        entirePageScreenShot.getImage().getSubimage(xcord,ycord,ImageWidth,ImageHeight);
		        //Write Screenshot to a file
		        ImageIO.write(dest,"png", ImageFile);
		    }
		 
		 //2nd using AShot for specific element 2nd
		 public static void usingAShotforele(String filepath,WebElement ele) throws Exception{ 
			 File entirePageImageFile = new File(filepath );
			
			 //Take Screenshot of area page by AShot
		        Screenshot ScreenShot1 = new AShot().takeScreenshot(driver,ele);
		        //Write Screenshot to a file
		        ImageIO.write(ScreenShot1.getImage(), "PNG", entirePageImageFile);

		 }
		 
		//using robot Viewable Area’s Screenshot
		 public static void usingrobot(String filepath) throws Exception{
			 
			 try{
					// Creating Robot class object
					Robot robotClassObject = new Robot();
					// Get screen size
					Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
					// Capturing screenshot by providing size
		            BufferedImage dest = robotClassObject.createScreenCapture(screenSize);
		            // To copy temp image in to permanent file
		            ImageIO.write(dest, "png",new File(filepath)); 
		            
				}catch(Exception e)
				{
					System.out.println("Some exception occured." + e.getMessage());
					
				}
		 }
		 
		@AfterMethod									
		public static void TearDown(){
			System.out.println("@AfterMethod");
			driver.quit();
		}
}
