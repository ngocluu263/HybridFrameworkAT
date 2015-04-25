package selenium.Report.com;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LogAnalyzer {
	
	//Global variables
	ArrayList RowArr = new ArrayList();
	
	public void ReadFile() throws FileNotFoundException {
	String file="F:\\Automation\\Selenuim\\workspace\\HybridFramework\\Logs\\logs.log";
	BufferedReader br;
	
	//intialize array list
	//RowArr.add(new ArrayList());
	
	try {
		br = new BufferedReader(new FileReader(file));
		int rownum=0;
		RowArr.add(new ArrayList());		
		
		for(String line; (line = br.readLine()) != null;) {
		    // process the line.
			String  RowData  = line;
			
			if(RowData.contains("ActionParser")) {
				RowArr.add(new ArrayList());		
				//Debug: System.out.println(RowData);
				//((ArrayList)RowArr.get(rownum)).add(RowData);
				((ArrayList)RowArr.get(rownum)).add(RowData);
				//RowArr.set(rownum, RowData);
				//RowArr.add(rownum, RowData);
				rownum++;
			} 
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	//Debug:
	//System.out.print( (String)((ArrayList)RowArr.get(0)).get(1));  
	for(int r=0; r<RowArr.size(); r++){
		System.out.println(RowArr.get(r));  
	} 
	
}
	
}
