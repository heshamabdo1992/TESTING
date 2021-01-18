package samples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sample_driver {
	
	public static void main(String[] args) throws InterruptedException, InstantiationException, IllegalAccessException {
		System.out.println("--Test--");
		//WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");		
		
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("01004827411");
		
		WebElement passField=driver.findElement(By.id("pass")) ;
		passField.sendKeys("01004827411");
		
		driver.findElement(By.id("loginbutton")).click();
		
		Thread.sleep(3000);
		
		driver.quit();	
	}

}
