package resources;


import java.io.FileReader;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {

	public WebDriver driver;
	public Properties prop;
	
	
	
	public WebDriver initializeDriver() throws IOException
	{
		prop=new Properties();
		FileReader fis=new FileReader("data.properties");
		prop.load(fis);
		String bName = prop.getProperty("browser");
		if(bName.equals("Chrome"))
		{
			  WebDriverManager.chromedriver().setup();
			  driver=new ChromeDriver();
		}
		else if(bName.equals("Edge"))
		{
			WebDriverManager.edgedriver().setup();
			  driver=new EdgeDriver();
		}
		else if(bName.equals("IE"))
		{
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
		else if(bName.equals("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
		return driver;
		
	}
	


}
