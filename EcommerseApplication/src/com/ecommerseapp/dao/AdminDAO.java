package com.ecommerseapp.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ecommerseapp.connection.ConnectionCreated;
import com.ecommerseapp.interfaces.AdminInterface;
import com.ecommerseapp.products.ProductList;
import com.ecommerseapp.products.Products;

public class AdminDAO implements AdminInterface{
	Connection con;
	Scanner sc;
	List<Products> list;
//	int quantity;
//	int price;
	int totalAmount;
	
	ConnectionCreated obj= new ConnectionCreated();
	
	// method implementation to add products
	public String insertProduct() {
		
		try {
		     
		      con=obj.getConnection();
		
		    //System.out.println("Connection created");
		
		sc = new Scanner(System.in);
		System.out.println("Enter Product name");
		String name= sc.nextLine();
		System.out.println("Enter Product Description:");
		String desc= sc.nextLine();
		System.out.println("Enter Product Price:");
		int price= sc.nextInt();
		System.out.println("Enter Product Quntity:");
		int quentity= sc.nextInt();
	String query="Insert into products (productName,productDescription,productPrice,quantity) values (?,?,?,?)";	
    PreparedStatement ps= con.prepareStatement(query);
    ps.setString(1,name);
    ps.setString(2,desc);
    ps.setInt(3, price);
    ps.setInt(4, quentity);
    
    
    ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
   finally {
	   try {
	   con.close();
	    sc.close();
	   }
	   catch(SQLException e){
		   e.printStackTrace();
	   }
   }
    
	return "product inserted succesfully";
		
	}
	
	
// method to calculate bill
	public int calculateBill()  {
		
		try {
		 con=obj.getConnection();
		
		String query="select * from cartdeatils";	
	    PreparedStatement ps= con.prepareStatement(query);
	    ResultSet rs=  ps.executeQuery();
	    
	    	
	    ProductList plist= new ProductList();
	    System.out.println(".......Bill........");
	    while(rs.next()) {
	    	int productId=rs.getInt("productId");
	    	int quantity=rs.getInt("quantity");
	    	list =plist.getProductList();
	    	for(Products p:list) {
	    		if(p.getProductId()==productId) {
	    			int price=p.getProductPrice();
	    			totalAmount= totalAmount+quantity* price;
	    		}
	    	}
	    }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
			con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return totalAmount;
		
	}

// method implementation to display amount to user
	@Override
	public int DisplayAmountToUser() {
	 int amount = calculateBill();
		return amount;
	}

// method implementation to check product quantity
	@Override
	public void checkQuantity() {
		
			 ProductList plist= new ProductList();
		    	 list =plist.getProductList();
		    Map<Integer,Integer> map= new HashMap<>();	 
		    	 for(Products p :list) {
		    		 map.put(p.getProductId(),p.getQuantity());
		    	 }
		    sc= new Scanner(System.in);
		    System.out.println("Enter the product id: ");
		    int pId=sc.nextInt();
		        for(Map.Entry<Integer,Integer> entry:map.entrySet()) {
		        	
		        	
		        	while(entry.getKey()==pId) {
		        		System.out.println("Quantity is:"+entry.getValue());
		        		break;
		        	}

		        }	   
	}


	

	// Method implementation to check registered user
	
	@Override
	public void checkRegistedUser() {
		
		try {
			 con=obj.getConnection();
			
			String query="select * from user";	
		    PreparedStatement ps= con.prepareStatement(query);
		    ResultSet rs=  ps.executeQuery();
		    while(rs.next()) {
		    	System.out.println("Username: "+rs.getString("username"));
		    	System.out.println("First name: "+rs.getString("firstName"));
		    	System.out.println("Last name: "+rs.getString("lastName"));
		    	System.out.println("Email Id: "+rs.getString("mailId"));
		    	System.out.println("Mobile: "+rs.getString("mobileNumber"));
		    	System.out.println("city: "+rs.getString("city"));
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
		
		
		
	}

// method to check the particular user history
	@Override
	public void checkUserHistory() {
		
		try {
			 con=obj.getConnection();
			
			String query="select * from orderhistory";	
		    PreparedStatement ps= con.prepareStatement(query);
		    ResultSet rs=  ps.executeQuery();
		    System.out.println("Enter the user id");
		    sc= new Scanner(System.in);
		    int userId= sc.nextInt();
		   
		    while(rs.next()) {  
		    	
		    	System.out.println("Order Id: "+rs.getInt("orderId"));
		    	if(rs.getInt("userId")==userId) {
		    	  System.out.println("Product Id: "+rs.getInt("productId"));
		    	    
		    	  ProductList plist= new ProductList();
			    	list =plist.getProductList();
			    	for(Products p:list) {
			    		if(p.getProductId()==rs.getInt("productId")) {
			    			System.out.println("Product Description: "+p.getProductDescription());
			    		}
			    	}
			    	System.out.println("Quantity: " +rs.getInt("quantity"));
			    	System.out.println(".....");
		    	}
		    }
//		    int amount = calculateBill();
//		    System.out.println("Amount: "+amount);
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

}
