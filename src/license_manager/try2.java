package license_manager;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class try2 {
	
	WebDriver driver;
	@Test
	public void UpdateModule() throws InterruptedException
	{
		System.out.println("@Test");
//		URL
        //driver.get("http://192.168.5.186:333/");
        driver.get("https://paxeraultima.net:863/"); 
        
 	   WebElement checkBox1;
 	   WebElement checkBox2;
 	   WebElement faciltiyfield;
 	  String customername="";
//        Login to License Manager
//        driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_txt_UserName\']")).sendKeys("mohamed.mostafa");
//        driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_txt_Password\']")).sendKeys("123456");
    driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_txt_UserName\']")).sendKeys("hesham");
    driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_txt_Password\']")).sendKeys("Hesham12345");
 	  
 	  driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_btn_Login\']")).click();
//        Open control customer
        driver.findElement(By.xpath("//*[@id=\'TreeView1n0\']/img")).click();
        driver.findElement(By.xpath("//*[@id=\'TreeView1t2\']")).click();
//        Thread.sleep(3000);  //  for testing
        
       Select Customerselect = new Select(driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_ddl_Customer\']")));
       List<WebElement> List=Customerselect.getOptions();
       System.out.println("List Size : "+List.size());
       

   
       for(  int i=491;i<List.size();i++)
       {
        Select Customerselect2 = new Select(driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_ddl_Customer\']")));
        List<WebElement> List2=Customerselect2.getOptions();
    	 System.out.println(i);
    	// int count=i;
    	 //  Thread.sleep(1000);
     //  customername="";
   	   customername=List2.get(i).getText();
       System.out.println(customername);
       
       driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_ddl_Customer\']")).click();
       Thread.sleep(1000);
           System.out.println("i2 ="+i);
    	   Customerselect2.selectByIndex(i);
    	   System.out.println("i3 ="+i);
    	   Thread.sleep(3000);

//    	   //Advanced report module checkbox
    	   checkBox1 = driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_cbl_Modules_15\']"));
//    	   //Basic report module checkbox
    	   checkBox2 = driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_cbl_Modules_16\']"));
    	   
    	   faciltiyfield=driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_txt_Maxnofacility\']"));
    	   System.out.println("facility Text : "+faciltiyfield.getAttribute("value"));
//   	   Thread.sleep(3000);
    	   
    	   if(!faciltiyfield.getAttribute("value").isEmpty())
    	   {
    		   System.out.println(faciltiyfield.getText());
//    	   To checkbox 
    	   if(!checkBox1.isSelected())
    	   {
   		    System.out.println("checkBox1 checked");
    		   checkBox1.click();
    		   Thread.sleep(3000);   
    	   }

    		//checkBox2 is selected by default
    		if(!checkBox2.isSelected())
    		{
    			System.out.println("checkBox2 checked");
    			checkBox2.click();
    			Thread.sleep(3000);
    		}
    		
    		//Customerselect.deselectByIndex(i);
////Update Customer
    		driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_btn_Edit\']")).click();
////    		customer is updated
    		String Success = driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_lbl_Message\']//span")).getText();
    		System.out.println(Success);
//    		
    		System.out.println(customername+" Is  updated");
    		if(Success.contains("Success"))
    		{
////    		Return to control customer
    		driver.findElement(By.xpath("//*[@id=\'TreeView1t2\']")).click();
    		}
//
    	   }
    	   else 
    	   {
//       			
    		   
           		System.out.println(customername+" Is not updated , number of facility is empty");
//           		
           	//	Customerselect.deselectByIndex(i);	
           		driver.findElement(By.xpath("//*[@id=\'TreeView1t2\']")).click();
           		Thread.sleep(3000);
    	   }
    		
       }
//          Need to loop inside customer list
	}
	
	@BeforeMethod
	public void setup()
	{
		System.out.println("@BeforeMethod");
		driver =new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void teardown()
	{
		System.out.println("@AfterMethod");
		driver.quit();
	}

}
