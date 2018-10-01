package chinu.prog.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsetTest {

	public static void main(String[] args) {
		Scanner sc= null;
		  int sno =0;
		 String sname = null;
		 String sadd=null;
		 Connection con = null;
		 Statement st= null;
		  int result=0;
		
		try {
			sc=  new Scanner(System.in);
			if (sc!=null) {
				System.out.println(" ENTER THE STUDENT SNO:=");
				sno= sc.nextInt();  //GIVES 101
				System.out.println("ENTER THE STUDENT NAME:=");
				sname= sc.next();   // GIVES CHANDAN
				System.out.println("ENTER THE STUDENT ADDRESS:=");
				sadd= sc.next();   // GIVES PURI
			}
			//CONVERT INPUT VALUE AS REQUIRED BY SQL QUERY
			sname= "'"+sname+"'";
			sadd="'"+sadd+"'";
			// REGISTER THE DRIVER
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// ESTABLISH THE CONNECTION
			con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott","tiger");
			// CREATE JDBC STATEMENT
			if(con!=null)
				st=con.createStatement();
			// PREPARE SQL STATEMENT
			//INSERT INTO STUDENT VALUES(1001,'CHANDAN','PURI');
			String query= "INSERT INTO STUDENT VALUES(" +sno+","+sname+","+sadd+")";
			System.out.println(query);
		      //SEND AND EXECUTE SQL QUERY
			if(st!=null)
				result=st.executeUpdate(query);
			if(result==0)
			System.out.println("RECORD INSERTION FAILED");
			else
				System.out.println("RECORD INSERTED");
							
		}// end of try
		catch(SQLException se) {
			if(se.getErrorCode()==1) {
				System.out.println("already existed"+se.getMessage());
			}
			else if(se.getErrorCode()==12899){
				System.out.println("out of size"+se.getMessage());
			}
			else {
				System.out.println("UNKNOWN ERROR"+se.getMessage());
			}
		}
			catch (ClassNotFoundException cnf) {
				cnf.printStackTrace();
			} 
		catch (Exception e) {
				e.printStackTrace();
			} 
		finally {
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

	