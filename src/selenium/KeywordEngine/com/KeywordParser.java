package selenium.KeywordEngine.com;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;

public class KeywordParser {
	
	public void FormatSteps(ArrayList StepsArray, WebDriver driver){
		
		System.out.println("Driver Using " + driver);
		for(int i=0;i<StepsArray.size();i++) {

			//Action:
			System.out.println("Action " + StepsArray.get(i));
			
			//Locator by ID:
			System.out.println("Locator by ID " + StepsArray.get(i+1));
			
			//Locator by xPath:
			System.out.println("Locator by xPath " + StepsArray.get(i+2));
			
			//Data value:
			System.out.println("Data Value " + StepsArray.get(i+3));
			
			i=i+3;
		}
		
		
	} // End Format Steps
}
