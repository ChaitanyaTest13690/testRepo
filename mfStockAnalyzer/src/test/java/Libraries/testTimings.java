package Libraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class testTimings 
{
	WebDriver driver;
	DateTimeClass dt = new DateTimeClass();
	
	@Test (priority=1)
	public void test1() throws Exception
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\S00297\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		String s = dt.getTime();
		System.out.println("URL Passed to Browser Timings:"+ s);
		driver.get("https://cloudforms.services.infinity.common.airbusds.corp");
		
		String s1 = dt.getTime();
		System.out.println("URL is open in the browser Timings:"+ s1);
	}
}
