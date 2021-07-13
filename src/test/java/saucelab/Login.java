package saucelab;

import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.LoginPage;
import pageObject.ProductPage;
import resources.BaseClass;

public class Login extends BaseClass{
	
	
	LoginPage l;
	ProductPage p;
	
	@BeforeTest
	public void initialize() throws IOException
	{
		driver=initializeDriver();
	}
	@BeforeMethod
	public void launch()
	{
		driver.get("https://www.saucedemo.com/");
		l=new LoginPage(driver);
	}
	
	@Test(dataProvider="negative")
	public void tc1nLogin(String uname,String pass)
	{
		l.getEmail().sendKeys(uname);
		l.getPass().sendKeys(pass);
		l.Login().click();
	    Assert.assertNotEquals("https://www.saucedemo.com/inventory.html",driver.getCurrentUrl(),"Login Failed");
	}
	
	
	@Test(dataProvider="positive")
	public void tc2pLogin(String uname,String pass)
	{	
		l.getEmail().sendKeys(uname);
		l.getPass().sendKeys(pass);
		l.Login().click();
	    //Assert.assertEquals("https://www.saucedemo.com/inventory.html",driver.getCurrentUrl(),"Login Failed");
	    if(driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html"))
	    	System.out.println(uname+" is Valid user");
	    else
	    	System.out.println(uname+" account has been locked out");
	    
	}
	
	
	
	@DataProvider(name="positive")
	public Object[][] testdata1()
	{
		return new Object[][]{{"standard_user","secret_sauce"},          //1.ValidUsername ValidPassword
							  {"locked_out_user","secret_sauce"},        //2.ValidUsername2 ValidPassword
			                  {"problem_user","secret_sauce"},           //3.ValidUsername3 ValidPassword
			                  {"performance_glitch_user","secret_sauce"},//4.ValidUsername4 ValidPassword
			                 };
	}
	
	@DataProvider(name="negative")
	public Object[][] testdata2()
	{
		return new Object[][]{ {"standard_user","secrsauce"},             //5.ValidUsername InvalidPassword
                               {"problem_user"," "},               //7.ValidUsername3 InvalidPassword
                               {"performance_glitch_user","secret_se"},   //8.ValidUsername4 InvalidPassword
                               {"pe","secret_sauce"},                     //9.InvalidUsername ValidPassword
                               {"peershf","secsauce"}                     //10.InvalidUsername InvalidPassword
			                 };
	}
	
	@AfterTest
	public void logout()
	{
		p=new ProductPage(driver);
		p.getmenu().click();
		p.getLogout().click();
		driver.close();
	}

}
