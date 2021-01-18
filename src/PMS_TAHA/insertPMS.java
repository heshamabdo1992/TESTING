package PMS_TAHA;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class insertPMS {


    public Connection conn;
    public Statement stment ;
    public Connection conndongle;
    public Statement stmentdongle ;
    public WebDriver driver;
    public List<String> list;
    public ArrayList<String> donglelist; 

    public ResultSet rs = null;
    public ResultSet rsdongles = null;
    public FileWriter fw;
    public String path;

	 @Test(enabled=false)
	 public void addcustomers() throws SQLException, InterruptedException, IOException
	    {
			//text file
			 path="D:\\PMS\\Insert customers to PMS.txt";
	         addtotextfile("----------------------------------------", true);
	         addtotextfile("This is the log of adding customers to PMS", true);
	         addtotextfile("----------------------------------------", true);
	        
	         //
	         WebDriver driver= new ChromeDriver();
	         //driver.get("http://192.0.0.172/"); //Open PMS Page
	         driver.get("https://paxeracloud.com:8058"); //Open PMS Page

     		driver.manage().window().maximize();
     		driver.findElement(By.id("ContentPlaceHolder1_txt_UserName")).sendKeys("prod");
     		driver.findElement(By.id("ContentPlaceHolder1_txt_Password")).sendKeys("Prod1234");
     		driver.findElement(By.id("ContentPlaceHolder1_btn_Login")).click();Thread.sleep(1000);
     		Thread.sleep(1000);
	         //read from access file
	       	connect("SELECT * FROM sheet1");
	       	while(rs.next())
		       {
         	 		String Customer   = rs.getString("Customer") ;
		            String Country    = rs.getString("Country") ; 
		            if(list.contains(Customer)) {
		            addtotextfile(Customer+" alreday added before!", true);
		            addtotextfile("", true);
	        		addtotextfile("", true);
		            }else {
		            	//login
		        	   
		        		driver.findElement(By.xpath("//*[@id='TreeView1t4']")).click();
		        		Thread.sleep(500);
		        		driver.findElement(By.xpath("//*[@id='TreeView1t5']")).click();
		        		Thread.sleep(500);
		        		try {
		        			addtotextfile("Customer: "+Customer, true);
		        			driver.findElement(By.id("ContentPlaceHolder1_txt_CustomerName")).sendKeys(Customer);
		        		} catch (Exception e) {
		        			addtotextfile("(catch) Customer: "+Customer, true);
		        		}
		        		Select select=new Select(driver.findElement(By.id("ContentPlaceHolder1_ddl_Countries")));
		        		try {
		        			addtotextfile("Country: "+Country, true);
		        			select.selectByVisibleText(Country);
		        			addtotextfile("Country is correct", true);
		        		} catch (Exception e) {
		        			select.selectByVisibleText("Egypt");
		        			addtotextfile("(catch) Country changed to Egypt!", true);
		        		}
		        		driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_btn_Add']")).click();
		        		Thread.sleep(500);
		        		String message="";
		        		try {
		        			message = driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lbl_CustomerAdd']/div/span")).getText();
		        			addtotextfile(message, true);
		        		} catch (Exception e) {
		        			System.out.println("(catch) Can't add customer: "+Customer+" in country "+Country);
		        		}
		        		addtotextfile("", true);
		        		addtotextfile("", true);
		        		driver.findElement(By.xpath("//*[@id='TreeView1t4']")).click();
		            }
		            list.add(Customer);
		       }
	       	driver.quit();
	    }

	 @Test(enabled=false)
	 public void addbranches() throws InterruptedException, SQLException, IOException {
		 //text file
		 path="D:\\PMS\\Insert branches to PMS.txt";
         addtotextfile("----------------------------------------", true);
         addtotextfile("This is the log of adding branches to PMS", true);
         addtotextfile("----------------------------------------", true);
         
		//login
		WebDriver driver= new ChromeDriver();
		//driver.get("http://192.0.0.172/");
		 driver.get("https://paxeracloud.com:8058");

	    driver.manage().window().maximize();
	    driver.findElement(By.id("ContentPlaceHolder1_txt_UserName")).sendKeys("prod");
	    driver.findElement(By.id("ContentPlaceHolder1_txt_Password")).sendKeys("Prod1234");
	    driver.findElement(By.id("ContentPlaceHolder1_btn_Login")).click();Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='TreeView1t8']")).click();
		Thread.sleep(100);
		driver.findElement(By.xpath("//*[@id='TreeView1t9']")).click();
		Thread.sleep(100);
		connect("SELECT * FROM sheet1");

		while(rs.next())
	       {
				Select customers=new Select(driver.findElement(By.id("ContentPlaceHolder1_ddl_Customers")));
				Select countries=new Select(driver.findElement(By.id("ContentPlaceHolder1_ddl_Country")));
     	 		String Customer   = rs.getString("Customer") ;
	            String Country    = rs.getString("Country") ; 
	            Thread.sleep(100);
	            if(list.contains(Customer)) {
	            	addtotextfile("Branch checked before: " +Customer, true);
	           }else {

		           	try {
						customers.selectByVisibleText(Customer);
						Thread.sleep(100);
				        driver.findElement(By.id("ContentPlaceHolder1_txt_BranchName")).sendKeys(Customer);
				        countries.selectByVisibleText(Country);
				        driver.findElement(By.id("ContentPlaceHolder1_btn_Add")).click();
				        addtotextfile(Customer, true);
				        addtotextfile(driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lbl_BranchAdd']/div/span")).getText(), true);
					} catch (Exception e) {
						System.out.println("Can't add branch for customer: " + Customer);
					}
		            driver.findElement(By.xpath("//*[@id='TreeView1t8']")).click();
		            driver.findElement(By.xpath("//*[@id='TreeView1t8']")).click();
		    		Thread.sleep(100);
		    		driver.findElement(By.xpath("//*[@id='TreeView1t9']")).click();
	           }
	            list.add(Customer);
	            addtotextfile("-------------", true);
	       }
		addtotextfile("", true);
		addtotextfile("", true);
		driver.quit();
	 }
	 
	 @Test(enabled=true)
	 public void adddongles() throws InterruptedException, SQLException, IOException {
		 Date date = new Date() ;
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;

		 //text file
		 path="D:\\PMS\\Insert dongle to PMS"+dateFormat.format(date)+".txt";
         addtotextfile("----------------------------------------", true);
         addtotextfile("This is the log of adding dongles to PMS", true);
         addtotextfile("----------------------------------------", true);
         
		//login
		WebDriver driver= new ChromeDriver();
		// driver.get("http://192.0.0.172/");
		 driver.get("https://paxeracloud.com:8058");

	    driver.manage().window().maximize();
	    driver.findElement(By.id("ContentPlaceHolder1_txt_UserName")).sendKeys("prod");
	    driver.findElement(By.id("ContentPlaceHolder1_txt_Password")).sendKeys("Prod1234");
	    driver.findElement(By.id("ContentPlaceHolder1_btn_Login")).click();
	    Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='TreeView1t16']")).click();
		Thread.sleep(100);
		driver.findElement(By.xpath("//*[@id='TreeView1t17']")).click();
		Thread.sleep(100);
		connect("SELECT * FROM sheet1 "
				+ "where ([dongl_UID] LIKE '%0%' "
				+ "or [dongl_UID] LIKE '%1%'"
				+ "or [dongl_UID] LIKE '%2%'"
				+ "or [dongl_UID] LIKE '%3%'"
				+ "or [dongl_UID] LIKE '%4%'"
				+ "or [dongl_UID] LIKE '%5%'"
				+ "or [dongl_UID] LIKE '%6%'"
				+ "or [dongl_UID] LIKE '%7%'"
				+ "or [dongl_UID] LIKE '%8%'"
				+ "or [dongl_UID] LIKE '%9%'"
				+ ")"
				+ " and [index] >= 7000 and[index] <= 8000");

		while(rs.next())
	       {
				Select donglelocations 	=new Select(driver.findElement(By.id("ContentPlaceHolder1_ddl_Location")));
				Select dongletype		=new Select(driver.findElement(By.id("ContentPlaceHolder1_ddl_DongleType")));
	            String Country  = rs.getString("Country") ; 
     	 		String Type		= rs.getString("Dongol_Type") ;
     	 		String UID	    = rs.getString("dongl_UID") ; 
     	 		String Serial   = rs.getString("Dongle_Serial") ;
				if(Serial !=null && !Serial.isEmpty() && UID !=null && !UID.isEmpty()) {
				    Thread.sleep(100);
				    if(list.contains(Serial)) {
				    	addtotextfile("Dongle serial: " +Serial, true);
				    	addtotextfile("Dongle checked before!", true);
				   }else {
				
				       	try {
				       		//select dongle location
				       		addtotextfile("-------------", true);
				       		addtotextfile("Country     : " + Country, true);
				       		addtotextfile("Dongle type : " + Type, true);
				       		addtotextfile("Serial      : " + Serial, true);
				       		addtotextfile("UID         : "+ UID, true);
				       		
				       		System.out.println();
				       		System.out.println("------------------------------");
				       		System.out.println("Country     : " + Country);
				       		System.out.println("Dongle type : " + Type);
				       		System.out.println("Serial      : " + Serial);
				       		System.out.println("UID         : "+ UID);
				       		
				       		if(Country.contains("usa") || Country.contains("USA") ) {
				       			donglelocations.selectByValue("2");
				       			addtotextfile("Selected location: USA", true);
				       		}else {
				       			donglelocations.selectByValue("1");
				       			addtotextfile("Selected location: Egypt", true);
				       		}
				       		
				       		
				       		//select dongle type
				       		if(Type.contains("reen")) {
				       			if(Type.contains("net") || Type.contains("Net") || Type.contains("NET") || Type.contains("nET")) {
				       				dongletype.selectByValue("4");
				       				addtotextfile("Selected type: Paxera NET", true);
				       			}else {
				       				dongletype.selectByValue("3");
				       				addtotextfile("Selected type: Paxera WS", true);
				       			}
				       		}else if(Type.contains("MILLENMED") || Type.contains("millinmed")) {
				       			if(Type.contains("net") || Type.contains("Net") || Type.contains("NET") || Type.contains("nET")) {
				       				dongletype.selectByValue("5");
				       				addtotextfile("Selected type: MILLENMED ROCKEY NET USB", true);
				       			}else {
				       				dongletype.selectByValue("2");
				       				addtotextfile("Selected type: MILLENMED ROCKEY USB ND", true);
				       			}
				       		}else{
				       			if(Type.contains("net") || Type.contains("Net") || Type.contains("NET") || Type.contains("nET")) {
				       				dongletype.selectByValue("6");
				       				addtotextfile("Selected type: ROCKEY NET USB RED", true);
				       			}else {
				       				dongletype.selectByValue("1");
				       				addtotextfile("Selected type: ROCKEY USB ND", true);
				       			}
				       		}
				       		
				       		
				       		//select dongle category
				       		if(Type.contains("reen")) {
				       			Thread.sleep(1000);
				       			if(Type.contains("1")) {
				       				Select donglecategory 	=new Select(driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddl_DongleCategories']")));
					       			if(Type.contains("net") || Type.contains("Net") || Type.contains("NET") || Type.contains("nET")) {
					       				donglecategory.selectByVisibleText("Green 1_Net");
					       				addtotextfile("Selected category: Green 1_Net", true);
					       			}else {
					       				donglecategory.selectByVisibleText("Green 1");
					       				addtotextfile("Selected category: Green 1", true);
					       			}
				       			}else if(Type.contains("2")) {
				       				Select donglecategory 	=new Select(driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddl_DongleCategories']")));
				       				if(Type.contains("net") || Type.contains("Net") || Type.contains("NET") || Type.contains("nET")) {
					       				donglecategory.selectByVisibleText("Green 2_Net");
					       				addtotextfile("Selected category: Green 2_Net", true);
					       			}else {
					       				donglecategory.selectByVisibleText("Green 2");
					       				addtotextfile("Selected category: Green 2", true);
					       			}
				       			}else if(Type.contains("3")) {
				       				Select donglecategory 	=new Select(driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_ddl_DongleCategories']")));
				       				if(Type.contains("net") || Type.contains("Net") || Type.contains("NET") || Type.contains("nET")) {
					       				donglecategory.selectByVisibleText("Green 3_NET");
					       				addtotextfile("Selected category: Green 3_Net", true);
					       			}else {
					       				donglecategory.selectByVisibleText("Green 3");
					       				addtotextfile("Selected category: Green 3", true);
					       			}
				       			}else {
				       				addtotextfile("Unknown Green dongle: "+ Type, true);
				       			}
				       		}
				       		
				       		driver.findElement(By.id("ContentPlaceHolder1_txt_DongleSerial")).clear();
				       		driver.findElement(By.id("ContentPlaceHolder1_txt_DongleUID")).clear();
				       		if(!Serial.isEmpty()) {
				       		driver.findElement(By.id("ContentPlaceHolder1_txt_DongleSerial")).sendKeys(Serial);
				       		}else {
					       		driver.findElement(By.id("ContentPlaceHolder1_txt_DongleSerial")).sendKeys(UID);

				       		}
				       		driver.findElement(By.id("ContentPlaceHolder1_txt_DongleUID")).sendKeys(UID);
				       		driver.findElement(By.id("ContentPlaceHolder1_btn_Add")).click();
				       		Thread.sleep(3000);
				       		addtotextfile(driver.findElement(By.xpath("//*[@id='ContentPlaceHolder1_lbl_DongleAdd']/div/span")).getText(), true);
				       	} catch (Exception e) {
				       		addtotextfile("(catch) Can't add dongle!", true);
						}
				
				   }
				}else {
					addtotextfile("Can't add dongle!", true);
					addtotextfile("Serial : " + Serial, true);
					addtotextfile("UID : " + UID, true);
				}
			    list.add(Serial);
			    addtotextfile("-------------", true);
			    addtotextfile("", true);
			    addtotextfile("", true);	    
	       }
		driver.quit();
	 }
	 
	 @Test(enabled=false)
	 public void getfileheaders() throws InterruptedException, SQLException {

		connect("select * from  sheet1 where 1=0");

		for(int i=1; i<21 ;i++)
	       {
				System.out.println(rs.getMetaData().getColumnName(i));
	           	System.out.println("-------------");
	       }
	 }
	  
	 @Test(enabled=false)
	 public void addorder() throws SQLException, InterruptedException, IOException
	    {
			//text file
			 path="G:\\PMS\\Insert orders to PMS.txt";
	         addtotextfile("----------------------------------------", true);
	         addtotextfile("This is the log of adding order to PMS", true);
	         addtotextfile("----------------------------------------", true);
	        
	         /*/
	        WebDriver driver= new ChromeDriver();
     		driver.get("https://paxeracloud.com:8058/Login.aspx");
     		driver.manage().window().maximize();
     		driver.findElement(By.id("ContentPlaceHolder1_txt_UserName")).sendKeys("taha.hussein");
     		driver.findElement(By.id("ContentPlaceHolder1_txt_Password")).sendKeys("*********");
     		driver.findElement(By.id("ContentPlaceHolder1_btn_Login")).click();
     		Thread.sleep(1000);*/
	         //read from access file
	       	connect("select distinct Dongle_Serial  from  sheet1");
	        donglelist = new ArrayList<String>();
	       	while(rs.next()){
		            String Serial		    = rs.getString("Dongle_Serial") ; 
	            	addtotextfile("Serial: "+Serial, true);
	            	if(Serial==null) {
			            	try {
								addtotextfile("Serial is null", true);
							} catch (Exception e) {
								addtotextfile("(catch) serial is null!", true);
							} 
			        }
	            	if(donglelist.contains(Serial)) {
			            	try {
								addtotextfile("Records of dongle: "+ Serial+" already listed above!", true);
							} catch (Exception e) {
								addtotextfile("(catch) serial in list!", true);
							} 
			        }
	            	if(!donglelist.contains(Serial)){
			            getallrecordsofdongleserial(Serial);
						//addtotextfile("These are all records for dongle serial: "+Serial, true);
			            WebDriverWait wait=new WebDriverWait(driver, null, null, 0, 0);

			            while(rsdongles.next()) {
		            		String Country			= rsdongles.getString("Country") ; 
		            		String Date		   	 	= rsdongles.getString("Date") ; 
		            		String Customer	    	= rsdongles.getString("Customer") ; 
		            		String Version			= rsdongles.getString("Version_Info") ; 
		            		String UltimaVersion	= rsdongles.getString("Ultima_Version") ; 
		            		String SoftwareSerial	= rsdongles.getString("Software_Serial") ; 
		            		String UID		   		= rsdongles.getString("Dongl_UID") ; 
		            		String SPF		    	= rsdongles.getString("SPF") ; 
			            	addtotextfile(Date+"   ", false);
			            	addtotextfile(Country+"   ", false);
			            	addtotextfile(Customer+"   ", false);
			            	addtotextfile(Version+"   ", false);
			            	addtotextfile(UltimaVersion+"   ", false);
			            	addtotextfile(SoftwareSerial+"   ", false);
			            	addtotextfile(UID+"   ", false);
			            	addtotextfile(SPF+"   ", false);
					         try {

							} catch (Exception e) {
								addtotextfile("   ", true);
								addtotextfile("(catch) Can't find records for dongle serial: "+Serial, true);
							}
								addtotextfile("---", true);
				       	}
		            }
	            	donglelist.add(Serial);
					addtotextfile("", true);

		       }
	       	//driver.quit();
	    }
 
	 public void teardown() {
			driver.quit();
	 }
	 
	 public void connect(String query) throws SQLException {
		conn=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Hesham\\Desktop\\Multiframe CT (Done)\\newsheet.accdb");
		stment= conn.createStatement();  
	    //String qry = "SELECT Country, Type, UID, Serial FROM sheet1 where UID Is Not Null and Serial Is Not Null";
	    rs= stment.executeQuery(query);
        list = new ArrayList<>();
	 }

	 public void getallrecordsofdongleserial(String dongleserial) throws SQLException {
		conndongle=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Hesham\\Desktop\\Multiframe CT (Done)\\newsheet.accdb");
		stmentdongle= conn.createStatement();  
	    String qrydongle = "SELECT * FROM Sheet1 where Dongle_Serial='"+dongleserial+"'";
	    rsdongles= stment.executeQuery(qrydongle);
	 }

	 public void addtotextfile(String text, Boolean newline) throws IOException {
		
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
        try {
			bw.write(text);
		} catch (Exception e) {
			bw.write("NULLTAHA");
		}
        if(newline) {
            bw.newLine();
        }
		bw.close();
	}

	public boolean donglelistedbefore(String serial) {
		 	/*boolean exist=false;
		 	int i=0;
			while (i <donglelist.size()) {
			System.out.println("Dongle serial"+serial);
			System.out.println("Dongle in posittion " + String.valueOf(i) + " " + donglelist.get(i));
			if(donglelist.get(i)==serial) {
				System.out.println("Dongle "+serial+" already listed before!");
				exist=true;
			}else {
				i++;
			}
		}
			return exist;*/
		for(String str: donglelist) {
		    if(str.contains(serial))
		       return true;
		}
		return false;
	 }



}
