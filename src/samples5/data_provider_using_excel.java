package samples5;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class data_provider_using_excel {
	 public static WebDriver driver;
		private static ArrayList<Integer> iTestCaseRow;

		@BeforeMethod									
		public void setup(){
		System.out.println("@BeforeMethod");
		driver= new ChromeDriver();
       driver.manage().window().maximize();
	       
	 }	
		

		  @DataProvider(name = "login") 
		  public static Object[][] credentials() throws Exception {
		 
			  // Setting up the Test Data Excel file
			  ExcelUtils.setExcelFile("D:\\user_login.xlsx","Sheet1");		  
			  // Fetching the Test Case row number from the Test Data Sheet
			  // Getting the Test Case name to get the TestCase row from the Test Data Excel sheet
			  iTestCaseRow = ExcelUtils.getRowContains("pxadmin",0);
			  Object[][] testObjArray = ExcelUtils.getTableArray("D:\\user_login.xlsx","Sheet1",iTestCaseRow);
			  System.out.println(testObjArray[0][0]);
			  return (testObjArray);	 
		  }
		  
		  
		@Test(dataProvider = "login")
		public static void data_provider_run (String sUsername, String sPassword, String rownumber) throws Exception{
			System.out.println("@test:TestCase1");
			driver.get("https://192.168.5.183/pxadmin");	      
	        WebElement username_field=driver.findElement(By.xpath("//*[@id=\"txt_UserName\"]"));    
	        WebElement password_field=driver.findElement(By.xpath("//*[@id=\"txt_Password\"]"));
	        WebElement login_btn=driver.findElement(By.xpath("//*[@id=\"btn_Login\"]"));
	        username_field.clear();
	        password_field.clear();
	        username_field.sendKeys(sUsername);
	        password_field.sendKeys(sPassword);
	        login_btn.click();
	       // Thread.sleep(2000);

	        try {
	        WebElement profile=driver.findElement(By.xpath("//*[@id=\"lbl_admin_FullName\"]"));
	        System.out.println(profile.getText());
	    	int rowN = Integer.parseInt(rownumber);			
	        write_excel(rowN,"Passed");
	        }
	        catch (Exception  e){
		        WebElement message=driver.findElement(By.xpath("//*[@id=\"lbl_Error\"]/div/span"));
				   Assert.assertTrue(message.getText().contains("Invalid user name or password"));;
				    	int rowN = Integer.parseInt(rownumber);			
				        write_excel(rowN,"Failed");
	        }
	        
	       // if(profile.isDisplayed())
	      //  {
	       // Assert.assertEquals("Lakshay Sharma", sValue);
	       // Assert.assertTrue(profile.getText().equalsIgnoreCase("nada"));;
		//   Assert.assertTrue(profile.getText().contains("nada"));;
	        //System.out.println(profile.getText());
	    	//int rowN = Integer.parseInt(rownumber);			
	       // write_excel(rowN,"Pass");
	     //   }
	       // else {
		   // 	int rowN = Integer.parseInt(rownumber);			
		   //     write_excel(rowN,"Failed");
	      //  }

	       // Thread.sleep(3000);
		}
		
		public static void write_excel(int rownum,String Result) throws Exception
		{
			
			write_excel eat = new write_excel("G:\\user_login.xlsx");
	        eat.setCellData("Sheet1",3,rownum,Result);
		}
		
		
		@AfterMethod									
		public static void TearDown(){
			System.out.println("@AfterMethod");
			driver.quit();
		}
}
