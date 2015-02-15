package selenium.ObjectRepository.com;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MouseClick  {
	
	public void LeftClick(String locator, WebDriver getDriver, boolean classFlag, String CurrentStepID) {
		getDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		try{
		WebElement mouse;
		if(classFlag) {
			mouse= getDriver.findElement(By.className(locator));
		} else {
			mouse= getDriver.findElement(By.id(locator));
		}
		mouse.click();
		getDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} catch (Exception e) {
			File screenshot = ((TakesScreenshot)getDriver).getScreenshotAs(OutputType.FILE);
			File targetFile = new File("F:\\Automation\\Selenuim\\workspace\\HybridFramework\\OutputXL\\ScreenShot\\" + CurrentStepID + ".jpg");
			try {
				FileUtils.copyFile(screenshot, targetFile);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
