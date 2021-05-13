package Libraries;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirstTestClass {
     public static void main(String args[]) throws InterruptedException 
     {                  
            WebDriver driver = new FirefoxDriver();       
             driver.get("http://toolsqa.com/Automation-practice-form/");             
             String strPassword= "sbct1235";// read this value from an excel or notepad 
             
            driver.findElement(By.name("firstname")).sendKeys (passwordEncryption.SelsetSecure(strPassword)); // if excel is provide with actual password
            driver.findElement(By.name("firstname")).sendKeys (passwordEncryption.decodePassword(strPassword)); // if excel has the encrypted password
                          
     }
}
