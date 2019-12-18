package com.revature;

import java.util.Scanner;

import com.revature.models.User;
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
		System.out.println("5. Update your information");
		System.out.println("6. Log off.");

		String userInput = sc.nextLine();

		switch (userInput) {

		case "1":
			String accountType = CheckAccountType.checkAccountType();
			if (us.accountApproved(Login.getLogin_name(), accountType) == true) {
				int depositAmt = userInputs.getNumber("deposit");
				us.deposit(depositAmt, accountType, Login.getLogin_name());
				loggedIn();
			} else {
				System.out.println("Account Still pending approval. Check back later.");
				loggedIn();
			}
			break;

		case "2":
			accountType = CheckAccountType.checkAccountType();
			if (us.accountApproved(Login.getLogin_name(), accountType) == true) {
				int withdraw = userInputs.getNumber("withdrawakl");
				if (us.checkFunds(accountType, Login.getLogin_name(), withdraw) == true) {
					us.withdrawal(withdraw, accountType, Login.getLogin_name());

				}
				loggedIn();
			} else {
				System.out.println("Account Still pending approval. Check back later.");
				loggedIn();
			}
			break;

		case "3":
			accountType = CheckAccountType.checkAccountType();
			if (us.accountApproved(Login.getLogin_name(), accountType) == true) {
				int transferAmt = userInputs.getNumber("transfer");
				if (us.checkFunds(accountType, Login.getLogin_name(), transferAmt) == true) {
					us.transfer(accountType, Login.getLogin_name(), transferAmt);
				}
			} else {
				System.out.println("Account Still pending approval. Check back later.");
				loggedIn();
			}
			loggedIn();
			break;

		case "4":
			us.createAccount();
			loggedIn();
			break;

		case "5":
			System.out.println("Please enter a user name for your account.");
			String user_name = sc.nextLine();
			System.out.println("Please enter a password for your account");
			int user_password = sc.nextLine().hashCode();
			System.out.println("If you would like to enter your first name do so now or press enter to continue.");
			String first_name = sc.nextLine();
			System.out.println("If you would like to enter your last name do so now or press enter to continue.");
			String last_name = sc.nextLine();
			User u = new User(user_name, user_password, first_name, last_name);
			us.updateUser(u);
			loggedIn();
			break;

		case "6":
			System.out.println("Goodbye " + Login.getLogin_name());
			Login.setLogin_name(null);
			HaveAccount.haveAccount();
			break;
		}
	}
}
