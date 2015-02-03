package selenium.KeywordEngine.com;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
	String classPath = "selenium.KeywordEngine.com.ActionParser";
	//static WebDriver driver;
	
	public void FormatSteps(ArrayList StepsArray, WebDriver driver){
		
		//Debug: System.out.println("Class name " + classPath);
		//Debug:
		System.out.println("Driver Using " + driver);
		for(int i=0;i<StepsArray.size();i++) {

			//Get Action to call corresponding method name
			String ActionMethodName = (String) StepsArray.get(i);
			//Debug: 
			System.out.println("Action " + StepsArray.get(i));
			
			
			if(StepsArray.get(i).equals("EndTest") || StepsArray.get(i).equals("NoValue")) {
				//There is no use of invoke method for no values and EntTest
			} else { 
			
			//To read the parameters from class method
			Class[] parameterTypes = new Class[1];
			parameterTypes[0] = WebDriver.class;
			
			//Implement Java Reflection here
			//Steps: 
			//-Get class name.
			//-Get Object of this class.
			//-Get method of the class.
			//-Invoke method.
			
			//From class name, get class object
			try {
				Class cls = Class.forName(classPath);
				Object clsObj = cls.newInstance();
				Method method= cls.getMethod(ActionMethodName,parameterTypes);
				//Debug: System.out.println("Action " + StepsArray.get(i) + "and class " + cls);
				method.invoke(clsObj,driver);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					Throwable cause = e.getCause();
					System.out.format("Invocation of %s failed because of: %s%n", ActionMethodName, cause.getMessage());
					e.printStackTrace();
				} catch (ClassNotFoundException | InstantiationException |IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		
			
			//Locator by ID:
			System.out.println("Locator by ID " + StepsArray.get(i+1));
			//Locator by xPath:
			System.out.println("Locator by xPath " + StepsArray.get(i+2));
			//Data value:
			System.out.println("Data Value " + StepsArray.get(i+3));
			i=i+3;
		}
			
		} //End on EntStep
	} // End Format Steps

	//Reflection Methods based on Action keywords
	//Actions are the input activities and input types. User can either type or click. Based on user input Action will executed.
	
	public void VisitURL(WebDriver driver) {
		driver.get("http://www.amazon.in");
		if(!driver.getTitle().startsWith("Online Shopping:")) {
			throw new NotFoundException("Page Not Found");
		}
		//Debug: System.out.println("Sample " + driver);
	}
	
	public void LeftClick(WebDriver driver) {
		
	}
	
	public void Type(WebDriver driver) {
		
	}
	
	public void Submit(WebDriver driver) {
		
	}
	
	public void RightClick(WebDriver driver) {
		
	}
	
	public void MouseHover(WebDriver driver) {
		
	}
	
	public void screenshot(WebDriver driver) {
		
	}
	public void WAIT(WebDriver driver) {
		
	}
	
	public void VerifyLabel(WebDriver driver) {
		
	}
	
	} // End Class
