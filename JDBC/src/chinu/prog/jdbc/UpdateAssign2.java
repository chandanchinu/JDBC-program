package chinu.prog.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateAssign2 {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;

		int result = 0;
		try {

			// REGISTER THE DRIVER
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// ESTABLISH THE CONNECTION
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
			// CREATE THE JDBC STATEMENT
			if (con != null)
				st = con.createStatement();

			// PREPARE THE SQL QUERY
			// update emp set comm=nvl2(comm,comm+sal*10/100,sal*10/100) where
			// job='SALESMAN';

			String query = "UPDATE EMP2 SET COMM=NVL2(COMM,COMM+SAL*10/100,SAL*10/100) WHERE JOB='SALESMAN'";

			// send and execute sql query
			if (st != null)
				result = st.executeUpdate(query);
			if (result == 0)
				System.out.println("record not found");
			else
				System.out.println("record found and updated");
		} // try end
		catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		} // finally end

	}// main

} // class end
