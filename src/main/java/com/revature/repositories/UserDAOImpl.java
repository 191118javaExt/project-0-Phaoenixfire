package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.AdminLoggedIn;
import com.revature.CheckAccountType;
import com.revature.EmployeeLoggedIn;
import com.revature.Login;
import com.revature.UserLoggedIn;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO {

	private static Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Override
	public boolean deposit(int deposit, String accountType, String username) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql;
			sql = "Update accounts set account_balance = account_balance + ? FROM users "
					+ " where owner_id = users.user_id AND accounts.account_type = ?" + " AND users.user_name = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, deposit);
			stmt.setString(2, accountType);
			stmt.setString(3, username);
			
			stmt.execute();
			
			stmt.close();
		} catch (SQLException e) {
			logger.warn("Jee Wilikers Batman we're not gonna pass", e);
		}
		System.out.println("Deposited " + deposit + " into your " + accountType + " account.");
		logger.info(deposit + " was added to " + username + "'s " + accountType + " account");
		return true;
	}

	@Override
	public boolean withdrawal(int withdraw, String accountType, String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "Update accounts set account_balance = account_balance - ? FROM users "
					+ " where owner_id = users.user_id AND accounts.account_type = ?" + " AND users.user_name = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, withdraw);
			stmt.setString(2, accountType);
			stmt.setString(3, username);
		
			stmt.execute();
			
			stmt.close();
		} catch (SQLException e) {
			logger.warn("Something went wrong", e);
		}
		System.out.println("Withdrawn " + withdraw + " from your " + accountType + " account.");
		logger.info(withdraw + " was removed from " + username + "'s " + accountType + " account");
		return true;
	}

	@Override
	public boolean transfer(String account_type, String user_name, int transferAmt) {

		String otherAccount;
		if (account_type.equals("Checking")) {
			otherAccount = "Savings";
		} else {
			otherAccount = "Checking";
		}
		withdrawal(transferAmt, account_type, user_name);
		deposit(transferAmt, otherAccount, user_name);

		return true;

	}

	@Override
	public boolean createUser(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT INTO users (user_name,user_password, first_name, last_name) " + "VALUES (?, ?, ?, ?);";

			// This PreparedStatement object is a wrapper around our SQL string
			// And is obtained through our connection to the database
			// And allows us to insert into the placeholders
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(3, u.getFirst_name());
			stmt.setInt(2, u.getUser_password());
			stmt.setString(4, u.getLast_name());
			stmt.setString(1, u.getUser_name());

			if (!stmt.execute()) {
				return false;
			}
		} catch (SQLException e) {
			logger.warn("Unable to create account", e);
			return false;
		}

		return true;
	}

	@Override
	public boolean createAccount() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			Scanner sc = new Scanner(System.in);
			String accountType = CheckAccountType.checkAccountType();
			System.out.println("How much would you like for you initial deposit to be?");
			String initialDeposit = sc.nextLine();
			String sql = "Insert INTO accounts(owner_id, account_balance, account_type)"
					+ "VALUES((SELECT user_id from users where user_name = ?),?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, Login.getLogin_name());
			stmt.setInt(2, Integer.parseInt(initialDeposit));
			stmt.setString(3, accountType);

			stmt.execute();
			System.out.println("Account created. Awaiting administrator approval.");
			UserLoggedIn.loggedIn();
		} catch (SQLException e) {
			logger.warn("Jee Wilikers Batman we're not gonna pass", e);
		}

		return true;
	}

	@Override
	public boolean logIn(String username, int password) {

		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "select * from users WHERE user_name = ? AND user_password = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setInt(2, password);
			ResultSet rs = stmt.executeQuery();
			int attemptedPass = 0;
			while (rs.next()) {
				attemptedPass = rs.getInt("user_password");
				Login.setLogin_id(rs.getInt("user_id"));
			}
			if (attemptedPass == password) {
				System.out.println("you are now logged in");
				Login.setLogin_name(username);
				employeeCheck();
				UserLoggedIn.loggedIn();
			} else if (attemptedPass != password) {
				System.out.println("Login failed.");
				Login.loginAccount();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.warn("Unable to find Account", e);
		}

		return false;
	}

	@Override
	public boolean employeeCheck() {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "Select * from users where user_name = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, Login.getLogin_name());
			ResultSet rs = stmt.executeQuery();
			boolean is_admin = false;
			boolean is_employee = false;
			while (rs.next()) {
				is_employee = rs.getBoolean("is_employee");
				is_admin = rs.getBoolean("is_admin");
			}

			if (is_admin == true) {
				System.out.println("Admin logged in");
				AdminLoggedIn.loggedIn();
			} else if (is_employee == true) {
				System.out.println("Employee logged in");
				EmployeeLoggedIn.loggedIn();
			} else {
				UserLoggedIn.loggedIn();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean accountApproved(String username, String account_type) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "Select account_approved from accounts inner join users on users.user_id = accounts.owner_id where user_name = ? and account_type = ?;";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, account_type);
			ResultSet rs = stmt.executeQuery();
			boolean isApproved = false;
			while (rs.next()) {
				isApproved = rs.getBoolean("account_approved");
			}
			return isApproved;

		} catch (SQLException e) {
			logger.warn("Something went wrong", e);
		}
		return false;
	}

	@Override
	public User updateUser(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "Update users set user_name = ?, user_password = ?, first_name = ?, last_name = ? "
					+ "where user_id = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getUser_name());
			stmt.setInt(2, u.getUser_password());
			stmt.setString(3, u.getFirst_name());
			stmt.setString(4, u.getLast_name());
			stmt.setInt(5, Login.getLogin_id());
			if (!stmt.execute()) {
				return u;
			}
		} catch (SQLException e) {
			logger.warn("Somethings wrong", e);
		}
		return u;
	}

	public boolean checkFunds(String account_type, String user_name, int withdraw) {
		try (Connection conn = ConnectionUtil.getConnection()) {

			int account_balance = 0;
			String sql = "select users.user_name, users.first_name, users.last_name, accounts.account_balance,"
					+ " accounts.account_type from users INNER JOIN accounts "
					+ "On user_id = owner_id WHERE user_name = ? and account_Type = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(2, account_type);
			stmt.setString(1, user_name);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				account_balance = rs.getInt("account_balance");
			}
			if (withdraw > account_balance) {
				System.out.println("Cannot Process, exceeds current funds");
				System.out.println("Current Balance is " + account_balance);
				return false;
			}

		} catch (SQLException e) {
			logger.warn("Something went wrong", e);
		}
		return true;
	}
}