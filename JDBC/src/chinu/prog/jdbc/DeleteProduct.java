package chinu.prog.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteProduct {
 

	public static void main(String[] args) {
		double Fprice = 0;
		double Lprice = 0;
		int result = 0;
		Scanner sc = null;
		Statement st = null;
		Connection con = null;
		try {
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("Enter first price range=");
				Fprice = sc.nextDouble();
				System.out.println("enter the last price range=");
				Lprice = sc.nextDouble();
			}
			// registr the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
			// create statment
			if (con != null) {
				st = con.createStatement();

			}
			if (st != null) {
		
				result = st.executeUpdate("DELETE FROM PRODUCT WHERE PRICE BETWEEN  "+Fprice+ " AND " +Lprice);
			}
			if (result == 0) {
				System.out.println(result + "no records found to delet");
			} else {
				System.out.println(result + "records found and deleted");
			}

		} // end of try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close the jdbc
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

		} // FINALLY

	}// main
}// class
