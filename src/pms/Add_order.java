package pms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
//import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Add_order {
	public static WebDriver driver;
    WebDriverWait wait;
     @BeforeMethod
        public void setUp() {
    	 ChromeOptions chromeOptions = new ChromeOptions();
//    	 chromeOptions.addArguments("--headless");
//    	 chromeOptions.addArguments("window-size=2048,1024");
    	 driver = new ChromeDriver(chromeOptions);  
         driver.manage().window().maximize();
         wait = new WebDriverWait(driver, 6, 2000);
        }
     
     @Test
     public void Add_order_1() throws Exception {
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
         driver.findElement(By.xpath("//*[@id=\"TreeView1t12\"]")).click(); //Open Order Tree
         driver.findElement(By.xpath("//*[@id=\"TreeView1t14\"]")).click();; // Open 	Control Order
        
         Thread.sleep(1000);
         
         ReadFromAccess search_branch=new ReadFromAccess();
         ResultSet rs=search_branch.select_dongle_uid();
         while(rs.next())
         {
        	 String D_UID    = rs.getString("Dongl_UID") ;
	            controlorder(D_UID);       

         }
         
	     /*   while(rs.next())
        {
//	        	String customer    = rs.getString("Customer") ;
	        	String Serial    = rs.getString("Software_Serial") ;
	            String SPF    = rs.getString("SPF") ;
//	            Date Date1    = rs.getDate("Date") ;
//	            String Version    = rs.getString("Version_Info") ;
//	            String Ultima    = rs.getString("Ultima_Version") ;
//	            String D_Type    = rs.getString("Dongol_Type") ;
//	            String D_Serial    = rs.getString("Dongol_Serial") ;
//	            String D_Name    = rs.getString("Dongole_Name") ;
//	            String D_UID    = rs.getString("Dongl_UID") ;
//	            String D_S    = rs.getString("Dongle_Serial") ;
//	            String[]DateArray=Date1.toString().split("-");
	            HashMap<String, String> Data = new HashMap<>();
	            Data.put("Serial", Serial); 
	            Data.put("SPF", SPF); 
//	            Data.put("Version", Version); 
//	            Data.put("Ultima", Ultima); 
//	            Data.put("D_Type", D_Type); 
//	            Data.put("D_Serial", D_Serial); 
//	            Data.put("D_Name", D_Name); 
//	            Data.put("D_UID", D_UID); 
//	            Data.put("D_S", D_S); 
//	            Data.put("customer", customer); 
//	            for(int i=0;i<DateArray.length;i++)
//	            { System.out.println(DateArray[i]);}
//	            controlorder(Data,DateArray);
	            controlorder(Data);       
        }*/
         

  }
     public void controlorder(String D_UID) throws InterruptedException, SQLException
     {
    	 WebElement Dongle_UID = 
    	 driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_txt_DongleUID\"]"));
         Thread.sleep(1000);
         Dongle_UID.clear();
         Dongle_UID.sendKeys(D_UID);
         WebElement search=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_btn_Search\"]"));
         search.click();//search click
         WebElement list = driver.findElement(By.xpath("//*[@id=\"list\"]"));
    	 Thread.sleep(1000);
    	//List is list of orders
         if(list.isDisplayed())
         {
        	 Thread.sleep(1000);
        	 System.out.println(D_UID+" dongle is existed before");

        	 //Update_button
//        	WebElement Update_button =driver.findElement(By.xpath("//*[@id=\"m1\"]/td[9]/center/img"));
//        	Update_button.click();
//         	Update_order(Data,DateArray);

        	///////////////---------------------------------------------
         }
         else
         { 
        	 Thread.sleep(1000);
        	 System.out.println(D_UID+" dongle is not existed before");
        	 Add_New_order(D_UID);
         }
         
     }
     
