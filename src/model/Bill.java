package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {

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
		public String insertBill(int cusID, int noOfWatts, double amount, double total)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " insert into bill_table(`billID`,`cusID`,`noOfWatts`,`amount`,`total`)"
		 + " values (?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setInt(2, cusID);
		 preparedStmt.setInt(3, noOfWatts);
		 preparedStmt.setDouble(4, amount);
		 preparedStmt.setDouble(5, total);
		 
		 // execute the statement
		 
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting bill.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		public String readBill()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Bill ID</th><th>Customer ID</th><th>Number of Watts</th><th>Bill Amount</th>" +
		 "<th>Total Amount</th>" + "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from bill_table";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String billID = Integer.toString(rs.getInt("billID"));
		 String cusID = rs.getString("cusID");
		 String noOfWatts = rs.getString("noOfWatts");
		 String amount = rs.getString("amount");
		 String total = rs.getString("total");

		 // Add into the html table
		 
		 output += "<tr><td>" + billID+ "</td>";
		 output	+= "<td>" + cusID + "</td>";
		 output +=	"<td>" + noOfWatts + "</td>";
		 output += "<td>" + amount + "</td>";
		 output += "<td>" + total + "</td>";
		
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='bill.jsp'>"
		 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
		 + "<input name='billID' type='hidden' value='" + billID
		 + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading bill.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		
		
		public String updateBill(int ID, int cusID, int noOfWatts, double amount, double total)
		 
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE bill_table SET noOfWatts=?,amount=?,total=?WHERE billID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, noOfWatts);
		 preparedStmt.setDouble(2, amount);
		 preparedStmt.setDouble(3, total);
		 preparedStmt.setInt(4, ID);
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating bill.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		public String deleteBill(String billID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from bill_table where billID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(billID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting bill.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
}
