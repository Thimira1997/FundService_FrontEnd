package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Fund {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fund", "root", "Thimira1997.");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return con;
	}

	public String readFund() {
		String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for reading."; 
			 } 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Name </th> <th>National Identity Card Number</th><th>Address</th>"
						+ "<th>Phone Number</th> <th>Email Address</th> <th>Amount</th> <th>Description(why are you request fund)</th>  <th>Update</th><th>Remove</th></tr>"; 
			 String query = "select * from fund"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query);  
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
					String fundID = Integer.toString(rs.getInt("fundID"));
					String name = rs.getString("name");
					String nic = rs.getString("nic");
					String address = rs.getString("address");
					String phone = rs.getString("phone");
					String email = rs.getString("email");
					String amount = Double.toString(rs.getDouble("amount"));
					String description = rs.getString("description");
				 // Add into the html table
					output += "<tr><td><input id='hidfundIDUpdate' name='hidfundIDUpdate' type='hidden' value='" + fundID
							+ "'>" + name + "</td>";
					output += "<td>" + nic + "</td>";
					output += "<td>" + address + "</td>";
					output += "<td>" + phone + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + amount + "</td>";
					output += "<td>" + description + "</td>";
				 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' "
							 + "class='btnUpdate btn btn-secondary' data-fundid='" + fundID + "'></td>"
							 + "<td><input name='btnRemove' type='button' value='Remove' "
							 + "class='btnRemove btn btn-danger' data-fundid='" + fundID + "'></td></tr>";
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "Error while reading the funds."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	}

	public String insertFund(String name, String nic, String address, String phone, String email, String amount,String description) {
		String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
				 return "Error while connecting to the database for inserting."; 
			 } 
			 // create a prepared statement
			 String query = "insert into fund (fundID,name,nic,address,phone,email,amount,description) values (?, ?, ?, ?, ?, ?, ?, ?);"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, nic);
			preparedStmt.setString(4, address);
			preparedStmt.setInt(5, Integer.parseInt(phone));
			preparedStmt.setString(6, email);
			preparedStmt.setDouble(7, Double.parseDouble(amount));
			preparedStmt.setString(8, description); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			String newItems = readFund(); 
			output = "{\"status\":\"success\", \"data\": \""
					+ "" +newItems + "\"}"; 
			} 
			catch (Exception e) 
			{ 
				System.err.println("ERORR : "+e.getMessage());
				output = "{\"status\":\"error\", \"data\":"
					 + "\"Error while inserting the item.\"}"; 
				 
			} 
			return output;
		 }

	public String updateFund(String fundID, String name, String nic, String address, String phone , String email , String amount , String description) {
		
		String output = ""; 
		 try
		 { 
				 Connection con = connect(); 
			 if (con == null) 
			 { 
				 return "Error while connecting to the database for updating."; 
			 } 
			 // create a prepared statement
			 String query = "UPDATE fund SET name=?,nic=?,address=?,phone=?,email=?,amount=?,description=? WHERE fundID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, name); 
			 preparedStmt.setString(2, nic);
			 preparedStmt.setString(3, address);
			 preparedStmt.setString(4, phone);
			 preparedStmt.setString(5, email);
			 preparedStmt.setDouble(6, Double.parseDouble(amount)); 
			 preparedStmt.setString(7, description); 
			 preparedStmt.setInt(8, Integer.parseInt(fundID));
			// execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newItems = readFund(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": "
			 		+ "\"Error while updating the item.\"}"; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output;
	}

	public String deleteFund(String fundID) {
		String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
				 return "Error while connecting to the database for deleting."; 
			 } 
			 // create a prepared statement
			 String query = "delete from fund where fundID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(fundID)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newItems = readFund(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": "
			 		+ "\"Error while deleting the item.\"}"; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output;
	}
}
