package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Account;
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
				int id = rs.getInt("user_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String user_name = rs.getString("user_name");
				int user_password = rs.getInt("user_password");
				boolean user_employee = rs.getBoolean("is_employee");
				boolean user_admin = rs.getBoolean("is_admin");
				Employee e = new Employee(id, user_name, user_password, first_name, last_name, user_employee,
						user_admin);
				System.out.println(e.toString());
				list.add(e);
			}
			
			rs.close();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all users", e);
		}
		return list;

	}

	@Override
	public User findByUserName(String username) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "Select * from users inner join accounts "
					+ "on users.user_id = accounts.owner_id where users.user_name = '" + username + "';";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				String user_name = rs.getString("user_name");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				boolean is_employee = rs.getBoolean("is_employee");
				boolean is_admin = rs.getBoolean("is_admin");
				User u = new User(user_id, user_name, first_name, last_name, is_employee, is_admin);
				
				System.out.println(u.toString());
				findAccountByUserId(user_id);
				return u;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean approveAccount(int user_id, String account_type) {

		try (Connection conn = ConnectionUtil.getConnection()) {
			
			// Isn't working yet
			String sql = "Update accounts set account_approved = true where owner_id = " + user_id + ""
					+ " and account_type = '" + account_type + "';";
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean denyAccount(int user_id, String account_type) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			// Isn't working yet
			String sql = "Update accounts set account_approved = false where owner_id = " + user_id
					+ " and account_type = '" + account_type + "';";
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Account findAccountByUserId(int user_id) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "Select * from accounts where owner_id = " + user_id + ";";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				int account_id = rs.getInt("account_id");
				int owner_id = rs.getInt("owner_id");
				int account_balance = rs.getInt("account_balance");
				String account_type = rs.getString("account_Type");
				boolean account_approved = rs.getBoolean("account_approved");
				Account a = new Account(account_id,owner_id,account_balance,account_type,account_approved);
				
				System.out.println(a.toString());
	
				return a;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
