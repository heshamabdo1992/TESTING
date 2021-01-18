package PMS_TAHA;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class Solution extends WebDriverWait {

    
    public Solution(WebDriver driver, long timeOutInSeconds) {
		super(driver, timeOutInSeconds);
		// TODO Auto-generated constructor stub
	}

	@Test
    public void verifyLoginPageTitle() {
                     WebDriver driver= new ChromeDriver();
             		driver.get("https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_search/6f03f4361b080eeb747193aadd94cd2b/static/attachments/reference_page.html");

        String title = driver.getTitle();
        assertEquals("ARR",title);


    }
}