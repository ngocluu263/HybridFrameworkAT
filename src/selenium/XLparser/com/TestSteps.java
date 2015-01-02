package selenium.XLparser.com;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class TestSteps {
	//Global Variables
			ArrayList TestSteps = new ArrayList();
			boolean found = false;
			int step_row;
			//ArrayList RequiredTestCases = new ArrayList();
			
		
		//Logging details in log.property file
			static Logger log = Logger.getLogger(TestSteps.class.getName());
			//static final String LOG_PROPERTIES_FILE = "Conf/log.properties";
			
			/**
			 * This function will be used to Read steps from Input XL file and return values in Array List.
			 * This function requires three parameters: only the test id number.
			 * @return 
			 */
			public ArrayList readSteps(String filePath, String sheet_name, String stepID) {
				log.info("Reading XL for Test Case Steps for selected ID...");		
				
				//Start Reading XL file for test cases
				try {
					FileInputStream FSRead = new FileInputStream(filePath);
					Workbook WB = new HSSFWorkbook(FSRead);
					Sheet sh = WB.getSheet(sheet_name);
					int rows = sh.getPhysicalNumberOfRows();
					System.out.println("No. of rows in Input XL file for Test Steps Sheet = " + rows);
					int cols = sh.getRow(0).getLastCellNum();
					System.out.println("No. of columns in input file for Test Steps Sheet = " + cols);
					
					//Iterate XL file to get the step ID
					for(int r=0; r<rows; r++) {		
						//Iterate Columns
						for(int c=0; c<cols; c++) {
							//Get Current Row
							Row current_row = sh.getRow(r);
							
							//To get date from excel file
							@SuppressWarnings("unused")
							Cell cell = current_row.getCell(c);
							SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
						
							//Debug: System.out.println("row and col value =" + i + " , " + j);
							Cell blank_cell = current_row.getCell(c, current_row.CREATE_NULL_AS_BLANK);
							//Debug: System.out.println("Blank cell = " + blank_cell);
							int type = current_row.getCell(c).getCellType();
							//Debug: System.out.println("Value of Type = " + type);
							
							if(c==0 && type==1) {
								String id = current_row.getCell(c).getStringCellValue();								
								if(stepID.equals(id)) {
									found = true;
									step_row = r+2;
									System.out.println("Here");						
								}
							}
							
						} if (found == true) {
							break;
						}
					}
					
					System.out.println("Broken");
					//Iterate Rows and Read complete input XL file
					for(int i=step_row; i<12; i++) {
						//TestStepsCounter
						int s=0;
						//Debug: System.out.println(step_row);
						TestSteps.add(new ArrayList());				
						//Iterate Columns
						for(int j=0; j<cols; j++) {
							
								//Get Current Row
								Row current_row = sh.getRow(i);
								
								//To get date from excel file
								Cell cell = current_row.getCell(j);
								SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
							
								
								//Debug: System.out.println("row and col value =" + i + " , " + j);
								Cell blank_cell = current_row.getCell(j, current_row.CREATE_NULL_AS_BLANK);
								//Debug: System.out.println("Blank cell = " + blank_cell);
								int type = current_row.getCell(j).getCellType();
								//Debug: System.out.println("Value of Type = " + type);
								
								if(type == 3){
									System.out.println("Empty Cell");
									((ArrayList)TestSteps.get(s)).add("");
								}
								if (type == 1) {
									String data = current_row.getCell(j).getStringCellValue();
									Debug: System.out.println("Cell Value" + " " + data + "\n");
									((ArrayList)TestSteps.get(s)).add(data);
								}
								if (type == 0 ) {
									if((DateUtil.isCellDateFormatted(cell))){
										String Dvalue = sdf.format(current_row.getCell(j).getDateCellValue());
										((ArrayList)TestSteps.get(s)).add(Dvalue);
									} else {
									double value = current_row.getCell(j).getNumericCellValue();
									//Debug: System.out.println("Cell Value" + " " + value + "\n");
									((ArrayList)TestSteps.get(s)).add(value);
									}
								}
								type=-1;					
						} s++;
					} FSRead.close();
					
					
				
				}catch (IOException e) {
					log.debug("Error in TestSteps.java while reading XL file", e);
				}
				log.info("Reading XL for Test Case Steps for selected ID complete...");	
				//Debug: System.out.println(TestCases);
					
				return TestSteps;
			}
}
