package com.revature.repositories;

import com.revature.models.User;

public interface UserDAO {

	public boolean deposit(int deposit,String accountType, String username);
	public boolean withdrawal(int withdraw,String accountType, String username);
	public boolean transfer(String account_type,String user_name, int transferAmt);
	public boolean createUser(User u);
	public boolean createAccount();
	public boolean logIn(String username, int password);
	public boolean employeeCheck();
	public boolean accountApproved(String username, String account_type);
	public User updateUser(User u);
	public boolean checkFunds(String account_type,String user_name, int withdraw);
}
