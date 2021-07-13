package saucelab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObject.CartPage;
import pageObject.CheckoutPage;
import pageObject.LoginPage;
import pageObject.ProductPage;
import resources.BaseClass;

public class CartTest extends BaseClass {

	
	LoginPage l;CartPage c;
	ProductPage p;
	CheckoutPage ch;
	List<WebElement> a;
	
	
	@BeforeTest
	@Parameters({"uname","pass"})
	public void login(String uname, String pass) throws IOException
	{
		
		driver=initializeDriver();
		driver.get("https://www.saucedemo.com/");
		l=new LoginPage(driver);
		l.getEmail().sendKeys(uname);
		l.getPass().sendKeys(pass);
		l.Login().click();
		
	}
	
	@BeforeMethod
	public void verify()
	{
		p=new ProductPage(driver);
		c=new CartPage(driver);
		ch=new CheckoutPage(driver);
		a=new ArrayList<WebElement>();
	}
	
	@Test
	public void tc09VerifyAddtoCartandRemovecart()
	{
		a=p.a2cart();
		for(int i=1;i<=a.size();i++)
		{
			WebElement w=driver.findElement(By.xpath("(//*[text()='Add to cart'])["+i+"]"));
			w.click();
			if(p.shoppingcart().getText().equals("1"))
			p.removecart().click();
					
		}
	}
	@Test
	public void tc10ValidatingCartpage()
	{
		p.addcart().click();
		p.shoppingcart().click();
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/cart.html","Cart Page Not Loading");   //To verify cart page is loadred
		Assert.assertEquals(p.prodname().getText(),c.productname().getText()); 									 //To verify if added product is displayed in Cart Page
		c.remove().click();
		Assert.assertFalse(c.removecart().isDisplayed());
		c.contshop().click();		
	}
	
	@Test
	public void tc11CheckoutcartwithSingleItem()
	{   
		p.addcart().click();
	    p.shoppingcart().click();
		Assert.assertEquals(p.prodname().getText(),c.productname().getText());
		c.checkout().click();
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-one.html");
		
	}
	
	@Test(dataProvider="data")
	public void tc12validatecheckoutcredentials(String fname,String lname,String pcode)
	{
		ch.fname().sendKeys(fname);
		ch.lname().sendKeys(lname);
		ch.pcode().sendKeys(pcode);
		ch.cont().click();
		if(driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html"))
		{
			System.out.println("Navigated to Checkout Page"); 
			ch.finish().click();//Validating CheckoutPage
			Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ch.home().click();
		}
		else
			System.out.println("Invalid firstname/lastname/postalcode");
		    driver.navigate().refresh();
		
	}
	

	@Test(dataProvider="data2")
	public void tc13CheckoutwithMultipleProducts(String fname,String lname,String pcode)
	{
		a=p.a2cart();
		for(int i=1;i<=3;i++)
		{
			WebElement w=driver.findElement(By.xpath("(//*[text()='Add to cart'])["+i+"]"));
			w.click();
		}
	    p.shoppingcart().click();
		Assert.assertEquals(p.prodname().getText(),c.productname().getText());
		c.checkout().click();
		ch.fname().sendKeys(fname);
		ch.lname().sendKeys(lname);
		ch.pcode().sendKeys(pcode);
		ch.cont().click();
		if(driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html"))
		{
			System.out.println("Navigated to Checkout Page"); 
			ch.finish().click();//Validating CheckoutPage
			Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ch.home().click();
		}
		else
			System.out.println("Invalid firstname/lastname/postalcode");
		    driver.navigate().refresh();
		
	}
	
	@Test(dataProvider="data2")
	public void tc14CancelCheckout(String fname,String lname,String pcode)
	{
		p.addcart().click();
	    p.shoppingcart().click();
		Assert.assertEquals(p.prodname().getText(),c.productname().getText());
		c.checkout().click();
		ch.fname().sendKeys(fname);
		ch.lname().sendKeys(lname);
		ch.pcode().sendKeys(pcode);
		ch.cont().click();
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-two.html");
		System.out.println("Navigated to Checkout Page");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ch.cancel1().click();
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");
	}
	
	@DataProvider(name="data")
	public Object[][] data()
	{
		return new Object[][] {{"","Peter","234"},
							   {"Paul","Adams",""},
							   {"","",""},
							   {"Paul","","123"},
							   {"Sam","Adams","123"}};
	}
	
	@DataProvider(name="data2")
	public Object[][] data2()
	{
		return new Object[][] {{"Sam","Adams","123"}};
	}
	
	@AfterTest
	public void logout()
	{
		p.getmenu().click();
		p.getLogout().click();
	}

}
