package selenium_api;
 import org.testng.annotations.Test;
 import org.testng.annotations.BeforeTest;

 import java.util.concurrent.TimeUnit;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class Topic_10_JavaScriptExcute{
  WebDriver driver;

  @BeforeTest
  public void beforeTest() {
    driver = new ChromeDriver();
    driver.get("https://demo.guru99.com/v4/");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void verifyElementIsDisplayed() { 

    System.out.println("step 1: Verify elements are displayed.");  
    

  }
  //https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit

  @AfterTest
  public void afterTest() {
    driver.quit();
  }

  public void hightlightWebElement(){}
  public Object executeForBrowserByJS(){
	  return null;
  }
  public Object clickToElementByJS(){
	  return null;
  }
  public Object sendKeyToElementByJS(){
	  return null;
  }
  public Object scrollToBottomPageByJS(){
	  return null;
  }
  public Object removeAttributeInDOM(){
	  return null;
  }

  public Object navigateToPageByJS(){
	  return null;
  }


}
