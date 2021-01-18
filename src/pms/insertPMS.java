package pms;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


public class insertPMS {


    public Connection conn;
    public Statement stment ;
    public WebDriver driver;
    public List<String> list;
    public ResultSet rs = null;
    public FileWriter fw;
    public String path;

	 @Test(enabled=false)
	 public void addcustomers() throws SQLException, InterruptedException, IOException
	    {
			//text file
			 path="D:\\PMS\\Insert customers to PMS.txt";
	         addtotextfile("----------------------------------------");
	         addtotextfile("This is the log of adding customers to PMS");
	         addtotextfile("----------------------------------------");
	        
	         //read from access file
	       	connect();
	       	while(rs.next())
		       {
         	 		String Customer   = rs.getString("Customer") ;
		            String Country    = rs.getString("Country") ; 
		            if(list.contains(Customer)) {
		            addtotextfile(Customer+" alreday added before!");
		            }else {
			            addcustomer(Customer, Country);
		            }
		            list.add(Customer);
		       }
	    }

	 @Test(enabled=false)
	 public void addbranches() throws InterruptedException, SQLException, IOException {
		 //text file
		 path="D:\\PMS\\Insert branches to PMS.txt";
         addtotextfile("----------------------------------------");
         addtotextfile("This is the log of adding branches to PMS");
         addtotextfile("----------------------------------------");
         
		//login
		WebDriver driver= new ChromeDriver();
	    driver.get("https://paxeracloud.com:8058");
	    driver.manage().window().maximize();
	    driver.findElement(By.id("ContentPlaceHolder1_txt_UserName")).sendKeys("taha.hussein");
	    driver.findElement(By.id("ContentPlaceHolder1_txt_Password")).sendKeys("01113471786thk");
	    driver.findElement(By.id("ContentPlaceHolder1_btn_Login")).click();Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='TreeView1t8']")).click();
		Thread.sleep(100);
		driver.findElement(By.xpath("//*[@id='TreeView1t9']")).click();
		Thread.sleep(100);
		connect();

		while(rs.next())
	       {
				Select customers=new Select(driver.findElement(By.id("ContentPlaceHolder1_ddl_Customers")));
				Select countries=new Select(driver.findElement(By.id("ContentPlaceHolder1_ddl_Country")));
     	 		String Customer   = rs.getString("Customer") ;
	            String Country    = rs.getString("Country") ; 
	            Thread.sleep(100);
	            if(list.contains(Customer)) {
	            	addtotextfile("Branch checked before: " +Customer);
	           }else {

		           	try {
						customers.selectByVisibleText(Customer);
						Thread.sleep(100);
				        driver.findElement(By.id("ContentPlaceHolder1_txt_BranchName")).sendKeys(Customer);
				        countries.selectByVisibleText(Country);
				        driver.findElement(By.id("ContentPlaceHolder1_btn_Add")).click();
				        addtotextfile(Customer);
				        addtotextfile(driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lbl_BranchAdd']/div/span")).getText());
					} catch (Exception e) {
						System.out.println("Can't add branch for customer: " + Customer);
					}
		            driver.findElement(By.xpath("//*[@id='TreeView1t8']")).click();
		            driver.findElement(By.xpath("//*[@id='TreeView1t8']")).click();
		    		Thread.sleep(100);
		    		driver.findElement(By.xpath("//*[@id='TreeView1t9']")).click();
	           }
	            list.add(Customer);
	            addtotextfile("-------------");
	       }
		addtotextfile("");
		addtotextfile("");
		driver.quit();
	 }
	 
	 @Test(enabled=true)
	 public void adddongles() throws InterruptedException, SQLException, IOException {
			
		 //text file
		 path="D:\\PMS\\Insert dongle to PMS.txt";
         addtotextfile("----------------------------------------");
         addtotextfile("This is the log of adding dongles to PMS");
         addtotextfile("----------------------------------------");
         
		//login
		WebDriver driver= new ChromeDriver();
	    driver.get("https://paxeracloud.com:8058");
	    driver.manage().window().maximize();
	    driver.findElement(By.id("ContentPlaceHolder1_txt_UserName")).sendKeys("taha.hussein");
	    driver.findElement(By.id("ContentPlaceHolder1_txt_Password")).sendKeys("01113471786thk");
	    driver.findElement(By.id("ContentPlaceHolder1_btn_Login")).click();
	    Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='TreeView1t16']")).click();
		Thread.sleep(100);
		driver.findElement(By.xpath("//*[@id='TreeView1t17']")).click();
		Thread.sleep(100);
		connect();

