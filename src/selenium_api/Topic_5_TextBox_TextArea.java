/* Author: truonganhdung
 * Created Date: xx/xx/2018
 * Modified Date: xx/xx/2018
 * */

package selenium_api;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_5_TextBox_TextArea {
    WebDriver driver;

	String customerName = "KPAuto" + randomChar();
	String dob = "12/16/1983";
	String add = "1246 Tinh lo 10";
	String city = "HCM";
	String state = "NY";
	String email = "KP_" + randomNumber() + "@gmail.com";
	String PIN = "123456";
	String cell = "0906161283";
	String pass = "KP" + randomNumber();

	String customerId = "11147";
	String msgCreate = "Customer Registered Successfully!!!";

	String updateAdd = "Nguyen Van Dau";
	String updateCity = "BT";
	String updateState ="BT";
	String updateEmail = "taday@gmail.com";
	String updatePIN = "345621";
	String updateCell = "09090987654";

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		driver.get("http://live.guru99.com");
		//mngr430516
		//murydUg
		//implicityWait nay apply cho find WebElement / List Element trong class nay 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//( ._.')----------------------------------------------------
	@Test(enabled=true)
	public void TC_01_Login() {
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr430516");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("murydUg");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		
		Assert.assertEquals(driver.getTitle(),"Guru99 Bank Manager HomePage");
	}

	@Test(enabled=false)
	public void TC_02_CreateNewCustomer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// if not found iframe then just wait 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<WebElement> popup1 = driver.findElements(By.xpath("//iframe[@id='google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0']"));	
		System.out.println("Popup1 =" +popup1.size());
		if(popup1.size() > 0 && popup1.get(0).isDisplayed())
		{
			driver.switchTo().frame(popup1.get(0));
			List<WebElement> popup2 = driver.findElements(By.xpath("//iframe[@id='ad_iframe']"));
			System.out.println("Popup2 =" + popup2.size());

			if(popup2.size() > 0 && popup2.get(0).isDisplayed())
			{
				driver.switchTo().frame(popup2.get(0));

				WebElement btnClose = driver.findElement(By.xpath("//div[@id='dismiss-button']//span[text()='Close']"));
				Assert.assertTrue(btnClose.isDisplayed());
				btnClose.click();
				
				//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
			driver.switchTo().defaultContent();
		}
		//set default as inital to avoid next steps
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Assert.assertEquals(driver.getTitle(),"Guru99 Bank New Customer Entry Page");
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(customerName);
		WebElement rdGender = driver.findElement(By.xpath("//input[@value='m']"));
		if (!rdGender.isSelected())
		rdGender.click();

		driver.findElement(By.xpath("//input[@id='dob']")).sendKeys(dob);
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(add);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(PIN);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(cell);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pass);


		driver.findElement(By.xpath("//input[@name='sub']")).click();

		// get customer ID 11147
		WebElement msgWelcome = driver.findElement(By.xpath("//tr[td[text()='Customer ID']]/preceding-sibling::tr//p"));
		Assert.assertEquals(msgWelcome.getText(), msgCreate);

		customerId = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();


	}
	
	/**
	 * 
	 */
	@Test(enabled=true)
	public void TC_03_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		// if not found iframe then just wait 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<WebElement> popup1 = driver.findElements(By.xpath("//iframe[@id='google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0']"));	
		System.out.println("Popup1 =" +popup1.size());
		if(popup1.size() > 0 && popup1.get(0).isDisplayed())
		{
			driver.switchTo().frame(popup1.get(0));
			List<WebElement> popup2 = driver.findElements(By.xpath("//iframe[@id='ad_iframe']"));
			System.out.println("Popup2 =" + popup2.size());

			if(popup2.size() > 0 && popup2.get(0).isDisplayed())
			{
				driver.switchTo().frame(popup2.get(0));

				WebElement btnClose = driver.findElement(By.xpath("//div[@id='dismiss-button']//span[text()='Close']"));
				Assert.assertTrue(btnClose.isDisplayed());
				btnClose.click();
				
				//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
			
			driver.switchTo().defaultContent();
		}
		//set default as inital to avoid next steps
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement msgWelcome = driver.findElement(By.xpath("//p[text()='Edit Customer Form']"));
		Assert.assertTrue(msgWelcome.isDisplayed());
		
		// enter customerID
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerId);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		//clear all data before update
		driver.findElement(By.xpath("//textarea[@name='addr']")).clear();
		driver.findElement(By.xpath("//input[@name='city']")).clear();
		driver.findElement(By.xpath("//input[@name='state']")).clear();
		driver.findElement(By.xpath("//input[@name='pinno']")).clear();
		driver.findElement(By.xpath("//input[@name='telephoneno']")).clear();
		driver.findElement(By.xpath("//input[@name='emailid']")).clear();

		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(updateAdd);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(updateCity);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(updateState);
		driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(updatePIN);
		driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(updateCell);
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(updateEmail);

		driver.findElement(By.xpath("//input[@name='sub']")).click();

		//send enter to popup window
		try {

			Robot robot = new Robot();
			//Press key Ctrl+Shift+i
			robot.keyPress(KeyEvent.VK_ENTER);
			
			//Release key Ctrl+Shift+i
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			// action.sendKeys(Keys.ENTER).build().perform();
		}catch (Exception e){}
	}

	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	public int randomNumber(){
		Random rand = new Random();
		int upperbound = 1000;
		int int_random = rand.nextInt(upperbound); 
		// double double_random=rand.nextDouble();
		// float float_random=rand.nextFloat(); 
		return int_random;
	}
	public char randomChar(){
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		return c;
	}
}
