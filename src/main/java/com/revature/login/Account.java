package com.revature.login;

import java.util.HashSet;
import java.util.Set;

public class Account {
	private String userName;
	private String password;
	public Set<Account> accounts = new HashSet<Account>();
	//Needs to be factory method of construction. Only creating new account when needed.
	
	
	public Account(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public Account() {
		super();
	
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

}
