package selenium.Conf.com;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class BrowserDriver {

	static Logger log = Logger.getLogger(BrowserDriver.class.getName());
	
	
	public WebDriver getDriver(String ConfDriver) {
		//Logging details
		PropertyConfigurator.configure("Configuration/log.properties");
		log.info("Pick Driver to use.");
		
		//String Driver = readDriver.DriverToUse();
		WebDriver driverOpted = null;
			if(ConfDriver.equals("chrome")) {
				//TODO need to place OS Validation as well
				//MAC
				System.setProperty("webdriver.chrome.driver", "/Users/ashv/Automation/workspace/HybridFramework/BrowserDrivers/ChromeMac/chromedriver");
				//WIN
				//System.setProperty("webdriver.chrome.driver", "F:\\Automation\\Selenuim\\AshDataDriven\\BrowserNativeDriver\\chromedriver");
				driverOpted = new ChromeDriver();
			}
			else if(ConfDriver.equals("firefox")) {
				driverOpted = new FirefoxDriver();
			}
			//TODO implement validation on Driver name
			return driverOpted;
	}
}
