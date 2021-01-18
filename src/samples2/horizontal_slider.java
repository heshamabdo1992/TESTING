package samples2;

import org.openqa.selenium.By;
//import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class horizontal_slider {
    
	public static WebDriver driver;
    WebDriverWait wait;
	
    @BeforeMethod

	public void setup() throws Exception{

	    	System.out.println("Before Method - Chrome");
			driver = new ChromeDriver();
	        driver.manage().window().maximize();

	}
    public WebElement findHiddenElement(By locator) {
    	  return 
    		wait.until(ExpectedConditions. presenceOfElementLocated(locator));
    	}
    
	@Test    
    public void Run()throws InterruptedException{
    	System.out.println("Test");
    	 driver.get("https://the-internet.herokuapp.com/horizontal_slider");
    	 
    	 
    	 WebElement slider= driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/input"));
//    	   int width = slider.getSize().getWidth();

    	 WebElement percent = driver.findElement(By.xpath("//*[@id=\"range\"]"));
    	 String percent2 = slider.getAttribute("value");

    	 Assert.assertTrue(percent.getText().contains("0"));
    	 Assert.assertTrue(percent2.contains("0"));

    	 Assert.assertTrue(slider.isDisplayed());
    	 
    	    Actions action= new Actions(driver);
    	    action.click(slider).build().perform();
            action.sendKeys(Keys.ARROW_LEFT).build().perform();

			System.out.println(percent.getText());	

//    	 -----------------------------------------
//         Actions move = new Actions(driver);
//         Action action = (Action) move.dragAndDropBy(slider, 1, 0).build();
//         action.perform();
//    	 ------------------------------
//    	 Dimension sliderSize = slider.getSize();
//    		int sliderWidth = sliderSize.getWidth();
//    		System.out.println(sliderWidth);
//    		int xCoord = slider.getLocation().getX();
//    		System.out.println(xCoord);			
//    		Actions builder = new Actions(driver);   
//    		builder.moveToElement(slider).click().dragAndDropBy(slider,xCoord+sliderWidth, 0).build().perform();
//    				System.out.println(percent.getText());	
//    		WebElement hiddenPrice = findHiddenElement(hiddenPriceLocator);
    					 
//    		int priceValue = Integer.parseInt(hiddenPrice.getAttribute("value"));
    				
//    		assertEquals(priceValue, 1000000);	
    		
//    		priceSlider = findElement(priceSliderLocator);
    					   
//    		String sliderPercent = priceSlider.getAttribute("style");
    		
    	 
    	 Thread.sleep(10000);
    	 

        }
        
 
    
    @AfterMethod
		public void tearDown() {
    	System.out.println("After Method");
    	driver.quit();
    }
}