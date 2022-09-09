package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_9_HandleWindow {
  WebDriver driver;

  String email = "abc@gmail.com";

  String title = "Guru99 Bank Home Page";

  @BeforeTest
  public void beforeTest() {
    driver = new ChromeDriver();
   
  }

  @Test
  public void TC_01_2wnidows() { 
    driver.get("http://demo.guru99.com/popup.php");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();

    driver.findElement(By.xpath("//a[text()='Click Here']")).click();
    String mainWindowId = driver.getWindowHandle();
    SwitchByID(mainWindowId);

    driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
    driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
    
    WebElement result = driver.findElement(By.xpath("//td[@class='accpage']/following-sibling::td"));

    Assert.assertTrue(result.isDisplayed());

    driver.close();
  }
  

  @AfterTest
  public void afterTest() {
    //driver.quit();
  }
public void SwitchByID(String mainWindowId){
  Set<String> s1 = driver.getWindowHandles();
  Iterator<String> i1 = s1.iterator();
  while(i1.hasNext()){
    String childWindowId = i1.next();
    if(!mainWindowId.equals(childWindowId)){
        driver.switchTo().window(childWindowId);
        break;
    }
  }
}
public void SwitchByTitle(String childTitle){
  Set<String> s1 = driver.getWindowHandles();
  Iterator<String> i1 = s1.iterator();
  while(i1.hasNext()){
    String childWindowId = i1.next();
    driver.switchTo().window(childWindowId);
    String title = driver.getTitle();
    if(title.equals(childTitle))
      break;
  }
  
}
public void isDisplayed(String id){
  
}
public void CloseAllWihtoutParentWindow(String id){
  
}

}
