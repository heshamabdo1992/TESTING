package samples;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class remote_driver  {
    static RemoteWebDriver  driver;
    static DesiredCapabilities capabilities = DesiredCapabilities.chrome();

    @BeforeMethod
    public void SetUp() throws MalformedURLException  {
          System.out.println("BeforeMethod");
//          System.setProperty("webdriver.chrome.driver","./chromedriver.exe");
//          capabilities.setVersion("latest");
//          capabilities.setPlatform(org.openqa.selenium.Platform.WINDOWS);
//          capabilities.setBrowserName("chrome");

        driver = new RemoteWebDriver(new URL("http://192.0.0.176:4444/wd/hub"), capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver = new ChromeDriver();  //for local check

    }

    @Test
    public void Run(){
    	
        System.out.println("Test");

		driver.get("https://www.facebook.com/");		
		
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("01004827411");
		
		WebElement passField=driver.findElement(By.id("pass")) ;
		passField.sendKeys("01004827411");
		
		driver.findElement(By.id("loginbutton")).click();
    
    }


    @AfterMethod
    public void closeFirefox(){
        System.out.println("AfterMethod");

        driver.quit();
    }
}
