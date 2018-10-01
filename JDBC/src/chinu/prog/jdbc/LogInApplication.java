package chinu.prog.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LogInApplication {

	public static void main(String[] args) {

		Connection con = null;
		Scanner sc = null;
		String user = null, pswrd = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;

		try {
			// READ INPUTS
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("USERNAME:-");
				user = sc.nextLine();
				System.out.println("PASSWORD:-");
				pswrd = sc.nextLine();
			}
			// CONVERT INPUT VALUES INTO SQL QUERY
			user = "'" + user + "'";
			pswrd = "'" + pswrd + "'";
			// REGISTER JDBC DRIVER
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// ESTABLISH THE CONNECTION
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
			// CREATE STATEMENT OBJECT
			if (con != null)
				st = con.createStatement();
			// PREPARE SQL QUERY
			if (st != null)
				rs = st.executeQuery("SELECT COUNT(*)FROM USERLIST WHERE UNAME=" + user + "AND PWD=" + pswrd);
			// PROCESS THE RESLUTSET
			if (rs != null) {
				if (rs.next())
					count = rs.getInt(1);
				System.out.println(count);
			}
			if (count == 0)
				System.out.println("ACCESS DENIED");
			else
				System.out.println("AUTHORIZED ACCESS");
		} // end try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (st != null)
					st.close();
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
		} // end of finally

	}// END OF MAIN

}// end of class
