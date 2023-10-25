package com.ecommerseapp.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecommerseapp.connection.ConnectionCreated;

public class ProductList {
	Connection con;
	List<Products> list;
	public List<Products> getProductList() {
		try {
		ConnectionCreated obj= new ConnectionCreated();
		 con=obj.getConnection();
		
		String query="select * from products";	
	    PreparedStatement ps= con.prepareStatement(query);
	    ResultSet rs=  ps.executeQuery();
	    
	     list = new ArrayList<>();
	     
	    
	       while(rs.next()) {
	    	
	    	list.add(new Products(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));    	
//	    	System.out.println("Product Id: "+rs.getInt(1));
//	    	System.out.println("Product Name: "+rs.getString(2));
//	    	System.out.println("Product Description: "+rs.getString(3));
//	    	System.out.println("Available Quantity: "+rs.getInt(5));
//	    	System.out.println("Product Price: "+rs.getInt(4));
//	    	System.out.println("..................");
	      }
	       
	       
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
		return list;
	    
	}
}
