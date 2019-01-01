package com.ibm.test;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.ibm.initialization.WebDriverLaunch;
import com.ibm.pages.AdminPage;
import com.ibm.pages.Login;

import com.sun.java.swing.plaf.windows.resources.windows;


public class BaseTest extends WebDriverLaunch {
	
	
	@Test(priority=0)
        public void adminCredentials() throws FileNotFoundException, IOException
        {
		String url = p.getProperty("url");
		String UserName = p.getProperty("user");
		String Password = p.getProperty("password");
        driver.get(url);
		Login login = new Login(driver,wait);
		login.enterEmailAddress(UserName);
		login.enterPassword(Password);
		login.clickOnLogin();

        }
	   
	   
	  @Test(priority=1)
	    public void AddPushNotificationAndVerify() throws InterruptedException, SQLException
	    {
	    	String headr=p.getProperty("header");
	    	String notName=p.getProperty("notificationName");
	    	String errormessage=p.getProperty("error");
	    	String msg=p.getProperty("message");
	    	String confirmationmessage=p.getProperty("confmessage");
	    	String searchnotification=p.getProperty("notificationName");
	    	String table_name=p.getProperty("table");
	    	String col_name=p.getProperty("coloumn");
		   AdminPage adm=new AdminPage(driver,wait);
		   Thread.sleep(3000);
		   adm.clickOnMarketing();
		   Thread.sleep(3000);
		   adm.clickOnPushNotification();
		   Thread.sleep(3000);
		   adm.pushNotificationHeaderVerification(headr);
		   adm.clickOnAddPushNotification();
		   adm.inputNotificationName(notName);
		   adm.clickOnSave();
		   adm.errorMessageValidation(errormessage);
		   adm.inputMessage(msg);
		   Thread.sleep(2000);
		   adm.clickOnNotificationAndUploadImage();
		  adm.verifyOnAdminPage(confirmationmessage,searchnotification,msg);
	      String acts=adm.singleDataQuery(table_name, col_name, searchnotification);
		  Assert.assertEquals(acts,searchnotification);
		  
	  }
}

