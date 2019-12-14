package com.revature.repositories;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.User;

public interface UserDAO {

	public int deposit();
	public int withdrawal();
	public int transfer();
	public boolean closeAccount();
}