		while(rs.next())
	       {
				Select donglelocations 	=new Select(driver.findElement(By.id("ContentPlaceHolder1_ddl_Location")));
				Select dongletype		=new Select(driver.findElement(By.id("ContentPlaceHolder1_ddl_DongleType")));
	            String Country  = rs.getString("Country") ; 
     	 		String Type		= rs.getString("Type") ;
     	 		String UID	    = rs.getString("UID") ; 
     	 		String Serial   = rs.getString("Serial") ;
				if(Serial !=null && !Serial.isEmpty() && UID !=null && !UID.isEmpty()) {
				    Thread.sleep(100);
				    if(list.contains(Serial)) {
				    	addtotextfile("Dongle serial: " +Serial);
				    	addtotextfile("Dongle checked before!");
				   }else {
				
				       	try {
				       		//select dongle location
				       		addtotextfile("-------------");
				       		addtotextfile("Country     : " + Country);
				       		addtotextfile("Dongle type : " + Type);
				       		addtotextfile("Serial      : " + Serial);
				       		addtotextfile("UID         : "+ UID);
				       		
				       		System.out.println();
				       		System.out.println("------------------------------");
				       		System.out.println("Country     : " + Country);
				       		System.out.println("Dongle type : " + Type);
				       		System.out.println("Serial      : " + Serial);
				       		System.out.println("UID         : "+ UID);
				       		
				       		if(Country.contains("united") || Country.contains("United") || Country.contains("states") || Country.contains("States")) {
				       			donglelocations.selectByValue("2");
				       			addtotextfile("Selected location: USA");
				       		}else {
				       			donglelocations.selectByValue("1");
				       			addtotextfile("Selected location: Egypt");
				       		}
				       		
				       		
				       		//select dongle type
				       		if(Type.contains("reen")) {
				       			if(Type.contains("net") || Type.contains("Net") || Type.contains("NET") || Type.contains("nET")) {
				       				dongletype.selectByValue("4");
				       				addtotextfile("Selected type: Paxera NET");
				       			}else {
				       				dongletype.selectByValue("3");
				       				addtotextfile("Selected type: Paxera WS");
				       			}
				       		}else if(Type.contains("MILLENMED") || Type.contains("millinmed")) {
				       			if(Type.contains("net") || Type.contains("Net") || Type.contains("NET") || Type.contains("nET")) {
				       				dongletype.selectByValue("5");
				       				addtotextfile("Selected type: MILLENMED ROCKEY NET USB");
				       			}else {
				       				dongletype.selectByValue("2");
				       				addtotextfile("Selected type: MILLENMED ROCKEY USB ND");
				       			}
				       		}else{
				       			if(Type.contains("net") || Type.contains("Net") || Type.contains("NET") || Type.contains("nET")) {
				       				dongletype.selectByValue("6");
				       				addtotextfile("Selected type: ROCKEY NET USB RED");
				       			}else {
				       				dongletype.selectByValue("1");
				       				addtotextfile("Selected type: ROCKEY USB ND");
				       			}
				       		}
				       		
				       		
				       		//select dongle category
				       		if(Type.contains("reen")) {
				       			Thread.sleep(1000);
				       			if(Type.contains("1")) {
				       				Select donglecategory 	=new Select(driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddl_DongleCategories']")));
					       			if(Type.contains("net") || Type.contains("Net") || Type.contains("NET") || Type.contains("nET")) {
					       				donglecategory.selectByVisibleText("Green 1_Net");
					       				addtotextfile("Selected category: Green 1_Net");
					       			}else {
					       				donglecategory.selectByVisibleText("Green 1");
					       				addtotextfile("Selected category: Green 1");
					       			}
				       			}else if(Type.contains("2")) {
				       				Select donglecategory 	=new Select(driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddl_DongleCategories']")));
				       				if(Type.contains("net") || Type.contains("Net") || Type.contains("NET") || Type.contains("nET")) {
					       				donglecategory.selectByVisibleText("Green 2_Net");
					       				addtotextfile("Selected category: Green 2_Net");
					       			}else {
					       				donglecategory.selectByVisibleText("Green 2");
					       				addtotextfile("Selected category: Green 2");
					       			}
				       			}else if(Type.contains("3")) {
				       				Select donglecategory 	=new Select(driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddl_DongleCategories']")));
				       				if(Type.contains("net") || Type.contains("Net") || Type.contains("NET") || Type.contains("nET")) {
					       				donglecategory.selectByVisibleText("Green 3_Net");
					       				addtotextfile("Selected category: Green 3_Net");
					       			}else {
					       				donglecategory.selectByVisibleText("Green 3");
					       				addtotextfile("Selected category: Green 3");
					       			}
				       			}else {
				       				addtotextfile("Unknown Green dongle: "+ Type);
				       			}
				       		}
				       		
				       		driver.findElement(By.id("ContentPlaceHolder1_txt_DongleSerial")).clear();
				       		driver.findElement(By.id("ContentPlaceHolder1_txt_DongleUID")).clear();
				       		driver.findElement(By.id("ContentPlaceHolder1_txt_DongleSerial")).sendKeys(Serial);
				       		driver.findElement(By.id("ContentPlaceHolder1_txt_DongleUID")).sendKeys(UID);
				       		driver.findElement(By.id("ContentPlaceHolder1_btn_Add")).click();
				       		Thread.sleep(3000);
				       		addtotextfile(driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lbl_DongleAdd']/div/span")).getText());
				       	} catch (Exception e) {
				       		addtotextfile("(catch) Can't add dongle!");
						}
				
				   }
				}else {
					addtotextfile("Can't add dongle!");
					addtotextfile("Serial : " + Serial);
					addtotextfile("UID : " + UID);
				}
			    list.add(Serial);
			    addtotextfile("-------------");
			    addtotextfile("");
			    addtotextfile("");	    
	       }
		driver.quit();
	 }
	 
	 @Test(enabled=false)
	 public void getfileheaders() throws InterruptedException, SQLException {

		connect();

		for(int i=1; i<15 ;i++)
	       {
				System.out.println(rs.getMetaData().getColumnName(i));
	           	System.out.println("-------------");
	       }
		driver.quit();
	 }
	  
	 public void addcustomer(String name, String country) throws InterruptedException, IOException {
		
		//login
	    WebDriver driver= new ChromeDriver();
		driver.get("https://paxeracloud.com:8058");
		driver.manage().window().maximize();
		driver.findElement(By.id("ContentPlaceHolder1_txt_UserName")).sendKeys("prod");
		driver.findElement(By.id("ContentPlaceHolder1_txt_Password")).sendKeys("Prod1234");
		driver.findElement(By.id("ContentPlaceHolder1_btn_Login")).click();Thread.sleep(1000);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='TreeView1t4']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id='TreeView1t5']")).click();
		Thread.sleep(500);
		try {
			addtotextfile("Customer is: "+name);
			driver.findElement(By.id("ContentPlaceHolder1_txt_CustomerName")).sendKeys(name);
		} catch (Exception e1) {
			addtotextfile("Customer is: "+name);
		}
		Select select=new Select(driver.findElement(By.id("ContentPlaceHolder1_ddl_Countries")));
		try {
			addtotextfile("Country is: "+country);
			select.selectByVisibleText(country);
			addtotextfile("Country is correct");
		} catch (Exception e) {
			select.selectByVisibleText("Egypt");
			addtotextfile("Country changed to Egypt!");
		}
		driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btn_Add']")).click();
		Thread.sleep(500);
		String message="";
		try {
			message = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lbl_CustomerAdd']/div/span")).getText();
			addtotextfile(message);
			addtotextfile(name);
		} catch (Exception e) {
			System.out.println("Can't add customer: "+name+" in country"+country);
		}
		addtotextfile("");
		addtotextfile("");
		driver.quit();
	 }

	 public void teardown() {
			driver.quit();
	 }
	 
	 public void connect() throws SQLException {
		conn=DriverManager.getConnection("jdbc:ucanaccess://D:\\PMS\\PMS.accdb");
		stment= conn.createStatement();  
	    String qry = "SELECT Country, Type, UID, Serial FROM PMS where UID Is Not Null and Serial Is Not Null";
	    rs= stment.executeQuery(qry);
        list = new ArrayList<>();
	 }

	public void addtotextfile(String text) throws IOException {
		
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
        bw.write(text);
        bw.newLine();
		bw.close();
	}

}
