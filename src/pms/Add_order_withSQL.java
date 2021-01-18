package pms;

import java.io.BufferedWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Add_order_withSQL {
	public static WebDriver driver;
    WebDriverWait wait;
    public String path;
    Date date;
    SimpleDateFormat dateFormat;
     @BeforeMethod
    public void setUp() {
    	 ChromeOptions chromeOptions = new ChromeOptions();

    	 driver = new ChromeDriver(chromeOptions);  
         driver.manage().window().maximize();
         wait = new WebDriverWait(driver, 6, 2000);
         	//text file
    	 date = new Date() ;
    	 dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
    	 path="D:\\PMS_Log\\log"+dateFormat.format(date)+".txt";
        }
     
     @Test
     public void Add_order_1() throws Exception {
//         driver.get("http://192.0.0.172/"); //Open PMS Page
        driver.get("https://paxeracloud.com:8058"); //Open PMS Page

         WebElement Username =driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_txt_UserName\"]"));// Insert Username and Password
         Username.clear();
//         Username.sendKeys("hesham.gharib");
        Username.sendKeys("prod");

        // System.out.println("Adding Username");
         WebElement Password =driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_txt_Password\"]")); //Password
         Password.clear();
//         Password.sendKeys("Test1234");
         Password.sendKeys("Prod1234");

        // System.out.println("Adding Password");
         driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_btn_Login\"]")).click(); // Login Click
         Thread.sleep(5000);
