package license_manager;

import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

//import javax.swing.JFileChooser;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import com.google.common.collect.Table.Cell;

public class ReadGuru99ExcelFile {

    

    public void readExcel(String filePath,String fileName,String sheetName) throws IOException{

    //Create an object of File class to open xlsx file

    //File file =    new File(filePath+"\\"+fileName);
    File file =    new File(filePath+"\\"+fileName);

    //Create an object of FileInputStream class to read excel file

    FileInputStream inputStream = new FileInputStream(file);

    Workbook guru99Workbook = null;

    //Find the file extension by splitting file name in substring  and getting only extension name

    String fileExtensionName = fileName.substring(fileName.indexOf("."));

    //Check condition if the file is xlsx file

    if(fileExtensionName.equals(".xlsx")){

    //If it is xlsx file then create object of XSSFWorkbook class

    guru99Workbook = new XSSFWorkbook(inputStream);

    }

    //Check condition if the file is xls file

    else if(fileExtensionName.equals(".xls")){

        //If it is xls file then create object of XSSFWorkbook class

        guru99Workbook = new HSSFWorkbook(inputStream);

    }

    //Read sheet inside the workbook by its name

    Sheet guru99Sheet = guru99Workbook.getSheet(sheetName);

    //Find number of rows in excel file

    int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();

    //Create a loop over all the rows of excel file to read it

    for (int i = 0; i < rowCount+1; i++) {

        Row row = guru99Sheet.getRow(i);

        //Create a loop to print cell values in a row

        for (int j = 0; j < row.getLastCellNum(); j++) {

            //Print Excel data in console
        	 DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
        	// Cell cell = row.getCell(j).getStringCellValue();
        //Returns the formatted value of a cell as a String regardless of the cell type.
       	 String cell_info=formatter.formatCellValue(row.getCell(j));
            System.out.print(cell_info+"|| ");

        }

        System.out.println();

    }

    

    }

    

    //Main function is calling readExcel function to read data from excel file

    public static void main(String...strings) throws IOException{

    	
    	
/*//    	JFileChooser fc = new JFileChooser();
//    	File yourFolder = null;
//    	fc.setCurrentDirectory(new File(System.getProperty("user.home"))); // start at application current directory
//    	fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//    	fc.setDialogTitle("choosertitle");
//    	fc.setAcceptAllFileFilterUsed(false);
//    	int returnVal = fc.showOpenDialog(fc);
//    	if(returnVal == JFileChooser.APPROVE_OPTION) {
//    	    yourFolder = fc.getSelectedFile();
//    	}
//    	
*/    	
    //Create an object of ReadGuru99ExcelFile class

    ReadGuru99ExcelFile objExcelFile = new ReadGuru99ExcelFile();

    //Prepare the path of excel file

   // String filePath = System.getProperty("user.dir")+"\\src\\excelExportAndFileIO";
    String filePath="G:\\Automatic Testing";
   // String filePath=yourFolder.getAbsolutePath();
    //Call read file method of the class to read data

    objExcelFile.readExcel(filePath,"ExportExcel.xlsx","ExcelGuru99Demo");

    }

}