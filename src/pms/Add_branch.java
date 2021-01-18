package pms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

public class Add_branch {
	
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
     public void Run() throws InterruptedException, SQLException {
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
        
         Thread.sleep(3000);
         driver.findElement(By.xpath("//*[@id=\"TreeView1t8\"]")).click(); //Open Branch Tree

         ReadFromAccess branch=new ReadFromAccess();
         ResultSet rs1=branch.search_customer();
	        while(rs1.next())
        {

	            String Customer    = rs1.getString("Customer") ;
	            String Country    = rs1.getString("Country") ;
	            System.out.println(Country+" "+Customer);
	            add_branch(Customer,Country);

        }
         

  }
     

     public void add_branch(String Customer,String Country) throws InterruptedException
     {
         driver.findElement(By.xpath("//*[@id=\"TreeView1t9\"]")).click();; // Open add Branch
         
        	 Select Customerselect = new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_Customers\"]")));
             System.out.println(Customer);
             List<WebElement> List=Customerselect.getOptions();
             List<String> strings = new ArrayList<String>();

             for(WebElement e : List){
                 strings.add(e.getText());
             }
             
             driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_Customers\"]")).click();
             Thread.sleep(1000);
             
             if(strings.contains(Customer))
             {
      	     Customerselect.selectByVisibleText(Customer);
      	     Thread.sleep(3000);
      	     WebElement branch_name=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_txt_BranchName\"]"));
      	     branch_name.clear();
      	     branch_name.sendKeys(Customer);
      	     Select Countryselect2 = new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_ddl_Country\"]")));
      	     System.out.println(Country);
      	    Countryselect2.selectByVisibleText(Country);
      	    driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_btn_Add\"]")).click();
            WebElement after_add=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_lbl_BranchAdd\"]/div/span"));
      	    System.out.println(Customer+" "+after_add.getText());

             }
             else
             {
                 System.out.println(Customer+" is not existed");

             }
     }

     
     @After
		public void tearDown() {
         
     	driver.quit();
     	System.out.println("Test Case Success");
     }
     
     

}
