/* Author: truonganhdung
 * Created Date: xx/xx/2018
 * Modified Date: xx/xx/2018
 * */

package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_8_User_Interaction_Action {
    WebDriver driver;
	Actions action;

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		action = new Actions(driver);
		//driver.get("http://live.guru99.com");

	}

	//( ._.')----------------------------------------------------
	@Test(enabled=true)
	public void TC_01_HoverMouse() throws Exception {
		driver.get("https://www.tomerlerner.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\anhdung.truong\\eclipse-workspace\\AUTO-BASIC\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", ".\\chromedriver.exe");

		WebElement profile = driver.findElement(By.xpath("//a[@class='jsx-1770341306 project' and text()='Metechi']"));
		action.moveToElement(profile);
		action.perform();
		Thread.sleep(1000);

		Assert.assertTrue(profile.findElement(By.xpath("//li[@class='jsx-1770341306' and text()='Product Design']")).isDisplayed());	
		
	}

	@Test(enabled=true)
	public void TC_02_ClickAndHold() throws Exception {
		driver.get("https://jqueryui.com/selectable/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		List<WebElement> frame = driver.findElements(By.xpath("//iframe[@class='demo-frame']"));
		if (frame.size() > 0)
		{
			driver.switchTo().frame(frame.get(0));
			List<WebElement> items = driver.findElements(By.xpath("//li[@class='ui-widget-content ui-selectee']"));
			action.clickAndHold(items.get(0)).moveToElement(items.get(3)).release().perform();
			Thread.sleep(1000);
	
			List<WebElement> itemSelected = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
			Assert.assertEquals(itemSelected.size(),4);	

			driver.switchTo().defaultContent();
		}
	}


	@Test(enabled=true)
	public void TC_03_ClickAndHold_Random() throws Exception {
		driver.get("https://jqueryui.com/selectable/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		List<WebElement> frame = driver.findElements(By.xpath("//iframe[@class='demo-frame']"));
		if (frame.size() > 0)
		{
			driver.switchTo().frame(frame.get(0));
			List<WebElement> items = driver.findElements(By.xpath("//li[@class='ui-widget-content ui-selectee']"));
			action.keyDown(Keys.LEFT_CONTROL)
					.click(items.get(0))
					.click(items.get(3))
					.click(items.get(6))
					.release().perform();
			Thread.sleep(1000);
	
			List<WebElement> itemSelected = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
			Assert.assertEquals(itemSelected.size(),3);	

			driver.switchTo().defaultContent();
		}
	}

	@Test(enabled=true)
	public void TC_03_DoubleClick() throws Exception {
		driver.get("https://demo.guru99.com/test/simple_context_menu.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement dbClick = driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
		action.doubleClick(dbClick).perform();

		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "You double clicked me.. Thank You..");

		alert.accept();

		driver.switchTo().defaultContent();

	}
	@Test(enabled=true)
	public void TC_04_RightClick() throws Exception {
		driver.get("https://demo.guru99.com/test/simple_context_menu.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		WebElement dbClick = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(dbClick).perform();

		
		WebElement quit = driver.findElement(By.xpath("//li/span[text()='Quit']"));
		action.moveToElement(quit).click().perform();

		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: quit");
		alert.accept();


	}

	@Test(enabled=true)
	public void TC_05_DrapAndDrop() throws Exception {
		driver.get("https://demo.guru99.com/test/drag_drop.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		WebElement source = driver.findElement(By.xpath("//li[@id='fourth']/a[text()=' 5000 ']"));
		WebElement target1 = driver.findElement(By.xpath("//ol[@id='amt7']/li[@class='placeholder']"));
		WebElement target2 = driver.findElement(By.xpath("//ol[@id='amt8']/li[@class='placeholder']"));
		action.dragAndDrop(source, target1).perform();
		action.dragAndDrop(source, target2).perform();

		WebElement source1 = driver.findElement(By.xpath("//li[@id='credit2']/a[text()=' BANK ']"));
		WebElement target3 = driver.findElement(By.xpath("//ol[@id='bank']/li"));
		WebElement source2 = driver.findElement(By.xpath("//li[@id='credit1']/a[text()=' SALES ']"));
		WebElement target4 = driver.findElement(By.xpath("//ol[@id='loan']/li"));
		action.dragAndDrop(source1, target3).perform();
		action.dragAndDrop(source2, target4).perform();

		WebElement text = driver.findElement(By.xpath("//div[@id='equal']/a"));
		Assert.assertEquals(text.getText(), "Perfect!");

	}
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
