package com.ecommerseapp.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecommerseapp.connection.ConnectionCreated;
import com.ecommerseapp.interfaces.GuestInterface;

public class GuestDAO implements GuestInterface {
	Connection con;
	   ConnectionCreated obj = new ConnectionCreated();
		@Override
		public void viewProductItem() {
			try {
				con=obj.getConnection();
				
				String query="select * from products";	
			    PreparedStatement ps= con.prepareStatement(query);
			    ResultSet rs=  ps.executeQuery();
			      while(rs.next()) {
			    	System.out.println("Product Id: "+rs.getInt(1));
			    	System.out.println("Product Name: "+rs.getString(2));
			    	System.out.println("Product Description: "+rs.getString(3));
			    	System.out.println("Available Quantity: "+rs.getInt(5));
			    	System.out.println("Product Price: "+rs.getInt(4));
			    	System.out.println("..................");
			      }
			      
			      System.out.println("Please login to purchase items");
				}
			    catch(Exception e) {
					e.printStackTrace();
				}
				finally {
				    try {
				    	con.close();
				    	}
				    catch(SQLException e) {
				    	e.printStackTrace();
				    }  
				}
		}
		@Override
		public String notPurchaseItem() {
			
			return "Do user registration first to purchase product";
		}

}
