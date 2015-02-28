package selenium.ObjectRepository.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AjaxRequest {
	
	public void AjaxCall (String locator, WebDriver getDriver) {
		getDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement click = getDriver.findElement(By.xpath(locator));
	}
}
