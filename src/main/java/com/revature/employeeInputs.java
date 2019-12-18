package com.revature;

import java.util.Scanner;

public class employeeInputs {

	public static int getNumber(String transactionType) {
		Scanner sc = new Scanner(System.in);
		String userInput;
		int numberTested = 0;
		switch (transactionType) {

		case "deposit": {
			System.out.println("Please enter the number you are trying to deposit. Or type exit to quit");
			userInput = sc.nextLine().trim();
			System.out.println(userInput);
			if (userInput.equals("exit")) {
				EmployeeLoggedIn.loggedIn();
			} else {
				for (int i = 1; i < userInput.length(); i++) {
					if (Character.isDigit(i)) {
						System.out.println("invalid input please enter a number.");
						getNumber(transactionType);
					}
				}
				
				numberTested = Integer.parseInt(userInput);
				return numberTested;

			}

		}
		case "withdrawal": {
			System.out.println("Please enter the number you are trying to withdraw. Or type exit to quit");
			userInput = sc.nextLine().trim();
			if (userInput.equals("exit")) {
				EmployeeLoggedIn.loggedIn();
			} else {
				for (int i = 0; i < userInput.length(); i++) {
					if (Character.isDigit(i)) {
						System.out.println("invalid input please enter a number.");
						getNumber(transactionType);
					}
				}
				numberTested = Integer.parseInt(userInput);
				return numberTested;

			}

		}
		case "transfer": {
			System.out.println("Please enter the number you are trying to transfer. Or type exit to quit");
			userInput = sc.nextLine().trim();
			if (userInput.equals("exit")) {
				EmployeeLoggedIn.loggedIn();
			} else {
				for (int i = 0; i < userInput.length(); i++) {
					if (Character.isDigit(i)) {
						System.out.println("invalid input please enter a number.");
						getNumber(transactionType);
					}
				}
				numberTested = Integer.parseInt(userInput);
				return numberTested;

			}

		}

		}
		return 0;
	}
}
