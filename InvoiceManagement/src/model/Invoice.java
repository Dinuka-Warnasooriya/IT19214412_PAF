package model;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Invoice {
	
	public String insertInvoice(String customerName, String email, String units, String amount, String month) 
	{ 
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for inserting."; } 
			// create a prepared statement
			String query = " insert into invoices(`invoiceID`,`customerName`,`email`,`units`,`amount`,`month`)"
					+ " values (?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, customerName); 
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, units);
			preparedStmt.setString(5, amount); 
			preparedStmt.setString(6, month); 
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newItems = readInvoices();
			
			System.out.println(newItems);

			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";

		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the invoice.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String readInvoices() 
	{ 
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for reading."; } 
			// Prepare the html table to be displayed
			output = "<table class='table table-hover'><tr><th>Customer Name</th><th>E-mail</th>" +
					"<th>Units</th>" + 
					"<th>Amount(Rs)</th>" +
					"<th>Month</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 

			String query = "select * from invoices"; 
			Statement stmt = con1.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String invoiceID = Integer.toString(rs.getInt("invoiceID")); 
				String customerName = rs.getString("customerName"); 
				String email = rs.getString("email"); 
				String units = Integer.toString(rs.getInt("units")); 
				String amount = rs.getString("amount");
				String month = rs.getString("month");
				// Add into the html table
				output += "<tr><td><input type='hidden' name='hidInvoiceIDUpdate' id='hidInvoiceIDUpdate' value='"+invoiceID+"'>" + customerName + "</td>"; 
				output += "<td>" + email + "</td>"; 
				output += "<td>" + units + "</td>"; 
				output += "<td>" + amount + "</td>";
				output += "<td>" + month + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-success' data-invoiceID='"+invoiceID+"'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-invoiceID='"+invoiceID+"'></td></tr>"; 
			} 
			con1.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the invoices."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String updateInvoice(String id, String customerName, String email, String units, String amount, String month) 

	{ 
		System.out.println("came here as well");
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for updating."; } 
			// create a prepared statement
			String query = "UPDATE invoices SET customerName=?,email=?,units=?,amount=?,month=? WHERE invoiceID=?"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, customerName); 
			preparedStmt.setString(2, email); 
			preparedStmt.setString(3, units); 
			preparedStmt.setString(4, amount);
			preparedStmt.setString(5, month);
			preparedStmt.setInt(6, Integer.parseInt(id)); 
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newItems = readInvoices();

			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	public String deleteInvoice(String invoiceID) 
	{ 
		String output = ""; 
		try
		{ 
			connection con = new connection();
			Connection con1 = con.connect(); 
			if (con1 == null) 
			{return "Error while connecting to the database for deleting."; } 
			// create a prepared statement
			String query = "delete from invoices where invoiceID=?"; 
			PreparedStatement preparedStmt = con1.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, invoiceID); 
			// execute the statement
			preparedStmt.execute(); 
			con1.close(); 
			String newItems = readInvoices();

			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} 
		catch (Exception e) 
		{ 
			output =  "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
}
