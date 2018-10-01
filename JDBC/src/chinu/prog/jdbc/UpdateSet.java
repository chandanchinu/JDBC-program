package chinu.prog.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateSet {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		String newname = null, newadd = null;
		int no = 0;
		int result = 0;

		try {
			// READ INPUTS
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("ENTER THE EXISTING STUDENT NO TO UPDATE:=");
				no = sc.nextInt();
				sc.nextLine(); // gives 1001
				System.out.println("ENTER THE NEW NAME FOR STUDENT:=");
				newname = sc.next();// gives new name
				System.out.println("ENTER THE NEW ADDRESS=");
				newadd = sc.next();
			}

			// convert input values as sql query
			newname = "'" + newname + "'";// gives raja
			newadd = "'" + newadd + "'";
			// register the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
			// create statement
			if (con!=null)
				st = con.createStatement();
			// prepare query
			// update student set sname='CHKKKK' ,sadd='KKKKKK' where sno=5555;

			String query = "UPDATE STUDENT SET SNAME=" + newname + ",SADD=" + newadd + "WHERE SNO=" + no;
			// send and execute
			if (st != null)
				result = st.executeUpdate(query);
			if (result == 0)
				System.out.println("RECORD NOT FOUND");
			else
				System.out.println("RECORD  FOUND AND UPDATE");

		} // end of try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
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
		} // finally
	}// main

}// class
