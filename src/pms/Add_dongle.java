package pms;

import java.sql.ResultSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Add_dongle {
	public static WebDriver driver;
    WebDriverWait wait;
     @Before
        public void setUp() {
    	 ChromeOptions chromeOptions = new ChromeOptions();
//    	 chromeOptions.addArguments("--headless");
//    	 chromeOptions.addArguments("window-size=2048,1024");
    	 driver = new ChromeDriver(chromeOptions);  
         driver.manage().window().maximize();
         wait = new WebDriverWait(driver, 6, 2000);
        }
     
     
     @Test
     public void Run() throws Exception {
         driver.get("http://192.0.0.172/"); //Open PMS Page
         
         WebElement Username =driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_txt_UserName\"]"));// Insert Username and Password
         Username.clear();
         Username.sendKeys("hesham.gharib");
         System.out.println("Adding Username");
         WebElement Password =driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_txt_Password\"]")); //Password
         Password.clear();
         Password.sendKeys("Test1234");
         System.out.println("Adding Password");
         driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_btn_Login\"]")).click(); // Login Click
         Thread.sleep(10000);
         driver.findElement(By.xpath("//*[@id=\"TreeView1t16\"]")).click(); //Open Dongle Stock
         driver.findElement(By.xpath("//*[@id=\"TreeView1t17\"]")).click();; // Open Add in Dongle Stock
        
         Thread.sleep(5000);
         
         ReadFromAccess search_branch=new ReadFromAccess();
         ResultSet rs=search_branch.search_dongle();
	        while(rs.next())
        {
	            String Dongl_UID    = rs.getString("Dongl_UID") ;
	            String Dongle_Serial    = rs.getString("Dongle_Serial") ;
	            String Dongol_Type    = rs.getString("Dongol_Type") ;
	            String Country    = rs.getString("Country") ;
	            AddDongle(Dongl_UID,Dongle_Serial,Dongol_Type,Country);
        }
         

  }
     

		public void AddDongle(String Dongl_UID,String Dongle_Serial,String Dongol_Type,String Country) throws InterruptedException {
//	         Select Location
       	 Select Location  = new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_Location\"]")));
         System.out.println(Country);
         driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_Location\"]")).click();
         Thread.sleep(1000);
         
         if(Country.contains("United States")) {
         Location.selectByVisibleText("Paxeramed USA");
  	     Thread.sleep(1000);
         }
         else
         {
             Location.selectByVisibleText("Paxeramed Egypt");
      	     Thread.sleep(1000);
         }
         
//         Select Dongle_Type
       	 Select Dongle_Type  = new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_DongleType\"]")));
        driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_DongleType\"]")).click();
         Dongle_Type.selectByVisibleText("Paxera WS");
  	     Thread.sleep(1000);
  	     
//         Select Dongle_Category
       	 Select Dongle_Category  = new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_DongleCategories\"]")));
         System.out.println(Dongol_Type);
         driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_DongleCategories\"]")).click();
         Thread.sleep(1000);
         
         if(Dongol_Type.contains("Green 1")) {
        	 Dongle_Category.selectByVisibleText("Green 1");
  	     Thread.sleep(1000);
         }
         else if (Dongol_Type.contains("Green 2"))
         {
        	 Dongle_Category.selectByVisibleText("Green 2");
      	     Thread.sleep(1000);
         }
         else if (Dongol_Type.contains("Green 3"))
         {
        	 Dongle_Category.selectByVisibleText("Green 3");
      	     Thread.sleep(1000);
         }
         
         //Add Dongle Serial
         WebElement Serial=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_txt_DongleSerial\"]"));
         Serial.clear();
         Serial.sendKeys(Dongle_Serial);
        
         //Add Dongle UID
         WebElement UID=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_txt_DongleUID\"]"));
         UID.clear();
         UID.sendKeys(Dongl_UID);
         
         //Click on Add Dongle Data
         driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_btn_Add\"]")).click();;
           
         //Check if Dongle Existed
         WebElement Success=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_lbl_DongleAdd\"]/div/span"));
         System.out.println("Dongle_Serial : "+Dongle_Serial+" ,Dongl_UID"+Dongl_UID+" "+Success.getText());
         
         
         
         }
  
     
     @After
		public void tearDown() {
      
  	driver.quit();
  	System.out.println("Test Case Success");
  }  
     
     

}
