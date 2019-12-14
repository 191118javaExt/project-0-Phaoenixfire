package com.revature.login;

import java.util.Scanner;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;

public class CreateAccount {

	public static void createAccount() {
		Scanner scan = new Scanner(System.in);
		EmployeeService es = new EmployeeService();
		System.out.println("Please enter your first name");
		String first_name = scan.nextLine();
		System.out.println("Please enter your last name");
		String last_name = scan.nextLine();
		System.out.println("Please enter your email");
		String email = scan.nextLine();
		Employee emp = new Employee(0, first_name, last_name, email, 300 , null);
		es.insert(emp);
//		Account account = new Account();
//		account.setUserName(scan.nextLine());
//
//		System.out.println("Please Enter a Password");
//		account.setPassword(scan.nextLine());
//		return account;

	}
}