//         driver.findElement(By.xpath("//*[@id=\"TreeView1t12\"]")).click(); //Open Order Tree
//         driver.findElement(By.xpath("//*[@id=\"TreeView1t14\"]")).click();; // Open 	Control Order

         driver.findElement(By.xpath("//*[@id=\"TreeView1t4\"]")).click(); //Open Order Tree
         driver.findElement(By.xpath("//*[@id=\"TreeView1t6\"]")).click();; // Open 	Control Order
        
         Thread.sleep(1000);
         
         ReadFromSQL search_dongle=new ReadFromSQL();
         ResultSet rs=search_dongle.select_dongle_uid();
         while(rs.next())
         {
        	 String Dongle_UID    = rs.getString("Dongle_UID") ;
        	// if ( Dongle_UID >="1451660071")
        	// {
	            controlorder(Dongle_UID);       
        	// }
         }

  }
     
     public void controlorder(String D_UID) throws InterruptedException, SQLException, ClassNotFoundException, IOException
     {
    	 WebElement Dongle_UID = 
    	 driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_txt_DongleUID\"]"));
         Thread.sleep(1000);
         Dongle_UID.clear();
         Dongle_UID.sendKeys(D_UID);
         WebElement search=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_btn_Search\"]"));
         Thread.sleep(1000);

         search.click();//search click
    	 Thread.sleep(10000);

         WebElement list = driver.findElement(By.xpath("//*[@id=\"list\"]"));
    	//List is list of orders
         if(list.isDisplayed())
         {
        	 //update case
        	 Thread.sleep(1000);
        	 System.out.println(D_UID+" dongle is existed before");
       		addtotextfile(D_UID+" dongle is existed before", true);
 	        addtotextfile("-----------------------", true);
//             driver.findElement(By.xpath("//*[@id=\"TreeView1t14\"]")).click();; // Open 	Control Order
// 	         driver.findElement(By.xpath("//*[@id=\"TreeView1t6\"]")).click();; // Open 	Control Order

        	 //Update_button
//        	WebElement Update_button =driver.findElement(By.xpath("//*[@id=\"m1\"]/td[9]/center/img"));
//        	Update_button.click();
//         	update_order2(D_UID);

        	///////////////---------------------------------------------
         }
         else
         { 
        	//New case
        	 Thread.sleep(1000);
        	 System.out.println(D_UID+" dongle is not existed before");
      		addtotextfile(D_UID+" dongle is not existed before", true);
 	        addtotextfile("-----------------------", true);
        	 Add_New_order(D_UID);
//             driver.findElement(By.xpath("//*[@id=\"TreeView1t14\"]")).click();; // to Open 	Add new Order
             driver.findElement(By.xpath("//*[@id=\"TreeView1t6\"]")).click();; // to Open 	Add new Order

         }
         
     }
       
	public void Add_New_order(String DongleUID) throws InterruptedException, SQLException, ClassNotFoundException, IOException
     {
    	driver.navigate().refresh();
     	//Open Order
     	//driver.findElement(By.xpath("//*[@id=\"TreeView1t12\"]")).click();
     	//Open Add Order
//     	driver.findElement(By.xpath("//*[@id=\"TreeView1t13\"]")).click();
     	driver.findElement(By.xpath("//*[@id=\"TreeView1t5\"]")).click();

     	//
     	ReadFromSQL search_by=new ReadFromSQL();
        ResultSet rs=search_by.search_by_dongle_uid(DongleUID);
   //   String customer    = rs.getString("Customer") ;
        List<String> customername= new ArrayList<String>();
        List<String> Serial= new ArrayList<String>();
        List<String> SPF  = new ArrayList<String>() ;
        List<Date> Date1 = new ArrayList<Date>() ;
        List<String> Version = new ArrayList<String>();
        List<String> Ultima = new ArrayList<String>();
        List<String> D_Type   = new ArrayList<String>() ;
        List<String>D_Serial= new ArrayList<String>() ;
        List<String>D_Name = new ArrayList<String>();
        List<String> D_UID = new ArrayList<String>();
        List<String> Dongle_SerialName = new ArrayList<String>();
        List<String> index_1  = new ArrayList<String>();
        List<String> country  = new ArrayList<String>();

        while(rs.next())
        {
        	
    	customername.add(rs.getString("Customer")) ;
    	//Serial.add( rs.getString("Software_Serial")) ;
    	if(rs.getString("Software_Serial")!=null)
    	{
    		Serial.add( rs.getString("Software_Serial")) ;
    	}
    	else
    	{
    		Serial.add("12345") ;
    	}
    	if(rs.getString("SPF")!=null)
    	{
        SPF.add(rs.getString("SPF")) ;
    	}
    	else
    	{
    		SPF.add("no SPF number") ;
    	}
        Date1.add( rs.getDate("Date") );
       
        //Version.add(rs.getString("Version_Info")) ;
    	
        if(rs.getString("Version_Info")!=null)
    	{
    		Version.add(rs.getString("Version_Info")) ;
    	}
    	else
    	{
    		Version.add("version not existed") ;
    	}
    	
    	if(rs.getString("Ultima_Version")!=null)
    	{
    		Ultima.add(rs.getString("Ultima_Version")) ;
    	}
    	else
    	{
    		Ultima.add("version not existed") ;
    	}  
        D_Type.add( rs.getString("Dongol_Type") );
        D_Serial.add(rs.getString("Dongol_Serial")) ;
        D_Name.add( rs.getString("Dongole_Name")) ;
        D_UID.add( rs.getString("Dongle_UID")) ;
        Dongle_SerialName.add( rs.getString("Dongle_SerialName")) ;
        index_1.add( rs.getString("index")) ;
        country.add(rs.getString("Country"));

       // System.out.println(customername+Serial+SPF+Date1+Version+Ultima
        	//	+ D_Type+ D_Serial+ D_Name+D_UID+D_S);
        }


        System.out.println(customername.get(0)+" is will be selected "+Date1.get(0).toString());
	    addtotextfile("-----------1-start to adding------------", true);
	   // addtotextfile(customername.get(0)+" is will be selected "+Date1.get(0).toString(), true);

        Thread.sleep(1000);
if(SPF.get(0) == null)
{
	SPF.add(0, " ");;
	}
if(Version.get(0)== null)
{
	Version.add(0, " ");
	}
if(Ultima.get(0)== null)
{
	Ultima.add(0, " ");
	}
        addtotextfile("---------start to add--------------", true);
        addtotextfile("Customer : "+customername.get(0)+" is will be selected "
        +" Date"+Date1.get(0).toString()+" SPF:"+SPF.get(0).toString(), true);
	    addtotextfile("-----------------------", true);
        driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_CustomerName\"]")).click();
      // try {
     	Select Customer=new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_CustomerName\"]")));

        List<WebElement> List=Customer.getOptions();
      
        System.out.println("List Size : "+List.size());
      //  String customername2="";
      //  int indexx=-1;
   /////////////////////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     
     /*   
       try {
        WebElement customer_name_option=
        driver.findElement(
        		By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_CustomerName\"]/*[.='"
        +customername.get(0).trim()+"']"));

            if(customer_name_option !=null)
        {*/
        /////////////////////start to select customer>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
       String [] Selectcustomer=  SelectElementContainsItemText(Customer,customername.get(0).trim()).split("\\^");
    	System.out.println("cond : "+Selectcustomer[0]+"^^^^^"+Selectcustomer[1]);
        if(Selectcustomer[0].contains("true")) {
         //	Select Customer2=new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_CustomerName\"]")));

        	System.out.println("customer name: ("+Selectcustomer[1].trim()+")");
        Customer.selectByVisibleText(Selectcustomer[1].trim());
       // Thread.sleep(10000);
        Thread.sleep(1000);
        
        /////////////////////start to select branch>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   

     	Select branch=new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_Branches\"]")));
     	branch.selectByIndex(1);
     	
     	WebElement desc=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_txt_Description\"]"));
     	desc.clear();
     	desc.sendKeys(" SPF: "+SPF.get(0)+"\n");
     	for (int x = 0;x<Version.size();x++) {
     	desc.sendKeys(" Software: "+Ultima.get(x)+"\n Version "+Version.get(x)+"\n");
     	}
        String[] DATE=Date1.get(0).toString().split("-");
        
        /////////////////////start to select SPF date >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   

        WebElement dater=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd\"]/tbody/tr[2]/td/fieldset/table/tbody/tr[4]/td[2]/img"));
     	dater.click();
     	
     	Select year=new Select(driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[2]")));
     	year.selectByVisibleText(DATE[0]);

     	Select month=new Select(driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]")));
     	month.selectByIndex(Date1.get(0).getMonth());
     	int daynum =Integer.parseInt(DATE[2]);
     	WebElement day=driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]"
     			+ "/table//a[.='"+daynum+"']"));
     	day.click();
        /////////////////////click to next page >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   

     	WebElement nextbtn=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_StartNavigationTemplateContainerID_StartNextButton\"]"));
     	nextbtn.click();
        Thread.sleep(1000);
     	//Select Softwatetype1=new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_SoftwareTypes\"]")));
     	//driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_SoftwareTypes\"]")).click();
     	
        
        ///////////////////to insert software and serial 
        for(int x =0;x<Version.size();x++) {
     		System.out.println("x is "+x+" - "+Version.get(x));
       		addtotextfile("x is "+x+" - "+Version.get(x), true);
    	   // addtotextfile("-----------------------", true);
       	
       		
       //select Software type
     	Select Softwatetype2=new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_SoftwareTypes\"]")));
     	//Ultima or PACS
     	if(Version.get(x).contains("ltima")
     			&& (!Version.get(x).contains("Ris"))
     			&& (!Version.get(x).contains("RIS"))
     			||( (Version.get(x).contains("PACS"))
     			|| (Version.get(x).contains("Pacs"))))
     	{
         	driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_SoftwareTypes\"]")).click();
     		Softwatetype2.selectByVisibleText("Ultima (PACS) / Ultima360 (VNA)");;
     	}	
     	//Broker
     	else if(Version.get(x).contains("orker")
     			||Version.get(x).contains("roker")||
     			Version.get(x).contains("PaxeraBroker")){
     		Softwatetype2.selectByVisibleText("PAXERA PACS/RIS COMPONENTS");
        }
     	//Burner
     	else if(Version.get(x).contains("urner")){
     		Softwatetype2.selectByVisibleText("PAXERA PACS COMPONENTS");
        }     	
     	//PaxeraView
     	else if(Version.get(x).contains("View")){
     		Softwatetype2.selectByVisibleText("PAXERA MULTIMODALITY WORKSTATIONS");
        }
     	//PaxeraPrint
     	else if(Version.get(x).contains("Print")){
     		Softwatetype2.selectByVisibleText("PAXERA PACS/RIS COMPONENTS");
        }
     	//RIS
     	else if(Version.get(x).contains("Ris")||
     			Version.get(x).contains("RIS")){
     		Softwatetype2.selectByVisibleText("PAXERA RIS COMPONENTS");
        }
     	//PaxeraServer
     	else if((Version.get(x).contains("PACS")||
     			Version.get(x).contains("Pacs")||
     			Version.get(x).contains("erver"))
     			&& (!Version.get(x).contains("ltima"))){
     		Softwatetype2.selectByVisibleText("PAXERA PACS COMPONENTS");
        }
       	else {
     		System.out.println(Version.get(x)+" is software type not existed");
     		System.out.println(index_1.get(x)+" is  skipped");
       		addtotextfile(Version.get(x)+" is not existed", true);
       		addtotextfile(index_1.get(x)+" is  skipped", true);
    	    addtotextfile("-----------failed due software type not existed------------", true);
     		continue;
        }
     	Thread.sleep(4000);
     	
     	
     	
     	///////////////////////////////////////////
     	//select software name
     		Select Software=new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_Software\"]")));
         	driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_Software\"]")).click();
     //Ultima
         	if(Version.get(x).contains("ltima")
         			&& (!Version.get(x).contains("Ris"))
         			&& (!Version.get(x).contains("RIS"))
         			&&(!Version.get(x).contains("PACS"))
         			&&(!Version.get(x).contains("Pacs"))) {
         	Software.selectByVisibleText("Ultima (PACS)");
         	System.out.println("Ultima (PACS) is selected");
         	}
         	//Ultima PACS
         	else if(Version.get(x).contains("PACS")&& 
         			Version.get(x).contains("ltima")){
             	Software.selectByVisibleText("Ultima (PACS)");
             	System.out.println("Ultima (PACS) is selected");

            }
         	//Broker
         	else if(Version.get(x).contains("orker")
         			||(Version.get(x).contains("roker"))||
         			(Version.get(x).contains("PaxeraBroker"))){
         		Software.selectByVisibleText("PaxeraBroker");
             	System.out.println("PaxeraBroker is selected");

            }
         	//Burner
         	else if(Version.get(x).contains("urner")){
         		Software.selectByVisibleText("PaxeraBurner");
             	System.out.println("PaxeraBurner is selected");

            }     	
         	//PaxeraView
         	else if(Version.get(x).contains("iew")){
         		Software.selectByVisibleText("PaxeraView 100");
             	System.out.println("PaxeraView 100 is selected");

            }
         	//PaxeraPrint
         	else if(Version.get(x).contains("rint")){
         		Software.selectByVisibleText("PaxeraPrint");
             	System.out.println("PaxeraPrint is selected");

            }
         	//RIS
         	else if(Version.get(x).contains("Ris")||
         			Version.get(x).contains("RIS")){
         		Software.selectByVisibleText("PaxeraRis 100");
             	System.out.println("PaxeraRis 100 is selected");

            }
         	//PACS SERVER
         	else if((Version.get(x).contains("PACS")||
         			Version.get(x).contains("Pacs")||
         			Version.get(x).contains("erver"))
         			&& (!Version.get(x).contains("ltima"))){
         		Software.selectByVisibleText("PaxeraServer Pro");
             	System.out.println("PaxeraServer Pro is selected");

            }
           	else {
         		System.out.println(Version.get(x)+" is software name not existed");
         		System.out.println(index_1.get(x)+" is  skipped");
           		addtotextfile(Version.get(x)+" is software type not existed", true);
           		addtotextfile(index_1.get(x)+" is  skipped", true);
        	    addtotextfile("-----------failed due software name not existed------------", true);
         		continue;         		
            }
         	
         	//Select Software version
         	Select Software_Version=new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_SoftwareVersions\"]")));
         	driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_ddl_SoftwareVersions\"]")).click();
         	if(Version.get(x).contains("7.1.0")) {
         		Software_Version.selectByVisibleText("7.1.0");
         		}
         	else if(Version.get(x).contains("3.4.13")) {
         		Software_Version.selectByVisibleText("3.4.13");
         		}
         	else if(Version.get(x).contains("7.2.0")) {
         		Software_Version.selectByVisibleText("7.2.0");
         		}
         	else if(Version.get(x).contains("7.3.0")) {
         		Software_Version.selectByVisibleText("7.3.0");
         		}
         	else if(Version.get(x).contains("7.3.6")) {
         		Software_Version.selectByVisibleText("7.3.6");
         		}
         	else if(Version.get(x).contains("6.5.5")) {
         		Software_Version.selectByVisibleText("6.5.5");
         		}         	
         	else if(Version.get(x).contains("6.3.1")
         			||Version.get(x).contains("6.3")) {
             	Software_Version.selectByVisibleText("6.3.1");
             	}         	
         	else if(Version.get(x).contains("6.4.2")) {
                Software_Version.selectByVisibleText("6.4.2");
             		}         	
         	else if(Version.get(x).contains("6.4.9")) {
                Software_Version.selectByVisibleText("6.4.9");
             		}         	
         	else if(Version.get(x).contains("6.6.2")) {
                Software_Version.selectByVisibleText("6.6.2");
             		}
         	else if(Version.get(x).contains("4.0.4")) {
         		Software_Version.selectByVisibleText("4.0.4");
     		}
         	else if(Version.get(x).contains("4.7.2")) {
         		Software_Version.selectByVisibleText("4.7.2");
     		}
         	else if(Version.get(x).contains("4.0.9")) {
         		Software_Version.selectByVisibleText("4.0.9");
     		}
         	else if(Version.get(x).contains("3.9.1.3")) {
         		Software_Version.selectByVisibleText("4.0.4");
     		}
         	else if(Version.get(x).contains("4.2.2")) {
         		Software_Version.selectByVisibleText("4.2.2");
     		}
         	else if(Version.get(x).contains("4.2.9")) {
         		Software_Version.selectByVisibleText("4.2.9");
     		}
         	else if(Version.get(x).contains("4.3.7")) {
         		Software_Version.selectByVisibleText("4.3.7");
     		}
         	else if(Version.get(x).contains("4.5.0")) {
         		Software_Version.selectByVisibleText("4.5.0");
     		}
         	else if(Version.get(x).contains("4.6.6")) {
         		Software_Version.selectByVisibleText("4.6.6");
     		}
         	else if(Version.get(x).contains("4.6.7")) {
         		Software_Version.selectByVisibleText("4.6.6");
     		}
         	else if(Version.get(x).contains("4.7.2")) {
         		Software_Version.selectByVisibleText("4.7.2");
     		}
         	else if(Version.get(x).contains("4.8.4")) {
         		Software_Version.selectByVisibleText("4.8.4");
     		}
         	else if(Version.get(x).contains("4.9.0")) {
         		Software_Version.selectByVisibleText("4.9.0");
     		}
         	else if(Version.get(x).contains("5.0.3")) {
         		Software_Version.selectByVisibleText("5.0.3");
     		}
         	else if(Version.get(x).contains("6.2.4")) {
         		Software_Version.selectByVisibleText("6.2.4");
     		}
         	else if(Version.get(x).contains("5.1.0")) {
         		Software_Version.selectByVisibleText("5.1.0");
     		}
         	
         	//Broker verisons
         	else if(Version.get(x).contains("3.82")) {
         		Software_Version.selectByVisibleText("3.82.0");
     		}
         	else if(Version.get(x).contains("3.83")) {
         		Software_Version.selectByVisibleText("3.83.0");
     		}
         	else if(Version.get(x).contains("3.69")) {
         		Software_Version.selectByVisibleText("3.69.0");
     		}
         	else if(Version.get(x).contains("3.13")) {
         		Software_Version.selectByVisibleText("3.13.0");
     		}
         	else if(Version.get(x).contains("3.16")
         			&&!Version.get(x).contains("urner")) {
         		Software_Version.selectByVisibleText("3.16.0");
     		}
         	else if(Version.get(x).contains("3.17")) {
         		Software_Version.selectByVisibleText("3.17.0");
     		}
         	else if(Version.get(x).contains("3.18")) {
         		Software_Version.selectByVisibleText("3.18.0");
     		}
         	else if(Version.get(x).contains("3.22")) {
         		Software_Version.selectByVisibleText("3.22.0");
     		}
         	else if(Version.get(x).contains("3.24")) {
         		Software_Version.selectByVisibleText("3.24.0.0");
     		}
         	else if(Version.get(x).contains("3.25")) {
         		Software_Version.selectByVisibleText("3.25.0.0");
     		}
         	else if(Version.get(x).contains("3.33")) {
         		Software_Version.selectByVisibleText("3.33.0.0");
     		}
         	else if(Version.get(x).contains("3.43")) {
         		Software_Version.selectByVisibleText("3.43.0");
     		}
         	else if(Version.get(x).contains("3.47")) {
         		Software_Version.selectByVisibleText("3.47.0");
     		}
         	else if(Version.get(x).contains("3.48")) {
         		Software_Version.selectByVisibleText("3.48.0");
     		}
         	else if(Version.get(x).contains("3.56")) {
         		Software_Version.selectByVisibleText("3.56.0");
     		}
         	else if(Version.get(x).contains("3.58")) {
         		Software_Version.selectByVisibleText("3.58.0");
     		}
         	else if(Version.get(x).contains("3.59")) {
         		Software_Version.selectByVisibleText("3.59.0");
     		}
         	else if(Version.get(x).contains("3.60")) {
         		Software_Version.selectByVisibleText("3.60.0");
     		}
         	else if(Version.get(x).contains("3.61")) {
         		Software_Version.selectByVisibleText("3.61.0");
     		}
         	
         	else if(Version.get(x).contains("3.62")) {
         		Software_Version.selectByVisibleText("3.62.0");
     		}
         	else if(Version.get(x).contains("3.63")) {
         		Software_Version.selectByVisibleText("3.63.0");
     		}
         	else if(Version.get(x).contains("3.64")) {
         		Software_Version.selectByVisibleText("3.64.0");
     		}
         	else if(Version.get(x).contains("3.65")) {
         		Software_Version.selectByVisibleText("3.65.0");
     		}
         	else if(Version.get(x).contains("3.67")) {
         		Software_Version.selectByVisibleText("3.67.0");
     		}
         	else if(Version.get(x).contains("3.70")) {
         		Software_Version.selectByVisibleText("3.70.0");
     		}
         	else if(Version.get(x).contains("3.72")) {
         		Software_Version.selectByVisibleText("3.72.0");
     		}
         	else if(Version.get(x).contains("3.73")) {
         		Software_Version.selectByVisibleText("3.73.0");
     		}
         	else if(Version.get(x).contains("3.74")) {
         		Software_Version.selectByVisibleText("3.74.0");
     		}
         	else if(Version.get(x).contains("3.75")) {
         		Software_Version.selectByVisibleText("3.75.0");
     		}
         	else if(Version.get(x).contains("3.8")
         			&&!Version.get(x).contains("3.80")
         			&&!Version.get(x).contains("3.81")
         			&&!Version.get(x).contains("3.82")
         			&&!Version.get(x).contains("3.83")
         			&&!Version.get(x).contains("3.84")
         			&&!Version.get(x).contains("3.85")
         			&&!Version.get(x).contains("3.86")
         			&&!Version.get(x).contains("3.87")
         			&&!Version.get(x).contains("3.88")
         			&&!Version.get(x).contains("3.89")) {
         		Software_Version.selectByVisibleText("3.8.0.0");
     		}
         	else if(Version.get(x).contains("3.80")) {
         		Software_Version.selectByVisibleText("3.80.0");
     		}
         	else if(Version.get(x).contains("3.86")) {
         		Software_Version.selectByVisibleText("3.86.0");
     		}
         	else if(Version.get(x).contains("3.94")) {
         		Software_Version.selectByVisibleText("3.94.0");
     		}
         	else if(Version.get(x).contains("3.96")) {
         		Software_Version.selectByVisibleText("3.96.0");
     		}
         	/////////////////
         	//Burner verions
         	else if(Version.get(x).contains("3.34")) {
         		Software_Version.selectByVisibleText("3.34.0");
     		}
         	else if(Version.get(x).contains("4.2")
         			&&!Version.get(x).contains("6.4.2")
         			&&!Version.get(x).contains("4.2.2")
         			&&!Version.get(x).contains("4.2.9")
         			&&Version.get(x).contains("urner")) {
         		Software_Version.selectByVisibleText("4.2.0");
     		}
         	else if(Version.get(x).contains("3.16")
         			&&Version.get(x).contains("urner")) {
         		Software_Version.selectByVisibleText("3.16.0.0");
     		}
         	else if(Version.get(x).contains("4.3")&&
         			!Version.get(x).contains("4.3.7")
         			&&Version.get(x).contains("urner")) {
         		Software_Version.selectByVisibleText("4.3.0");
     		}
         	////////////////////////////////////
         	//PaxeraView versions
         	else if(Version.get(x).contains("11.11.13")) {
         		Software_Version.selectByVisibleText("11.11.13.0");
     		}
         	else if(Version.get(x).contains("11.11.24")) {
         		Software_Version.selectByVisibleText("11.11.24.0");
     		}
         	else if(Version.get(x).contains("12.0")) {
         		Software_Version.selectByVisibleText("12.0.4");
     		}
         	else if(Version.get(x).contains("12.1.19")||Version.get(x).contains("12.1.9")) {
         		Software_Version.selectByVisibleText("12.1.9");
     		}
         	else if(Version.get(x).contains("12.1.4")) {
         		Software_Version.selectByVisibleText("12.1.4");
     		}
         	else if(Version.get(x).contains("12.1.7")) {
         		Software_Version.selectByVisibleText("12.1.7");
     		}
         	else if(Version.get(x).contains("12.1.9")) {
         		Software_Version.selectByVisibleText("12.1.9");
     		}
         	else if(Version.get(x).contains("12.2.3")) {
         		Software_Version.selectByVisibleText("12.2.3");
     		}
         	else if(Version.get(x).contains("12.2.4")) {
         		Software_Version.selectByVisibleText("12.2.4");
     		}
         	else if(Version.get(x).contains("12.2.6")) {
         		Software_Version.selectByVisibleText("12.2.6");
     		}
         	/////////////////////////////////////////////////
         	//paxeraPrint version PaxeraPrint 1.0.1.0
         	//PaxeraPrint 1.0.1.7
         	else if(Version.get(x).contains("1.0.1.0")) {
         		Software_Version.selectByVisibleText("1.0.1.0");
     		}         	
         	else if(Version.get(x).contains("1.0.1.7")) {
         		Software_Version.selectByVisibleText("1.0.1.7");
     		}
         	////////////////////////////
         	//PACS server version
         	else if(Version.get(x).contains("4.6.9.8")) {
         		Software_Version.selectByVisibleText("4.6.9.8");
     		}         	
         	else if(Version.get(x).contains("6.0.0.0")) {
         		Software_Version.selectByVisibleText("6.0.0.0");
     		}
         	else if(Version.get(x).contains("6.0.0.10")) {
         		Software_Version.selectByVisibleText("6.0.0.10");
     		}         	
         	else if(Version.get(x).contains("6.0.0.21")&&!Version.get(x).contains("6.0.0.21")&&!Version.get(x).contains("6.0.0.22")) {
         		Software_Version.selectByVisibleText("6.0.0.21");
     		}
         	else if(Version.get(x).contains("6.0.0.51")) {
         		Software_Version.selectByVisibleText("6.0.0.51");
     		}         	
         	else if(Version.get(x).contains("6.0.0.90")) {
         		Software_Version.selectByVisibleText("6.0.0.90");
     		}
         	else if(Version.get(x).contains("6.0.0.66")) {
         		Software_Version.selectByVisibleText("6.0.0.66");
     		}         	
         	else if(Version.get(x).contains("5.0.0.13")) {
         		Software_Version.selectByVisibleText("5.0.0.13");
     		}
         	else if(Version.get(x).contains("5.0.0.15")) {
         		Software_Version.selectByVisibleText("5.0.0.15");
     		}         	
         	else if(Version.get(x).contains("5.0.0.2")) {
         		Software_Version.selectByVisibleText("5.0.0.2");
     		}
         	else if(Version.get(x).contains("5.0.0.9")) {
         		Software_Version.selectByVisibleText("5.0.0.9");
     		}         	
         	else if(Version.get(x).contains("6.0.0.22")&&!Version.get(x).contains("6.0.0.21")) {
         		Software_Version.selectByVisibleText("6.0.0.22");
     		}
         	else if(Version.get(x).contains("6.0.0.2")&&!Version.get(x).contains("6.0.0.21")&&!Version.get(x).contains("6.0.0.22")) {
         		Software_Version.selectByVisibleText("6.0.0.2");
     		}
         	else if(Version.get(x).contains("6.0.0.31")) {
         		Software_Version.selectByVisibleText("6.0.0.31");
     		}         	
         	else if(Version.get(x).contains("6.0.0.42")) {
         		Software_Version.selectByVisibleText("6.0.0.42");
     		}
         	else if(Version.get(x).contains("6.0.0.50")) {
         		Software_Version.selectByVisibleText("6.0.0.50");
     		}         	
         	else if(Version.get(x).contains("6.0.0.45")) {
         		Software_Version.selectByVisibleText("6.0.0.45");
     		}
         	else if(Version.get(x).contains("6.0.0.51")) {
         		Software_Version.selectByVisibleText("6.0.0.51");
     		}
         	else if(Version.get(x).contains("6.0.0.59")) {
         		Software_Version.selectByVisibleText("6.0.0.59");
     		}
         	else if(Version.get(x).contains("6.0.0.80")) {
         		Software_Version.selectByVisibleText("6.0.0.80");
     		}
         	else if(Version.get(x).contains("6.0.0.90")) {
         		Software_Version.selectByVisibleText("6.0.0.90");
     		}
         	else if(Version.get(x).contains("6.0.0.83")) {
         		Software_Version.selectByVisibleText("6.0.0.83");
     		}
         	////////////////////////////////////////
         	//Ris version 
         	else if(Version.get(x).contains("6.16")) {
         		Software_Version.selectByVisibleText("6.16.0.0");
     		}
         	else if(Version.get(x).contains("6.21")) {
         		Software_Version.selectByVisibleText("6.21.0.0");
     		}
         	///////////////////////////////
           	else {
           		//Software_Version.selectByIndex(1);
         		System.out.println(Version.get(x)+" version is not existed");
         		System.out.println(index_1.get(x)+" is skipped");
           		addtotextfile(Version.get(x)+" version is not existed", true);
           		addtotextfile(index_1.get(x)+"is skipped", true);
     	        addtotextfile("----------failed due to version not existed-------------", true);
         		continue;
            }
         	
         	//Enter Software serial
         	WebElement Software_Serial=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_txt_SoftwareSerial\"]"));
         	Software_Serial.clear();
         	//Thread.sleep(10000);
         	Software_Serial.sendKeys(Serial.get(x));
            //Thread.sleep(10000);
