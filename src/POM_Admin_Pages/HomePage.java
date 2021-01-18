package POM_Admin_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	By FullUserNametext= By.xpath("//*[@id=\"lbl_admin_FullName\"]");
	By Settingbtn= By.xpath("//*[@id=\"setting_icon\"]");
	By Profilebtn= By.xpath("//*[@id=\"ctl00_btn_Profile\"]");
	By Signoutbtn= By.xpath("//*[@id=\"ctl00_btn_LogOut\"]");
	By Customerbtn=By.cssSelector("#ctl00_ContentPlaceHolder1_lbl_cust");
	By Branchbtn= By.cssSelector("#ctl00_ContentPlaceHolder1_lbl_branch");
	By Rolebtn= By.cssSelector("#ctl00_ContentPlaceHolder1_lbl_role");
	By Userbtn= By.cssSelector("#ctl00_ContentPlaceHolder1_lbl_user");
	By Groupbtn=By.cssSelector("#ctl00_ContentPlaceHolder1_lbl_group");
	By Langbtn= By.cssSelector("#ctl00_ContentPlaceHolder1_lbl_lang");
	By Logbtn= By.cssSelector("#ctl00_ContentPlaceHolder1_lbl_log");
	By PRbtn=By.cssSelector("#ctl00_ContentPlaceHolder1_lbl_peer");
	By intergbtn= By.cssSelector("#ctl00_ContentPlaceHolder1_lbl_integ");
	By Reporttmpbtn= By.cssSelector("#ctl00_ContentPlaceHolder1_lbl_report");

	private  WebDriverWait wait;
     private static final int TIMEOUT = 5;
     private static final int POLLING = 100;
     
	public  HomePage(WebDriver driver)  {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);

    }
	// assert user name
	public void assertusername(String z) {
		WebDriverWait wait = new WebDriverWait(driver, 60, 250);
		wait.until(ExpectedConditions.textToBe(FullUserNametext, z));
//OR
//		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h2[contains(.,'" +z+ "')]")));

	}
	
    //Click on User Setting
    public  String GetFullUserName()    {
        return driver.findElement(FullUserNametext).getText();
    }
	
    
    //Click on User Setting
    public  void UserSetting_btn()    {
        driver.findElement(Settingbtn).click();
    }
    
    //Click on User Profile Setting
    public  void Profile_btn(String pass)    {
    	UserSetting_btn();
    	wait.until(ExpectedConditions.visibilityOfElementLocated((By) driver.findElement(Profilebtn)));
        driver.findElement(Profilebtn).click();
    }
    
    //Click on Sign out
    public  void Signout_btn()    {
    	UserSetting_btn();
    	wait.until(ExpectedConditions.visibilityOfElementLocated((By) driver.findElement(Signoutbtn)));
        driver.findElement(Signoutbtn).click();
    }
    
    //Click on Customer button
    public void Customerbtn_click(){
        driver.findElement(Customerbtn).click();
    }
    
    //Click on Branch button
    public void branchbtn_click(){
        driver.findElement(Branchbtn).click();
    }
    
    //Click on Role button
    public void Rolebtn_click(){
        driver.findElement(Rolebtn).click();
    }
    
    //Click on User button
    public void Userbtn_click(){
        driver.findElement(Userbtn).click();
    }
    
    //Click on Group button
    public void Groupbtn_click(){
        driver.findElement(Groupbtn).click();
    }

    //Click on Language button
    public void Langbtn_click(){
        driver.findElement(Langbtn).click();
    }
    
    //Click on Log button
    public void Logbtn_click(){
        driver.findElement(Logbtn).click();
    }
    
    //Click on Peer Review Config button
    public void PeerReviewbtn_click(){
        driver.findElement(PRbtn).click();
    }
    
    //Click on intergration button
    public void intergrationbtn_click(){
        driver.findElement(intergbtn).click();
    }
    
    //Click on Report template button
    public void Reporttemplatebtn_click(){
        driver.findElement(Reporttmpbtn).click();
    }


}
