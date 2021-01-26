package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestUtility 
{
	
	private RemoteWebDriver driver;
	private WebDriverWait wait;
	
	public TestUtility()
	{
		driver=null;
		wait=null;
	}

	
	public String getValuesFromPropsFile(String propname) throws Exception
	{
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\"+"config.properties";
		File f=new File(path);
		FileInputStream fi=new FileInputStream(f);
		Properties p=new Properties();
		p.load(fi);
        String value=p.getProperty(propname);
        fi.close();
        return(value);
	}
	
	public RemoteWebDriver openbrowser(String browser)
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else
		{
			System.out.println("enter valid browser name");
			System.exit(0);
		}
		return driver;
	}
	
	public WebDriverWait defineWait(RemoteWebDriver driver) throws Exception
	{
		String temp=getValuesFromPropsFile("maxwait");
		int max=Integer.parseInt(temp);
		wait=new WebDriverWait(driver,max);
		return wait;
		
	}
	
	public void launchSite() throws Exception
	{
       String url=getValuesFromPropsFile("url");
       driver.get(url);
       driver.manage().window().maximize();
	}
	public String captureScreenshot() throws Exception
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy");
		Date dt=new Date();
		String fpath=System.getProperty("user.dir")+"\\target"+sf.format(dt)+".png";
		File dest=new File(fpath);
		File src=driver.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, dest);
		return dest.getAbsolutePath();
		
	}
	public void closeSite()
	{
		driver.quit();
	}
}
