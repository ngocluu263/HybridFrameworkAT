package selenium.ObjectRepository.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MouseClick  {
	
	public void LeftClick(String locator, WebDriver getDriver, int classFlag) {
		getDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement mouse;
		if(classFlag == 0) {
			mouse= getDriver.findElement(By.id(locator));
		} else { 
			if (classFlag == 1) {
				mouse= getDriver.findElement(By.id(locator));
			} else {
					mouse= getDriver.findElement(By.xpath(locator));	
			}
		}
		mouse.click();
		getDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
}
