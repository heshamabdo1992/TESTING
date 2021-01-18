package license_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SearchData {
    public static void main(String[] args) {
		try (Connection con = DriverManager
				.getConnection("jdbc:odbc:yourDatabaseName");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM users WHERE key=9");) {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			while (rs.next()) {
				System.out.println(rs.getString("name") + " "
						+ rs.getString("age"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}