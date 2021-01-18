package pms;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class insertfromexceltosql {
	 public static void main( String [] args ) {
	        String fileName="C:\\Users\\Hesham\\Desktop\\Multiframe CT (Done)"
	        		+ "\\protection.xlsx";
	        Vector dataHolder=read(fileName);
	        saveToDatabase(dataHolder);
	    }
	 
	  public static Vector read(String fileName)    {
		  ZipSecureFile.setMinInflateRatio(0);

	        Vector cellVectorHolder = new Vector();
	        try{
	            FileInputStream myInput = new FileInputStream(fileName);
	            //POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
	            XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
	            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
	            Iterator rowIter = mySheet.rowIterator();
	            while(rowIter.hasNext()){
	                XSSFRow myRow = (XSSFRow) rowIter.next();
	                Iterator cellIter = myRow.cellIterator();
	                //Vector cellStoreVector=new Vector();
	                List list = new ArrayList();
	                while(cellIter.hasNext()){
	                    XSSFCell myCell = (XSSFCell) cellIter.next();
	                    list.add(myCell);
	                }
	                cellVectorHolder.addElement(list);
	            }
	        }catch (Exception e){e.printStackTrace(); }
	        return cellVectorHolder;
	    }

	  private static void saveToDatabase(Vector dataHolder) {
		  String  Index ; 
		  String Date ; 
			String  Country ; 
			String  Customer ; 
			String  Version_Info ; 
			String  Ultima_Version ; 
			 String Options ; 
			 String Software_Serial ; 
			 String Lock_Code ; 
			String  Dongol_Type ; 
			String  Dongol_Serial ; 
			String  Dongole_Name ; 
			String  Product_ID ; 
			 String Dongl_UID ; 
			String  Dongle_Serial ; 
			String  Demo_Requester ; 
			String  SPF ; 
			String  Recorded ; 
			String  comment ; 
			String  ID ;
	        System.out.println(dataHolder);

	        for(Iterator iterator = dataHolder.iterator();iterator.hasNext();) {
	            List list = (List) iterator.next();
	            Index = list.get(0).toString();
	            Date = list.get(1).toString();
				Country = list.get(2).toString(); 
				Customer= list.get(3).toString() ; 
			  Version_Info = list.get(4).toString(); 
				Ultima_Version = list.get(5).toString(); 
				 Options = list.get(6).toString(); 
				 Software_Serial = list.get(7).toString(); 
				 Lock_Code = list.get(8).toString(); 
				Dongol_Type = list.get(9).toString(); 
				Dongol_Serial = list.get(10).toString(); 
				Dongole_Name = list.get(11).toString(); 
				Product_ID = list.get(12).toString(); 
				 Dongl_UID = list.get(13).toString(); 
				Dongle_Serial = list.get(14).toString(); 
				Demo_Requester = list.get(15).toString(); 
				 SPF = list.get(16).toString(); 
				Recorded = list.get(17).toString(); 
				 comment = list.get(18).toString(); 
				 ID = list.get(19).toString();

	            try {
	            	String userName = "sa";
	        		String password = "PXDBPass2015";
	        		String ServerName= "PXTEST-007";
	        		String DBName="protection";
	        		String TableName="Table1";

	        		// JDBC Driver class
	        		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

	        		// Creating a connection to the database. //like(Connection conn = DriverManager.getConnection("DatabaseURL","UserName", "Password");
	        		// String url ="jdbc:sqlserver://DESKTOP-1TGL11O;databaseName=NORTHWND"; //"jdbc:sqlserver://[servername];databaseName=MYDB";
	        		Connection con = DriverManager.getConnection("jdbc:sqlserver://"+
	        		ServerName+";databaseName="+DBName+"",userName, password);
	         
	                //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "welcome");
	                System.out.println("connection made...");
	                PreparedStatement stmt=con.prepareStatement("INSERT INTO "+TableName+
	                		"([Index]\r\n" + 
	                		"      ,[Date]\r\n" + 
	                		"      ,[Country]\r\n" + 
	                		"      ,[Customer]\r\n" + 
	                		"      ,[Version_Info]\r\n" + 
	                		"      ,[Ultima_Version]\r\n" + 
	                		"      ,[Options]\r\n" + 
	                		"      ,[Software_Serial]\r\n" + 
	                		"      ,[Lock_Code]\r\n" + 
	                		"      ,[Dongol_Type]\r\n" + 
	                		"      ,[Dongol_Serial]\r\n" + 
	                		"      ,[Dongole_Name]\r\n" + 
	                		"      ,[Product_ID]\r\n" + 
	                		"      ,[Dongl_UID]\r\n" + 
	                		"      ,[Dongle_Serial]\r\n" + 
	                		"      ,[Demo_Requester]\r\n" + 
	                		"      ,[SPF]\r\n" + 
	                		"      ,[Recorded]\r\n" + 
	                		"      ,[comment]\r\n" + 
	                		"      ,[ID]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	                stmt.setString(1, Index);
	                stmt.setString(2, Date);
	                stmt.setString(3, Country);
	                stmt.setString(4, Customer);	                
	                stmt.setString(5, Version_Info);
	                stmt.setString(6, Ultima_Version);
	                stmt.setString(7, Options);
	                stmt.setString(8, Software_Serial);	                
	                stmt.setString(9, Lock_Code);
	                stmt.setString(10, Dongol_Type);
	                stmt.setString(11, Dongol_Serial);
	                stmt.setString(12, Dongole_Name);	                
	                stmt.setString(13, Product_ID);
	                stmt.setString(14, Dongl_UID);
	                stmt.setString(15, Dongle_Serial);
	                stmt.setString(16, Demo_Requester);
	                stmt.setString(17, SPF);	                
	                stmt.setString(18, Recorded);
	                stmt.setString(19, comment);
	                stmt.setString(20, ID);
	                stmt.executeUpdate();

	                System.out.println("Data is inserted");
	                stmt.close();
	                con.close();
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }



	        }
	  
}
