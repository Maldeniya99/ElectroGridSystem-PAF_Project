package model;


import java.sql.*;

public class User

{ 
	
	//connect to the  electric grid system DB
	
	
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
				 
				 {
					 
					 e.printStackTrace();
				 
				 
				 }
	 
	 
		 return con;
		 
		 
		 }
	
	
	
//insert users to the system
	
	
			public String insertUser(String userName, String name, String phone, String email,String password){
				
				 String output = "";
				 
				 try
				 
				 {
					 
						 Connection con = connect();
						 
						 if (con == null)
							 
						 {return "Error while connecting to the database for inserting!"; }
						 
						 // create a prepared statement
						 
						 String query = " insert into users(`userID`,`userName`,`name`,`phone`,`email`,`password`)"+ " values (?, ?, ?, ?, ?,?)";
						 
						 PreparedStatement preparedStmt = con.prepareStatement(query);
						 
						 // binding values
						 
						 preparedStmt.setInt(1, 0);
						 preparedStmt.setString(2, userName);
						 preparedStmt.setString(3, name);
						 preparedStmt.setString(4, phone);
						 preparedStmt.setString(5, email);
						 preparedStmt.setString(6, email);
						 
						 // execute the statement
						
						 preparedStmt.execute();
						 
						 con.close();
						 
						 output = "Inserted successfully!";
						 
						 }
						 catch (Exception e)
				 
							 {
								 output = "Error while inserting the user.";
								 
								 System.err.println(e.getMessage());
								 
							 }
				 
							 return output;
							 
							 
							 }
			
			
			
// view user list of the system
			
						
			public String readUsers(){
				
				
				
				
			 String output = "";
			 
			 try
					 {
				 
					 Connection con = connect();
					 
					 if (con == null)
						 
					 {return "Error while connecting to the database for reading."; }
					 
					 // Prepare the html table to be displayed
					 
					 output = "<table border='1'><tr><th>User Name</th><th>Name</th>" +"<th>Phone</th>" +"<th>Email</th>" +"<th>Password</th>" +"<th>Update</th><th>Remove</th></tr>";
					
					 String query = "select * from users";
					 
					 
					 Statement stmt = con.createStatement();
					 
					 
					 ResultSet rs = stmt.executeQuery(query);
					 
					 
					 // iterate through the rows in the result set
					 
					 while (rs.next())
						 
					 {
					 String userID = Integer.toString(rs.getInt("userID"));
					 String userName = rs.getString("userName");
					 String name = rs.getString("name");
					 String phone = rs.getString("phone");
					 String email = rs.getString("email");
					 String password = rs.getString("password");
					 
					 // Add into the html table
					 
					 output += "<tr><td>" + userName + "</td>";
					 output += "<td>" + name + "</td>";
					 output += "<td>" + phone + "</td>";
					 output += "<td>" + email + "</td>";
					 output += "<td>" + password + "</td>";
					 
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' >"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"+ "<input name='userID' type='hidden' value='" + userID+ "'>" + "</form></td></tr>";
					
					 }
					 
					
					 con.close();
					 
					 // Complete the html table
					
					 output += "</table>";
					 
					 }
					 catch (Exception e)
			 
						 {
						 
						 output = "Error while reading the users.";
						 
						 System.err.println(e.getMessage());
						 
						 
						 }
							 return output;
							 
							 
						}
			
// update users in user list
			
			
		 public String updateUser(String userID, String userName, String name, String phone, String email,String password){
			 
			 
			 
			   String output = "";
			try
				{
					Connection con = connect();
					
					if (con == null)
						
					{return "Error while connecting to the database for updating."; }
					
					// create a prepared statement
					
					String query = "UPDATE users SET userName=?,name=?,phone=?,email=?,password=?WHERE userID=?";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					
					preparedStmt.setString(1, userName);
					preparedStmt.setString(2, name);
					preparedStmt.setString(3, phone);
					preparedStmt.setString(4, email);
					preparedStmt.setString(5, password);
					preparedStmt.setInt(6, Integer.parseInt(userID));
					
					// execute the statement
					
					preparedStmt.execute();
					
					con.close();
					
					output = "Updated successfully!";
					
				}
					catch (Exception e)
			
					{
						
					output = "Error while updating the user!.";
					
					System.err.println(e.getMessage());
					
					}
			
					return output;
					
					}
			 
		 
		 
			public String deleteUser(String userID){
				
				
			String output = "";
			
			try
			
					{
						Connection con = connect();
						
						if (con == null)
							
						{return "Error while connecting to the database for deleting."; }
						
						// create a prepared statement
						
						String query = "delete from users where userID=?";
						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						// binding values
						
						preparedStmt.setInt(1, Integer.parseInt(userID));
						
						// execute the statement
						
						preparedStmt.execute();
						
						con.close();
						
						output = "Deleted successfully";
						
				}
			catch (Exception e)
			
				{
				
				output = "Error while deleting the users.";
				
				System.err.println(e.getMessage());
				
				}
			
			return output;
			
			}
			
//get particular user
			public String getUser(String email) {

				String output = "";

				try {
				Connection con = connect();

				if (con == null) {
				return "Error while connecting to the database for reading";
				}

				// Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>userID</th><th>user Name</th><th>Name</th>" +"<th>Phone</th>" +"<th>Email</th>" +"<th>Password</th>" +"<th>Update</th><th>Remove</th></tr>";
				Statement stmt = con.createStatement();
				String query = "select * from users where email='" + email + "'";
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					 String userID = Integer.toString(rs.getInt("userID"));
					 String userName = rs.getString("userName");
					 String name = rs.getString("name");
					 String phone = rs.getString("phone");
					 String uemail = rs.getString("email");
					 String password = rs.getString("password");

				// Add new row to the html table
						output += "<tr><td>" + userID + "</td>"; 
				output += "<tr><td>" + userName + "</td>";
				
				output += "<td>" + name + "</td>";
			
				output += "<td>" + phone + "</td>";
				output += "<td>" + uemail + "</td>";
				
				output += "<td>" + password + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				+ "<td><form method='post' action=''>"
				+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>";
				
				}

				con.close();

				// Complete the html table
				output += "</table>";

				} catch (Exception e) {
				output = "Error while searching user!";
				System.err.println(e.getMessage());
				}

				return output;

				}
			
			
			

} 