package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.BaseClass;

public class LoginPage extends BaseClass {
	
public WebDriver d;

	private By uname=By.id("user-name");
	private By pass=By.id("password");
	private By login=By.id("login-button");
		
	public LoginPage(WebDriver d2) {
		this.d=d2;
	}

	public WebElement getEmail()
	{
		return d.findElement(uname);
	}

	public WebElement getPass()
	{
		return d.findElement(pass);
	}
	
	public WebElement Login()
	{
		return d.findElement(login);
		
	}
	

}