package com.revature;

import java.util.Scanner;

public class CheckAccountType {

	public static String checkAccountType() {
		String accountType;
		Scanner sc = new Scanner(System.in);

		System.out.println("what type of account are you trying to access Checking/Savings?");
		accountType = sc.nextLine();
		if (accountType.charAt(0) == 'C' || accountType.charAt(0) == 'c') {

			return "Checking";
		} else if (accountType.charAt(0) == 'S' || accountType.charAt(0) == 's') {

			return "Savings";
		} else
			System.out.println("Invalid account Type Please try again");
		checkAccountType();
		return null;
	}
}
