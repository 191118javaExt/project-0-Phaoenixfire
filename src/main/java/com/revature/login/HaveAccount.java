package com.revature.login;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class HaveAccount {

	public static void haveAccount() {
		int control = 0;
		System.out.println("Do you have an account?");
		System.out.println("1. Yes");
		System.out.println("2. No");

		Scanner userInput = new Scanner(System.in);
		String savedInput = new String(userInput.nextLine());

		if (savedInput.equalsIgnoreCase("Yes") || savedInput.equalsIgnoreCase("n") || savedInput.charAt(0) == '1') {
			control = 1;
		} else if (savedInput.equalsIgnoreCase("No") || savedInput.equalsIgnoreCase("n")
				|| savedInput.charAt(0) == '2') {
			control = 2;
		} else {
			System.out.println("Invalid response");

		}
		switch (control) {
		case 0:
			haveAccount();
			break;

		case 1: // Call to login class.
			System.out.println("case 1");
			Login.loginAccount();
//			for (Account n : accounts) {
//				Login.loginAccount();
//			}
			break;

		case 2: // Call to create account
			System.out.println("case 2");
			CreateAccount.createAccount();
//			accounts.add(newAccount);
			haveAccount();
			break;
		}
	}
}
