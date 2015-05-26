package selenium.Report.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ReportHTML {
	
	Date date = new Date(0);
	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
	File filename = new File("/Users/ashv/Automation/workspace/HybridFramework/Report" + dateFormat.format(date) + ".html"); 
	//File filename = new File("F:\\Automation\\Selenuim\\workspace\\HybridFramework\\Report" + dateFormat.format(date) + ".html"); 
	
	public boolean RptHtmlRender() {
		System.out.println(filename);
		try {
			//Generate HTML report in table format
			OutputStream htmlfile= new FileOutputStream(filename); //Create html file
			PrintStream printhtml = new PrintStream(htmlfile);				//Format output stream content
			BufferedReader templateHTML = new BufferedReader(new FileReader("/Users/ashv/Automation/workspace/HybridFramework/Report/TempHTML.html"));
			//BufferedReader templateHTML = new BufferedReader(new FileReader("F:\\Automation\\Selenuim\\workspace\\HybridFramework\\Report\\TempHTML.html"));
			
			
			
			//Closing file and open streams
			printhtml.close();
			htmlfile.close();
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return true;
	}
	
	
}
