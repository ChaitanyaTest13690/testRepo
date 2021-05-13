package Pages.Common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class baseClass
{
	public WebDriver driver;
	public Properties prop;
	public String globalPath = System.getProperty("user.dir");
	
	public WebDriver initDriver() throws IOException, InterruptedException
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(globalPath+"\\src\\test\\java\\Pages\\Common\\config.properties");
		prop.load(fis);
		String browserName=prop.getProperty("browser");
				
		if (browserName.contains("chrome"))
		{
			deleteChromeProcess();
			setDesiredCapabilities();
			System.setProperty(prop.getProperty("driverName"),globalPath+prop.getProperty("chromeDriverPath"));
			driver = new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		return driver;
	}
	
	public void deleteChromeProcess() throws IOException, InterruptedException
	{
		Process batch = Runtime.getRuntime().exec(globalPath+prop.getProperty("chromeDriverKill"));
		batch.waitFor();
	}
	
	public void setDesiredCapabilities()
	{
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
	}
}
