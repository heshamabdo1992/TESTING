package samples4;

import java.io.IOException;
import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class WriteExcel {
	
	@Test
	public static void WriteExcelFile_run () throws IOException{ 
        //Create an array with the data in the same order in which you expect to be filled in excel file
	      // Instantiate a Date object
	      Date date = new Date();
		Object[][] datatypes = {
                {"Datatype", "Type", "Size(in bytes)"},
                {"int", "Primitive", 2},
                {"float", "Primitive", 4},
                {"double", "Primitive", 8},
                {"char", "Primitive", 1.99},
                {"String", "Non-Primitive", "No fixed size"},
                {"ttt","ttt","test",date},
                {date,0.999999999999999999,'c',"test"},
                {"testtttt"}
        };
        //Create an object of current class
        WriteExcel objExcelFile = new WriteExcel();
        String filepath="G:\\";
        //Write the file using file name, sheet name and the data to be filled
        objExcelFile.writeExcel(filepath,"TESTDATA.xlsx","Write",datatypes);	
	}
	
	
	public void writeExcel(String filePath,String fileName,
			String sheetName,Object[][] datatypes) throws IOException{
        //Create an object of File class to open xlsx file
        File file =    new File(filePath+"\\"+fileName);
        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook Workbook1 = null;
        //Find the file extension by splitting  file name in substring and getting only extension name
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
        
    //Read excel sheet by sheet name    
    Sheet sheet = Workbook1.getSheet(sheetName);
   
    //Get the current count of rows in excel file
    int rowNum = sheet.getLastRowNum();
    if (rowNum>0) {
    	rowNum = sheet.getLastRowNum()+1;   	
    }
    
    System.out.println(rowNum+"Creating excel");

    for (Object[] datatype : datatypes) {    	
    	Row row= sheet.createRow(rowNum++);
    		
        int colNum = 0;
        for (Object field : datatype) {
            Cell cell = row.createCell(colNum++);
            if (field instanceof String) {
                cell.setCellValue((String) field);
            } else if (field instanceof Integer) {
                cell.setCellValue((Integer) field);
            }
            else if (field instanceof Float) {
               cell.setCellValue( (Float) field);
            }
            else if (field instanceof Double) {
                cell.setCellValue((Double) field);
           }
            else if (field instanceof Character) {
                cell.setCellValue( field.toString());
            }
        else if (field instanceof Date) {
	           	 //DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
	          	//formatter.formatCellValue((Cell) field);
            	CellStyle cellStyle = Workbook1.createCellStyle();
            	CreationHelper createHelper = Workbook1.getCreationHelper();
            	cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dddd, MMMM d, yyyy"));
	           	 cell.setCellValue((Date) field);
	           	 cell.setCellStyle(cellStyle);
            }
        }
        
    }
    
    System.out.println("Creating excel is done");
    //Close input stream
    inputStream.close();
    //Create an object of FileOutputStream class to create write data in excel file
    FileOutputStream outputStream = new FileOutputStream(file);
    //write data in the excel file
    Workbook1.write(outputStream);
    //close output stream
    outputStream.close();
    }
	

}
