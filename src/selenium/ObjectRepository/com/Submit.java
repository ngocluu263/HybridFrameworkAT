package selenium.ObjectRepository.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Submit {

	public void SubmitPage(String locator, WebDriver getDriver, int classFlag) {
		getDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement Submit;
		if(classFlag == 0) {
			Submit = getDriver.findElement(By.id(locator));
		} else {
			if(classFlag == 1 ) {
				Submit = getDriver.findElement(By.className(locator));
			} else {
				Submit = getDriver.findElement(By.xpath(locator));
			}
		}
		Submit.click();	
	}
}
