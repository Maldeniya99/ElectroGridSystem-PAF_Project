package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Staff {

	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		public String insertStaff(String name, String password, String email, String tel, String salary, String department)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " insert into staff(`staffID`,`sname`,`spassword`,`semail`,`stel`,`salary`,`department`)"
		 + " values (?, ?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, name);
		 preparedStmt.setString(3, password);
		 preparedStmt.setString(4, email);
		 preparedStmt.setString(5, tel);
		 preparedStmt.setString(6, salary);
		 preparedStmt.setString(7, department);
		 // execute the statement
		 
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting staff.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		
		
		public String readStaff()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Staff Name</th><th>Password</th>" +
		 "<th>Email</th>" + "<th>Telephone No</th>" + "<th>Salary</th>" + "<th>Department</th>" + "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from staff";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String staffID = Integer.toString(rs.getInt("staffID"));
		 String sname = rs.getString("sname");
		 String spassword = rs.getString("spassword");
		 String semail = rs.getString("semail");
		 String stel = rs.getString("stel");
		 String salary = rs.getString("salary");
		 String department = rs.getString("department");
		 // Add into the html table
		 output += "<tr><td>" + sname + "</td>";
		 output += "<td>" + spassword + "</td>";
		 output += "<td>" + semail + "</td>";
		 output += "<td>" + stel + "</td>";
		 output += "<td>" + salary + "</td>";
		 output += "<td>" + department + "</td>";
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='staff.jsp'>"
		 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
		 + "<input name='staffID' type='hidden' value='" + staffID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading staff.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		
		
		public String updateStaff(String ID, String name, String password, String email, String tel, String salary, String department)
		 
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE staff SET sname=?,spassword=?,semail=?,stel=?,salary=?,department=?WHERE staffID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, name);
		 preparedStmt.setString(2, password);
		 preparedStmt.setString(3, email);
		 preparedStmt.setString(4, tel);
		 preparedStmt.setString(5, salary);
		 preparedStmt.setString(6, department);
		 preparedStmt.setInt(7, Integer.parseInt(ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating staff.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		
		
		public String deleteStaff(String staffID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from staff where staffID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(staffID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting staff.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
}
