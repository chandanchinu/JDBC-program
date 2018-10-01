package chinu.prog.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertAssign {

	public static void main(String[] args) {
	     Connection con = null;
	     Statement st=null;
		Scanner sc=null;
		int empno=0;
		String ename=null;
		String desg=null;
		int sal=0;
		int result =0;
		try {
			// read inputs
			 sc= new Scanner(System.in);
			 if (sc!=null) {
				 System.out.println("ENETR THE EMPNO");
				 empno= sc.nextInt();
				 System.out.println("ENTER THE EMPLOYEE NAME");
				 ename = sc.next();
				 System.out.println("ENTER THE DESG");
				 desg=sc.next();
				 System.out.println("ENTER THE SALARY");
				 sal= sc.nextInt();
	         }// if
			 
			 //CONVERT INPUT VALUES FOR THE SQLQUEY
			 ename="'"+ename+"'";// gives the chandan
			 desg="'"+desg+"'"; // gives the DESG
			  // REGISTER THE DRIVER
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //ESTABLISH THE CONNECTION
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "tiger");
			 // CREATE THE JDBC STATEMENT
			 if(con!=null)
				 st=con.createStatement();
				 
			 //PREPARE THE SQL QUERY
			 // insert into emp2 (empno,ename,job,sal) values (55,'bbbb','sales',5000);
			 String query="INSERT INTO EMP2 (EMPNO,ENAME,JOB,SAL) values ("+empno+","+ename+","+desg+","+sal+")" ;
			 // send and execute sql query
			 if(st!=null)
				 result=st.executeUpdate(query);
			  if(result==0)
				  System.out.println("record insertion failed");
			  else
				  System.out.println("record inserted");
			  }// try end
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
		}//finally end

	}// main

} // class end
