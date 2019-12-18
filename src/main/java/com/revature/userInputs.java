package com.revature;

import java.util.Scanner;

public class userInputs {

	
	public static int getNumber(String transactionType) {
		Scanner sc = new Scanner(System.in);
		String userInput;
		int numberTested;
		switch(transactionType) {
		
		case "deposit":{
			System.out.println("Please enter the number you are trying to deposit. Or type exit to quit");
			userInput = sc.nextLine();
			if(userInput.equals("exit")) {
				
			}
			try { 
		        Integer.parseInt(userInput); 
		    } catch(NumberFormatException e) { 
		    	System.out.println("That isn't an accepted number. Please try again");
		    	getNumber(transactionType);
		        return 0; 
		    } catch(NullPointerException e) {
		    	System.out.println("That isn't an accepted number. Please try again");
		    	getNumber(transactionType);
		        return 0;
		    }
		   
		    return 0;
		}
		}
		return 0;
		
	}
}
