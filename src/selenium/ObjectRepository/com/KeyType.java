package selenium.ObjectRepository.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class KeyType {
	
	public void TypeKeys(String locator, String content, WebDriver getDriver) {
		getDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement type = getDriver.findElement(By.id(locator));
		type.sendKeys(content);
	}
}
