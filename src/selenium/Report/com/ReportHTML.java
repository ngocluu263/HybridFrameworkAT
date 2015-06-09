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
import java.util.Calendar;
import java.util.TimeZone;

public class ReportHTML {
	

	boolean copyheader=false;
	boolean copybody=false;
	int size = 0;
	String row = "";
	String[] generateRptHtml = new String[500];
	
	public boolean RptHtmlRender(ArrayList Output) {

		Date date = new Date(0);
		DateFormat dateFormat = new SimpleDateFormat("hh_mm_ss__dd_mm_yyyy");
		Calendar cal = Calendar.getInstance();
		String Curdate=dateFormat.format(cal.getTime());
		//System.out.println("Time Zone " + dateFormat.format(cal.getTime()));
		
		//File filename = new File("/Users/ashv/Automation/workspace/HybridFramework/Report/" + dateFormat.format(date) + ".html"); 
		File filename = new File("F:\\Automation\\Selenuim\\workspace\\HybridFramework\\Report\\" + Curdate + ".html"); 
		
		
		//Debug: System.out.println(filename);

		try {
			//Generate HTML report in table format
			OutputStream htmlfile= new FileOutputStream(filename); //Create html file
			PrintStream printhtml = new PrintStream(htmlfile);				//Format output stream content
			//BufferedReader templateHTML = new BufferedReader(new FileReader("/Users/ashv/Automation/workspace/HybridFramework/Report/TempHTML.html"));
			BufferedReader templateHTML = new BufferedReader(new FileReader("F:\\Automation\\Selenuim\\workspace\\HybridFramework\\Report\\TempHTML.html"));
			
			while( (row=templateHTML.readLine()) != null) {
			    // count and copy content from template.
				generateRptHtml[size] = row;
				size++;
			}
			//debug:
			System.out.println("Size of template file" + size);
			
			//Placing header in html report based on configuration file
			for(int i=0; i<=size-1; i++) {
				if(generateRptHtml[i].contains("Release")) {
					//debug: System.out.println("value found in 1st row" + generateRptHtml[i]);
					generateRptHtml[i+1]=("<td>" + " 1.0 " +  "</td>");
				}
				if(generateRptHtml[i].contains("Browser")) {
					//debug: System.out.println("value found in 2nd row" + generateRptHtml[i]);
					generateRptHtml[i+1]=("<td>" + " Chrome " +  "</td>");
				}
				if(generateRptHtml[i].contains("Environment")) {
					generateRptHtml[i+1]=("<td>" + " Windows " +  "</td>");
				}
				if(generateRptHtml[i].contains("Total Test Cases")) {
					generateRptHtml[i+1]=("<td>" + " 10 " +  "</td>");
				}
				if(generateRptHtml[i].contains("Total Pass")) {
					generateRptHtml[i+1]=("<td>" + " 10 " +  "</td>");
				}
				if(generateRptHtml[i].contains("Total Fail")) {
					generateRptHtml[i+1]=("<td>" + " 0 " +  "</td>");
				}
			}	
			
			//Final report file lines:
			//length of new file should be (size (template file) + number of test cases in output * 7 (number of lines to be added for single test cases).			
			int length= size+(7*Output.size());
			
			//Debug:
			System.out.println(" Size of Test Logs results" + Output.size());
			//Debug:
			for(int r=0; r<Output.size(); r++){
				String newLine = System.getProperty("line.separator");
				System.out.println(newLine);
				for(int c=0; c <((ArrayList)Output.get(r)).size(); c++) {
					System.out.print("Row / Col no. " +  r + "/" + c + " " + ((String)((ArrayList)Output.get(r)).get(c) + "  "));  
				}
			} 
			
			//Placing result in html table, based on log analyzer output
			int count=0;
			for(int j=0; j<=length-1; j++) {
				if(generateRptHtml[j].contains("Start Body")) {
					for(int row=0; row<Output.size(); row++) {
						generateRptHtml[j+1] = "<tr>";
						generateRptHtml[j+2]=("<td>" + count +  "</td>");
						generateRptHtml[j+3]=("<td>" + ((String)((ArrayList)Output.get(row)).get(8)) +  "</td>");
						generateRptHtml[j+4]=("<td>" + ((String)((ArrayList)Output.get(row)).get(9)) +  "</td>");
						generateRptHtml[j+5]=("<td>" + ((String)((ArrayList)Output.get(row)).get(0)) + " " + ((String)((ArrayList)Output.get(row)).get(1)) +  "</td>");
						generateRptHtml[j+6]=("<td>" + "Comments" +  "</td>");
						generateRptHtml[j+7] = "</tr>";
						j=j+8;
						count++;
					}
				}
			} 
			
			
			//Copy content in printhtml format to file
			for(int i=0; i<=(size+7); i++) { 
				printhtml.println(generateRptHtml[i]);
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
