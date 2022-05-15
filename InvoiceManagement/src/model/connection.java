package model;

import java.sql.*;

public class connection {
	
	public Connection connect() {
		
		Connection con = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/invoicemanagement", "root", "");
			
			//for testing
			System.out.print("Successfully Connected");
			
		}
		catch(Exception e){
			
			e.printStackTrace();
			
		}
		return con;
		
	}

}
