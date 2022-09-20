package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_14_Wait_WebDriverWait_FluentWait{
  WebDriver driver;

  @BeforeTest
  public void beforeTest() {
    driver = new ChromeDriver();

  }

  public void ImplicitWait_Example() { 
	String guruHomePageTitle ="Demo Guru99 Page";
    driver.get("https://demo.guru99.com/test/guru99home/");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
    driver.manage().window().maximize();   
    
    Assert.assertTrue(driver.findElement(By.xpath("//i[@class='icon-usd']")).isDisplayed());
    Assert.assertEquals(driver.getTitle(), guruHomePageTitle);
  }
  
  @Test
  public void ExplicitWait_Example() { 
    driver.get("https://demos.telerik.com/aspnet-core/daterangepicker");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();   
    
    Assert.assertTrue(driver.findElement(By.xpath("//i[@class='icon-usd']")).isDisplayed());
    Assert.assertEquals(driver.getTitle(), "");

  }
  
  @Test
  public void FluentWait_Example() {
	  
  }
  @AfterTest
  public void afterTest() {
    driver.quit();
  }

}
