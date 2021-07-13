package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.BaseClass;

public class ProductPage extends BaseClass {
	
	public WebDriver d;

	
	private By menu=By.xpath("//button[contains(text(),'Menu')]");
	private By addcart=By.xpath("//*[text()='Add to cart']");
	private By removecart=By.xpath("//*[text()='Remove']");
	private By logout=By.xpath("//a[@id='logout_sidebar_link']");
	private By productname=By.xpath("//div[@class='inventory_item_name']");
	private By b2cart=By.xpath("//*[text()='Back to products']");
	private By sort=By.xpath("//*[@class='product_sort_container']");
	private By price=By.xpath("//div[@class='inventory_item_price']");
	private By shoppingcart=By.xpath("//*[@class='shopping_cart_badge']");
	
	
	public ProductPage(WebDriver d2) {
		this.d=d2;
	}

	public WebElement getmenu()
	{
		return d.findElement(menu);
	}

	public WebElement getLogout()
	{
		return d.findElement(logout);
	}
	
	public List<WebElement> productname()
	{
		return d.findElements(productname);
	}
	
	public WebElement b2cart()
	{
		return d.findElement(b2cart);
	}

	public WebElement getSort() {
		
		return d.findElement(sort);
	}

	public List<WebElement> price()
	{
		return d.findElements(price);
	}

	public List<WebElement> a2cart()
	{
		return d.findElements(addcart);
	}
	
	public WebElement shoppingcart()
	{
		return d.findElement(shoppingcart);
	}
	
	public WebElement removecart()
	{
		return d.findElement(removecart);
	}
	
	public WebElement addcart()
	{
		return d.findElement(addcart);
	}

	public WebElement prodname()
	{
		return d.findElement(productname);
	}
}
