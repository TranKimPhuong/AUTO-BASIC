/* Author: truonganhdung
 * Created Date: xx/xx/2018
 * Modified Date: xx/xx/2018
 * */

package selenium_api;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Strings;


public class Topic_6_CustomDropdown {
    WebDriver driver;
	WebDriverWait wait;

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
	}

	//( ._.')----------------------------------------------------
	@Test(enabled=false)
	public void TC_01_HTMLDropdown() throws InterruptedException {
		driver.get("https://demo.guru99.com/test/newtours/register.php");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String country = "CENTRAL AFRICAN REPUBLIC";

		Select selection = new Select(driver.findElement(By.xpath("//select[@name='country']")));
		Assert.assertFalse(selection.isMultiple());
		Thread.sleep(3000);
		selection.selectByVisibleText(country);
		Assert.assertEquals(selection.getFirstSelectedOption().getText(), country);

	}

	//( ._.')----------------------------------------------------
	@Test(enabled=false)
	public void TC_02_Custom_Dropdown_Jquery() {

		driver.get("https://jqueryui.com/selectmenu/#default");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String selected = "15"; 
		
		/*Get Iframe and switch to iframe*/
		List<WebElement> frames = driver.findElements(By.xpath("//iframe[@class='demo-frame']"));
		if(frames.size() > 0){
			driver.switchTo().frame(frames.get(0));
			
			/*Get locator and click parent node*/
			WebElement btn = driver.findElement(By.xpath("//span[@id='number-button']"));
			Assert.assertTrue(btn.isEnabled());
			btn.click();
			
			/*Get all items without scroll and click when meet the expected value*/
			List<WebElement> items = driver.findElements(By.xpath("//ul[@id='number-menu']//div"));
			for(WebElement w : items){
				if (w.getText().equals(selected)){
					w.click();
					break;
				}
			}
			String actual = driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText();
			Assert.assertEquals(selected, actual);

			driver.switchTo().defaultContent();

		}
		driver.close();
		
		//driver.get("https://www.jqueryscript.net/demo/Drop-Down-Combo-Tree/");

	}
	
	//( ._.')----------------------------------------------------
	@Test(enabled=true)
	public void TC_04_Custom_Dropdown_Angular_scroll() throws InterruptedException {
		//Angular phải scroll những item ẩn thì mới select dc =>sshould be
		driver.get("https://material.angular.io/components/select/overview");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		String country = "Wisconsin";

		/*Scroll and click dropdown */
		WebElement element = driver.findElement(By.xpath("//div[@class='mat-form-field-infix ng-tns-c153-17']/mat-select"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

		Thread.sleep(1000);
		//OR
		//driver.findElement(By.xpath("//div[@class='mat-form-field-infix ng-tns-c153-17']/mat-select")).click();

		/*Get list and click when meet*/
		List<WebElement> state = driver.findElements(By.xpath("//mat-option[starts-with(@id,'mat-option')]/span"));
		System.out.println(state.size());
		for(WebElement e : state){
			if (e.getText().equals(country)){
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
				break;
			}
		}
		String actual = driver.findElement(By.xpath("//div[@class='mat-form-field-infix ng-tns-c153-17']/mat-select//span/span")).getText();
		Assert.assertEquals(country, actual);
		
	}

	//( ._.')----------------------------------------------------
	@Test(enabled=true)
	public void TC_05_Custom_Dropdown_TelerikForJQuery() throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		String categoryValue = "Produce";
		String productValue = "Manjimup Dried Apples";
		String shipToValue = "Carrera 22 con Ave. Carlos Soublette #8-35";
		Thread.sleep(15000);

		/*Close adv and cookies*/
		driver.findElement(By.xpath("//div[@class='qual_x_close']")).click();
		driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();

		/*Select Categories */
		//driver.findElement(By.xpath("//label[@id='categories_label']/following-sibling::span[span[span[contains(text(),'Select category...')]]]")).click();
		WebElement category = driver.findElement(By.xpath("//label[@id='categories_label']/following-sibling::span[@aria-disabled='false']"));
		if (category.isEnabled()){
			selectItem(category, "//ul[@id='categories_listbox']/li//h3", categoryValue,"","");
			assertValue(category, categoryValue);
		}

		Thread.sleep(3000);

		/*Select product */
		WebElement product = driver.findElement(By.xpath("//label[@id='products_label']/following-sibling::span[@aria-disabled='false']"));
		// ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
		// ((JavascriptExecutor) driver).executeScript("arguments[0].click();", product);
		if (product.isEnabled()){
			selectItem(product, "//ul[@id='products_listbox']/li/span", productValue,"","");
			Thread.sleep(3000);
			assertValue(product, productValue);
		}
		
		Thread.sleep(3000);

		/*Select shipto */
		WebElement shipTo = driver.findElement(By.xpath("//label[@id='shipTo_label']/following-sibling::span[@aria-disabled='false']"));
		if (shipTo.isEnabled()){
			selectItem(shipTo, "//ul[@id='shipTo_listbox']/li/span", shipToValue,"","");
			Thread.sleep(3000);
			assertValue(shipTo,shipToValue);
		}
	}

	

	//( ._.')----------------------------------------------------
	@Test(enabled=true)
	public void TC_06_Custom_Dropdown_Multiple () throws InterruptedException {
		driver.get("https://semantic-ui.com/modules/dropdown.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		/*Search Selection: enter text*/
		String countryName = "Senegal";
		String child = "//div[@class='menu transition visible']/div[@class='item']";
		String search = "//div[@class='dropdown example']//input[@class='search']";

		WebElement parent = driver.findElement(By.xpath("//div[@class='dropdown example']"));
		Thread.sleep(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", parent);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", parent);
		selectItem(parent, child, countryName,search,countryName);
		String actual = driver.findElement(By.xpath("//div[@class='dropdown example']//div[@class='item active selected']")).getText();
		Assert.assertEquals(actual, countryName);


		/*Multiple Selection*/
		String[] multiValue = new String[] {"CSS","HTML","Javascript"};
		String multiSearch = "";
		String[] multiSearchValue = new String[] {};

		String multiChild = "//div[@class='dropdown example']//div[@class='menu transition visible']/div";
		Thread.sleep(3000);

		WebElement multiple = driver.findElement(By.xpath("//h4[text()='Multiple Selection']/following-sibling::div"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", multiple);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", multiple);
		selectMultiItems(multiple, multiChild, multiValue, multiSearch, multiSearchValue);
		driver.findElement(By.xpath("//h4[text()='Multiple Selection']")).click();
		assertMultiValue(multiple, multiValue);


		/*Multiple Search Selection*/

	}

	//( ._.')----------------------------------------------------
	@Test(enabled=true)
	public void TC_03_Custom_Dropdown_Vue () {
		driver.get("https://www.telerik.com/kendo-vue-ui/components/dropdowns/dropdownlist/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// search box

	}

	
	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	public int randomNumber(int size){
		Random rand = new Random();
		int upperbound = size;
		int int_random = rand.nextInt(upperbound); 
		return int_random;
	}
	public char randomChar(){
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		return c;
	}
	public void selectItem(WebElement parent, String child, String value, String search, String searchValue) throws InterruptedException{
		parent.click();
		Thread.sleep(5000);
		if (!search.equals(""))
		{
			String c = searchValue.substring(0, 1); 
			driver.findElement(By.xpath("//div[@class='dropdown example']//input[@class='search']")).sendKeys(c);
		}

		List<WebElement> childs = driver.findElements(By.xpath(child));
		if (childs.size() > 0){
			for(WebElement e: childs){
				System.out.println(e.getText());
				if (e.getText().equals(value)){
					if (childs.size() > 10)
					{
						System.out.println("Use JavaScriptExecutore");
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
					}
					else
						e.click();
					break;
				}
			}
		}
		
	}
	public void assertValue(WebElement parent, String value){
		String actual =  parent.findElement(By.xpath(".//span")).getText();
		Assert.assertTrue(actual.equals(value));
	}
	
	public void selectMultiItems(WebElement parent, String child, String[] value, String search, String[] searchValue) throws InterruptedException{
		parent.click();
		Thread.sleep(5000);
		if (!search.equals(""))
		{
			// String c = searchValue.substring(0, 1); 
			// driver.findElement(By.xpath("//div[@class='dropdown example']//input[@class='search']")).sendKeys(c);
		}

		List<WebElement> childs = driver.findElements(By.xpath(child));
		if (childs.size() > 0){
			for(WebElement e: childs){
				System.out.println(e.getText());
				if (Arrays.asList(value).contains(e.getText())){
					if (childs.size() > 10)
					{
						System.out.println("Use JavaScriptExecutore");
						((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
					}
					else
						e.click();
				}
			}
		}
		
	}
	public void assertMultiValue(WebElement parent, String[] value){
		List<WebElement> texts= parent.findElements(By.xpath(".//a"));
		for(WebElement e: texts){
			Assert.assertTrue(Arrays.asList(value).contains(e.getText()));
		}
	}
	
}
