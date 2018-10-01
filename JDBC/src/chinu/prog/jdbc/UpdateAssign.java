package chinu.prog.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateAssign {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		Scanner sc = null;
		String desg = null;

		int result = 0;
		try {
			// read inputs
			sc = new Scanner(System.in);
			if (sc != null) {

				System.out.println("ENTER THE EXISTING DESG");
				desg = sc.next().toUpperCase();

			} // if

			// CONVERT INPUT VALUES FOR THE SQLQUEY
			desg= "'" + desg + "'";// gives the clerk

			// REGISTER THE DRIVER
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// ESTABLISH THE CONNECTION
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
			// CREATE THE JDBC STATEMENT
			if (con != null)
				st = con.createStatement();

			// PREPARE THE SQL QUERY
			// update emp2 set sal=sal+(sal*20/100)where ename='SMITH';
			String query = "UPDATE EMP2 SET SAL=SAL+(SAL*20/100) WHERE JOB=" + desg;

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
			try {
				if (sc != null)
					sc.close();
			} catch (Exception se) {
				se.printStackTrace();
			}
		} // finally end

	}// main

} // class end
