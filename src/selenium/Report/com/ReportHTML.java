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
import java.util.ArrayList;

public class ReportHTML {
	
	Date date = new Date(0);
	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
	File filename = new File("/Users/ashv/Automation/workspace/HybridFramework/Report/" + dateFormat.format(date) + ".html"); 
	//File filename = new File("F:\\Automation\\Selenuim\\workspace\\HybridFramework\\Report" + dateFormat.format(date) + ".html"); 
	
	boolean copyheader=false;
	boolean copybody=false;
	int size = 0;
	String row = "";
	String[] templatedata = new String[500];
	
	public boolean RptHtmlRender(ArrayList Output) {
		System.out.println(filename);
		try {
			//Generate HTML report in table format
			OutputStream htmlfile= new FileOutputStream(filename); //Create html file
			PrintStream printhtml = new PrintStream(htmlfile);				//Format output stream content
			//BufferedReader templateHTML = new BufferedReader(new FileReader("/Users/ashv/Automation/workspace/HybridFramework/Report/TempHTML.html"));
			BufferedReader templateHTML = new BufferedReader(new FileReader("F:\\Automation\\Selenuim\\workspace\\HybridFramework\\Report\\TempHTML.html"));
			
			while( (row=templateHTML.readLine()) != null) {
			    // count and copy content from template.
				templatedata[size] = row;
				size++;
			}
			
			//Placing header in html report based on configuration file
			for(int i=0; i<=size; i++) {
				if(templatedata[i].contains("Release")) {
					templatedata[i+1]=("<td>" + " 1.0 " +  "</td>");
				}
				if(templatedata[i].contains("Browser")) {
					templatedata[i+1]=("<td>" + " Chrome " +  "</td>");
				}
				if(templatedata[i].contains("Environment")) {
					templatedata[i+1]=("<td>" + " Windows " +  "</td>");
				}
				if(templatedata[i].contains("Total Test Cases")) {
					templatedata[i+1]=("<td>" + " 10 " +  "</td>");
				}
				if(templatedata[i].contains("Total Pass")) {
					templatedata[i+1]=("<td>" + " Windows " +  "</td>");
				}
				if(templatedata[i].contains("Total Fail")) {
					templatedata[i+1]=("<td>" + " 10 " +  "</td>");
				}
			}	
			
			//Final report file lines:
			int length= size+(7*Output.size());
			//Placing result in html table, based on log analyzer output
			for(int j=0; j<=length; j++) {
				if(templatedata[j].contains("Start Body")) {
					for(int row=0; row<=Output.size(); row++) {
						
						templatedata[j+1] = "<tr>";
						
						
						templatedata[j+7] = "</tr>";
						j=j+7;
					}
				}
			}
			
			
			
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
