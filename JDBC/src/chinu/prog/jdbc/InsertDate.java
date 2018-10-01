package chinu.prog.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InsertDate {
	private static final String query = "insert into person values(?,?)";

	public static void main(String[] args) {
		Connection con = null;
		Scanner sc = null;
		java.util.Date udate = null;
		java.sql.Date sdate = null;
		PreparedStatement ps = null;

		long ms = 0;
		SimpleDateFormat sdf = null;
		String name = null, dob = null;
		int result = 0;
		try {
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("ENTER THE NAME:=");
				name = sc.next();
				System.out.println("ENTER THE DATE OF BIRTH(DD-MM-YYYY):=");
				dob = sc.next();
			}
			// CONVERT STRING VALUES INTO JAVA.SQL.DATE
			sdf = new SimpleDateFormat("dd-MM-yyyy");
			if (sdf != null)
				udate = sdf.parse(dob);
			if (udate != null)
				ms = udate.getTime();
			sdate = new java.sql.Date(ms);
			// REGISTER THE JDBC DRIVER
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
			// crete Prepared statement
			if (con != null)
				ps = con.prepareStatement(query);
			// set values
			if (ps != null) {
				ps.setString(1, name);
				ps.setDate(2, sdate);
			}
			// execute query
			if (ps != null)
				result = ps.executeUpdate();
			// process the result
			if (result == 0)
				System.out.println("DATA NOT INSERTED");
			else
				System.out.println("DATA INSERTED");

		} // end of try

		catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (ps != null) {
					ps.close();
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // end of finally
	}// end of main

}// end of class
