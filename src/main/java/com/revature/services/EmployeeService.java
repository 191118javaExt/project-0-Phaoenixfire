package com.revature.services;

import java.util.List;


import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeDAOImpl;

public class EmployeeService {
	
	EmployeeDAO repository = new EmployeeDAOImpl();

	public List<Employee> findAll() {
		return repository.findAll();
	}
	public User findByUserName(String username) {
		return repository.findByUserName(username);
	}
	
	public boolean approveAccount(int user_id, String account_type) {
		return repository.approveAccount(user_id, account_type);
	}
	public boolean denyAccount(int user_id, String account_type) {
		return  repository.denyAccount(user_id, account_type);
	}

}
