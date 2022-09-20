package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class Extra_ShadowDOMElement {
	WebDriver driver;
  @BeforeTest
  public void beforeTest() {
	  driver = new ChromeDriver();
	   
  }

  @Test(enabled=false)
  public void shadowDom() throws InterruptedException {
	  
	  driver.get("https://letcode.in/shadow");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  JavascriptExecutor js = (JavascriptExecutor) driver; 
	  //control shadow DOM    open
	  String fname = "return document.querySelector('#open-shadow').shadowRoot.querySelector('#fname')";
	  
	  WebElement fnameElement =(WebElement) js.executeScript(fname);
	  fnameElement.sendKeys("abc");
	    
	  // closed, worked 
	  // myRoot là cái mà Dev define trong code nha, chứ mình ko bít đâu mà lấy
	  // ko dùng sendkey như trên đc vì sendKey của selenium => và ko work chỉ work với JS
	  String lname = "return document.querySelector('my-web-component').myRoot.querySelector('#lname').value='xyz'"; 
	  js.executeScript(lname); 
	  
	  //closed, not worked
	  // nếu trong code mà dùng attachShadow với mode= "closed" là ko làm đc
	  // vì shadowRoot = null

  }
  
  @Test
  public void shadowDom_shopee() {
	  driver.get("https://shopee.vn/");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  JavascriptExecutor js1 = (JavascriptExecutor) driver;
	  
	  String str = "return document.querySelector(\"shopee-banner-popup-stateful[spacekey='PC-VN-HOME_POPUP_01']\").shadowRoot.querySelector('.shopee-popup__close-btn')";
	  WebElement fnameElement =(WebElement) js1.executeScript(str);
	  fnameElement.click();
	  
  }
  
  @AfterMethod
  public void Wait() {
	  try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
  }
  
  @AfterTest
  public void afterTest() {
		  driver.quit();
  }

}
