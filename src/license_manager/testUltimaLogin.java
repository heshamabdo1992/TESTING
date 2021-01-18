package license_manager;

import java.io.File;
import java.util.concurrent.TimeUnit;
//import org.testng.annotations.*;
//import static org.testng.Assert.*;
import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.phantomjs.*;


public class testUltimaLogin {
 // private WebDriver driver;
  private PhantomJSDriver  driver;

  //private StringBuffer verificationErrors = new StringBuffer();

  //@BeforeClass
  public void setUp() throws Exception {
	  File file = new File("C:\\Program Files\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
//	  System.setProperty("webdriver.gecko.driver","C:\\Program Files\\Mozilla Firefox\\geckodriver.exe");
	  System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
	   //driver = new FirefoxDriver();
	  driver = new PhantomJSDriver();
    driver.manage().window().maximize();
  
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get("http://192.0.0.179");
    driver.findElement(By.id("txt_UserName")).clear();
    driver.findElement(By.id("txt_UserName")).sendKeys("test");
    driver.findElement(By.id("txt_Password")).clear();
    driver.findElement(By.id("txt_Password")).sendKeys("Test1234");
    driver.findElement(By.id("btn_Login")).click();
    System.out.println("test");
    driver.findElement(By.id("menu_Arrow")).click();
    System.out.println("test2");
   // driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\'menu\']")));
   // System.out.println("test3");
    driver.findElement(By.id("ctl00_lnk_SignOut")).click();
    System.out.println("test4");
    driver.quit();
  }

 // @Test
//  public void testUntitledTestCase() throws Exception {
//	  
//
//    driver.get("http://192.0.0.179");
//    driver.findElement(By.id("txt_UserName")).clear();
//    driver.findElement(By.id("txt_UserName")).sendKeys("test");
//    driver.findElement(By.id("txt_Password")).clear();
//    driver.findElement(By.id("txt_Password")).sendKeys("Test1234");
//    driver.findElement(By.id("btn_Login")).click();
//    System.out.println("test");
//    driver.findElement(By.id("menu_Arrow")).click();
//    System.out.println("test2");
//   // driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\'menu\']")));
//   // System.out.println("test3");
//    driver.findElement(By.id("ctl00_lnk_SignOut")).click();
//    System.out.println("test4");
//  }

 // @AfterClass
//  public void tearDown() throws Exception {
//    driver.quit();
////    String verificationErrorString = verificationErrors.toString();
////    if (!"".equals(verificationErrorString)) {
////      fail(verificationErrorString);
////    }
//  }
}
