
package CheckFunctions;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckIfDirectoryExists {
		
     
     
    
    private static SimpleDateFormat dateFormat;


	public static ResultSet Study_Directory() throws ClassNotFoundException, SQLException
    {
    	Connection conn;
	   String userName = "sa";
 		String password = "PAXDB@dm1n";
 		String ServerName= "PaxeraAI-02";
 		String DBName="MIGlobal";
 		
//  	   String userName = "sa";
//  		String password = "PXDBPass2015";
//  		String ServerName= "192.168.5.179\\sqlexpress";
//  		String DBName="MIGlobal";
 		//String TableName="CalcTable";
 		// JDBC Driver class
 		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
 		// Creating a connection to the database. //like(Connection conn = DriverManager.getConnection("DatabaseURL","UserName", "Password");
 		conn = DriverManager.getConnection
 				("jdbc:sqlserver://"+ServerName+";databaseName="
 		+DBName+"",userName, password);

        Statement stment ;

 	       stment= conn.createStatement();  
 	       ResultSet rs = null;
    	try
 	    {
    	 String qry = "SELECT [Study_Inc_ID] ,[Study_Directory] "
    	 		+ " FROM [Studies] ";
    	 		//+ " Where [Study_Directory] like 'E:\\%'";

    	 rs= stment.executeQuery(qry);
 	     System.out.println(rs.getMetaData().getColumnCount());
 	   //  stment.close();
 		// conn.close();
 	    }
 	    catch(Exception err)
 	    {
 	        System.out.println(err);
 	    }
    	return rs;
    }
    
    
    
		public static void main(String[] args)
				throws ClassNotFoundException, SQLException, IOException {
		     String text;
		     String path;
		     
			ResultSet rs=Study_Directory();
			List<String> Study_Directory= new ArrayList<String>();
			List<String> Study_Inc_ID= new ArrayList<String>();
			 text=null;
	    	path="C:\\Log\\log.txt";

			while(rs.next())
		       {
				Study_Inc_ID.add(rs.getString("Study_Inc_ID")) ;
				Study_Directory.add(rs.getString("Study_Directory")) ;
		       }
			
			//System.out.println("Directory No 0 : " 
				//	+Study_Directory.get(2).toString());
			for(int i=0;i<Study_Directory.size();i++) {
				
			if (Study_Directory.get(i)!= null) {
			File dir = new File(Study_Directory.get(i));
			// Tests whether the directory denoted by this abstract pathname exists.
			boolean exists = dir.exists(); 
			System.out.println("Directory " + dir.getPath() + 
					" exists: " + exists);	
			text="Study_UID "+Study_Inc_ID.get(i)+" Directory " + dir.getPath() + 
					" exists: " + exists;
			addtotextfile(text,path);
			}
			else
			{
				text="Directory " + Study_Inc_ID.get(i) + 
						" is NULL: ";
				addtotextfile(text,path);
			}
			}
			
		       }
			
		
		 public static  void addtotextfile(String text,String path) throws IOException {
				
		        File file = new File(path);
		        if (!file.exists()) {
		            file.createNewFile();
		        }
				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
				BufferedWriter bw = new BufferedWriter(fw);
		        try {
					bw.write(text);
				} catch (Exception e) {
					bw.write("NULL");
				}

		            bw.newLine();
				    bw.close();
			}
	}
