package com.revature.login;

import java.util.Scanner;

import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.services.EmployeeService;
import com.revature.services.UserService;

public class CreateAccount {

	public static void createAccount() {
		Scanner scan = new Scanner(System.in);
		UserService us = new UserService();
		System.out.println("Please enter a user name for your account.");
		String user_name = scan.nextLine();
		System.out.println("Please enter a password for your account");
		int user_password = scan.nextLine().hashCode();
		System.out.println("If you would like to enter your first name do so now or press enter to continue.");
		String first_name = scan.nextLine();
		System.out.println("If you would like to enter your last name do so now or press enter to continue.");
		String last_name = scan.nextLine();
		User usr = new User(user_name,user_password, first_name, last_name);
		us.createUser(usr);

	}
}