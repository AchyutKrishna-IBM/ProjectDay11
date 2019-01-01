package com.ibm.pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AdminPage {
	WebDriver driver;
	WebDriverWait wait;
	

	@FindBy(xpath = "//*[@id='side-menu']/li[5]/a")
	WebElement marketingEle;;
	@FindBy(xpath = "//*[@id='side-menu']/li[5]/ul/li[3]/a")
	WebElement pushNotificationEle;
	@FindBy(xpath = "//div[@class='header-title']/h1")
	WebElement pushNotificationHeader;
	@FindBy(xpath="//*[@id='page-wrapper']/div/div[1]/div/ol/li[1]/a")
	WebElement AddPushNotificationEle;
	@FindBy(xpath="//input[@placeholder='Notification Name']")
	WebElement notificationNameEle;
	@FindBy(xpath="//*[@id='page-wrapper']/div/form/div[1]/button")
	WebElement saveEle;
@FindBy(xpath="//textarea[@name='message']")
WebElement messageEle;
@FindBy(xpath="//input[@id='files']")
WebElement notificationimageElement;
@FindBy(xpath="//*[@id='page-wrapper']/div/div[2]")
WebElement confMessageEle;
@FindBy(xpath="//input[@type='search']")
WebElement searchPushNotification;
@FindBy(xpath="//table[@id='dataTableExample2']/tbody/tr/td[2]")
WebElement addedNotificationName;
@FindBy(xpath="//table[@id='dataTableExample2']/tbody/tr/td[3]")
WebElement addedNotificationMessage;

	public AdminPage(WebDriver driver, WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = wait;
	}

	public void clickOnMarketing() {
		marketingEle.click();
	}

	public void clickOnPushNotification() {
		pushNotificationEle.click();
	}

	public void pushNotificationHeaderVerification(String header) {
		Assert.assertTrue(pushNotificationHeader.getText().trim().equals(header));
		
	}
	public void clickOnAddPushNotification()
	{
		AddPushNotificationEle.click();
	}
	public void inputNotificationName(String nn)
	{
		notificationNameEle.sendKeys(nn);
	}
	public void clickOnSave()
	{
		saveEle.click();
	}
public void errorMessageValidation(String eror)
{
	JavascriptExecutor js=(JavascriptExecutor)driver;
String errmessage=(String) js.executeScript("return document.getElementsByName('message')[0].validationMessage;".toString());
	System.out.println(errmessage);
	Assert.assertEquals(eror,errmessage);
	
}
public void inputMessage(String msg)
{
	messageEle.sendKeys(msg);
}
public void clickOnNotificationAndUploadImage() throws InterruptedException
{
	
	
	notificationimageElement.sendKeys("C:\\Users\\IBM_ADMIN\\Downloads\\notification.jpg");
	Thread.sleep(5000);
	clickOnSave();
}
public void verifyOnAdminPage(String msg,String searchpn,String notmsg)
{
	Assert.assertTrue(confMessageEle.getText().contains(msg));
	searchPushNotification.sendKeys(searchpn);
	Assert.assertTrue(addedNotificationName.getText().trim().equals(searchpn));
	Assert.assertTrue(addedNotificationMessage.getText().trim().equals(notmsg));
	
}

	public String singleDataQuery(String tab_name,String col,String name_p) throws SQLException{
		String text=null;
		Connection con = DriverManager.getConnection("jdbc:mysql://foodsonfinger.com:3306/foodsonfinger_atozgroceries", 
				"foodsonfinger_atoz",
				"welcome@123");
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM "+tab_name+" WHERE "+col+"='"+name_p+"'");
		if(rs.next()) {
		text = rs.getString(col);
		}
		return text;
	}

}
