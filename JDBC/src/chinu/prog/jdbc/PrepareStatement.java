package chinu.prog.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PrepareStatement {

	public static void main(String[] args) {
		Scanner sc = null;
		int count = 0;
		Connection con = null;
		String query = null;
		PreparedStatement ps = null;
		int no = 0;
		String name = null, adres = null;
		int result = 0;

		try {
			// READ INPUTS
			sc = new Scanner(System.in);
			System.out.println("ENTER THE STUDENT COUNT:-");
			if (sc != null)
				count = sc.nextInt();
			// REGISTER THE DRIVER
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// ESTABLISH THE CONNECTION
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
			// PREPARE SQL QUERY
			query = "INSERT INTO STUDENT VALUES(?,?,?)";
			if (con != null && sc != null)
				ps = con.prepareStatement(query);
			if (sc != null) {
				for (int i = 1; i <= count; ++i) {
					System.out.println("ENTER THE " + i + " STUDENT DETAILS:=");
					System.out.println("ENTER THE NO:=");
					no = sc.nextInt();
					System.out.println("ENTER THE NAME:=");
					name = sc.next();
					System.out.println("ENTER THE ADDRESS:=");
					adres = sc.next();
					// SET INPUT VALUES FROM ENDUSER
					ps.setInt(1, no);
					ps.setString(2, name);
					ps.setString(3, adres);
					// EXECUTE THE QUERY
					result = ps.executeUpdate();
					if (result == 0)
						System.out.println(i + "CANT BE INSERTED");
					else
						System.out.println(i + "ALREADY INSERTED");
				} // end of for
			}
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

	}// main
}// class
