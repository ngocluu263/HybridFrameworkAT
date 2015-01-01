package selenium.XLparser.com;

//Java lib import 
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

//Class Import
import selenium.Conf.com.ConfigurationInterface;
import selenium.Conf.com.ReadTestEnvConfiguration;

public class ReadXL {
		
	/* Steps:
	 * Read Test Scenarios
	 * Read Test Cases
	 * Process Keyword from Object locator
	 * Create Execute table file inside OutputXL folder
	 */
	
	//Logging details in log.property file 
	static Logger log = Logger.getLogger(ReadXL.class.getName());
	static final String LOG_PROPERTIES_FILE = "Configuration/log.properties";

	public  ArrayList GetExecutableSteps() {
		//Logging details
		PropertyConfigurator.configure("Configuration/log.properties");
		
		// Step: Read Test Scenarios
		//Reading configuration file to get Test Scenarios from Input XL file
		log.info("Step: Read Test Scenarios from Input XL file");
		log.info("Call to Read configuration file...");
		ConfigurationInterface conf = new ReadTestEnvConfiguration();
		String InputfilePath = conf.getInputfilePath();
		String OutputfilePath = conf.getoutputfilePath();
		String TS_sheet_name=conf.getInputTSSheetName();
		String TC_sheet_name=conf.getInputTCSheetName();
		String getInputTCStepsSheetName=conf.getInputTCStepsSheetName();
		String release=conf.getRelease();
		log.info("Call to Read configuration file complete...");
		
		//Read XL file for Test Scenarios
		ArrayList ReadTestScenarios = new ArrayList();
		log.info("Call to Read XL file for scenarios...");
		TestScenarios TS_ReadExcel = new TestScenarios();
		ReadTestScenarios = TS_ReadExcel.readScenarios(InputfilePath, TS_sheet_name, release);
		log.info("Call to Read XL file for scenarios complete...");
		//Debug: System.out.println(ReadTestScenarios);
		
		//Read Excel File for test cases and filter required scenarios columns
		ArrayList ReadTestCases = new ArrayList();
		log.info("Call to Read XL file for Test cases...");
		TestCases TC_ReadExcel = new TestCases();
		ReadTestCases = TC_ReadExcel.readTestCases(InputfilePath, TC_sheet_name, ReadTestScenarios);
		log.info("Call Reading XL file for Test cases complete..."); 
		//Debug: System.out.println(ReadTestCases);
		
		return ReadTestCases;
	}
	
}
