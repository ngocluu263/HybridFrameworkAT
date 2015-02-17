package selenium.ObjectRepository.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Label {

	public String CheckLabel(String loactor, String label, WebDriver getDriver) {
		getDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement element = getDriver.findElement(By.id(loactor));
		String labelText = element.getText();
		return labelText;
	}
	
}
