package license_manager;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
//import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class generate_OTP {
	private WebDriver driver;
	  private StringBuffer verificationErrors = new StringBuffer();
	  
	  @BeforeClass(alwaysRun = true)
	  public void setUp() throws Exception {
	    driver = new ChromeDriver();
	   // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	  }
	  
	  
	  @Test
	  public void run() throws Exception {
		Clipboard c=Toolkit.getDefaultToolkit().getSystemClipboard();
	    System.out.println(c.getData(DataFlavor.stringFlavor));
	    String copied= new String((String) c.getData(DataFlavor.stringFlavor));
		driver.get("http://192.168.5.186:333/");
	    driver.findElement(By.id("ContentPlaceHolder1_txt_UserName")).clear();
	    driver.findElement(By.id("ContentPlaceHolder1_txt_UserName")).sendKeys("mohamed.mostafa");
	    driver.findElement(By.id("ContentPlaceHolder1_txt_Password")).clear();
	    driver.findElement(By.id("ContentPlaceHolder1_txt_Password")).sendKeys("123456");
	    driver.findElement(By.id("ContentPlaceHolder1_btn_Login")).click();
	    
	    driver.findElement(By.xpath("//*[@id=\"TreeView1t52\"]")).click();
	    driver.findElement(By.xpath("//*[@id=\"TreeView1t53\"]")).click();
	    driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_txt_AdminCode\"]")).clear();
	    driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_txt_AdminCode\"]")).sendKeys(copied);
	    driver.findElement(By.xpath("//*[@id=\"ContentPlaceHolder1_btn_Generate\"]")).click();
	    copied = driver.findElement(By.id("ContentPlaceHolder1_lbl_OTP")).getText();
	    StringSelection stringSelection = new StringSelection(copied);
	    c.setContents(stringSelection, null);
	  }
	  
	  
	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
	  

}
