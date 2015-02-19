package selenium.ObjectRepository.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebFrame {
	
	public WebDriver FrameLocator(String locator, WebDriver getDriver) {
		getDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		//swapDriver = driver;
		WebElement frame = getDriver.findElement(By.id(locator));
		getDriver.switchTo().frame(frame);
		return getDriver;
	}
	
}
