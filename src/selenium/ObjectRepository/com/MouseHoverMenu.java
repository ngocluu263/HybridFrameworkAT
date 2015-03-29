package selenium.ObjectRepository.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseHoverMenu {
	public Actions  MouseH (String locator, WebDriver getDriver, int classFlag, String Content) {
		getDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getDriver.manage().window().maximize();
		Actions action = new Actions(getDriver);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement item;
		if(classFlag == 0) {
			item= getDriver.findElement(By.id(locator));
		} else { 
			if (classFlag == 1) {
				item= getDriver.findElement(By.id(locator));
			} else {
				item= getDriver.findElement(By.xpath(locator));	
			}
		}
	
		WebElement submenu = getDriver.findElement(By.xpath(Content));
	/*	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); */
		action.moveToElement(item).perform();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		action.click(submenu).perform();
		return action;
	}
	
	
}