//     public void controlorder(HashMap<String, String> Data,String[]DateArray) throws InterruptedException
    /*
     public void controlorder(HashMap<String, String> Data2) throws InterruptedException, SQLException
     {
    	 
            WebElement soft_serial = driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_txt_SoftwareSerial\"]"));
             Thread.sleep(1000);
             soft_serial.clear();
             if(Data2.get("Serial")!=null) {
             soft_serial.sendKeys(Data2.get("Serial").toString());
             
             WebElement search=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_btn_Search\"]"));
             search.click();//search click
             WebElement list = driver.findElement(By.xpath("//*[@id=\"list\"]"));
        	 Thread.sleep(1000);

             //List is list of orders
             if(list.isDisplayed())
             {
            	 Thread.sleep(1000);
            	 System.out.println(Data2.get("Serial").toString()+" is existed before");

            	 //Update_button
//            	WebElement Update_button =driver.findElement(By.xpath("//*[@id=\"m1\"]/td[9]/center/img"));
//            	Update_button.click();
//             	Update_order(Data,DateArray);
	
            	///////////////---------------------------------------------
             }
             else
             { 
            	 Thread.sleep(1000);
            	 System.out.println(Data2.get("Serial")+" is not existed before");
            	 Add_order_2(Data2);
             }
             }
     }*/
     
     @SuppressWarnings("deprecation")
	public void Add_New_order(String DongleUID) throws InterruptedException, SQLException
     {
    	driver.navigate().refresh();
     	//Open Order
     	//driver.findElement(By.xpath("//*[@id=\"TreeView1t12\"]")).click();
     	//Open Add Order
     	driver.findElement(By.xpath("//*[@id=\"TreeView1t13\"]")).click();
     	//
        ReadFromAccess search_by1=new ReadFromAccess();
        ResultSet rs=search_by1.search_by_dongle_uid(DongleUID);
   //     String customer    = rs.getString("Customer") ;
        while(rs.next())
        {
    	String customername    = rs.getString("Customer") ;
    	String Serial    = rs.getString("Software_Serial") ;
        String SPF    = rs.getString("SPF") ;
        Date Date1    = rs.getDate("Date") ;
        String Version    = rs.getString("Version_Info") ;
        String Ultima    = rs.getString("Ultima_Version") ;
        String D_Type    = rs.getString("Dongol_Type") ;
        String D_Serial    = rs.getString("Dongol_Serial") ;
        String D_Name    = rs.getString("Dongole_Name") ;
        String D_UID    = rs.getString("Dongl_UID") ;
        String D_S    = rs.getString("Dongle_Serial") ;
        
        //String[]DateArray=Date1.toString().split("-");
        //System.out.println(customer+" is will be selected");

     	Select Customer=new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_CustomerName\"]")));
        System.out.println(customername+" is will be selected "+Date1.toString());
       // System.out.println(Date1.getYear()+1900+" "+Date1.getMonth()+1+" "+Date1.getDay()*7);
      String[] DATE=Date1.toString().split("-");
      System.out.println(DATE[0]+" "+DATE[1]+" "+DATE[2]);
        driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_CustomerName\"]")).click();
        Customer.selectByVisibleText(customername.trim());
       // Thread.sleep(10000);
        Thread.sleep(1000);

     	Select branch=new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_Branches\"]")));
     	branch.selectByIndex(1);
     	
     	WebElement desc=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_txt_Description\"]"));
     	desc.clear();
     	desc.sendKeys("Version "+Version+" SPF: "+SPF);
     	WebElement dater=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd\"]/tbody/tr[2]/td/fieldset/table/tbody/tr[4]/td[2]/img"));
     	dater.click();
     	Select year=new Select(driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[2]")));
     	// Date1.getYear();
     	//Thread.sleep(10000);
     	
     	year.selectByVisibleText(DATE[0]);
     	//Thread.sleep(10000);

     	Select month=new Select(driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]")));
     	month.selectByIndex(Date1.getMonth());
     	WebElement day=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]"
     			+ "/table//a[.='"+DATE[2]+"']"));
     	day.click();
     	Thread.sleep(100000);
     	//WebElement date=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_txt_OrderDate\"]"));
     	//date.sendKeys(Date1.toString());
     	WebElement nextbtn=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_StartNavigationTemplateContainerID_StartNextButton\"]"));
     	nextbtn.click();
        Thread.sleep(10000);
     	Select Softwatetype=new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_SoftwareTypes\"]")));
     	if(Version.contains("ultima"))
     	{
     		//Softwatetype.selectByIndex(35);;
     	}
        }
     
     }
     
  
     @AfterMethod
		public void tearDown() {
         
     	driver.quit();
     	System.out.println("Test Case Success");
     }
     
     
}
