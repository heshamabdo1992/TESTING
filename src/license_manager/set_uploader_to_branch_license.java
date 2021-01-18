package license_manager;

import java.util.List;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class set_uploader_to_branch_license {
	private WebDriver driver;
	
	
	@BeforeMethod(alwaysRun = true)
	public void setup()throws Exception
	{
		System.out.println("@BeforeMethod");
		driver =new ChromeDriver();
		driver.manage().window().maximize();
	}
	  @DataProvider(name = "Cloud") 
	  public static String[][] Clouds() {
		  return new String[][] {{ "SHCT - Teleradiology Center", "Smart Healthcare Technology" }};
	     //   return new String[][] {{ "Diagnostics_Elite", "Diagnostics Elite Cloud2" }};
	 
	  }

	  
	@Test(dataProvider = "Cloud")
	public void UpdateModule(String CustomerName,String CloudName) throws InterruptedException
	{
	  
	  System.out.println("@Test");
//	  URL
     // driver.get("http://192.168.5.186:333");
      driver.get("https://paxeraultima.net:863");
//    Login to License Manager
    //  driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_txt_UserName\']")).sendKeys("mohamed.mostafa");
     // driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_txt_Password\']")).sendKeys("123456");
    driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_txt_UserName\']")).sendKeys("hesham");
    driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_txt_Password\']")).sendKeys("Hesham12345");
 	  
 	  driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_btn_Login\']")).click();
//    Open control customer
      driver.findElement(By.xpath("//*[@id=\"TreeView1t4\"]")).click();
      driver.findElement(By.xpath("//*[@id=\"TreeView1t6\"]")).click();
    Thread.sleep(2000);  //  for testing
   //   List<WebElement> branchlist=driver.findElements(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_cbl_AddBranches\"]/tbody/tr/td/input"));
      
      Select Customerselect = new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_CustomerName\"]")));
     // System.out.println(i);
      //Thread.sleep(1000);
      driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_CustomerName\"]")).click();
      Thread.sleep(1000);
      Customerselect.selectByVisibleText(CustomerName);
      System.out.println(CustomerName+" customer is selected");
 	//  Thread.sleep(1000);
      // j is column  // page number
      for (int j=2;j<=2;j++)
      {
    	  WebElement Page=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_"
    	  		+ "grid_Branches\"]/tbody/tr[12]/td/table/tbody/tr[1]/td["+j+"]"));
    	  Page.click();
    	  Thread.sleep(3000);
    	  
    // i number of rows	// strat from any row -1 
 	  for(  int i=1;i<10;i++)
       { 
     	 WebElement Branchname=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_grid_Branches\"]/tbody/tr["+(i+2)+"]/td[1]"));
     	System.out.println(Branchname.getText()+" is selected");
     	String Branch=Branchname.getText();
     	 WebElement set_Uploader_btn=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_grid_Branches_link_addULTInfo_"+i+"\"]"));
        set_Uploader_btn.click(); 
        Thread.sleep(1000);
        Select Cloudselect = new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_Cloud_ddl\"]")));
        Cloudselect.selectByVisibleText(CloudName);
     	System.out.println(CloudName+" is selected");
    	 Thread.sleep(3000);
    	 WebElement save_btn=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_SaveUlt_btn\"]"));
    	 save_btn.click();
    	 Thread.sleep(2000);
//    	 assert Success message
    	 WebElement Success= driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_Error_lbl\"]/div/span"));
    	 System.out.println(Branch+" "+Success.getText());
    	 WebElement back_btn=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_CancelUlt_btn\"]"));
    	 back_btn.click();
    	 Thread.sleep(3000);
       }
      }
 	  
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown()throws Exception
	{
		System.out.println("@AfterMethod");
		driver.quit();
	}

}
