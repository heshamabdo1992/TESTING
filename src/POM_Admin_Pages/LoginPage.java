package POM_Admin_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	WebDriver driver;
	By UserName= By.xpath("//*[@id=\"txt_UserName\"]");
	By Password= By.xpath("//*[@id=\"txt_Password\"]");
	By Loginbtn= By.xpath("//*[@id=\"btn_Login\"]");
	By Resetbtn=By.xpath("//*[@id=\"btn_Reset\"]");
	
	public  LoginPage(WebDriver driver)  {
        this.driver = driver;
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
    
    //Click on Reset button
    public void Reset_click(){
        driver.findElement(Resetbtn).click();
    }
    
    // Login Function
    public void Login(String username,String pass){
    	setusername(username);
    	setpassword(pass);
    	Login_click();
    }
	
	
}
