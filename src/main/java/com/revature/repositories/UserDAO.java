package com.revature.repositories;

import com.revature.models.User;

public interface UserDAO {

	public boolean deposit();
	public boolean withdrawal();
	public boolean transfer();
	public boolean closeAccount();
	public boolean createUser(User u);
	boolean createAccount();
}
