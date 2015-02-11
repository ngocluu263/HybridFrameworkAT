package selenium.ObjectRepository.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Submit {

	public void SubmitPage(String locator, WebDriver getDriver, boolean classFlag) {
		getDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement Submit;
		if(classFlag) {
			Submit = getDriver.findElement(By.className(locator));
		} else {
			Submit = getDriver.findElement(By.id(locator));
		}
		Submit.click();	
	}
}