//         	try {
         	WebElement add_software=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_btn_AddSoftware\"]"));
         	add_software.click();
     		System.out.println(index_1.get(x)+"  is  added to list");
       		addtotextfile(Version.get(x)+" is existed", true);
       		
       		//addtotextfile(index_1.get(x)+" is not existed", true);
     		addtotextfile(index_1.get(x)+"  is  added to list", true);
 	        addtotextfile("----------added software-------------", true);
            Thread.sleep(3000);
//            }
//         	catch(NoSuchElementException e )
//         	{
//         		System.out.println(index_1.get(x)+" can't be  added to list");
//         		addtotextfile(index_1.get(x)+"  can't be  added to list", true);
//     	        addtotextfile("----------failed to add software-------------", true);
//                Thread.sleep(3000);
//         	}
     	}
        
        
//        try {
     	//next btn 
     	WebElement next_btn=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_StepNavigationTemplateContainerID_StepNextButton\"]"));
     	next_btn.click();
        Thread.sleep(3000);

     	//list of softwares
		List<WebElement> softwaress = driver.findElements(
				By.xpath("//input[@type='checkbox']"));
		int iSize = softwaress.size();
		// Start the loop from first Check Box to last Check Box
		for(int i=0; i < iSize ; i++ )
		{		
			String sValue = softwaress.get(i).getAttribute("value");

			// Select the Check Box it the value of the Check Box is same what you are looking for
			 if ( !softwaress.get(i).isSelected() ) {
				 softwaress.get(i).click();			    
				 }
			
			//System.out.println(sValue);
     		addtotextfile(sValue+" "+i+"  is  checked", true);

		}
		
        Thread.sleep(5000);
		System.out.println(country.get(0)+" is country");
 		addtotextfile(country.get(0)+" is country", true);

		//To select the dongle
		Select Location = new Select
				(driver.findElement(By.xpath(
						"//*[@id=\"ContentPlaceHolder1_wzd_TabContainer1_TabPanel0_ddl_Location\"]")));
        
		if(country.get(0).contains("gypt"))
        {        Thread.sleep(4000);

        	Location.selectByVisibleText("Paxeramed Egypt");
        	addtotextfile("Paxeramed Egypt is select", true);
        }
        else {        Thread.sleep(4000);

        	Location.selectByVisibleText("Paxeramed USA");
        	addtotextfile("Paxeramed USA is select", true);

        }
       // Thread.sleep(10000);
