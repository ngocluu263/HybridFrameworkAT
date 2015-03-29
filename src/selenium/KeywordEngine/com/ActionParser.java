package selenium.KeywordEngine.com;

//Java libs
import org.junit.Assert;
import org.junit.Test;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
//Selenium libs
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;





//Class libs
import selenium.Conf.com.BrowserDriver;
import selenium.ObjectRepository.com.AjaxRequest;
import selenium.ObjectRepository.com.KeyType;
import selenium.ObjectRepository.com.Label;
import selenium.ObjectRepository.com.MouseClick;
import selenium.ObjectRepository.com.PageTitle;
import selenium.ObjectRepository.com.PageURL;
import selenium.ObjectRepository.com.ScreenShot;
import selenium.ObjectRepository.com.Submit;
import selenium.ObjectRepository.com.WebFrame;
import selenium.ObjectRepository.com.WebTable;
import selenium.ObjectRepository.com.MouseHoverMenu;
import selenium.RunSuite.com.RunSuite;

public class ActionParser {
	
	//Global Variables
	WebElement Locator=null;
	static Logger log = Logger.getLogger(RunSuite.class.getName());
	String classPath = "selenium.KeywordEngine.com.ActionParser";
	static WebDriver driver;
	static int classFlag;
	static String CurrentStepID;
	//Actions action = new Actions(driver);

	//Page object interface class object declaration 
	PageURL pagevisit = new PageURL();
	MouseClick click = new MouseClick();
	PageTitle title = new PageTitle();
	KeyType type = new KeyType();
	Submit SubmitBtn = new Submit();
	Label txtlabel = new Label();
	ScreenShot screenshot = new ScreenShot();
	WebTable webTb = new  WebTable();
	WebFrame FR = new WebFrame();
	AjaxRequest AJ = new AjaxRequest();
	MouseHoverMenu MH = new MouseHoverMenu();

	public void FormatSteps(ArrayList StepsArray, WebDriver drivertoUse, String StepID){
		
		//Debug: System.out.println("Class name " + classPath);
		//Driver to use		
		//BrowserDriver selectedDriver = new BrowserDriver();
		//WebDriver drivertoUse = selectedDriver.getDriver(getDriver);
		//Debug: System.out.println("Driver Before " + driver); 
		
		
		//Debug:
		System.out.println("Driver Using " + drivertoUse);
		//Global variable assign
		driver=drivertoUse;
		CurrentStepID=StepID;
		
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
				String parameter3 = (String)(StepsArray.get(i+3)); 
				if(parameter1.equals("NoValue") && parameter2.equals("NoValue") && parameter3.equals("NoValue") ) {
					parameter = null;
				} else {
					if(parameter1 != "NoValue") {
						parameter = parameter1;
						classFlag=0;
					} else {
						if( parameter2 != "NoValue") {
							parameter = parameter2;
							classFlag=1;
						}
						else {
							parameter = parameter3;
							classFlag=2;
						}
					}
				}
				
				//Find Data value from input file
				String 	dataValue =  	 (String)(StepsArray.get(i+4));
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
			i=i+4;
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
	public void PageTitle(String temp1, String ExpectedText) {
		String ActualTitle = title.visitURLTitle(driver);
		if(ActualTitle.contains(ExpectedText)) {
			System.out.println("PageTitlePass for " + ExpectedText);
		}
		else {
			screenshot.SS(driver, CurrentStepID);
		}
	}
	
	//Selenium WebDriver
	public void Type(String locator, String content) {
		System.out.println("contents are " + content + " and Locator " + locator);
		type.TypeKeys(locator, content, driver);
	}
	
	/*
	public void InputTxtArea (String locator, String content) {
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		WebElement type = driver.findElement(By.id(locator));
		type.clear();
		//type.sendKeys(content);
	} */
	
	//Selenium WebDriver
	public void Submit(String locator, String temp) {
		SubmitBtn.SubmitPage(locator, driver, classFlag);
	}
	
	public void RightClick(String temp1, String temp2) {
		
	}

	public void MouseHoverMenu(String locator, String content) {
		MH.MouseH(locator, driver, classFlag,content);
	}
	
	public void JSalertPopUp(String temp1, String OkCancel) {
		Alert alert = driver.switchTo().alert();
		if(OkCancel.equals("OK")) {
			alert.accept();
		} else {
			alert.dismiss();
		}
		driver.switchTo().defaultContent();
	}
	
	public void Insideframe(String locator, String xpath) {
			driver=FR.FrameLocator(locator, driver);
	}
	
	public void OutSideframe(String temp1, String temp2) {
		driver.switchTo().defaultContent();
	}

	public void WebTable(String locator, String content) {
		if(webTb.WebTableContent(locator, driver, content)) {
			System.out.println("Web Table  " + content + " Verified for steps ID "  + CurrentStepID);
		} else {
			screenshot.SS(driver, CurrentStepID);
		}
	}
	
	public void AjaxLabel(String locator, String content) {
		String Textfound = AJ.AjaxCallRead(locator, driver);
		System.out.println("Actual label found: " + Textfound);
		if(Textfound.contains(content)) {
			System.out.println("Label " + content + "Verified for steps ID "  + CurrentStepID);
		}
		else {
			screenshot.SS(driver, CurrentStepID);
		}
	}
	
	public void ScreenShot(String temp1, String FileName) {
		screenshot.SS(driver, FileName);
	}
	
	public void CloseBrowser(String temp1, String temp2) {
		driver.close();
	}
	
	//Check on assert again to shoe error on JUnit console as well
	public void VerifyLabel(String loactor, String Expectedlabel) {
		String ActualText = txtlabel.CheckLabel(loactor, Expectedlabel, driver);
		System.out.println("Actual label found: " + ActualText);
		if(ActualText.contains(Expectedlabel)) {
			System.out.println("Label " + Expectedlabel + "Verified for steps ID "  + CurrentStepID);
		}
		else {
			screenshot.SS(driver, CurrentStepID);
		}
	}
	
	
	} // End Class
