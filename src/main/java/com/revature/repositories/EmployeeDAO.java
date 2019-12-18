package com.revature.repositories;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Employee;
import com.revature.models.User;

public interface EmployeeDAO{

	public List<Employee> findAll();
	public boolean approveAccount(int user_id, String account_type);
	public boolean denyAccount(int user_id, String account_type);
	User findByUserName(String username);
	Account findAccountByUserId(int user_id);
}
