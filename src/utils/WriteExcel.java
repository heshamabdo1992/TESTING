package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcel {

	
    public FileInputStream inputStream = null;
    public FileOutputStream outputStream = null;
    public Workbook workbook = null;
    public Sheet sheet = null;
    public Row row = null;
    public Cell cell = null;
    String xlFilePath;
 
    public  WriteExcel(String xlFilePath) throws Exception
    {
    	this.xlFilePath = xlFilePath;
    	//Create an object of File class to open xlsx file
        File file =    new File(xlFilePath);
        //Create an object of FileInputStream class to read excel file
        inputStream = new FileInputStream(file);
        workbook = null;
        //Find the file extension by splitting  file name in substring and getting only extension name
        String fileExtensionName = xlFilePath.substring(xlFilePath.lastIndexOf("."));
       
        //Check condition if the file is xlsx file
        if(fileExtensionName.equals(".xlsx")){
        //If it is xlsx file then create object of XSSFWorkbook class
        	workbook = new XSSFWorkbook(inputStream);
        }
        
        //Check condition if the file is xls file
        else if(fileExtensionName.equals(".xls")){
            //If it is xls file then create object of XSSFWorkbook class
        	workbook = new HSSFWorkbook(inputStream);
        }    
    	
        inputStream.close();
    }

    public void WriteaAll(String sheetName,Object[][] datatypes) 
    		throws IOException{
    	try
        {
            sheet = workbook.getSheet(sheetName);
            //Get the current count of rows in excel file
            int rowNum = sheet.getLastRowNum();
            if (rowNum>0) {
            	rowNum = sheet.getLastRowNum()+1;   	
            }
           // row = sheet.getRow(rowNum);
        //    if(row==null)
          //      row = sheet.createRow(rowNum);
          // cell = row.getCell(row.getLastCellNum());
           
            for (Object[] datatype : datatypes) {    	
            	row= sheet.createRow(rowNum++);	
                int colNum = 0;
                for (Object field : datatype) {
                     cell = row.createCell(colNum++);
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
                    	CellStyle cellStyle = workbook.createCellStyle();
                    	CreationHelper createHelper = workbook.getCreationHelper();
                    	cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dddd, MMMM d, yyyy"));
        	           	cell.setCellValue((Date) field);
        	           	cell.setCellStyle(cellStyle);
                    }
                }
                
            }
            outputStream = new FileOutputStream(xlFilePath);
            workbook.write(outputStream);
            outputStream.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();}}
 	   
    public void setCellData(String sheetName, int colNumber, int rowNum, String value)
    {
        try
        {
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
     
           
            if(row==null)
                row = sheet.createRow(rowNum);
           cell = row.getCell(  row.getLastCellNum());

          //  cell = row.getCell(colNumber);
            if(cell == null)
                cell = row.createCell(colNumber);
 
            cell.setCellValue(value);
 
            outputStream = new FileOutputStream(xlFilePath);
            workbook.write(outputStream);
            outputStream.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void setLastColData(String sheetName, int rowNum, String value)
    {
        try
        {
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
     
           
            if(row==null)
                row = sheet.createRow(rowNum);
          // cell = row.getCell(row.getLastCellNum());

          //  cell = row.getCell(colNumber);
            if(cell == null)
                cell = row.createCell(row.getLastCellNum());
            cell.setCellValue(value);
            outputStream = new FileOutputStream(xlFilePath);
            workbook.write(outputStream);
            outputStream.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void setLastRowData(String sheetName, int Column, String value)
    {
        try
        {
            sheet = workbook.getSheet(sheetName);
            //Get the current count of rows in excel file
            int rowNum = sheet.getLastRowNum();
            if (rowNum>0) {
            	rowNum = sheet.getLastRowNum()+1;   	
            }
            row = sheet.getRow(rowNum);
            if(row==null)
                row = sheet.createRow(rowNum);
           //cell = row.getCell(Column);

          //  cell = row.getCell(colNumber);
            if(cell == null)
                cell = row.createCell(Column);
            cell.setCellValue(value);
            outputStream = new FileOutputStream(xlFilePath);
            workbook.write(outputStream);
            outputStream.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
   
    public void setLastRowLastColData(String sheetName,String value)
    {
        try
        {
            sheet = workbook.getSheet(sheetName);
            //Get the current count of rows in excel file
            int rowNum = sheet.getLastRowNum();
            if (rowNum>0) {
            	rowNum = sheet.getLastRowNum()+1;   	
            }
            row = sheet.getRow(rowNum);
            if(row==null)
                row = sheet.createRow(rowNum);
           //cell = row.getCell(Column);

          //  cell = row.getCell(colNumber);
            if(cell == null)
                cell = row.createCell(row.getLastCellNum());
            cell.setCellValue(value);
            outputStream = new FileOutputStream(xlFilePath);
            workbook.write(outputStream);
            outputStream.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


}