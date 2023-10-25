package com.ecommerseapp.main.test;

import java.util.Scanner;
import com.ecommerseapp.dao.AdminDAO;
import com.ecommerseapp.dao.GuestDAO;
import com.ecommerseapp.dao.UserDAO;

public class MainTest {
public static void main(String[] args)  {
		
		System.out.println("Welcome to E-Commerse based application \n");
		System.out.println("User Operation");
		System.out.println("\t 1.User Registration");
		System.out.println("\t 2.User Login");
		System.out.println("\t 3.User view Product item as Sorted Order");
		System.out.println("\t 4.Buy Product");
		System.out.println("\t 5.View Cart");
		System.out.println("\t 6.Purchase the Item");
		System.out.println();
		System.out.println("Admin Operation");	
		System.out.println("\t 7.Add Product Item");
		System.out.println("\t 8.Calculate Bill");
		System.out.println("\t 9.Display Amount to end user");
		System.out.println("\t 10.Check Quantity");
		System.out.println("\t 11.Check Registered User");
		System.out.println("\t 12.Check the particular User history");
		System.out.println();
		System.out.println("Guest Operation");
		System.out.println("\t 13.view product item");
		System.out.println("\t 14.Not purchase Item");
	
		// user input to enter the choice
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your Choice- ");
		int choice = scanner.nextInt();
		
		AdminDAO admin= new AdminDAO();
		UserDAO user = new UserDAO();
		GuestDAO guest = new GuestDAO();
		
		
		switch (choice) { 
		
        case 1 :	String result=user.userRegistration();
   		            System.out.println(result);
   		            break;
   		            
        case 2 :	String result1=user.userLogin();
		            System.out.println(result1);
                    break;   
                    
        case 3 :    user.viewProductItem();
                    break;
                    
        case 4 :    user.buyProduct();
                    break;            
         
        case 5 :    user.displayCart();
                    break;
                    
        case 6 :    user.purchaseTheItem();
        			break;  
        			
        case 7 :    String result2=admin.insertProduct();
		            System.out.println(result2);
		            break;
		            
        case 8 :    int totalAmount=admin.calculateBill();
					if(totalAmount!=0) {
						System.out.println("Total amount is: Rs."+totalAmount);
					}else {
						System.out.println("Your cart is empty");
					}
					break;
   		            
        case 9 :    int totalAmount1=admin.DisplayAmountToUser();
		            if(totalAmount1!=0) {
			        System.out.println("Total amount is: Rs."+totalAmount1);
			        }else {
			        System.out.println("Your cart is empty");
			        }
		            break;
		            
        case 10 :   admin.checkQuantity();
        			break;
        			
        case 11 :   admin.checkRegistedUser();
		            break; 
		            
        case 12 :   admin.checkUserHistory();
                    break; 
                    
        case 13 :   guest.viewProductItem();
                    break;
              
        case 14 : String st=  guest.notPurchaseItem();
                  System.out.println(st);
                  break;     
         
         default : System.out.println("Invalid chioce. Enter choice between 1 to 14");        
                               
		
		}
	}
}	
		
		

	

	





