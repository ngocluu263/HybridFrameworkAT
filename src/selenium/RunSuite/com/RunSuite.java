package selenium.RunSuite.com;

//Java lib import
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

//Class Import 
import selenium.Conf.com.ConfigurationInterface;
import selenium.Conf.com.ReadTestEnvConfiguration;
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
		TestSteps RS = new TestSteps();
		ArrayList StepsDetails = new ArrayList();
		
		@Test
		public void TestExecution() {
			//Logging details
			PropertyConfigurator.configure("Configuration/log.properties");
			
			//Get Test Case to Read Steps from XL parser
			ArrayList StepIDs = new ArrayList();
			ReadXL getStepIDs= new ReadXL();
			StepIDs = getStepIDs.GetExecutableSteps();
			//Debug: 
			System.out.println(StepIDs);
			
			log.info("Got all executable Step IDs...");
			for(int i=1; i< StepIDs.size(); i++) {
				//Debug: System.out.println(StepIDs.get(i));
				StepsDetails=RS.readSteps(InputfilePath, SheetName, (String) StepIDs.get(i) );
				//Debug:
				System.out.println(StepsDetails);
			}			
		}

		
}
