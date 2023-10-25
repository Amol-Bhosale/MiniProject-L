package com.ecommerseapp.products;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserOrderInput {
	int pId;
	int quantity;
	Map<Integer,Integer> map= new HashMap<>();
	public Map<Integer,Integer> userOrderInput() {
		
	Scanner sc = new Scanner(System.in);
	
	
	
	
	System.out.println("Enter Product Id:");
	 pId= sc.nextInt();
	
	System.out.println("Enter Product Quntity:");
	 quantity= sc.nextInt();
	 
	 
	 map.put(pId, quantity);
	 
	 
	
	System.out.println("Do you want to purchase more item(yes/no)");
	String st = sc.next();
	if(st.equalsIgnoreCase("yes")) {
		
		userOrderInput();
		
	}
	
	return map;
	
	}
}
