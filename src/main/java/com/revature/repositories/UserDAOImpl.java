package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.login.Login;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO {

	private static Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Override
	public boolean deposit() {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql;
			System.out.println("How much would you like to deposit?");
			Scanner sc = new Scanner(System.in);
			String depositAmt = sc.nextLine();
			int depositInt = Integer.parseInt(depositAmt);
			System.out.println("What account would you like to deposit to Checking/Savings");
			String accountType = sc.nextLine();
			sql = "Update accounts set account_balance = account_balance + ? FROM users "
					+ " where owner_id = users.user_id AND accounts.account_type = ?" + " AND users.user_name = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, depositInt);
			stmt.setString(2, accountType);
			stmt.setString(3, Login.getLogin_name());
			if (!stmt.execute()) {
				return false;
			}

			stmt.close();
		} catch (SQLException e) {
			logger.warn("Jee Wilikers Batman we're not gonna pass", e);
		}

		return true;
	}

	@Override
	public boolean withdrawal() {
		try (Connection conn = ConnectionUtil.getConnection()) {

			int account_balance = 0;
			String sql = "select users.user_name, users.first_name, users.last_name, accounts.account_balance,"
					+ " accounts.account_type from users INNER JOIN accounts "
					+ "On user_id = owner_id WHERE user_name= ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, Login.getLogin_name());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				account_balance = rs.getInt("account_balance");
			}
			System.out.println("How much would you like to withdraw?");
			Scanner sc = new Scanner(System.in);
			String withdrawal = sc.nextLine();
			System.out.println(Integer.parseInt(withdrawal));
			System.out.println(account_balance);

			if (Integer.parseInt(withdrawal) > account_balance) {
				System.out.println("Cannot Process, exceeds current funds");
				return false;
			} else {

				int withdrawalInt = Integer.parseInt(withdrawal);
				System.out.println("What account would you like to withdraw from Checking/Savings");
				String accountType = sc.nextLine();
				sql = "Update accounts set account_balance = account_balance - ? FROM users "
						+ " where owner_id = users.user_id AND accounts.account_type = ?" + " AND users.user_name = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, withdrawalInt);
				stmt.setString(2, accountType);
				stmt.setString(3, Login.getLogin_name());
				if (!stmt.execute()) {
					return false;
				}
			}
			stmt.close();
		} catch (SQLException e) {
			logger.warn("Jee Wilikers Batman we're not gonna pass", e);
		}

		return true;
	}

	@Override
	public boolean transfer() {

		System.out.println("Press 1 to transfer money between your accounts.");
		System.out.println("Press 2 to transfer money to another account holder in a our bank.");
		Scanner sc = new Scanner(System.in);
		String userInput = sc.nextLine();
		switch (userInput) {

		case "1": {
			try (Connection conn = ConnectionUtil.getConnection()) {

				int account_balance = 0;
				String sql = "select users.user_name, users.first_name, users.last_name, accounts.account_balance,"
						+ " accounts.account_type from users INNER JOIN accounts "
						+ "On user_id = owner_id WHERE user_name= ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, Login.getLogin_name());
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					account_balance = rs.getInt("account_balance");
				}
				System.out.println("How much would you like to transfer?");
				String withdrawal = sc.nextLine();
				if (Integer.parseInt(withdrawal) > account_balance) {
					System.out.println("Cannot Process, exceeds current funds");
					return false;
				} else {

					int withdrawalInt = Integer.parseInt(withdrawal);
					System.out.println("What account would you like to Transfer from Checking/Savings");
					String accountType = sc.nextLine();
					String otherAccount;
					if (accountType.equals("Checking")) {
						otherAccount = "Savings";
					} else {
						otherAccount = "checking";
					}
					System.out.println(otherAccount);
					sql = "Update accounts set account_balance = account_balance - ? FROM users "
							+ " where owner_id = users.user_id AND accounts.account_type = ?"
							+ " AND users.user_name = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, withdrawalInt);
					stmt.setString(2, accountType);
					stmt.setString(3, Login.getLogin_name());

					stmt.addBatch();

					System.out.println("Now Transfering Your Money");
					sql = "Update accounts set account_balance = account_balance + ? FROM users "
							+ " where owner_id = users.user_id AND accounts.account_type = ?"
							+ " AND users.user_name = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, withdrawalInt);
					stmt.setString(2, otherAccount);
					stmt.setString(3, Login.getLogin_name());

					stmt.addBatch();

					stmt.executeBatch();

				}
				stmt.close();
			} catch (SQLException e) {
				logger.warn("Jee Wilikers Batman we're not gonna pass", e);
			}

			return true;

		}
		case "2": {

		}
			return true;
		}
		return false;
	}

	@Override
	public boolean closeAccount() {
		// TODO Auto-generated method stub
		return false;
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
			System.out.println("What kind of account would you like to make today Checking or Savings");
			String accountType = sc.nextLine();
			System.out.println("How much would you like for you initial deposit to be?");
			String initialDeposit = sc.nextLine();
			String sql = "Insert INTO accounts(owner_id, account_balance, account_type)" + 
					"VALUES((SELECT user_id from users where user_name = ?),?, ?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, Login.getLogin_name());
			stmt.setInt(2,Integer.parseInt(initialDeposit));
			stmt.setString(3, accountType);
			
			if(!stmt.execute()) {
				return false;
			}
			System.out.println("Account created. Awaiting administrator approval.");
		}
		catch(SQLException e){
			logger.warn("Jee Wilikers Batman we're not gonna pass", e);
		}
		return false;
	}

}