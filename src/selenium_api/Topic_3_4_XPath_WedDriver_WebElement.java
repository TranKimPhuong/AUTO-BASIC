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

public class Topic_3_4_XPath_WedDriver_WebElement {
  WebDriver driver;

  @BeforeTest
  public void beforeTest() {
	System.setProperty("webdriver.chrome.driver",".//resources/chromedriver.exe");
	driver = new ChromeDriver();

  }

  @Test
  public void verifyURLAndTitle() {
    driver.get("http://live.guru99.com");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();

    driver.findElement(By.xpath("//a[text()='here']")).click();
    
    driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("abc@gmail.com");
    driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
    
    
    //System.out.println("step 1: Navigate to Home Page"); 
    //String homePageTitle = driver.getTitle();
    //Assert.assertEquals(homePageTitle,"Home page");
    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    

    System.out.println("step 2: click MyAccount");  
    WebElement liMyAccount = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
    liMyAccount.click();
    String loginPageTitle = driver.getTitle();
    Assert.assertEquals(loginPageTitle, "Customer Login");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);



    System.out.println("step 3: create New Account "); 
    WebElement btnCreate = driver.findElement(By.xpath("//span[text()='Create an Account']"));
    btnCreate.click();
    String createAccountPageTitle = driver.getTitle();
    Assert.assertEquals(createAccountPageTitle, "Create New Customer Account");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    // WebElement txtFirstName = driver.findElement(By.xpath("//input[@id='firstname']"));
    // txtFirstName.sendKeys("KP");
    // WebElement txtMiddleName = driver.findElement(By.xpath("//input[@id='middlename']"));
    // txtMiddleName.sendKeys("_");

    // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    // LocalDateTime now = LocalDateTime.now(); 
    // String currentTime = dtf.format(now).replace("", "/").replace("", ":").replace("", " ");

    // WebElement txtLastName = driver.findElement(By.xpath("//input[@id='lastname']"));
    // txtLastName.sendKeys(currentTime);
    // //String fullName = "KP _ " + currentTime;

    // WebElement txtEmail = driver.findElement(By.xpath("//input[@id='email_address']"));
    // txtEmail.sendKeys("ttkp83.visualstudio@gmail.com");
    // WebElement txtPass = driver.findElement(By.xpath("//input[@id='password']"));
    // txtPass.sendKeys("lieu0808");
    // WebElement txtConfirm = driver.findElement(By.xpath("//input[@id='confirmation']"));
    // txtConfirm.sendKeys("lieu0808");
    // WebElement btnRegister = driver.findElement(By.xpath("//button[@title='Register']"));
    // btnRegister.click();
    // String myAccountPageTitle = driver.getTitle();
    // Assert.assertEquals(myAccountPageTitle, "My Account");
    // WebElement laContact = driver.findElement(By.xpath("//p[@class='hello']/strong"));
    // String contact = laContact.getText();
    // Assert.assertTrue(contact.contains(fullName));
    
    System.out.println("step 4: Back To login page"); 
    // WebElement btnBack = driver.findElement(By.xpath("//a[@class='back-link']"));
    // btnBack.click();
    driver.navigate().back();
    String loginURL = driver.getCurrentUrl();
    Assert.assertEquals(loginURL, "http://live.techpanda.org/index.php/customer/account/login/");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    System.out.println("step 5: Forward to createnew account page"); 
    driver.navigate().forward();
    String createAccountURL = driver.getCurrentUrl();
    Assert.assertEquals(createAccountURL, "http://live.techpanda.org/index.php/customer/account/create/"); 
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


  }

  @Test
  public void loginEmpty() { 
    driver.get("http://live.guru99.com");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();

    System.out.println("step 1: click MyAccount");  
    WebElement liMyAccount = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
    liMyAccount.click();
    String loginPageTitle = driver.getTitle();
    Assert.assertEquals(loginPageTitle, "Customer Login");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    System.out.println("step 2: Leave UserName and Password empty");
    System.out.println("step 3: Click Login button");
    WebElement btnLogin = driver.findElement(By.xpath("//button[@id='send2']"));
    btnLogin.click();

    System.out.println("step 4: Verify messages");
    WebElement msgUserName = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']"));
    WebElement msgPass = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']"));
    Assert.assertEquals(msgUserName.getText(), "This is a required field.");
    Assert.assertEquals(msgPass.getText(), "This is a required field.");

  }

  @Test
  public void loginInvalidEmail() { 
    driver.get("http://live.guru99.com");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    
    System.out.println("step 1: click MyAccount");  
    WebElement liMyAccount = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
    liMyAccount.click();
    String loginPageTitle = driver.getTitle();
    Assert.assertEquals(loginPageTitle, "Customer Login");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    System.out.println("step 2: Enter invalid email");
    WebElement txtUserName = driver.findElement(By.xpath("//input[@id='email']"));
    txtUserName.sendKeys("12345@12345.12345");

    System.out.println("step 3: Click Login button");
    WebElement btnLogin = driver.findElement(By.xpath("//button[@id='send2']"));
    btnLogin.click();

    System.out.println("step 4: Verify messages");
    WebElement msgUserName = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']"));
    Assert.assertEquals(msgUserName.getText(), "Please enter a valid email address. For example johndoe@domain.com.");

  }

  @AfterTest
  public void afterTest() {
    driver.quit();
  }

}
