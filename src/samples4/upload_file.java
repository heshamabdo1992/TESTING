package samples4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import java.awt.AWTException;	
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
//import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.awt.datatransfer.Clipboard;
//import java.awt.datatransfer.DataFlavor;

public class upload_file {
	 public static WebDriver driver;
		
		@BeforeMethod									
		public void setup(){
		System.out.println("@BeforeMethod");
		driver= new ChromeDriver();
     driver.manage().window().maximize();
	       
	 }	
	//sample for upload file
		//@Test
		public static void Alerts_testrun2 () throws Exception{
			
			System.out.println("@test:TestCase1");
			driver.get("https://the-internet.herokuapp.com/upload");
			WebElement uploadbutton=driver.findElement(By.xpath("//*[@id=\"file-upload\"]"));
			WebElement uploadsubmit=driver.findElement(By.xpath("//*[@id=\"file-submit\"]"));
	        // enter the file path onto the file-selection input field
			uploadbutton.sendKeys("G:\\Real IP Config.png");

	        // click the "UploadFile" button
			uploadsubmit.click();
			WebElement uploadtext=driver.findElement(By.xpath("//*[@id=\"uploaded-files\"]"));
			System.out.println(uploadtext.getText());
			Thread.sleep(3000);
			
		}
		
		//sample for upload file using Robot class
		@Test
		public static void Alerts_testrun () throws Exception{
			
			System.out.println("@test:TestCase2");
			driver.get("https://the-internet.herokuapp.com/upload");
			
			String path = "G:\\Real IP Config.png";
	        // File Location
			StringSelection stringSelection = new StringSelection(path);
	        // Copy to clipboard
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	        clipboard.setContents(stringSelection, null);
	       
	        //To print clipboard 
	        /*
	        Transferable trans = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
	        String clipboardtext = (String) trans.getTransferData(DataFlavor.stringFlavor);	
	        System.out.println(clipboardtext);
	        */
	        WebElement uploadbutton=driver.findElement(By.xpath("//*[@id=\"file-upload\"]"));
			WebElement uploadsubmit=driver.findElement(By.xpath("//*[@id=\"file-submit\"]"));
	        // enter the file path onto the file-selection input field
			uploadbutton.click();
	       
	        // Create object of Robot class
			Robot robot = new Robot();  // Robot class throws AWT Exception

	        // Press CTRL+V
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.delay(100);
	        // Release CTRL+V
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.delay(100);
	        // Press Enter
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        robot.delay(100);
	        
	        // click the "UploadFile" button
			uploadsubmit.click();
			Thread.sleep(3000);
			

			WebElement uploadtext=driver.findElement(By.xpath("//*[@id=\"uploaded-files\"]"));
			System.out.println(uploadtext.getText());
			Thread.sleep(3000);
			
		}
		
		
		@AfterMethod									
		public static void TearDown(){
			System.out.println("@AfterMethod");
			driver.quit();
		}

}
