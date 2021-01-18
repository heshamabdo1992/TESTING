package pms;

import java.sql.*;

public class ReadFromAccess {
	
     public Connection conn;
     public Statement stment ;
	
    public ReadFromAccess() 
    {

    }
// to get all customers
    public ResultSet search_customer() throws SQLException
    {
        conn=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Hesham"
        		+ "\\Desktop\\Multiframe CT (Done)\\P.accdb");
	       stment= conn.createStatement();  
	       ResultSet rs = null;
    	try
	    {
    	 String qry = "SELECT DISTINCT Customer,Country FROM Table1 "
    	 		+ "Where ID1 >=100 and ID1 <=200 and Customer Is Not NULL ";

    	 rs= stment.executeQuery(qry);
	     System.out.println(rs.getFetchSize());
	        
	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
    	return rs;
    }

    // to get all new dongles
    public ResultSet search_dongle() throws SQLException
    {
        conn=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Hesham\\"
        		+ "Desktop\\Multiframe CT (Done)\\P.accdb");
	       stment= conn.createStatement();  
	       ResultSet rs = null;
	       
    	try
	    {
    	 String qry = "SELECT DISTINCT Dongl_UID, Dongle_Serial, Dongol_Type, Country" + 
    	 		" FROM Table1 " + 
    	 		" WHERE Recorded Is Null AND  Dongl_UID Is Not Null "
    	 		+ "AND Dongle_Serial Is Not Null AND Dongol_Type "
    	 		+ "Is Not Null AND Dongol_Type <> '--' ";

    	 rs= stment.executeQuery(qry);
	     System.out.println(rs.getFetchSize());

	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
    	return rs;
    }
    
    // to get data by SPF AND Software_Serial

    public ResultSet search_by(String SPF,String Software_Serial) throws SQLException
    {
        conn=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Hesham\\"
        		+ "Desktop\\Multiframe CT (Done)\\P.accdb");
	       stment= conn.createStatement();  
	       ResultSet rs = null;
	       
    	try
	    {
    	 String qry = "SELECT *" + 
    	 		" FROM Table1 " + 
    	 		" WHERE SPF ='"+SPF+"' and Software_Serial = '"+Software_Serial+"' " ;

    	 rs= stment.executeQuery(qry);
	     System.out.println(rs.getFetchSize());

	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
    	return rs;
    }

 // to get data by Dongl_UID

    public ResultSet search_by_dongle_uid(String DongleUID) throws SQLException
    {
        conn=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Hesham\\"
        		+ "Desktop\\Multiframe CT (Done)\\P.accdb");
	       stment= conn.createStatement();  
	       ResultSet rs = null;
	       
    	try
	    {
    	 String qry = "SELECT *" + 
    	 		" FROM Table1 " + 
    	 		" WHERE Dongl_UID ='"+DongleUID+"' "
    	 				+ "and SPF =(select top 1 SPF from Table1 "
    	 				+ "where Dongl_UID='"+DongleUID+"' order by date ,id)" ;

    	 rs= stment.executeQuery(qry);
	     //System.out.println(rs.getFetchSize());

	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
    	return rs;
    }

    
   // to get data by SPF AND Software_Serial
/*
    public ResultSet search_order() throws SQLException
    {
        conn=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Hesham\\Desktop\\Multiframe CT (Done)\\P.accdb");
	       stment= conn.createStatement();  
	       ResultSet rs = null;
	       
    	try
	    {
    	 String qry = "SELECT * FROM Table1 " + 
    	 		"where ID1 >=6898";

    	 rs= stment.executeQuery(qry);
	        System.out.println(rs.getFetchSize());

	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
    	return rs;
    }
    */
    
    // to get data group by Software_Serial

    public ResultSet search_group() throws SQLException
    {
        conn=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Hesham\\"
        		+ "Desktop\\Multiframe CT (Done)\\P.accdb");
	       stment= conn.createStatement();  
	       ResultSet rs = null;
	       
    	try
	    {
    	 String qry = "SELECT Software_Serial,SPF FROM Table1 where ID1 >=6898 "
    	 		+ "and  Software_Serial Is not NULL and Recorded Is Null  "
    	 		+ "group by Software_Serial , SPF ";

    	 rs= stment.executeQuery(qry);
	        System.out.println(rs.getFetchSize());

	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
    	return rs;
    }
    
    
    // to get data group by Software_Serial

    public ResultSet select_dongle_uid() throws SQLException
    {
        conn=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Hesham\\"
        		+ "Desktop\\Multiframe CT (Done)\\P.accdb");
	       stment= conn.createStatement();  
	       ResultSet rs = null;
	       
    	try
	    {
    	 String qry = "\r\n" + 
    	 		"select distinct Dongl_UID FROM Table1 where ID1 >=6898 and "
    	 		+ "Dongl_UID <> 'uploader' and dongl_uid <>'--'";

    	 rs= stment.executeQuery(qry);
	        //System.out.println(rs.getFetchSize());

	    }
	    catch(Exception err)
	    {
	        System.out.println(err);
	    }
    	return rs;
    }
}
