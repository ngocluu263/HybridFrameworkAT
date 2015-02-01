package selenium.KeywordEngine.com;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import selenium.RunSuite.com.RunSuite;


public class ActionParser {
	
	//Global Variables
	WebElement Locator=null;
	static Logger log = Logger.getLogger(RunSuite.class.getName());
	String className = "selenium.Kayword.Engine.ActionParser.java";
	
	public void FormatSteps(ArrayList StepsArray, WebDriver driver){
		
		System.out.println("Driver Using " + driver);
		for(int i=0;i<StepsArray.size();i++) {

			//Action:
			String Action = (String) StepsArray.get(i);
			//Debug: System.out.println("Action " + StepsArray.get(i));
			
		
			//Implement Java Reflection here
			//Call Reflection method should be defined here
			try{ 
			Class cls = Class.forName(className);
			
			
			}  catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				log.debug(e);
				// e.printStackTrace();
			}
			
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
	
	public void VisitURL(WebDriver driver) {
		driver.get("http://www.amazon.in");
		if(!driver.getTitle().startsWith("Online Shopping:")) {
			throw new NotFoundException("Page Not Found");
		}
	}
	
	public void LeftClick() {
		
	}
	
	public void Type() {
		
	}
	
	public void Submit() {
		
	}
	
	public void RightClick() {
		
	}
	
	public void MouseHover() {
		
	}
	
	public void screenshot() {
		
	}
	
}
