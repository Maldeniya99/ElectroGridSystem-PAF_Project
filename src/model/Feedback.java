package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Feedback {

	
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
	public String insertItem(String cusID, String title, String rating, String desc) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) {
		 
		 return "Error while connecting to the database for inserting."; 
		 
	 } 
	 
	 // create a prepared statement
	 String query = " insert into feedback (`fbID`,`cusID`,`fbTitle`,`fbRating`,`fbDescription`)"
	 + " values (?, ?, ?, ?, ?)"; 
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setInt(2, Integer.parseInt(cusID)); 
	 preparedStmt.setString(3, title); 
	 preparedStmt.setDouble(4, Double.parseDouble(rating)); 
	 preparedStmt.setString(5, desc); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 
	 output = "Inserted successfully"; 
	 } 
	 
	 catch (Exception e) { 
		 
	 output = "Error while inserting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 
	 return output; 
	 } 
	
	
	public String readFeedbacks() { 
		
	 String output = ""; 
	 
	 try { 
		 
	 Connection con = connect(); 
	 
	 if (con == null) {
		 
		 return "Error while connecting to the database for reading.";
	 
	 } 
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr>"
	 		+ "<th>Feedback ID</th><th>Customer ID</th><th>Feedback Title</th>" +
	 "<th>Feedback Rating (From 10)</th>" + 
	 "<th>Description</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from feedback"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String fbID = Integer.toString(rs.getInt("fbID")); 
	 String cusID = rs.getString("cusID"); 
	 String fbTitle = rs.getString("fbTitle"); 
	 String fbRating = Double.toString(rs.getDouble("fbRating")); 
	 String fbDescription = rs.getString("fbDescription"); 
	 
	 // Add into the html table
	 output += "<td>" + fbID + "</td>";
	 output += "<td>" + cusID + "</td>"; 
	 output += "<td>" + fbTitle + "</td>"; 
	 output += "<td>" + fbRating + "</td>"; 
	 output += "<td>" + fbDescription + "</td>"; 
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
			 
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='fbID' type='hidden' value='" + fbID 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	public String updateFeedback(String fbID, String cusID, String title, String rating, String desc)  { 
		
	 String output = ""; 
	 
	 try { 
		 
	 Connection con = connect(); 
	 
	 if (con == null) {
		 return "Error while connecting to the database for updating."; 
	 } 
	 
	 // create a prepared statement
	 String query = "UPDATE feedback SET cusID=?,fbTitle=?,fbRating=?,fbDescription=? WHERE fbID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(cusID)); 
	 preparedStmt.setString(2, title); 
	 preparedStmt.setDouble(3, Double.parseDouble(rating)); 
	 preparedStmt.setString(4, desc); 
	 preparedStmt.setInt(5, Integer.parseInt(fbID)); 
	 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	public String deleteFeedback(String fbID) {
		
	 String output = ""; 
	 
	 try { 
		 
	 Connection con = connect(); 
	 
	 if (con == null) 
	 {
		 return "Error while connecting to the database for deleting."; 
	 
	 } 
	 
	 // create a prepared statement
	 String query = "delete from feedback where fbID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(fbID)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

}
