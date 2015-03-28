package selenium.ObjectRepository.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AjaxRequest {
	
	public String AjaxCallRead (String locator, WebDriver getDriver) {
		getDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement AjaxElement = (new WebDriverWait(getDriver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.className(locator)));
		String actualTxt = AjaxElement.getText();
		return actualTxt;
	}
}
