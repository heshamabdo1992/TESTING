package license_manager;
import java.sql.*;

public class ReadfromAcess
	{
	    public static void main(String[] args)
	    {
	    	  try
	    	    {

	    	        Connection conn=DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Hesham\\Desktop\\Multiframe CT (Done)\\Production.accdb");
	    	        Statement stment = conn.createStatement();
	    	        String qry = "SELECT * FROM Table1 where Recorded IS NULL OR Trim(Recorded)=\"\"";

	    	        ResultSet rs = stment.executeQuery(qry);
	    	        System.out.println(rs.getFetchSize());
	    	        while(rs.next())
	    	        {
	    	            String Dongl_UID    = rs.getString("Dongl_UID") ;
	    	            String Dongle_Serial = rs.getString("Dongle_Serial");
	    	            String Recorded = rs.getString("Recorded");
	    	            String Country=rs.getString("Country");
	    	            String Dongol_Type=rs.getString("Dongol_Type");
	    	            System.out.println(Country+" "+Dongol_Type+" "+Dongl_UID +" "+ Dongle_Serial+" " +Recorded);
	    	        }
	    	    }
	    	    catch(Exception err)
	    	    {
	    	        System.out.println(err);
	    	    }
	    }
	}
