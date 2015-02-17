package selenium.ObjectRepository.com;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebTable {
	
	public boolean WebTableContent(String locator, WebDriver getDriver, String content) {
		getDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean result = false;
		
		//To locate web table on web page
		WebElement mytable = getDriver.findElement(By.className(locator));
		//To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		//To calculate no of rows In table.
		int rows_count = rows_table.size();
		
		  //Loop will execute till the last row of table.
		  for (int row=0; row<rows_count; row++){
		   //To locate columns(cells) of that specific row.
		   List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
		   //To calculate no of columns(cells) In that specific row.
		   int columns_count = Columns_row.size();
		  //Debug: System.out.println("Number of cells In Row "+row+" are "+columns_count);
		   
		   //Loop will execute till the last cell of that specific row.
		   for (int column=0; column<columns_count; column++){
		    //To retrieve text from that specific cell.
			   String celtext = Columns_row.get(column).getText();
			   if(celtext.contains(content)) {
				   result = true;
			   }
			   // Debug: System.out.println("Cell Value Of row number "+row+" and column number "+column+" Is "+celtext);
		   }
		   //Debug formatting: System.out.println("--------------------------------------------------");
		  }  
		return result;
				  
	}
	
}
