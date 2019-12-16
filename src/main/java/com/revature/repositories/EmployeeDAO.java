package com.revature.repositories;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.User;

public interface EmployeeDAO{

	public List<Employee> findAll();
	public User findById(int id);
	public boolean approveAccount(User e);
	public boolean denyAccount(User e);
}
