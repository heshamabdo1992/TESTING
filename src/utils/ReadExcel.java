package utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	private static Sheet ExcelWSheet;
	private static Workbook ExcelWBook;
	private static Cell Cell=null;
	private static Row Row=null;
	
	
//This method is to set the File path and to open the Excel file, Pass Excel Path 
//and Sheetname as Arguments to this method	
public ReadExcel(String filePath,String SheetName) throws Exception {
   try {
	 //Create an object of File class to open xlsx file
       File file =    new File(filePath);
       //Create an object of FileInputStream class to read excel file
	   // Open the Excel file
	   FileInputStream inputStream = new FileInputStream(file);
	   ExcelWBook= null;
	 //Find the file extension by splitting file name in substring  and getting only extension name
       String fileExtensionName = filePath.substring(filePath.lastIndexOf("."));
     //Check condition if the file is xlsx file
       if(fileExtensionName.equals(".xlsx")){
       //If it is xlsx file then create object of XSSFWorkbook class
    	   ExcelWBook = new XSSFWorkbook(inputStream);
       }
       //Check condition if the file is xls file
       else if(fileExtensionName.equals(".xls")){
           //If it is xls file then create object of XSSFWorkbook class
    	   ExcelWBook = new HSSFWorkbook(inputStream);
       }
	   // Access the required test data sheet
       //Read sheet inside the workbook by its name
	  
	   } 
   catch (Exception e)
   {
    System.err.println("Can't open Excel sheet");
	throw (e);
	}
}

//Print ALL in Excel
public void PrintExcel(String FilePath, String SheetName) throws Exception {
	try{
		   //setExcelFile(FilePath,SheetName);
		   ExcelWSheet = ExcelWBook.getSheet(SheetName);
		 //Find number of rows in excel file
	        int rowCount = ReadExcel.getNumRow();
	        //Create a loop over all the rows of excel file to read it
	        for (int i = 0; i < rowCount+1; i++) {
	            Row = ExcelWSheet.getRow(i);
	            if (Row != null) {
	            //Create a loop to print cell values in a row
	            for (int j = 0; j < Row.getLastCellNum(); j++) {
	            //Print Excel data in console
	           	 DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
	           	// Cell cell = row.getCell(j).getStringCellValue();
	           //Returns the formatted value of a cell as a String regardless of the cell type.
	          	 String cell_info=formatter.formatCellValue(Row.getCell(j));
	          	if (Row.getCell(j) != null) {
	             System.out.print(cell_info+"|| ");
	             }else{
	        	   System.out.print("Empty Cell"+"|| ");
	        	   }}}
	            else 
	            {
		        	   System.out.print("Empty Row");
	            }
	            System.out.println();
	        } 
   
	}
	   catch (FileNotFoundException e)
	   {
		   System.err.println("Could not read the Excel sheet");
		   e.printStackTrace();
		   }
	   catch(IOException e)
	   {
		   System.err.println("Error-Could not read the Excel sheet");
		   e.printStackTrace();
		   }
}

//get all for specific columns
public static Object[][] getAllTable(String FilePath, String SheetName,int columns)    throws Exception{
	Object[][] tabArray = null;
	 try{
		 //  setExcelFile(FilePath,SheetName);	   
	   int startCol = 1;
	   int ci=0,cj=0;
	   int totalRows =getNumRow();
	   int totalCols = columns;
	   tabArray=new String[totalRows][totalCols];
	   for (int k =0;k<totalRows;k++) {
		   for (int j=startCol;j<totalCols;j++, cj++)
		   {
			   tabArray[ci][cj]=getCellData(k,j);
			   }
		   cj=0;ci++;
		   }}
	   catch (FileNotFoundException e)
	   {
		   System.err.println("Could not read the Excel sheet");
		   e.printStackTrace();
		   }
	   catch(IOException e)
	   {
		   System.err.println("Error-Could not read the Excel sheet");
		   e.printStackTrace();
		   }
	   return(tabArray);
}

//Get all sheet data for specific number of rows
public static Object[][] getTableArray(String FilePath, String SheetName, 
		ArrayList<Integer> iTestCaseRow)    throws Exception{   
	Object[][] tabArray = null;
   try{
	   //setExcelFile(FilePath,SheetName);
   //FileInputStream ExcelFile = new FileInputStream(FilePath);
   // Access the required test data sheet
   //ExcelWBook = new XSSFWorkbook(ExcelFile);
   //ExcelWSheet = ExcelWBook.getSheet(SheetName);   
   int startCol = 1;
   int ci=0,cj=0;
   int totalRows = iTestCaseRow.size();
   int totalCols = 3;
   tabArray=new String[totalRows][totalCols];
   for (int k =0;k<iTestCaseRow.size();k++) {
	   for (int j=startCol;j<totalCols;j++, cj++)
	   {
		   tabArray[ci][cj]=getCellData(iTestCaseRow.get(k),j);
		   // System.out.println(tabArray[ci][cj]);
		   }
	   tabArray[ci][cj]=iTestCaseRow.get(k).toString();
	   //  System.out.println(tabArray[ci][cj]);
	   cj=0;ci++;
	   }}
   catch (FileNotFoundException e)
   {
	   System.err.println("Could not read the Excel sheet");
	   e.printStackTrace();
	   }
   catch(IOException e)
   {
	   System.err.println("Error-Could not read the Excel sheet");
	   e.printStackTrace();
	   }
   return(tabArray);
   }

//This method is to read the test data from the Excel cell, 
//in this we are passing parameters as Row num and Col num
public static String getCellData(int RowNum, int ColNum) throws Exception{

   try{
  Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
  DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
//Returns the formatted value of a cell as a String regardless of the cell type.
  String CellData=formatter.formatCellValue(Cell);
  //String CellData = Cell.getStringCellValue();
  return CellData;
  }
   catch (Exception e){
		System.err.println(e.getMessage());
		return"";
		}}

//
public static String getTestCaseName(String sTestCase)throws Exception{

	String value = sTestCase;
	try{
		int posi = value.indexOf("@");
		value = value.substring(0, posi);
		posi = value.lastIndexOf("."); 
		value = value.substring(posi + 1);
		return value;
		}
	catch (Exception e){
	System.err.println(e.getMessage());
	throw (e);
	}}

//get Number of rows that contains sTestCaseName
public static ArrayList<Integer> getRowContains(String sTestCaseName, int colNum) 
		throws Exception{
	int i;
	//int []cos= new int[];
	try {
		int rowCount = ReadExcel.getRowUsed();
		ArrayList<Integer> myList = new ArrayList<Integer>();
		//int[] cos2 = null;
		for ( i=0 ; i<=rowCount; i++){
			if  (ReadExcel.getCellData(i,colNum).equalsIgnoreCase(sTestCaseName)){
				myList.add(i);
				}}
		//System.out.println(myList.size());
		return myList;}
	catch (Exception e){
	System.err.println(e.getMessage());
	throw(e);
	}}

//get last row number 
public static int getRowUsed() throws Exception {

	try{
		int RowCount = ExcelWSheet.getLastRowNum();
		return RowCount;}
	catch (Exception e){
		System.err.println(e.getMessage());
		throw (e);
		}}
	
	//get number row number 
	public static int getNumRow() throws Exception {

		try{
			int RowCount = ExcelWSheet.getLastRowNum()-ExcelWSheet.getFirstRowNum();
			return RowCount;}
		catch (Exception e){
			System.err.println(e.getMessage());
			throw (e);
			}}

}