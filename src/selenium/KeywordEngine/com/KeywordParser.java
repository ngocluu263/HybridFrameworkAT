package selenium.KeywordEngine.com;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class KeywordParser {
	
	//Global Variables
	WebElement Locator=null;
	
	public void FormatSteps(ArrayList StepsArray, WebDriver driver){
		
		System.out.println("Driver Using " + driver);
		for(int i=0;i<StepsArray.size();i++) {

			//Action:
			System.out.println("Action " + StepsArray.get(i));
			
			//Implement Java Reflection here
			//Call Reflection method should be defined here
			if(StepsArray.get(i).equals("EndTest")) {
				
			} else { 
			}
			
			//Locator by ID:
			System.out.println("Locator by ID " + StepsArray.get(i+1));
			//Locator by xPath:
			System.out.println("Locator by xPath " + StepsArray.get(i+2));
			//Data value:
			System.out.println("Data Value " + StepsArray.get(i+3));
			i=i+3;
		}
	} // End Format Steps
	
	//Reflection Methods based on Action keywords
	//Actions are the input activities and input types. User can either type or click. Based on user input Action will executed.
	public void VisitURL() {
		
	}
	
	public void LeftClick() {
		
	}
	
	public void Type() {
		
	}
	
	public void Submit() {
		
	}
	
	
	
	
}
