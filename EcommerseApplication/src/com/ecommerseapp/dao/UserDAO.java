package com.ecommerseapp.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ecommerseapp.connection.ConnectionCreated;
import com.ecommerseapp.interfaces.UserInterface;
import com.ecommerseapp.products.ProductList;

import com.ecommerseapp.products.Products;
import com.ecommerseapp.products.UserOrderInput;


public class UserDAO implements UserInterface{
	Connection con;
	Scanner sc;
	ConnectionCreated obj= new ConnectionCreated();
	
	
	
// method to register User	
	public String userRegistration()  {
		
		try {
		 con=obj.getConnection();
		
		 sc = new Scanner(System.in);
		System.out.println("Enter First name");
		String fName= sc.next();
		System.out.println("Enter Last Name:");
		String lName= sc.next();
		System.out.println("Enter username:");
		String username= sc.next();
		System.out.println("Enter password:");
		String password= sc.next();
		System.out.println("Enter city:");
		String city= sc.next();
		System.out.println("Enter mailId:");
		String mailId= sc.next();
		System.out.println("Enter Mobile Number:");
		String mobile= sc.next();
		
	String query="Insert into user (firstName,lastName,username,password,city,mailId,mobileNumber)values (?,?,?,?,?,?,?)";	
    PreparedStatement ps= con.prepareStatement(query);
	    ps.setString(1,fName);
	    ps.setString(2,lName);
	    ps.setString(3,username);
	    ps.setString(4,password);
	    ps.setString(5,city);
	    ps.setString(6,mailId);
	    ps.setString(7,mobile);
        
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
	    	catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    }
			
		return "Registered Successfully";
	}
	
