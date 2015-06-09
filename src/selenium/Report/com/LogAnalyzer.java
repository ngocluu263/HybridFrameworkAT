package selenium.Report.com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import selenium.Report.com.*;

public class LogAnalyzer {
	
	//Global variables
	ArrayList RowArr = new ArrayList();
	
	//Report Object
	ReportHTML rptObj= new ReportHTML();
	
	
	//Method to read log file and parse data for pass and fail test cases
	public void ReadFile() throws FileNotFoundException {
		//String file="/Users/ashv/Automation/workspace/HybridFramework/Logs/logs.log";
		String file="F:\\Automation\\Selenuim\\workspace\\HybridFramework\\Logs\\logs.log";
		BufferedReader br;
	
	//intialize array list
	//RowArr.add(new ArrayList());
	
	try {
		br = new BufferedReader(new FileReader(file));
		int rownum=0;
		
		for(String line; (line = br.readLine()) != null;) {
		    // process the line.
			String  RowData  = line;
			
			if(RowData.contains("ActionParser")) {
				RowArr.add(new ArrayList());		
				//Debug: System.out.println(RowData);
				//((ArrayList)RowArr.get(rownum)).add(RowData);
				String[] cbuf = RowData.split(" ");
				
				for(int li=0; li<cbuf.length ; li++) {
					//System.out.println(cbuf[li]);
					((ArrayList)RowArr.get(rownum)).add(cbuf[li]);
				}
				
				//((ArrayList)RowArr.get(rownum)).add(RowData);
				//RowArr.set(rownum, RowData);
				//RowArr.add(rownum, RowData);
				rownum++;
			} 
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	//Debug:  System.out.print((String)((ArrayList)RowArr.get(0)).get(0));  
/*	for(int r=0; r<RowArr.size(); r++){
		System.out.println(RowArr.get(r));  
		
	} */
	
	 /* Debug: 
	for(int r=0; r<RowArr.size(); r++){
		String newLine = System.getProperty("line.separator");
		System.out.println(newLine);
		for(int c=0; c <((ArrayList)RowArr.get(r)).size(); c++) {
			System.out.print("Row / Col no. " +  r + "/" + c + " " + ((String)((ArrayList)RowArr.get(r)).get(c) + "  "));  
		}
	} */ 
	
	//Generate HTML based on data
	Reportdata();
}
	public void Reportdata() {
		for(int r=0; r<RowArr.size(); r++){
			System.out.println(RowArr.get(r));  
		} 
		
		Boolean reportGen=false; 
		reportGen=rptObj.RptHtmlRender(RowArr);
		if(reportGen) {
			System.out.println("Report Generated Successfully");
		} else {
			System.out.println("Report Generatation Failed");
		}
	}
	
	
}
