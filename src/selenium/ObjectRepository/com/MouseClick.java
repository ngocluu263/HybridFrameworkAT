package selenium.ObjectRepository.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MouseClick  {
	
	public void LeftClick(String locator, WebDriver getDriver, boolean classFlag) {
		getDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement mouse;
		if(classFlag) {
			mouse= getDriver.findElement(By.className(locator));
		} else {
			mouse= getDriver.findElement(By.id(locator));
		}
		mouse.click();
		getDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
}
