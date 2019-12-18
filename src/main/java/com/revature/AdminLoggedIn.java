package com.revature;

import java.util.Scanner;

import com.revature.services.EmployeeService;
import com.revature.services.UserService;

public class AdminLoggedIn {

	public static void loggedIn() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome Employee " + Login.getLogin_name());
		System.out.println("What would you like to do today");
		System.out.println("1. Make a deposit.");
		System.out.println("2. Make a withdrawal.");
		System.out.println("3. Transfer money.");
		System.out.println("4. Find all users.");
		System.out.println("5. Find a users account information.");
		System.out.println("6. Approve an account.");
		System.out.println("7. Deny an account.");
		System.out.println("8. Create an account.");
		System.out.println("9. Log off.");
		EmployeeService es = new EmployeeService();
		UserService us = new UserService();

		String userInput = sc.nextLine();

		switch (userInput) {

		case "1":
			System.out.println("How much would you like to deposit?");
			String depositAmt = sc.nextLine();
			int depositInt = Integer.parseInt(depositAmt);
			System.out.println("What account would you like to deposit to Checking/Savings?");
			String accountType = sc.nextLine();
			System.out.println("What username are you depositing into today?");
			String userName = sc.nextLine();
			us.deposit(depositInt, accountType, userName);
			loggedIn();
			break;

		case "2":
			System.out.println("What account would you like to withdraw from Checking/Savings?");
			accountType = sc.nextLine();
			if (us.accountApproved(Login.getLogin_name(), accountType) == true) {
				System.out.println("How much would you like to withdraw?");
				String withdrawAmt = sc.nextLine();
				int withdraw = Integer.parseInt(withdrawAmt);
				System.out.println("What username are you trying to withdraw from?");
				String accountUser = sc.nextLine();
				if (us.checkFunds(accountType, accountUser, withdraw) == true) {
					us.withdrawal(withdraw, accountType, Login.getLogin_name());
				}
				loggedIn();
			} else {
				System.out.println("Account Still pending approval. Check back later.");
				loggedIn();
			}
			break;

		case "3":
			System.out.println("Which account are you transfering from Checking/Savings?");
			accountType = sc.nextLine();
			System.out.println("How much would you like to transfer?");
			String transferAmt = sc.nextLine();
			System.out.println("What username are you trying to transfer funds from?");
			String accountUser = sc.nextLine();
			if (us.checkFunds(accountType, accountUser, Integer.parseInt(transferAmt)) == true) {
				us.transfer(accountType, accountUser, Integer.parseInt(transferAmt));
			}
			loggedIn();
			break;
		case "4":
			es.findAll();
			loggedIn();
			break;

		case "5":
			System.out.println("What is the users Username?");
			es.findByUserName(sc.nextLine());
			loggedIn();
			break;

		case "6":
			System.out.println("What is the users Id number.");
			int user_id = Integer.parseInt(sc.nextLine());
			System.out.println("Which account do you want to approve Checking/Savings?");
			accountType = sc.nextLine();
			es.approveAccount(user_id, accountType);

			loggedIn();
			break;

		case "7":
			System.out.println("What is the users Id number");
			user_id = Integer.parseInt(sc.nextLine());
			System.out.println("Which account do you want to deny Checking/Savings?");
			accountType = sc.nextLine();
			es.denyAccount(user_id, accountType);

			loggedIn();
			break;

		case "8":
			us.createAccount();
			loggedIn();
			break;

		case "9":
			System.out.println("Goodbye " + Login.getLogin_name());
			Login.setLogin_name(null);
			HaveAccount.haveAccount();
			break;
		}
	}
}
