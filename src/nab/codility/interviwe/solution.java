// package nab.codility.interviwe;

// import org.junit.Assert;
// import org.junit.Before;
// import org.junit.Test;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.chrome.ChromeDriver;
// import com.codility.selenium.tests.WebDriverTestCase;

// public class solution {
//     /*
//       !!! Do not create your own WebDriver. !!!
//       You can copy credentials from here:
//        - login@codility.com    password
//        - unknown@codility.com  password
//     */
//     String creEmail, crePassword, invalidEmail, invalidPassword, errorMessage;
//     WebElement txtEmail,txtPassword, btnLogin, lbErrorMessage; 

//     @Before
//     public void beforeClass(){

//         creEmail ="login@codility.com";
//         crePassword = "password";
//         invalidEmail = "abc";
//         invalidPassword = "H!321";
//         errorMessage = "Email and Password are invalid.";

//         txtEmail = webDriver.findElement(By.id("email-input"));
//         txtPassword = webDriver.findElement(By.id("password-input"));
//         btnLogin = webDriver.findElement(By.id("login-button"));
//         lbErrorMessage = webDriver.findElement(By.id("msg-text"));
//     }

//     @Test
//   public void testLoginFormPresent() {
//     Assert.assertTrue(txtEmail.isDisplayed());
//     Assert.assertTrue(txtPassword.isDisplayed());
//     Assert.assertTrue(btnLogin.isEnabled());
//   }

//   @Test
//   public void testEmptyFieldsValidation(){
//     Assert.assertTrue(getTextElement(txtEmail).isEmpty());
//     Assert.assertTrue(getTextElement(txtPassword).isEmpty());
//   }

//   @Test
//   public void testEmailValidation(){
//     Assert.assertTrue(isValid(creEmail));
//   }

//   @Test
//   public void testWrongCredentials(){
//     inputValueToElement(txtEmail, invalidEmail);
//     inputValueToElement(txtPassword, invalidPassword);
//     clickToElement(btnLogin);
//     Assert.assertEquals(getTextElement(lbErrorMessage),errorMessage);
//   }

// public boolean isValid(String email) {
//   String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//   return email.matches(regex);
// }

// public void inputValueToElement(WebElement element,String value){
//   element.clear();
//   element.sendKeys(value);
// }

// public void clickToElement(WebElement element){
//   element.click();
// }
// public String getTextElement(WebElement element){
//   return element.getText();
// }

// }
