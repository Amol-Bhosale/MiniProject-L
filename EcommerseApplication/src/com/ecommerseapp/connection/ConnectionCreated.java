package com.ecommerseapp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreated {
	
	Connection con;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/E-CommerseApp";
		String username="root";
		String password="root";
		con=DriverManager.getConnection(url,username,password);
		return con;
		
	}
}