////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////select dongle type and cat
		////////////////////////////////////////////////////////////
		
		Select dongleType = new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_TabContainer1_TabPanel0_ddl_DongleType\"]")));
		//dongleType.selectByVisibleText("Paxera WS");
		Select dongleCat = new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_TabContainer1_TabPanel0_ddl_DongleCategories\"]")));
		
		System.out.println(D_Type.get(0)+" is dongle type");
		addtotextfile(D_Type.get(0)+" is dongle type", true);

		//System.out.println(D_Type.get(0)+" is dongle type");

		if(D_Type.get(0).contains("3"))
		{
//			Select dongleCat2 = new Select(driver.findElement(
//					By.xpath(
//				"//*[@id='ContentPlaceHolder1_wzd_TabContainer1_TabPanel0_ddl_DongleCategories']")));

			if(D_Type.get(0).contains("net") || D_Type.get(0).contains("Net") 
					|| D_Type.get(0).contains("NET") || D_Type.get(0).contains("nET")) {
		        Thread.sleep(5000);

				dongleType.selectByVisibleText("Paxera NET");
		        Thread.sleep(5000);
		        Select dongleCat2 = new Select(driver.findElement(
	 					By.xpath(
	 				"//*[@id='ContentPlaceHolder1_wzd_TabContainer1_TabPanel0_ddl_DongleCategories']")));
				dongleCat2.selectByVisibleText("Green 3_NET");
		        System.out.println(D_Type.get(0)+" Selected category");
   				addtotextfile("Selected category: Green 3_Net", true);
   			}else {
   		        Thread.sleep(5000);

   				dongleType.selectByVisibleText("Paxera WS");
   		        Thread.sleep(3000);
   		     Select dongleCat2 = new Select(driver.findElement(
 					By.xpath(
 				"//*[@id='ContentPlaceHolder1_wzd_TabContainer1_TabPanel0_ddl_DongleCategories']")));

//   				dongleCat2.selectByValue("6");
   				dongleCat2.selectByVisibleText("Green 3");
   				addtotextfile("Selected category: Green 3", true);
   			}
		//dongleCat.selectByVisibleText("Green 3");
		}
		else if (D_Type.get(0).contains("2"))
		{
			if(D_Type.get(0).contains("net") || D_Type.get(0).contains("Net") 
					|| D_Type.get(0).contains("NET") || D_Type.get(0).contains("nET")) {
   		        Thread.sleep(5000);

				dongleType.selectByVisibleText("Paxera NET");
   		        Thread.sleep(5000);

				dongleCat.selectByVisibleText("Green 2_Net");
   				addtotextfile("Selected category: Green 2_Net", true);
   			}else {
   		        Thread.sleep(5000);

   				dongleType.selectByVisibleText("Paxera WS");
   		        Thread.sleep(5000);
   		     Select dongleCat2 = new Select(driver.findElement(
	 					By.xpath(
	 				"//*[@id='ContentPlaceHolder1_wzd_TabContainer1_TabPanel0_ddl_DongleCategories']")));

   				dongleCat2.selectByVisibleText("Green 2");
   				addtotextfile("Selected category: Green 2", true);
   			}
			//dongleCat.selectByVisibleText("Green 2");

		}
		else if(D_Type.get(0).contains("1"))
		{
			if(D_Type.get(0).contains("net") || D_Type.get(0).contains("Net") 
					|| D_Type.get(0).contains("NET") || D_Type.get(0).contains("nET")) {
   		        Thread.sleep(5000);

				dongleType.selectByVisibleText("Paxera NET");
   		        Thread.sleep(5000);
   		     Select dongleCat2 = new Select(driver.findElement(
	 					By.xpath(
	 				"//*[@id='ContentPlaceHolder1_wzd_TabContainer1_TabPanel0_ddl_DongleCategories']")));

				dongleCat2.selectByVisibleText("Green 1_Net");
   				addtotextfile("Selected category: Green 1_Net", true);
   			}else {
   		        Thread.sleep(5000);
   				dongleType.selectByVisibleText("Paxera WS");
   			  Select dongleCat2 = new Select(driver.findElement(
	 					By.xpath(
	 				"//*[@id='ContentPlaceHolder1_wzd_TabContainer1_TabPanel0_ddl_DongleCategories']")));

   		        Thread.sleep(5000);
   				dongleCat2.selectByVisibleText("Green 1");
   				addtotextfile("Selected category: Green 1", true);
   			}
			//dongleCat.selectByVisibleText("Green 1");
	        Thread.sleep(10000);

		}
		else {
     		System.out.println(index_1.get(0)+" is skipped");
     		//System.out.println(D_Type.get(0)+" is not existed");
     		System.out.println(D_Type.get(0)+" dongle type is not existed");
     		System.out.println(Dongle_SerialName.get(0)+" dongle serial is not existed");
     		addtotextfile(index_1.get(0)+" is  skipped", true);
	       // addtotextfile(customername.get(0)+" customer name is not existed", true);
	        addtotextfile(D_Type.get(0)+" dongle type is not existed", true);
	        addtotextfile(Dongle_SerialName.get(0)+" dongle serial is not existed", true);
	        addtotextfile("------------failed due to dongle serial is not existed -----------", true);
	        addtotextfile("-----------------------", true);
	        Thread.sleep(4000);
	        ////break;
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////select dongle serial and uid
		/////////////////////////////////////////////////////////////////////////////////////
		
		Select dongleserial = new Select(driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_TabContainer1_TabPanel0_ddl_DongleSerials\"]")));
		String[] selectdongle = SelectElementContainsItemText(dongleserial,Dongle_SerialName.get(0)).split("^");
		
		if(selectdongle[0].contains("true"))
        {
		dongleserial.selectByVisibleText(Dongle_SerialName.get(0).trim());
		WebElement Param_name=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_TabContainer1_TabPanel0_txt_DongleParameterName\"]"));
		WebElement Param_code=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_TabContainer1_TabPanel0_txt_DongleParameterCode\"]"));
		WebElement add_btn=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_TabContainer1_TabPanel0_btn_AddDongle\"]"));
        Thread.sleep(4000);
        
        
        if(D_Name.get(0)== null)
        {
        	D_Name.add(0, "//");
        }
        if(D_Serial.get(0)== null)
        {
        	D_Serial.add(0, "//");
        }
        Param_name.clear();
       // int result1 = Integer.parseInt(D_Name.get(0));
		Param_name.sendKeys(D_Name.get(0));
		Param_code.clear();
		//int result2 = Integer.parseInt(D_Serial.get(0));
		Param_code.sendKeys(D_Serial.get(0));
		add_btn.click();
        Thread.sleep(4000);

		WebElement nextt_btn=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_StepNavigationTemplateContainerID_StepNextButton\"]"));
		nextt_btn.click();
        Thread.sleep(4000);
		WebElement finish_btn=driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_wzd_FinishNavigationTemplateContainerID_FinishButton\"]"));
		finish_btn.click();
        Thread.sleep(4000);

        addtotextfile(Dongle_SerialName.get(0)+" dongle uid is added", true);
        addtotextfile(SPF.get(0)+" SPF is added", true);
        addtotextfile(index_1.get(0)+" index is added", true);
        addtotextfile("-----------sucess------------", true);
        addtotextfile2(index_1.get(0), true);
		Thread.sleep(1000);
        }
        else
        {
        	System.out.println(D_Type.get(0)+" dongle type is not existed");
     		System.out.println(Dongle_SerialName.get(0)+" dongle serial is not existed");
     		System.out.println(SPF.get(0)+" SPF is  not added");
     	//	System.out.println(Dongle_SerialName.get(0)+" dongle serial is not existed");
     		
            addtotextfile(Dongle_SerialName.get(0)+" dongle uid is not add", true);
            addtotextfile(SPF.get(0)+" SPF is  not added", true);
            addtotextfile(index_1.get(0)+" index is not added", true);
            addtotextfile("-----------failed due to dongle uid is not existed------------", true);
    		Thread.sleep(1000);
    	    ////break;
        }
        
        }
            
            
        else {
     		System.out.println(customername.get(0)+" custmer name is not existed");
     		//System.out.println(D_Type.get(0)+" is not existed");
     		System.out.println(SPF.get(0)+" SPF is  not added");
     		System.out.println(index_1.get(0)+" index is not added");
            addtotextfile("-----------failed------------", true);
            addtotextfile(customername.get(0)+" custmer name is not existed", true);
            addtotextfile(SPF.get(0)+" SPF is  not added", true);
            addtotextfile(index_1.get(0)+" index is not added", true);
            addtotextfile("-----------failed------------", true);
    		Thread.sleep(2000);
    		//continue;
        	
        }
            
        /////////////////////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     
     /*   /////////////////////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>      //   }
        catch(Exception e)
        {
     		System.out.println(customername.get(0)+" custmer name is not existed");
     		//System.out.println(D_Type.get(0)+" is not existed");
     		System.out.println(SPF.get(0)+" SPF is  not added");
     		System.out.println(index_1.get(0)+" index is not added");
            addtotextfile(customername.get(0)+" custmer name is not existed", true);
            addtotextfile(SPF.get(0)+" SPF is  not added", true);
            addtotextfile(index_1.get(0)+" index is not added", true);
            addtotextfile("------------failed-----------", true);
    		Thread.sleep(2000);
        }*/

        
     	}
     	
     public String SelectElementContainsItemText(Select selElem, String itemText)
     {
    	 String  found = "false";
         List<WebElement> List= new ArrayList<>();
        		 List= selElem.getOptions(); 	
        		

         for (int i = 0; i < List.size(); i++)
         {
        	 //String blah = List.get(i).getText();
             if (List.get(i).getText().contains(itemText)||List.get(i).getText().equalsIgnoreCase(itemText))
             {
                 found = "true"+"^"+List.get(i).getText();
                 break;
             }
         }

         return found;
     }
     
     public void addtotextfile2(String text, Boolean newline) throws IOException {
    	String  path2="D:\\PMS_Log\\AddedIndexes.txt";
	        File file = new File(path2);
	        if (!file.exists()) {
	            file.createNewFile();
	        }
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
	        try {
				bw.write(text);
			} catch (Exception e) {
				bw.write("Can't write NULL "+ e.toString());
			}
	        if(newline) {
	            bw.newLine();
	        }
			bw.close();
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
				bw.write("Can't write NULL "+ e.toString());
			}
	        if(newline) {
	            bw.newLine();
	        }
			bw.close();
		}
     
     @AfterMethod
	 public void tearDown() {
         
     	driver.quit();
     	System.out.println("Test Case Success");
     }
     
     
}