	// method to implement user login
   public String userLogin()  {
	try {
		con=obj.getConnection();
		 sc = new Scanner(System.in);
		System.out.println("Connection created successfully");
		System.out.println("Enter username:");
		String username= sc.next();
		System.out.println("Enter password:");
		String password= sc.next();
		
		String query="select  username,password,userId from user";	
	    PreparedStatement ps= con.prepareStatement(query);
	    ResultSet rs=  ps.executeQuery();
	       while(rs.next()) {
	    	 if(rs.getString(1).equals(username) && rs.getString(2).equals(password)) {
	    	 System.out.println("Your userId is: "+rs.getInt(3));
	    		break;
	    	}
	      }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
	           sc.close();
		       con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return " login Sucessfully";	
	}
 
 // method implementation to view products in sorted form
 
 public void viewProductItem()  {
	 	 
	 try {
			con=obj.getConnection();
			
			String query="select * from products order by productName ";	
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
			}catch(Exception e) {
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
	  
 
	
 
 // method to buy product
 public void buyProduct()  {
	 int orderId=0;
		try {
		con=obj.getConnection();
		sc = new Scanner(System.in);
		
		System.out.println("Enter your user Id:");
		int userId= sc.nextInt();
		
		
	String query1="insert into orderdeatils (userId) values (?)";	
	    PreparedStatement ps1= con.prepareStatement(query1);
	    ps1.setInt(1, userId);
	      ps1.executeUpdate();
	   String query2="select  orderId,userId from orderdeatils ";	
		    PreparedStatement ps2= con.prepareStatement(query2);
		    ResultSet rs2=  ps2.executeQuery(); 
		while(rs2.next()) {
			if(rs2.getInt(2)==userId) {
				orderId=rs2.getInt(1);
			}
		}
		System.out.println("OrderId: "+orderId);
	     UserOrderInput obj= new UserOrderInput(); 
	     
	     Map<Integer,Integer> map=  obj.userOrderInput();
	   
	   //System.out.println(map);
	   for(Map.Entry<Integer, Integer> entry:map.entrySet()) {
		   int pId=entry.getKey();
		   int quantity=entry.getValue();
		  // System.out.println(pId);
		  // System.out.println(quantity);
		   String query3="insert into cartdeatils (orderId,productId,quantity)  values (?,?,?)";	
		    PreparedStatement ps3= con.prepareStatement(query3);
		    ps3.setInt(1, orderId);
		    ps3.setInt(2, pId);
		    ps3.setInt(3, quantity);
		    
		      ps3.executeUpdate();
		 
	   }
	   System.out.println("Product Items added to cart");
	    System.out.println("Do you want to view cart (Yes/No)");
	     String input= sc.next();
	       if(input.equalsIgnoreCase("Yes")) {
	    	 System.out.println("CART:");
	    	 
	    	 
	    	 String query4="select * from  cartdeatils";	
			    PreparedStatement ps4= con.prepareStatement(query4);
		        ResultSet rs4=	ps4.executeQuery();
			    while(rs4.next()) {
			    	if(rs4.getInt(2)==orderId) {
			    		System.out.println("ProductId: "+rs4.getInt(3)+"-->Quantity: "+rs4.getInt(4));
			    	}
			    	
			    } 
	      }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
	        try {
	           sc.close();
		       con.close();
	        }
	        catch(SQLException e) {
	        	e.printStackTrace();
	        }
		}
	}
// method implementation to display cart
   public void displayCart()  {
	   System.out.println("......CART:....... ");
		try {
		 con=obj.getConnection();
		String query="select * from cartdeatils";	
	    PreparedStatement ps1= con.prepareStatement(query);
	    ResultSet rs1=  ps1.executeQuery();
	       while(rs1.next()) {
	    	   
	    	System.out.println("Product Id: "+rs1.getInt(3));
	    	System.out.println("Quantity: "+rs1.getInt(4));
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
   
 // method implementation to purchase the item
   public void purchaseTheItem() {
	   int orderId=0;
	   int userId=0;
	   String username=null;
		try {
		 con =obj.getConnection();
		String query="select * from cartdeatils";	
	    PreparedStatement ps1= con.prepareStatement(query);
	    ResultSet rs1=  ps1.executeQuery();
	       while(rs1.next()) {
	    	  orderId=rs1.getInt(2);
	      }
	       //System.out.println("Order id: "+orderId);
	       String query1="select * from orderdeatils";	
		    PreparedStatement ps2= con.prepareStatement(query1);
		    ResultSet rs2=  ps2.executeQuery();
		    while(rs2.next()) {
		    	  if(rs2.getInt("orderId")==orderId) {
		    		  userId=rs2.getInt("userId");
		    	  }
		      }
	       //System.out.println("UserId is: "+userId);
	       String query2="select * from user";	
		    PreparedStatement ps3= con.prepareStatement(query2);
		    ResultSet rs3=  ps3.executeQuery();
		    while(rs3.next()) {
		    	  if(rs3.getInt("userId")==userId) {
		    		  username=rs3.getString("username");
		    	  }
		      }
	       System.out.println("Username is: "+username);
	       
	       AdminDAO admin= new AdminDAO();
	       int TotalAmount=admin.DisplayAmountToUser();
	       System.out.println("Total Bill Amount:Rs "+TotalAmount);
	       
	       System.out.println("Payment successfull..Your order is placed");
	    
	       // update order history after order placed  
	       
	       String query4="select * from cartdeatils";	
		    PreparedStatement ps4= con.prepareStatement(query4);
		    ResultSet rs4=  ps4.executeQuery();
		    while(rs4.next()) {
		    	int productId=rs4.getInt(3);
		    	int quantity=rs4.getInt(4);	    	
		    	String query5="Insert into orderhistory (userId,orderId,productId,quantity)values (?,?,?,?)";	
		        PreparedStatement ps5= con.prepareStatement(query5);
		    	    ps5.setInt(1,userId);
		    	    ps5.setInt(2,orderId);
		    	    ps5.setInt(3,productId);
		    	    ps5.setInt(4,quantity);
		    	 ps5.executeUpdate();
		    	
		    	 System.out.println("product details Inserted into orderhistory table");
		}	    
		   // decrement in quantity of product in stock after successful order
	       int quantityInStock=0; 
	       ProductList obj= new ProductList();
	   List<Products>list=obj.getProductList();
	       String query6="select * from cartdeatils";	
		    PreparedStatement ps6= con.prepareStatement(query6);
		    ResultSet rs6=  ps6.executeQuery();
		       while(rs6.next()) {
		    	   int productId=rs6.getInt("productId");
		    	  int quantityPurchased=rs6.getInt("quantity");
		    	  
		    	  for(Products p:list) {
		    		  if(p.getProductId()==productId) {
		    			   quantityInStock=p.getQuantity();
		    		  }
		    		  
		    	  }
		    	 int updatedQuantity=quantityInStock-quantityPurchased;
		    	 String query7="update products set quantity=? where productId=?";
		    	 PreparedStatement ps7= con.prepareStatement(query7);
		    	 ps7.setInt(1,updatedQuantity );
		    	 ps7.setInt(2,productId);
		    int x=	 ps7.executeUpdate();
		         if(x==1) {
		    	 System.out.println(" product quantity updated into stock after order placed");
		         }
		      }
	       
		    // clear cart after order placed
//		       String query8="delete from cartdeatils where orderId=? ";
//		    	 PreparedStatement ps8= con.prepareStatement(query8);
//		    	 ps8.setInt(1,orderId);
//		    	int y= ps8.executeUpdate();	
//		    	
//		    	 if(y==1) {
//			    	 System.out.println(" cart cleared");
//			         }
		    	
	       
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
