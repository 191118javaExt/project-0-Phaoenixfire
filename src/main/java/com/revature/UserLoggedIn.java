package com.revature;

import java.util.Scanner;

import com.revature.services.UserService;

public class UserLoggedIn {

	public static void loggedIn() {
		UserService us = new UserService();
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome User " + Login.getLogin_name());
		System.out.println("What would you like to do today");
		System.out.println("1. Make a deposit.");
		System.out.println("2. Make a withdrawal.");
		System.out.println("3. Transfer money.");
		System.out.println("4. Create an account.");
		System.out.println("5. Log off.");

		String userInput = sc.nextLine();

		switch (userInput) {

		case "1":
			System.out.println("What account would you like to deposit to Checking/Savings");
			String accountType = sc.nextLine();
			if (us.accountApproved(Login.getLogin_name(), accountType) == true) {
				System.out.println("How much would you like to deposit?");
				String depositAmt = sc.nextLine();
				int depositInt = Integer.parseInt(depositAmt);

				us.deposit(depositInt, accountType, Login.getLogin_name());
				loggedIn();
			} else {
				System.out.println("Account Still pending approval. Check back later.");
				loggedIn();
			}
			break;

		case "2":
			System.out.println("What account would you like to withdraw from Checking/Savings");
			accountType = sc.nextLine();
			if (us.accountApproved(Login.getLogin_name(), accountType) == true) {
				System.out.println("How much would you like to withdraw?");
				String withdrawAmt = sc.nextLine();
				int withdraw = Integer.parseInt(withdrawAmt);
				us.withdrawal(withdraw, accountType, Login.getLogin_name());
				loggedIn();
			} else {
				System.out.println("Account Still pending approval. Check back later.");
				loggedIn();
			}
			break;

		case "3":
			us.transfer();
			loggedIn();
			break;

		case "4":
			us.createAccount();
			loggedIn();
			break;

		case "5":
			System.exit(0);
			break;
		}
	}
}
