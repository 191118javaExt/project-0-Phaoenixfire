package com.revature;

import java.util.Scanner;

import com.revature.services.UserService;

public class Login {

	private static String login_name;

	public static String getLogin_name() {
		return login_name;
	}

	public static void setLogin_name(String login_name) {
		Login.login_name = login_name;
	}

	public static void loginAccount() {

		UserService us = new UserService();
		Scanner sc = new Scanner(System.in);
		System.out.println("What is your username?");
		String login_name = sc.nextLine();
		System.out.println("What is your password");
		String password = sc.nextLine();
		int hashPass = password.hashCode();
		us.logIn(login_name, hashPass);

	}

}
