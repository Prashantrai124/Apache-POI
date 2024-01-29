package ExcelDataProvider;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataprovider {
	
	 //multiple sets of data to our tests
	 // and store in the aarray
	DataFormatter formatter = new DataFormatter();
	@Test(dataProvider="driveTest")
	public void testCaseData(String userid,String password,String id)
	{
		System.out.println(userid +password +id );

	}

	@DataProvider(name="driveTest")
     public Object[][] getData() throws IOException
     {
	
		  //Use your file path Where Excel is file is Stored
		FileInputStream fil = new FileInputStream("/home/unicode/NewBook.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fil);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		System.out.println(rowCount);
		int columns = row.getLastCellNum();
		//System.out.println(columns);
		Object Data[][]  =new Object[rowCount-1][columns];
		//Now we can use two loops, outer is for row and inner is for the columns
		  for(int i=0;i<rowCount-1;i++)
		  {
			  //i+1 because we don't want the first row
			  row= sheet.getRow(i+1);
			  for(int j=0;j<columns;j++)
			  {
				  XSSFCell cell = row.getCell(j);
		
				Data[i][j]=formatter.formatCellValue(cell);
			  }
			  
		  }
		return Data;
		  

		   }
	     
	
     }
	
	

