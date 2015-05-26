package selenium.Report.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ReportHTML {
	
	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	Date date = new Date(0);
	
	public boolean RptHtmlRender() {
		try {
			//OutputStream htmlfile= new FileOutputStream(new File("/Users/ashv/Automation/workspace/HybridFramework/" + date));
			OutputStream htmlfile= new FileOutputStream(new File("F:\\Automation\\Selenuim\\workspace\\HybridFramework\\Report" + date));
			
			//Generate HTML report in table format
			
			

			htmlfile.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return true;
	}
	
	
}
