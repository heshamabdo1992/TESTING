package com.codility.selenium.candidate;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

//import org.junit.Before;
//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import com.codility.selenium.tests.WebDriverTestCase;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
//import org.junit.After;
import org.testng.annotations.Test;


public class Solution{
	public  WebDriver driver;
    
	@BeforeMethod
	public void setup(){

		driver = new ChromeDriver();
		//driver.manage().window().maximize();
	}
    
   // @Test
    public void TestCase1() {
		driver.get("https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_search/6f03f4361b080eeb747193aadd94cd2b/static/attachments/reference_page.html");

    	//WebElement search_input =driver.findElement(By.id("search-input"));
    	//WebElement search_button =driver.findElement(By.id("search-button"));
    	if(driver.findElement(By.id("search-input"))!= null){
    		System.out.println("TestCase1 - Element (search-input) is Present");
    		}else{
    		System.out.println("TestCase1 - Element (search-input)  is Absent");
    		}
    	
    	if(driver.findElement(By.id("search-button"))!= null){
    		System.out.println("TestCase1 - Element (search-input) is Present");
    		}else{
    		System.out.println("TestCase1 - Element (search-input)  is Absent");
    		}

    }
    
    
   // @Test
    public void TestCase2() {
		driver.get("https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_search/6f03f4361b080eeb747193aadd94cd2b/static/attachments/reference_page.html");

    	WebElement search_input =driver.findElement(By.id("search-input"));
    	WebElement search_button =driver.findElement(By.id("search-button"));
    	search_input.sendKeys("");
    	search_button.click();
    	WebElement error_empty_query =driver.findElement(By.id("error-empty-query"));
    	assertEquals( "Provide some query", error_empty_query.getText().trim());
    	System.out.println("TestCase2 - Empty search result");
    }
    
    
    
   @Test
    public void TestCase3() throws InterruptedException {
		driver.get("https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_search/6f03f4361b080eeb747193aadd94cd2b/static/attachments/reference_page.html");

    	WebElement search_input =driver.findElement(By.id("search-input"));
    	WebElement search_button =driver.findElement(By.id("search-button"));
    	search_input.sendKeys("isla");
    	search_button.click();
    	
    	driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

    	//WebDriverWait wait = new WebDriverWait(driver, 1,0);
    	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search-results\"]/li[1]")));

    	if(driver.findElement(By.xpath("//*[@id=\"search-results\"]/li[1]"))!= null){
    		System.out.println("TestCase3 - There is at least one search result");
    		}else{
    		System.out.println("TestCase3 - There is no search result");
    		}

    }
    
    
  // @Test
    public void TestCase4() throws InterruptedException {
		driver.get("https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_search/6f03f4361b080eeb747193aadd94cd2b/static/attachments/reference_page.html");

    	WebElement search_input =driver.findElement(By.id("search-input"));
    	WebElement search_button =driver.findElement(By.id("search-button"));
    	search_input.sendKeys("test");
    	search_button.click();
    	Thread.sleep(2000);

    	WebElement error_no_query =driver.findElement(By.id("error-no-results"));
    	assertEquals( "No results", error_no_query.getText().trim());
    	System.out.println("TestCase4 - No results");

    }
    
  //  @Test
    public void TestCase5() throws InterruptedException {
		driver.get("https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_search/6f03f4361b080eeb747193aadd94cd2b/static/attachments/reference_page.html");

    	WebElement search_input =driver.findElement(By.id("search-input"));
    	WebElement search_button =driver.findElement(By.id("search-button"));
    	search_input.sendKeys("Port Royal");
    	search_button.click();
    	Thread.sleep(1000);
    	List<WebElement> search_results=driver.findElements(By.xpath("//*[@id=\"search-results\"]/li"));
    	if(search_results.size()>1){
    		System.out.println("There is many search results");
    		}else if(search_results.size()==1){
    		System.out.println("TestCase5 - There is one search result");
    		}
    		else{
        		System.out.println("TestCase5 - There is no search result");
        		}
    }
    
    
	@AfterMethod
	public void quit() {
		driver.quit();
	}
}