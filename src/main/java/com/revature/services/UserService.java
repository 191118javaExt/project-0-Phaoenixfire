package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class UserService {

	UserDAO repository = new UserDAOImpl();

	public boolean deposit(int deposit,String accountType, String username) {
		return repository.deposit(deposit,accountType,username);
	}

	public boolean withdrawal(int withdraw,String accountType, String username) {
		return repository.withdrawal(withdraw,accountType,username);

	}

	public boolean transfer(String account_type, String user_name, int transferAmt) {
		return repository.transfer(account_type,user_name, transferAmt);
	}

	public boolean createAccount() {
		return repository.createAccount();
	}

	public boolean createUser(User u) {
		return repository.createUser(u);
	}
	public boolean logIn(String userName, int password) {
		return repository.logIn(userName, password);
	}
	public boolean accountApproved(String username, String account_type) {
		return repository.accountApproved(username,account_type);
	}
	public User updateUser(User u) {
		return repository.updateUser(u);
	}
	public boolean checkFunds(String account_type,String user_name, int withdraw) {
		return repository.checkFunds(account_type,user_name, withdraw);
	}
}
