package selenium.Conf.com;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class ReadTestEnvConfiguration implements ConfigurationInterface  {
	//Logging details in log.property file
	static Logger log = Logger.getLogger(ReadTestEnvConfiguration.class.getName());
	static final String LOG_PROPERTIES_FILE = "Configuration/log.properties";
	
	//TODO write about conf_file
	Properties conf_file = new Properties();
	
	//Constructor to default read properties from file whenever 'Config' object created
	public ReadTestEnvConfiguration() { 
	
	//Set logging file path in constructor 
	PropertyConfigurator.configure("Configuration/log.properties");
	log.info("Reading Configuration file...");

	//Reading configuration file in constructor
		try {
			FileInputStream read = new FileInputStream("Configuration/TestEnviornment.Configuration");
			conf_file.load(read);
		} catch (IOException e) {
			// TODO Log exception in log.properties file
			log.debug("Config file reading error", e);
		}
	}
	
	
	//Below METHOD to get configuration file details
	
	public String getRelease() {
		/**
		 * This method returns the release number defined in 'config' file
		 * @return String release_num
		 */
		String release_num = conf_file.getProperty("Release");
		log.info("Reading release number...");
		//TODO Need to implement validation 
		return release_num;
	}
	
	public String getInputfilePath() {
		/**
		 * This method return file path to read input .xls file.
		 * @return String inputfilePath;
		 */
		String InputfilePath = conf_file.getProperty("InputFilePath");
		log.info("Reading Input file path...");
		//TODO Need to implement validation 
		return InputfilePath;
	}
	
	public String getInputTSSheetName() {
		/**
		 * This method return XL sheet name to read.
		 * @return String sheet_name;
		 */
		log.info("Reading XL Sheet name for Test Scenarios...");
		String TS_sheet_name = conf_file.getProperty("TS_Sheet_Name");
		//TODO Need to implement validation 
		return TS_sheet_name;
	}
	
	public String getInputTCSheetName() {
		/**
		 * This method return XL sheet name to read.
		 * @return String sheet_name;
		 */
		log.info("Reading XL Sheet name for Test Cases...");
		String TC_sheet_name = conf_file.getProperty("TC_Sheet_Name");
		//TODO Need to implement validation 
		return TC_sheet_name;
	}
	
	public String getoutputfilePath() {
		/**
		 * This method return file path to write out .xls file
		 * @return String OutputfilePath;
		 */
		String OutputfilePath = conf_file.getProperty("OutputFilePath");
		log.info("Reading Ouput file path...");
		//TODO Need to implement validation 
		return OutputfilePath;
	}
	
	public String DriverToUse() {
		String ConfDriver = conf_file.getProperty("Browser");
		//TODO Need to implement validation 
		return ConfDriver;
	}
	
	public String getInputTCStepsSheetName() {
		/**
		 * This method returns the sheet name which holds test case steps.
		 * @return String TCSteps;
		 */
		String  TCSteps = conf_file.getProperty("TC_Steps_Sheet_Name");
		//TODO Need to implement validation 
		return TCSteps;
	}
	
	
}
