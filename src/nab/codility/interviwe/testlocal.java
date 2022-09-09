package nab.codility.interviwe;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;




public class testlocal extends WebDriverTestCase {

      String creEmail ="login@codility.com";
      String crePassword = "password";
      String invalidEmail = "abc";
      String invalidPassword = "H!321";
      String expectedMessage = "Enter a valid email";

      String locatorForm = "//div[@id='login-form']";
      String locatorEmail = "//input[@id='email-input']";
      String locatorPass = "//input[@id='password-input']";
      String locatorLogin = "//button[@id='login-button']";
      String locatorErrorMsg = "//div[text()='Enter a valid email']";


      @Test
      public void testLoginFormPresent() throws Exception{
        //Thread.sleep(2000);
        Assert.assertEquals(isPresent(locatorForm), true);
        //System.out.println("Login Form is presented successfully!");
      }
      
      @Test
      public void testEmptyFieldsValidation() throws Exception{
        //Thread.sleep(2000);
        Assert.assertEquals(getTextElement(locatorEmail).isEmpty(), true);
        Assert.assertEquals(getTextElement(locatorPass).isEmpty(), true);
        //System.out.println("Email And Password are empty!");
      }
      
      @Test
      public void testEmailValidation(){
        Assert.assertEquals(isValid(creEmail), true);
        //System.out.println("Email is valid!");
      }
      
      @Test
      public void testWrongCredentials(){
        inputValueToElement(locatorEmail, invalidEmail);
        inputValueToElement(locatorPass, invalidPassword);
        clickToElement(locatorLogin);
        Assert.assertEquals(getTextElement(locatorErrorMsg),expectedMessage);
        //System.out.println("Login with wrong credential!");
      }

      @After
      public void afterTest(){

          webDriver.quit();
      }
    
      public boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
      }

      public void inputValueToElement(String xpathExpresssion,String value){
        WebElement element = webDriver.findElement(By.xpath(xpathExpresssion));
        element.clear();
        element.sendKeys(value);
      }

      public void clickToElement(String xpathExpresssion){
        WebElement element = webDriver.findElement(By.xpath(xpathExpresssion));
        element.click();
      }
      public String getTextElement(String xpathExpresssion){
        WebElement element = webDriver.findElement(By.xpath(xpathExpresssion));
        return element.getText();
      }
      public boolean isPresent(String xpathExpresssion){
        WebElement element = webDriver.findElement(By.xpath(xpathExpresssion));
        return element.isDisplayed();
      }
}

class WebDriverTestCase {
  WebDriver webDriver;
  @Before
  public void initializeWD(){
    webDriver = new ChromeDriver();
    webDriver.get("https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_login_page/9a83bda125cd7398f9f482a3d6d45ea4/static/attachments/reference_page.html");
  }

}
