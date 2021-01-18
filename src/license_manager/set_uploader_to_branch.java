package license_manager;


//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class set_uploader_to_branch {
	private WebDriver driver;
	
	
	@BeforeMethod(alwaysRun = true)
	public void setup()throws Exception
	{
		System.out.println("@BeforeMethod");
		driver =new ChromeDriver();
		driver.manage().window().maximize();

	}
	
	@Test
	public void Run() throws InterruptedException
	{
	  
	  System.out.println("@Test");
//	  URL
      driver.get("https://paxeraultima.net:863");
      
 	  WebElement branchname;
 	  WebElement confirm;
      String customername = "Diagnostics_Elite";
//Login to License manager server
 	  driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_txt_UserName\']")).sendKeys("hesham");
 	  driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_txt_Password\']")).sendKeys("Hesham12345");
 	  driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_btn_Login\']")).click();
//    Open control branch
      driver.findElement(By.xpath("//*[@id=\"TreeView1t4\"]")).click();

      for(  int i=61;i<76;i++)
       { 
    	  
//    	 open generate uploader license tab
    	 driver.findElement(By.xpath("//*[@id=\"TreeView1t7\"]")).click();
//    	 customer select
         Select Customerselect = new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_customer\"]")));
         System.out.println(i);
         driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_customer\"]")).click();
         Thread.sleep(1000);	   
         Customerselect.selectByVisibleText(customername);
    	 Thread.sleep(3000); 
//    	 branch select
    	 branchname = driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_Branch\"]"));
    	 Select Branchselect=new Select (branchname);
//    	 Branchselect.selectByVisibleText("branch"+i);
    	 Branchselect.selectByIndex(i);
//    	 Confirm button
    	 confirm=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_confirm_btn\"]"));
    	 confirm.click();
    	 Thread.sleep(3000); 
//    	 Generate uploader license button
    	 WebElement generate=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_gen_Pfile_btn\"]"));
    	 generate.click();
    	 Thread.sleep(3000);
//    	 Send Email Text
    	 WebElement sendtext=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_sendto_txt\"]"));
    	 sendtext.sendKeys("hesham.gharib@paxerahealth.com");
    	 Thread.sleep(1000);
//    	 Send Email button
    	 WebElement sendbtn=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_send_mail\"]"));
    	 sendbtn.click();
    	 Thread.sleep(3000);
//    	 Assert success message
    	 WebElement successtext=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_sent_lbl\"]/div/span"));
    	 System.out.println("Iteration no. "+i+" __ "+successtext.getText());
    	 Thread.sleep(2000);
    	 
       }

	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown()throws Exception
	{
		System.out.println("@AfterMethod");
		driver.quit();
	}

}
