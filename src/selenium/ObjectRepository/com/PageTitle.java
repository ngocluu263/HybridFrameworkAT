package selenium.ObjectRepository.com;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;

public class PageTitle {
	
	public String visitURLTitle(WebDriver getDriver) {
		getDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return getDriver.getTitle();
	}
}
