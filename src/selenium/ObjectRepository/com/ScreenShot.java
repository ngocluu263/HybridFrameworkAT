package selenium.ObjectRepository.com;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class ScreenShot {
	
	//Need to place OS validation due to format
	public void SS(WebDriver getDriver, String fileName) {
		try {
			File screenshot = ((TakesScreenshot)getDriver).getScreenshotAs(OutputType.FILE);
			File targetFile = new File("F:\\Automation\\Selenuim\\workspace\\HybridFramework\\OutputXL\\ScreenShot\\" + fileName + ".jpg");
			FileUtils.copyFile(screenshot, targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failure to take screenshot "+e);
		}
	}
}
