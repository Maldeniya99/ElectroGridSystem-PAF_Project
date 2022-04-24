package model; 
import java.sql.*; 

public class Payment { //A common method to connect to the DB
	private Connection connect() { 
		Connection con = null; 
		 try { 
			 Class.forName("com.mysql.jdbc.Driver"); 
			 
			 //Provide the correct details: DBServer/DBName, username, password 
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", "root", ""); 
		 } catch (Exception e){
			 e.printStackTrace();
		 } 
		 return con; 
	 } 
	
	public String insertpayment(String acNo, String name, String address, String phn, String amount, String cardNo, String expire) { 
		 String output = ""; 
		 System.out.println(name);
		 try { 
			 Connection con = connect(); 
			 if (con == null) {
				 return "Error while connecting to the database for inserting."; 
			 } 
			 
			 // create a prepared statement
			 String query = " insert into payment (`id`,`AccNo`,`Name`,`Address`,`Phone`,`Amount`, `cardNo`,`Expire`)"
			 + " values (?, ?, ?, ?, ?, ?, ?, ?)"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, acNo); 
			 preparedStmt.setString(3, name); 
			 preparedStmt.setString(4, address); 
			 preparedStmt.setString(5, phn);
			 preparedStmt.setDouble(6, Double.parseDouble(amount)); 
			 preparedStmt.setString(7, cardNo);
			 preparedStmt.setString(8, expire);
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Inserted successfully"; 
			 
		 } catch (Exception e)  { 
			 output = "Error while inserting the Payment." + e.getMessage(); 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 }

// Retrieve data
	public String readpayment(/*String acNo*/) { 
		 String output = ""; 
		 
		 try { 
			 Connection con = connect(); 
			 if (con == null) {
				 return "Error while connecting to the database for reading."; 
			 } 
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr>"
			 		+ "<th>Name</th>"
			 		+ "<th>Address</th>" +
					 "<th>TelNo</th>" + 
					 "<th>Amount</th>" + 
					 "<th>CardNo</th>" + 
					 "<th>Expire</th> </tr>"; 
			 
			// System.out.println(acNo);
			 String query = "select * from payment"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next())  { 
				 String accNo = rs.getString("AccNo"); 
				 String Name = rs.getString("Name"); 
				 String Address = rs.getString("Address");  
				 String TelNo = Integer.toString(rs.getInt("Phone")); 
				 String Amount = Double.toString(rs.getDouble("Amount"));
				 String CardNo = rs.getString("cardNo");  
				 String Expire = rs.getString("Expire");
				 // Add into the html table
				 output += "<tr><td>" + Name + "</td>"; 
				 output += "<td>" + Address + "</td>"; 
				 output += "<td>" + TelNo + "</td>"; 
				 output += "<td>" + Amount + "</td>"; 
				 output += "<td>" + CardNo + "</td>";
				 output += "<td>" + Expire + "</td>";
				 // buttons
				 output += "</td></tr>"; 
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
		 }  catch (Exception e)  { 
			 output = "Error while reading the items.  " + e.getMessage(); 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 } 
	 
}


