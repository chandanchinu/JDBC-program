package chinu.prog.jdbc;

/* JDBC APP TO GET EMP BONUS DETAILS ON GIVEN EMPNO.
/VERSION:(no version  special edition)
/AUTHOR :TEAM (SAI,CHANDAN,VENKY)
/DATE   : 0/09/2018
*/
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class BonusDetails {
	public static void main(String[] args) {
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		Scanner sc = null;
		int empnum = 0;
		try {
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("eneter the emp no");
				empnum = sc.nextInt();
			}
			// REGISTER THE DRIVER
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// ESTABLISH CONNECTION
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
			// CREATE STATEMENT OBJ
			if (con != null)
				st = con.createStatement();
			// SEND &EXECUTE QUERY
			if (st != null)
				rs = st.executeQuery(" SELECT ENAME, JOB, SAL, SAL * 8 / 100 BONUS FROM EMP where empno=" + empnum);
			// PROCESS THE RESULTSET
			if (rs.next()) {
				System.out
						.println(rs.getString(1) + ".." + rs.getString(2) + ".." + rs.getInt(3) + ".." + rs.getInt(4));
			} else
				System.out.println("NO SUCH RECORD FOUND");

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

		} // end of finally
	}// end of main
}
