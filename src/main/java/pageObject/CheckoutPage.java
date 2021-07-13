package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.BaseClass;

public class CheckoutPage extends BaseClass {
	
	public WebDriver d;
	
	private By fname=By.id("first-name");
	private By lname=By.id("last-name");
	private By postalcode=By.id("postal-code");
	private By cont=By.id("continue");
	private By cancel=By.xpath("//button[contains(text(),'Cancel')]");
	private By all=By.xpath("//*[contains(text(),'All')]");
	private By productname=By.xpath("//div[@class='inventory_item_name']");
	private By cancel1=By.id("cancel");
	private By finish=By.id("finish");
	private By home=By.xpath("//button[contains(text(),'Back Home')]");
	
	public CheckoutPage(WebDriver d) {
		this.d=d;
	}
	
	public WebElement fname()
	{
		return d.findElement(fname);
	}
	
	public WebElement lname()
	{
		return d.findElement(lname);
	}
	public WebElement pcode()
	{
		return d.findElement(postalcode);
	}
	public WebElement cont()
	{
		return d.findElement(cont);
	}

	public WebElement cancel()
	{
		return d.findElement(cancel);
	}
	
	public WebElement cancel1()
	{
		return d.findElement(cancel1);
	}
	public WebElement all()
	{
		return d.findElement(all);
	}
	public WebElement productname()
	{
		return d.findElement(productname);
	}
	public WebElement finish()
	{
		return d.findElement(finish);
	}
	public WebElement home()
	{
		return d.findElement(home);
	}

}
