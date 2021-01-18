package samples4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadExcelFile {
		
		@Test
		public static void ReadExcelFile_run () throws IOException{ 
			System.out.println("@test:TestCase1");
			//Create an object of ReadGuru99ExcelFile class
			ReadExcelFile objExcelFile = new ReadExcelFile();
		    //Prepare the path of excel file
		    String filePath = "G:\\";
		    //Call read file method of the class to read data
		    objExcelFile.readExcel(filePath,"TESTDATA.xlsx","Read");
			
		}
		
		
	    public  void readExcel(String filePath,String fileName,String sheetName) throws IOException{
	        //Create an object of File class to open xlsx file
	        File file =    new File(filePath+"\\"+fileName);
	        //Create an object of FileInputStream class to read excel file
	        FileInputStream inputStream = new FileInputStream(file);
	        Workbook Workbook1 = null;
	        //Find the file extension by splitting file name in substring  and getting only extension name
	        String fileExtensionName = fileName.substring(fileName.indexOf("."));
	        //Check condition if the file is xlsx file
	        if(fileExtensionName.equals(".xlsx")){
	        //If it is xlsx file then create object of XSSFWorkbook class
	        	Workbook1 = new XSSFWorkbook(inputStream);
	        }
	        //Check condition if the file is xls file
	        else if(fileExtensionName.equals(".xls")){
	            //If it is xls file then create object of XSSFWorkbook class
	        	Workbook1 = new HSSFWorkbook(inputStream);
	        }

	        //Read sheet inside the workbook by its name
	        Sheet Sheet1 = Workbook1.getSheet(sheetName);
	        //Find number of rows in excel file
	        int rowCount = Sheet1.getLastRowNum()-Sheet1.getFirstRowNum();
	        //Create a loop over all the rows of excel file to read it
	        for (int i = 0; i < rowCount+1; i++) {
	            Row row = Sheet1.getRow(i);
	            if (row != null) {
	            //Create a loop to print cell values in a row
	            for (int j = 0; j < row.getLastCellNum(); j++) {
	            //Print Excel data in console
	           	 DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
	           	// Cell cell = row.getCell(j).getStringCellValue();
	           //Returns the formatted value of a cell as a String regardless of the cell type.
	          	 String cell_info=formatter.formatCellValue(row.getCell(j));
	          	if (row.getCell(j) != null) {
	             System.out.print(cell_info+"|| ");
	            }
	           else
	           {
	        	   System.out.print("Empty Cell"+"|| ");
	           }
	            }
	            }
	            else 
	            {
		        	   System.out.print("Empty Row");

	            }
	            
	            

	            System.out.println();
	        } 

	        } 
		
	/*	@AfterMethod									
		public static void TearDown(){
			System.out.println("@AfterMethod");
			driver.quit();
		}*/

}
