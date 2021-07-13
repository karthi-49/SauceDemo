package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
	
	public WebDriver d;

	private By menu=By.xpath("//button[contains(text(),'Menu')]");
	private By remove=By.xpath("//button[contains(text(),'Remove')]");
	private By checkout=By.xpath("//button[contains(text(),'Checkout')]");
	private By contshopping=By.xpath("//button[contains(text(),'Continue Shopping')]");
	private By productname=By.xpath("//div[@class='inventory_item_name']");
	private By rmvcart=By.xpath("//*[@class='removed_cart_item']");
	
	public CartPage(WebDriver d) {
		this.d=d;
	}
	
	public WebElement getmenu()
	{
		return d.findElement(menu);
	}
	
	public WebElement remove()
	{
		return d.findElement(remove);
	}
	public WebElement checkout()
	{
		return d.findElement(checkout);
	}
	public WebElement contshop()
	{
		return d.findElement(contshopping);
	}
	
	public WebElement productname()
	{
		return d.findElement(productname);
	}
	
	public WebElement removecart()
	{
		return d.findElement(rmvcart);
	}

}
