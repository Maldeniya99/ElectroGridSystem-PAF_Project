package model;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Notice 
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, user name, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", ""); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 

//insert
public String insertNotice(String nCategory, String nTitle, String nDesc, String nDate, String nAuthor ) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into notices(`noticeID`,`nCategory`,`nTitle`,`nDesc`,`nDate`, `nAuthor`)"
 + " values (?, ?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, nCategory); 
 preparedStmt.setString(3, nTitle); 
 preparedStmt.setString(4, nDesc); 
 preparedStmt.setString(5, nDate); 
 preparedStmt.setString(6, nAuthor); 
 // execute the statement

 preparedStmt.execute(); 
 con.close(); 
 output = "Inserted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while inserting the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String readNotices() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th> Notice Category</th>"+
		  "<th>Notice Title</th>" +
		  "<th>Notice Description</th>" + 
		  "<th>Notice Date</th>" +
		  "<th>Author</th>" +
		  "<th>Update</th><th>Remove</th></tr>"; 
 
 String query = "select * from notices"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String noticeID = Integer.toString(rs.getInt("noticeID")); 
 String nCategory = rs.getString("nCategory"); 
 String nTitle = rs.getString("nTitle"); 
 String nDesc = rs.getString("nDesc"); 
 String nDate = rs.getString("nDate"); 
 String nAuthor = rs.getString("nAuthor"); 
 // Add into the html table
 output += "<tr><td>" + nCategory + "</td>"; 
 output += "<td>" + nTitle + "</td>"; 
 output += "<td>" + nDesc + "</td>"; 
 output += "<td>" + nDate + "</td>"; 
 output += "<td>" + nAuthor + "</td>"; 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
 + "<td><form method='post' action='notices.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='noticeID' type='hidden' value='" + noticeID 
 + "'>" + "</form></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading the Notices."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 


public String updateNotice(String ID, String nCategory, String nTitle, String nDesc, String nDate, String nAuthor) 
{ 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE notices SET nCategory=?,nTitle=?,nDesc=?,nDate=?, nAuthor=? WHERE noticeID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, nCategory); 
	 preparedStmt.setString(2, nTitle); 
	 preparedStmt.setString(3, nDesc); 
	 preparedStmt.setString(4, nDate); 
	 preparedStmt.setString(5, nAuthor); 
	 preparedStmt.setInt(6, Integer.parseInt(ID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the notices."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

	//delete
	public String deleteNotice(String noticeID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from notices where noticeID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(noticeID)); 
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


