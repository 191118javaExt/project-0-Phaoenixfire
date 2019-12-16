package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static Logger logger = Logger.getLogger(EmployeeDAOImpl.class);

	@Override
	public List<Employee> findAll() {

		List<Employee> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM users;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("emp_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String user_name = rs.getString("user_name");
				int user_password = rs.getInt("user_password");
				boolean user_employee = rs.getBoolean("is_employee");
				boolean user_admin = rs.getBoolean("is_admin");
				Employee e = new Employee(id, user_name, user_password, first_name, last_name, user_employee,
						user_admin);
				list.add(e);
			}

			rs.close();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all users", e);
		}
		return list;

	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public boolean insert(User e) {
//		try (Connection conn = ConnectionUtil.getConnection()) {
//
//			String sql = "INSERT INTO users (first_name, last_name, email, salary, supervisor) "
//					+ "VALUES (?, ?, ?, ?, ?);";
//
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setString(1, e.getFirst_name());
//			stmt.setString(2, e.getLast_name());
//
//			if (!stmt.execute()) {
//				return false;
//			}
//		} catch (SQLException ex) {
//			logger.warn("Unable to retrieve all users", ex);
//			return false;
//		}
//
//		return true;
//	}

	@Override
	public boolean approveAccount(User e) {

		
		return false;
	}

	@Override
	public boolean denyAccount(User e) {
		// TODO Auto-generated method stub
		return false;
	}
}
