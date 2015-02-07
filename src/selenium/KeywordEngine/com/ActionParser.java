package selenium.KeywordEngine.com;

//Java libs
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
//Selenium libs
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


//Class libs
import selenium.RunSuite.com.RunSuite;


public class ActionParser {
	
	//Global Variables
	WebElement Locator=null;
	static Logger log = Logger.getLogger(RunSuite.class.getName());
	String classPath = "selenium.KeywordEngine.com.ActionParser";
	static WebDriver driver;
	boolean classFlag = false;
	
	public void FormatSteps(ArrayList StepsArray, WebDriver drivertoUse){
		
		//Debug: System.out.println("Class name " + classPath);
		//Debug:
		System.out.println("Driver Using " + driver);
		
		driver=drivertoUse;
		for(int i=0;i<StepsArray.size();i++) {

			classFlag=false;
			//Get Action to call corresponding method name
			String ActionMethodName = (String) StepsArray.get(i);
			//Debug: 
			System.out.println("Action " + StepsArray.get(i));
						
			if(StepsArray.get(i).equals("EndTest") || StepsArray.get(i).equals("NoValue")) {
				//There is no use of invoke method for no values and EntTest
			} else { 
			
			//To read the parameters from class method
			String parameter;
			
			//Implement Java Reflection here
			//Steps: 
			//-Get class name.
			//-Get Object of this class.
			//-Get method of the class.
			//-Invoke method.
			
			
			try {
				Class cls = Class.forName(classPath);
				Object clsObj = cls.newInstance();
				Method method= cls.getMethod(ActionMethodName,String.class, String.class);
				//Debug: System.out.println("Action " + StepsArray.get(i) + "and class " + cls);
				
				//Find Parameter value from input file
				String parameter1 = (String)(StepsArray.get(i+1));
				String parameter2 = (String)(StepsArray.get(i+2)); 
				if(parameter1.equals("NoValue") && parameter2.equals("NoValue")) {
					parameter = null;
				} else {
					if(parameter1 != "NoValue") {
						parameter = parameter1;
					} else {
						parameter = parameter2;
						classFlag=true;
					}
				}
				
				//Find Data value from input file
				String 	dataValue =  	 (String)(StepsArray.get(i+3));
			/*	if(dataValue.equals("NoValue")) {
					dataValue= "NoValue";
				} */
				
				
				//call method based on dynamic value
				method.invoke(clsObj,parameter,dataValue);
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
	
	public void VisitURL(String temp, String data) {
		driver.get(data);
		if(!driver.getTitle().startsWith("Online Shopping:")) {
			throw new NotFoundException("Page Not Found");
		} 
	}
	
	public void LeftClick(String locator, String temp1) {
		WebElement mouse = driver.findElement(By.id(locator));
		mouse.click();
	}
	
	public void Type(String locator, String content) {
		WebElement type = driver.findElement(By.id(locator));
		type.sendKeys(content);
	}
	
	public void Submit(String locator, String temp) {
		WebElement Submit;
		if(!classFlag) {
		Submit = driver.findElement(By.id(locator));
		} else {
		Submit = driver.findElement(By.className(locator));
		}
		Submit.click();	
	}
	
	public void FormSubmit(String locator, String temp) {
		WebElement Form = driver.findElement(By.className(locator));
		Form.submit();
	}
	
	public void RightClick(String temp1, String temp2) {
		
	}
	
	public void MouseHover(String temp1, String temp2) {
		
	}
	
	public void screenshot(String temp1, String temp2) {
		
	}
	public void WAIT(String temp1, final String until) {
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	public void VerifyLabel(String loactor, String label) {
		WebElement element = driver.findElement(By.id(loactor));
		String labelText = element.getText();
		System.out.println("Complete Text" + labelText);
		if(labelText.contains(label)) {
			System.out.println("Text Found");
		}
	}
	
	} // End Class
