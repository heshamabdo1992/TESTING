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
import org.testng.annotations.Test;

public class add_branch_license {
	private WebDriver driver;
	
	
	@BeforeMethod(alwaysRun = true)
	public void setup()throws Exception
	{
		System.out.println("@BeforeMethod");
		driver =new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void UpdateModule() throws InterruptedException
	{
	  
	  System.out.println("@Test");
//	  URL
      driver.get("http://192.168.5.186:333");
//      driver.get("https://paxeraultima.net:863");
 	  WebElement branchname;
 	  Select Country;
 	  WebElement add;
 	  WebElement ZFPbutton=null;
 	  WebElement D3button=null;
 	 WebElement Chiropractic=null;
//    Login to License Manager
      driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_txt_UserName\']")).sendKeys("testing.team");
      driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_txt_Password\']")).sendKeys("Test1234");
//    driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_txt_UserName\']")).sendKeys("hesham");
//    driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_txt_Password\']")).sendKeys("Hesham12345");
 	  
 	  driver.findElement(By.xpath("//*[@id=\'ContentPlaceHolder1_btn_Login\']")).click();
//    Open control customer
      driver.findElement(By.xpath("//*[@id=\"TreeView1t4\"]")).click();
      driver.findElement(By.xpath("//*[@id=\"TreeView1t5\"]")).click();
//    Thread.sleep(3000);  //  for testing
      List<WebElement> branchlist=driver.findElements(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_cbl_AddBranches\"]/tbody/tr/td/input"));
      Select Customerselect2 = new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_AddCustomerName\"]")));
//      System.out.println(i);
      Thread.sleep(1000);
      driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_AddCustomerName\"]")).click();
      Thread.sleep(1000);
      Customerselect2.selectByVisibleText("Diagnostics Elite");
      
      for(  int i=24;i<=100;i++)
       { 


    	 Thread.sleep(1000); 
//    	 open add branch tab
    	 branchname = driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_txt_AddBranchName\"]"));
    	 branchname.clear();
    	 branchname.sendKeys("Diagnostics Elite"+i);
//    	 Thread.sleep(1000);
//       select Country
    	 Country = new Select (driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_Country\"]")));
    	 Country.selectByVisibleText("Saudi Arabia");
    	 
    	 ZFPbutton=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_chk_branches_0\"]"));
    	 D3button=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_chk_branches_1\"]"));
    	 Chiropractic=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_chk_branches_2\"]"));
    	 if(!ZFPbutton.isSelected()) {
    	 ZFPbutton.click();}
    	 if(!Chiropractic.isSelected()) {
    	 Chiropractic.click();}
    	 if(!D3button.isSelected()) {
    		 D3button.click();
    	 }
    	 
    	 add=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_btn_Add\"]"));
    	 add.click();
//    	 Thread.sleep(1000);
//    	 assert Success message
    	 WebElement Success= driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_lbl_Add\"]/div/span"));
    	 System.out.println("branch "+i+" "+Success.getText());
    	 driver.navigate().back();
       }
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown()throws Exception
	{
		System.out.println("@AfterMethod");
		driver.quit();
	}

}
