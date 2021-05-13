package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Libraries.passEncryptDecrypt;

public class AMFI 
{
	WebDriver driver;
	WebDriverWait wait;
	passEncryptDecrypt td;
	
	public AMFI(WebDriver driver) throws Exception
	{
		this.driver= driver;
		this.wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		td= new passEncryptDecrypt();
	}
	
	private By clickOnEndType = By.xpath("//button[@data-id='end-type']");
	
	public String amfiResearchInfoPageValidation()
	{
		String result = "Fail";
		switchToFrame();
		if (wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnEndType)).isDisplayed())
			result = "Pass";
		return result;
	}
	
	public void switchToFrame()
	{
		int size = driver.findElements(By.tagName("iframe")).size();
		driver.switchTo().frame(0);
		wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnEndType)).isDisplayed();
	}
	
	public String selectEndType(String endType)
	{
		String result = "Fail";
		switchToFrame();
		driver.findElement(clickOnEndType).click();
		driver.findElement(By.xpath("//span[contains(text(),'"+endType+"')]")).click();
		String setEndType = driver.findElement(By.xpath("//span[contains(text(),'"+endType+"')]")).getText();
		if (endType.contains(setEndType))
			result="Pass";
		return result;
	}
	
	public String verifyUserAbleToSelectSchemeEndType(String endType)
	{
		String result = "Fail";
		if ((driver.findElement(By.xpath("//span[contains(text(),'"+endType+"')]")).getText()).contains(endType))
			result="Pass";
		return result;
	}
	
	public void selectFundType() {}
	public void selectFundCap() {}
	public void selectFundName() {}
	
}
