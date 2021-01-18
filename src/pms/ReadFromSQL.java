package pms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;

public class ReadFromSQL {
    public Connection conn;
    public Statement stment ;
	
   public ReadFromSQL() 
   {

   }
   
   public void createconnection() throws ClassNotFoundException, SQLException
   {
	   String userName = "sa";
		String password = "PXDBPass2015";
		String ServerName= "localhost\\sqlexpress";
		String DBName="protection";
		//String TableName="CalcTable";

		// JDBC Driver class
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		// Creating a connection to the database. //like(Connection conn = DriverManager.getConnection("DatabaseURL","UserName", "Password");
		// String url ="jdbc:sqlserver://DESKTOP-1TGL11O;databaseName=NORTHWND"; //"jdbc:sqlserver://[servername];databaseName=MYDB";
		conn = DriverManager.getConnection("jdbc:sqlserver://"+ServerName+";databaseName="+DBName+"",userName, password);
		if (conn != null) {
			System.out.println("Connected");
			}
		else
		{
			System.out.println("Failed Connection");
		}
   }
   
//to get all customers
   public ResultSet search_customer() throws SQLException, ClassNotFoundException
   {
	   createconnection();
	       stment= conn.createStatement();  
	       ResultSet rs = null;
   	try
	    {
   	 String qry = "SELECT DISTINCT [Customer],[Country] FROM [protection].[dbo].[Sheet1] "
   	 		+ "Where [index] >=100 and [index] <=200 and [Customer] Is Not NULL ";

   	 rs= stment.executeQuery(qry);
	     System.out.println(rs.getMetaData().getColumnCount());
	     stment.close();
		 conn.close();
	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
   	return rs;
   }

   // to get all new dongles
   public ResultSet search_dongle() throws SQLException, ClassNotFoundException
   {
	   createconnection();
	       stment= conn.createStatement();  
	       ResultSet rs = null;
	       
   	try
	    {
   	 String qry = "SELECT DISTINCT [Dongle_UID], [Dongle_SerialName], [Dongol_Type], [Country]" + 
   	 		" FROM [protection].[dbo].[Sheet1] " + 
   	 		" WHERE [Recorded] Is Null AND  [Dongle_UID] Is Not Null "
   	 		+ "AND [Dongle_SerialName] Is Not Null AND [Dongol_Type] "
   	 		+ "Is Not Null AND [Dongol_Type] <> '--' ";

   	 rs= stment.executeQuery(qry);
     System.out.println(rs.getMetaData().getColumnCount());
     stment.close();
	 conn.close();

	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
   	return rs;
   }
   
   // to get data by SPF AND Software_Serial

   public ResultSet search_by(String SPF,String Software_Serial) throws SQLException, ClassNotFoundException
   {
	   createconnection();
	       stment= conn.createStatement();  
	       ResultSet rs = null;
	       
   	try
	    {
   	 String qry = "SELECT *" + 
   	 		" FROM [protection].[dbo].[Sheet1] " + 
   	 		" WHERE [SPF] ='"+SPF+"' and [Software_Serial] = '"+Software_Serial+"' " ;

   	 rs= stment.executeQuery(qry);
     System.out.println(rs.getMetaData().getColumnCount());
     stment.close();
	 conn.close();
	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
   	return rs;
   }

// to get data by Dongle_UID

   public ResultSet search_by_dongle_uid(String DongleUID) throws SQLException, ClassNotFoundException
   {
	   createconnection();
	       stment= conn.createStatement();  
	       ResultSet rs = null;
	       
   	try
	    {
   /*	 String qry = "SELECT *" + 
   	 		" FROM [protection].[dbo].[Sheet1] " + 
   	 		" WHERE Dongle_UID ='"+DongleUID+"' "
   	 						+ " and [index] =(select top 1 [index] from [protection].[dbo].[Sheet1] " + 
   	 						" where [Dongle_UID]='"+DongleUID+"' order by [date] ,[index])" ;
*/
   	 
   	String qry2 = "DECLARE @val nvarchar(255);" + 
   			"DECLARE @val2 float;" + 
   			"Select top 1 @val = [SPF] from [protection].[dbo].[Sheet1] where [Dongle_UID]='"+DongleUID+"' order by [date] ,[index] ; " + 
   			"Select top 1 @val2 = [Index] from [protection].[dbo].[Sheet1]  where [Dongle_UID]='"+DongleUID+"' order by [date] ,[index] ; " + 
   			"IF @val  LIKE '%[0-9]%' " + 
   			"  BEGIN" + 
   			"  SELECT *" + 
   			"   	 		 FROM [protection].[dbo].[Sheet1]  " + 
   			"   	 		WHERE Dongle_UID ='"+DongleUID+"' " + 
   			"   	 						 and [SPF] = @val; " + 
   			"							 END; " + 
   			"ELSE " + 
   			" BEGIN " + 
   			"  SELECT *" + 
   			"   	 		 FROM [protection].[dbo].[Sheet1] " + 
   			"   	 		WHERE Dongle_UID ='"+DongleUID+"' " + 
   			"   	 						 and [index] =@val2; " + 
   			" END; " ;
   	
   	 rs= stment.executeQuery(qry2);
    // System.out.println(rs.getMetaData().getColumnCount());
    // stment.close();
	// conn.close();

	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
   	return rs;
   }

   
   public ResultSet search_recorded(String DongleUID) throws SQLException, ClassNotFoundException
   {
	   createconnection();
	       stment= conn.createStatement();  
	       ResultSet rs = null;
	       
   	try
	    {
   	 String qry = "	select distinct SPF from [protection].[dbo].[Sheet1] where [Dongle_UID]= '"+DongleUID+"' " + 
   	 		" and  ([Recorded] is null or [Recorded] not like '%yes%') "
   	 		+ " order by [SPF] asc";
   	 					
   	 
   	String qry2 = "DECLARE @val nvarchar(255);" + 
   			"DECLARE @val2 float;" + 
   			"Select top 1 @val = [SPF] from [protection].[dbo].[Sheet1] where [Dongle_UID]='"+DongleUID+"' order by [date] ,[index] ; " + 
   			"Select top 1 @val2 = [Index] from [protection].[dbo].[Sheet1]  where [Dongle_UID]='"+DongleUID+"' order by [date] ,[index] ; " + 
   			"IF @val  LIKE '%[0-9]%' " + 
   			"  BEGIN" + 
   			"  SELECT *" + 
   			"   	 		 FROM [protection].[dbo].[Sheet1]  " + 
   			"   	 		WHERE Dongle_UID ='"+DongleUID+"' " + 
   			"   	 						 and [SPF] = @val; " + 
   			"							 END; " + 
   			"ELSE " + 
   			" BEGIN " + 
   			"  SELECT *" + 
   			"   	 		 FROM [protection].[dbo].[Sheet1] " + 
   			"   	 		WHERE Dongle_UID ='"+DongleUID+"' " + 
   			"   	 						 and [index] =@val2; " + 
   			" END; " ;
   	
   	 rs= stment.executeQuery(qry);
    // System.out.println(rs.getMetaData().getColumnCount());
    // stment.close();
	// conn.close();

	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
   	return rs;
   }

   public ResultSet search_dongle_SPF(String DongleUID,String SPF) throws SQLException, ClassNotFoundException
   {
	   createconnection();
	       stment= conn.createStatement();  
	       ResultSet rs = null;
	       
   	try
	    {
   	 String qry = "	select * from [protection].[dbo].[Sheet1] where [Dongle_UID]= '"+DongleUID+"' "
   	 		+ " and SPF = '" +SPF +"'";

   	 					
   	 
   	String qry2 = "DECLARE @val nvarchar(255);" + 
   			"DECLARE @val2 float;" + 
   			"Select top 1 @val = [SPF] from [protection].[dbo].[Sheet1] where [Dongle_UID]='"+DongleUID+"' order by [date] ,[index] ; " + 
   			"Select top 1 @val2 = [Index] from [protection].[dbo].[Sheet1]  where [Dongle_UID]='"+DongleUID+"' order by [date] ,[index] ; " + 
   			"IF @val  LIKE '%[0-9]%' " + 
   			"  BEGIN" + 
   			"  SELECT *" + 
   			"   	 		 FROM [protection].[dbo].[Sheet1]  " + 
   			"   	 		WHERE Dongle_UID ='"+DongleUID+"' " + 
   			"   	 						 and [SPF] = @val; " + 
   			"							 END; " + 
   			"ELSE " + 
   			" BEGIN " + 
   			"  SELECT *" + 
   			"   	 		 FROM [protection].[dbo].[Sheet1] " + 
   			"   	 		WHERE Dongle_UID ='"+DongleUID+"' " + 
   			"   	 						 and [index] =@val2; " + 
   			" END; " ;
   	
   	 rs= stment.executeQuery(qry);
    // System.out.println(rs.getMetaData().getColumnCount());
    // stment.close();
	// conn.close();

	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
   	return rs;
   }

   
   public ResultSet search_group() throws SQLException, ClassNotFoundException
   {
	   createconnection();
	       stment= conn.createStatement();  
	       ResultSet rs = null;
	       
   	try
	    {
   	 String qry = "SELECT [Software_Serial],[SPF] FROM [protection].[dbo].[Sheet1] where [index] >=6898 "
   	 		+ "and  [Software_Serial] Is not NULL and [Recorded] Is Null  "
   	 		+ "group by [Software_Serial] , [SPF] ";

   	 rs= stment.executeQuery(qry);
     System.out.println(rs.getMetaData().getColumnCount());
     stment.close();
	 conn.close();

	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
   	return rs;
   }
   
   
   

   public ResultSet select_dongle_uid() throws SQLException, ClassNotFoundException
   {
	   createconnection();
	       stment= conn.createStatement();  
	       ResultSet rs = null;
	       
   	try
	    {
   	 String qry = 
   	 		"select distinct [Dongle_UID] FROM [protection].[dbo].[Sheet1] "
   	 		+ "where [index] >=7000 and "
   	 		+ "[Dongle_UID] LIKE '%[0-9]%'"
   	 		+ "and [Dongle_UID] > ='1336007033'" 
   	 		+ "order by  [Dongle_UID]";

   	 rs= stment.executeQuery(qry);
     //System.out.println(rs.getFetchSize());
     //stment.close();
	 //conn.close();

	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
   	return rs;
   }

   
   
   public ResultSet select_software_verison() throws SQLException, ClassNotFoundException
   {
	   createconnection();
	       stment= conn.createStatement();  
	       ResultSet rs = null;
	       
   	try
	    {
   	 String qry = 
   	 		"SELECT  distinct [Version_Info] FROM Sheet1 where [index] >=7000 ";

   	 rs= stment.executeQuery(qry);
     System.out.println(rs.getFetchSize());
     //stment.close();
	 //conn.close();

	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
   	return rs;
   }
}
