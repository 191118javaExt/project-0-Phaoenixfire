package com.revature.login;

import java.util.Scanner;

import com.revature.services.UserService;

public class Driver {

	public static void main(String[] args) {

		System.out.println("Welcome to the banking application.");
		HaveAccount.haveAccount();

		// Will be moved to a functioning Loggedin class
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome User " + Login.getLogin_name());
		System.out.println("What would you like to do today");
		System.out.println("1. Make a deposit.");
		System.out.println("2. Make a withdrawal.");
		System.out.println("3. Transfer money.");
		System.out.println("4. Create an account.");
		System.out.println("5. Log off.");

		String userInput = sc.nextLine();
		UserService us = new UserService();
		switch (userInput) {

		case "1":
			us.deposit();
			break;

		case "2":
			us.withdrawal();
			break;

		case "3":
			us.transfer();
			break;

		case "4":
			us.createAccount();
			break;
			
		case "5":
			System.exit(0);
			break;
		}
	}
}
