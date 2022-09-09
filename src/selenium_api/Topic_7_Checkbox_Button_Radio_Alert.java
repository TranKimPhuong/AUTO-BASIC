/* Author: truonganhdung
 * Created Date: xx/xx/2018
 * Modified Date: xx/xx/2018
 * */

package selenium_api;

import java.util.concurrent.TimeUnit;

import org.eclipse.jdt.internal.compiler.ast.Argument;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_7_Checkbox_Button_Radio_Alert {
    WebDriver driver;

    //( ._.')----------------------------------------------------
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//( ._.')----------------------------------------------------
	@Test(enabled=true)
	public void TC_1() {
		
		// ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", multiple);
		// ((JavascriptExecutor) driver).executeScript("arguments[0].click();", multiple);
		
	}

	//( ._.')----------------------------------------------------
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void clickElementByJavaScript(WebElement e){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argument[0].click();", e);
	}
	public void scrollAndClickElementByJavaScript(WebElement e){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argument[0].scrollInToView(true);", e);
		js.executeScript("argument[0].click();", e);
	}

}
