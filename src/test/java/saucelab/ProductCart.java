package saucelab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObject.CartPage;
import pageObject.LoginPage;
import pageObject.ProductPage;
import resources.BaseClass;

public class ProductCart extends BaseClass {

	
	LoginPage l;CartPage ca;
	ProductPage p;
	List<WebElement> a;
	List<String> b;List<WebElement> c;
	Select s;
	
	@BeforeTest
	@Parameters({"uname","pass"})
	public void login(String uname,String pass) throws IOException
	{
		
		driver=initializeDriver();
		driver.get("https://www.saucedemo.com/");
		l=new LoginPage(driver);
		l.getEmail().sendKeys(uname);
		l.getPass().sendKeys(pass);
		l.Login().click();
	}
	
	@BeforeMethod
	public void initialize()
	{
		a=new ArrayList<WebElement>();
		c=new ArrayList<WebElement>();
		p=new ProductPage(driver);
		a=p.productname();
		c=p.price();
		b=new ArrayList<String>();
	    for(int i=0;i<a.size();i++)
	    	 b.add(a.get(i).getText());
	    s=new Select(p.getSort());
	    ca=new CartPage(driver);
	    
		
	}
	
	
	@Test
	public void tc3VerifyProductlist()
	{
		for(WebElement w: a)
		{
			System.out.println(w.getText());
		}	
	}
	
	@Test
	public void tc4VerifyProductDetails()
	{
		for(int i=1;i<=a.size();i++)
		{	
		WebElement w=driver.findElement(By.xpath("(//div[@class='inventory_item_name'])["+i+"]"));
		w.click();
		Assert.assertTrue(p.b2cart().isDisplayed());
		p.b2cart().click();
		}
	}
	
	
	@Test
	public void tc5VerifySortAtoZ()
	{
		Collections.sort(b);
		for(WebElement w: a)
		Assert.assertTrue(b.contains(w.getText()));
		
	}
	
	@Test
	public void tc6VerifySortZtoA()
	{
		s.selectByValue("za");
		Collections.reverse(b);
		a=p.productname();
		for(WebElement w: a)
		Assert.assertTrue(b.contains(w.getText()));
	}
	
	@Test
	public void tc7VerifyPriceLowtoHigh()
	{b.clear();
		for(int i=0;i<c.size();i++)
   	 b.add(c.get(i).getText());
		s.selectByValue("lohi");
	Collections.sort(b);
	 a=p.price();
	 for(WebElement w: a)
	 Assert.assertTrue(b.contains(w.getText()));	
	}
	
	@Test
	public void tc8VerifyPriceHightoLow()
	{
		b.clear();
		for(int i=0;i<c.size();i++)
   	 b.add(c.get(i).getText());
		s.selectByValue("hilo");
		Collections.reverse(b);
		a=p.price();
		for(WebElement w: a)
		Assert.assertTrue(b.contains(w.getText()));
	}

}



