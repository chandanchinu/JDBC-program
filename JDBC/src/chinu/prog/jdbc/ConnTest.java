package chinu.prog.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnTest {

	public static void main(String[] args) throws Exception {
		// Create jdbc driver object
		oracle.jdbc.driver.OracleDriver driver = new oracle.jdbc.driver.OracleDriver();
		// register the driver
		DriverManager.registerDriver(driver);
		// create the connection
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
		// create statement
		Statement st = con.createStatement();
		// EXECUTE QUERY
		ResultSet rs = st.executeQuery("SELECT * FROM EMP");
		// process the result
		while (rs.next() != false) {
			System.out.println(rs.getString(1) + ".." + rs.getString(2));
		}

		rs.close();
		st.close();
		con.close();

	}

}
