package selenium.RunSuite.com;

//Java lib import
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;





//Class Import 
import selenium.Conf.com.ConfigurationInterface;
import selenium.Conf.com.BrowserDriver;
import selenium.Conf.com.ReadTestEnvConfiguration;
import selenium.KeywordEngine.com.ActionParser;
import selenium.Report.com.LogAnalyzer;
import selenium.XLparser.com.ReadXL;
import selenium.XLparser.com.TestSteps;

/**
 * 
 *  @version 2.0
 *  @author Ashvini Sharma
 *  
 *  This suite will do the following process. 
 *  -Read XL files to get the executable steps ID.
 *  
*/

public class RunSuite {
	
		//Logging details in log.property file 
		static Logger log = Logger.getLogger(RunSuite.class.getName());
		
		
		//Read configuration file for test case steps
		ConfigurationInterface conf = new ReadTestEnvConfiguration();
		String InputfilePath = conf.getInputfilePath();
		String SheetName = conf.getInputTCStepsSheetName();
		String getDriver = conf.DriverToUse();
		
		//Global Class Objects
		LogAnalyzer LA = new LogAnalyzer();	
		TestSteps RS = new TestSteps();
		ArrayList StepsDetails = new ArrayList();
		
		
		@Before
		//Clean log files before start
		public void CleanLogFiles() {
		    try{
		/*        BufferedWriter ReportLog = new BufferedWriter(new FileWriter("/Users/ashv/Automation/workspace/HybridFramework/Logs/logs.log"));
		        ReportLog.write("");
		        ReportLog.flush();
		        ReportLog.close(); */
		       BufferedWriter SuiteLog = new BufferedWriter(new FileWriter("F:\\Automation\\Selenuim\\workspace\\HybridFramework\\Logs\\logs.log"));
		        SuiteLog.write("");
		        SuiteLog.flush();
		        SuiteLog.close(); 
		    }catch(IOException ioe){
		        // You should really do something more appropriate here
		        ioe.printStackTrace();
		    }
		}
		
		@Test
		public void TestExecution() {
			//Logging details
			PropertyConfigurator.configure("Configuration/log.properties");
			
			//Debug: System.out.println("Class name " + classPath);
			//Driver to use		
			BrowserDriver selectedDriver = new BrowserDriver();
			WebDriver drivertoUse = selectedDriver.getDriver(getDriver);
			//Debug: System.out.println("Driver Before " + driver);
			
			
			//Get Test Case to Read Steps from XL parser
			ArrayList StepIDs = new ArrayList();
			ReadXL getStepIDs= new ReadXL();
			StepIDs = getStepIDs.GetExecutableSteps();
			ActionParser AP = new ActionParser();
			//Debug: 
			System.out.println(StepIDs);
			
			log.info("Got all executable Step IDs...");
			//for(int i=1; i< StepIDs.size(); i++) {
			//For testing custom runs
			for(int i=1; i<4; i++) {
				//Debug: System.out.println("Step IDs" + StepIDs.get(i));
				StepsDetails=RS.readSteps(InputfilePath, SheetName, (String) StepIDs.get(i) );
				//Debug:
				if(StepsDetails!=null) {
				//Debug: System.out.println("Step Details " + StepsDetails.get(0));
				String StepID = (String) StepIDs.get(i);
				System.out.println("Step IDs " + StepID);	
				
				log.info("Executing Test Step " + StepIDs.get(i));
				AP.FormatSteps((ArrayList) StepsDetails.get(0), drivertoUse, StepID);
				//KP.FormatSteps((ArrayList) StepsDetails.get(0), null);
				} else {
					log.warn("This Test Step ID is missing in test steps sheet " + StepIDs.get(i));
				}
			}			
		}
		
		
		//@AfterClass
		@After
		public void ReportGenerateTest() {
			System.out.println("Generating Report");
			try {
				LA.ReadFile();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
}
