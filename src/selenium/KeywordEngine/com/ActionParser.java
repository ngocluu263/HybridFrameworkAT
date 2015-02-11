package selenium.KeywordEngine.com;

//Java libs
import org.junit.Assert;
import org.junit.Test;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


//Selenium libs
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


//Class libs
import selenium.Conf.com.BrowserDriver;
import selenium.ObjectRepository.com.KeyType;
import selenium.ObjectRepository.com.Label;
import selenium.ObjectRepository.com.MouseClick;
import selenium.ObjectRepository.com.PageTitle;
import selenium.ObjectRepository.com.PageURL;
import selenium.ObjectRepository.com.Submit;
import selenium.RunSuite.com.RunSuite;

public class ActionParser {
	
	//Global Variables
	WebElement Locator=null;
	static Logger log = Logger.getLogger(RunSuite.class.getName());
	String classPath = "selenium.KeywordEngine.com.ActionParser";
	static WebDriver driver;
	static boolean classFlag;

	//Page object interface class object declaration 
	PageURL pagevisit = new PageURL();
	MouseClick click = new MouseClick();
	PageTitle title = new PageTitle();
	KeyType type = new KeyType();
	Submit SubmitBtn = new Submit();
	Label txtlabel = new Label();

	public void FormatSteps(ArrayList StepsArray, WebDriver drivertoUse){
		
		//Debug: System.out.println("Class name " + classPath);
		//Driver to use		
		//BrowserDriver selectedDriver = new BrowserDriver();
		//WebDriver drivertoUse = selectedDriver.getDriver(getDriver);
		//Debug: System.out.println("Driver Before " + driver); 
		
		
		//Debug:
		System.out.println("Driver Using " + drivertoUse);
		
		driver=drivertoUse;
		for(int i=0;i<StepsArray.size();i++) {
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
						classFlag=false;
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
			//Debug: System.out.println("Locator by ID " + StepsArray.get(i+1));
			//Locator by xPath:
			//Debug: System.out.println("Locator by ClassName " + StepsArray.get(i+2));
			//Data value:
			//Debug: System.out.println("Data Value " + StepsArray.get(i+3));
			
			//Jumping on next row in steps section 
			i=i+3;
		}
			
		} //End on EntStep
	} // End Format Steps

	
	//Reflection Methods based on Action keywords
	//Actions are the input activities and input types. User can either type or click. Based on user input Action will executed.
	
	//Selenium WebDriver
	public void VisitURL(String temp, String data) {
		pagevisit.visitURL(data, driver);		
	}
	
	//Selenium WebDriver
	public void LeftClick(String locator, String temp1) {
		click.LeftClick(locator, driver, classFlag);		
	}
	
	//Assert to verify
	public void PageTitle(String ExpectedText, String temp1) {
		String ActualText = title.visitURLTitle(driver);
	}
	
	//Selenium WebDriver
	public void Type(String locator, String content) {
		type.TypeKeys(locator, content, driver);
	}
	
	//Selenium WebDriver
	public void Submit(String locator, String temp) {
		SubmitBtn.SubmitPage(locator, driver, classFlag);
	}
	
	public void RightClick(String temp1, String temp2) {
		
	}
	
	public void MouseHover(String temp1, String temp2) {
		
	}
	
	public void screenshot(String temp1, String temp2) {
		
	}
	
	public void CloseBrowser(String temp1, String temp2) {
		driver.close();
	}
	

	public void VerifyLabel(String loactor, String label) {
		String result = txtlabel.CheckLabel(loactor, label, driver);
	}
	
	
	} // End Class
