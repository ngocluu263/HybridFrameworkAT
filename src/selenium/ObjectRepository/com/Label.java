package selenium.ObjectRepository.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Label {

	public String CheckLabel(String locator, String label, WebDriver getDriver, int classFlag) {
		getDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement element;
		if(classFlag == 0) {
			element= getDriver.findElement(By.id(locator));
		} else { 
			if (classFlag == 1) {
				element= getDriver.findElement(By.id(locator));
			} else {
				element= getDriver.findElement(By.xpath(locator));	
			}
		}
		String labelText = element.getText();
		return labelText;
	}
	
}
