package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class UserService {

	UserDAO repository = new UserDAOImpl();

	public boolean deposit() {
		return repository.deposit();
	}

	public boolean withdrawal() {
		return repository.withdrawal();

	}

	public boolean transfer() {
		return repository.transfer();
	}

	public boolean createAccount() {
		return repository.createAccount();
	}

	public boolean createUser(User u) {
		return repository.createUser(u);
	}

}
