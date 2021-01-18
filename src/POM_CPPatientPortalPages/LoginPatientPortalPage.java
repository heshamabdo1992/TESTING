package POM_CPPatientPortalPages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPatientPortalPage {
	WebDriver driver;
	WebDriverWait wait;

	//-------https://carepassport.net/patients---------//
	By UserName= By.xpath("//*[@id='txt_UserName']");
	By Password= By.xpath("//*[@id='txt_Password_login']");
	By Loginbtn= By.xpath("//*[@id='LoginButton']");
	
	By FBLogin=By.xpath("//*[@id=\"dv_login\"]/div[1]/div[1]/a/span[2]/span");
	
	By FBUser=By.id("email");
	By FBPassword=By.id("pass");
	By FBLoginbtn=By.name("login");

	By GoogleLogin=By.xpath("//*[@id=\"dv_login\"]/div[1]/div[2]/a/span[2]/span");
	
	By GoogleNext=By.xpath("//*[@id='identifierNext']//span");
	By GoogleUserName= By.xpath("//*[@id='identifierId']");
	By GoolgePassword= By.xpath("//*[@id='password']/div[1]//div[1]/input");
	By GoolgePasswordNext= By.xpath("//*[@id='passwordNext']//span");
	By GoolgeallowNext= By.xpath("//*[@id='submit_approve_access']/content/span");
	
	public  LoginPatientPortalPage(WebDriver driver)  {
        this.driver = driver;
         wait = new WebDriverWait(driver, 10,100);
    }
	
    //Set user name in textbox
    public void setusername(String user)   {
        driver.findElement(UserName).clear();
        driver.findElement(UserName).sendKeys(user);
    }
    
    //Set password in password textbox
    public  void setpassword(String pass)    {
        driver.findElement(Password).clear();
        driver.findElement(Password).sendKeys(pass);
    }
	
    //Click on login button
    public void Login_click(){
        driver.findElement(Loginbtn).click();
    }
    
    //Click on Facebook login button
    public void LoginFB_click(){
        driver.findElement(FBLogin).click();
    }
    
    //Click on Google login button
    public void LoginGoogle_click(){
        driver.findElement(GoogleLogin).click();
    }
    
    
    public void Login(String username,String Password,String type) throws InterruptedException
    {
		 if(type=="N")
		{
			 LoginN(username, Password);
				//Thread.sleep(10000);	
		 }
		 else 
		 if (type=="G")
		 {
			 LoginGoogle(username, Password);
			//	Thread.sleep(10000);
		 }
	 else  if (type=="F")
		{
			 LoginFB(username, Password);
				//Thread.sleep(10000);
	     }
    }
    
    //Normal Login function
    public void LoginN(String username,String pass) throws InterruptedException{
    	setusername(username);
    	setpassword(pass);
		System.out.println("Normal Login for "+username);
//		System.out.println("before --LoginN_click();");
    	Login_click();
//		System.out.println("after --LoginN_click();");
		Thread.sleep(6000);
		String Home= driver.getCurrentUrl();
		if(Home.contains("https://carepassport.net/patients/Home.aspx"))
			{
			System.out.println("Successed Normal Login for "+username);
			Assert.assertTrue(true);
			}
		else
			{
			System.out.println("Failed Normal Login for "+username);
			Assert.assertTrue(false);
			}
		//Thread.sleep(6000);

    }
    
    //FaceBook Login function
    public void LoginFB(String username,String pass) throws InterruptedException{
		wait.until(ExpectedConditions.visibilityOfElementLocated(FBLogin));
		System.out.println("Login using Facebook for "+username);
//		System.out.println("before --LoginFB_click();");
		LoginFB_click();
//		System.out.println("after --LoginFB_click();");
		Thread.sleep(6000);
		String winHandlerBefore= driver.getWindowHandle();
		for(String winHandle: driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle);
	    	driver.manage().window().maximize();

		}
    	wait.until(ExpectedConditions.visibilityOfElementLocated(FBUser));
		driver.findElement(FBUser).sendKeys(username);
		driver.findElement(FBPassword).sendKeys(pass);
		driver.findElement(FBLoginbtn).click();
		driver.switchTo().window(winHandlerBefore);
		Thread.sleep(6000);
		String Home= driver.getCurrentUrl();
		if(Home.contains("https://carepassport.net/patients/Home.aspx"))
			{
			System.out.println("Successed Login using Facebook for "+username);
			Assert.assertTrue(true);
			}
		else
			{
			System.out.println("Failed Login using Facebook for "+username);
			Assert.assertTrue(false);
			}
		//Thread.sleep(6000);

    }
    
    //Google Login function
    public void LoginGoogle(String username,String pass) throws InterruptedException{
    	wait.until(ExpectedConditions.visibilityOfElementLocated(GoogleLogin));
		System.out.println("Login using google for "+username);
//		System.out.println("before --LoginG_click();");
    	LoginGoogle_click();
//		System.out.println("after --LoginG_click();");
		Thread.sleep(6000);
    	String winHandlerBefore= driver.getWindowHandle();
		for(String winHandle: driver.getWindowHandles())
		{
			driver.switchTo().window(winHandle);
	    	driver.manage().window().maximize();
		}
		
		if(driver.findElement(By.id("yDmH0d")).isDisplayed())
		{
			//Thread.sleep(3000);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(GoogleUserName));
			driver.findElement(GoogleUserName).sendKeys(username);
			driver.findElement(GoogleNext).click();
			//Thread.sleep(3000);
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(GoolgePassword));
			driver.findElement(GoolgePassword).sendKeys(pass);
			driver.findElement(GoolgePasswordNext).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(GoolgeallowNext));
			driver.findElement(GoolgeallowNext).click();
			driver.switchTo().window(winHandlerBefore);
			
			Thread.sleep(6000);
			String Home= driver.getCurrentUrl();
			if(Home.contains("https://carepassport.net/patients/Home.aspx"))
				{
				System.out.println("Successed Login using google for "+username);
				Assert.assertTrue(true);
				}
			else
				{
				System.out.println("Failed Login using google for "+username);
				Assert.assertTrue(false);
				}
		//	Thread.sleep(6000);
		}
		else
		{
			driver.findElement(By.className("BHzsHc")).click();
			driver.findElement(By.xpath("//*[@id='identifierNext']//span")).sendKeys("sally.sedky@paxerahealth.com");
		//	Thread.sleep(6000);
		}
    }

}
