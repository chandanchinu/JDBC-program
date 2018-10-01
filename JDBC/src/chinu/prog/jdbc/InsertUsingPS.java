package chinu.prog.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertUsingPS {
	private static final String query = "insert into emp2 (empno,ename,job,sal) values(?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc = null;
		int count = 0;
		Connection con = null;

		PreparedStatement ps = null;
		int eno = 0, sal = 0;
		String ename = null, job = null;
		int result = 0;

		try {
			// READ INPUTS
			sc = new Scanner(System.in);
			System.out.println("ENTER THE EMPLOYEE COUNT:-");
			if (sc != null)
				count = sc.nextInt();
			// REGISTER THE DRIVER
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// ESTABLISH THE CONNECTION
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
			// PREPARE SQL QUERY

			if (con != null && sc != null)
				ps = con.prepareStatement(query);
			if (sc != null) {
				for (int i = 1; i <= count; ++i) {
					System.out.println("ENTER THE " + i + " STUDENT DETAILS:=");
					System.out.println("ENTER THE eNO:=");
					eno = sc.nextInt();
					System.out.println("ENTER THE eNAME:=");
					ename = sc.next();
					System.out.println("ENTER THE JOB:=");
					job = sc.next();
					System.out.println("ENTER THE SALARY:=");
					sal = sc.nextInt();
					// SET INPUT VALUES FROM ENDUSER
					ps.setInt(1, eno);
					ps.setString(2, ename);
					ps.setString(3, job);
					ps.setInt(4, sal);
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
