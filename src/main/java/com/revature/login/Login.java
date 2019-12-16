package com.revature.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.util.ConnectionUtil;

public class Login {

	private static String login_name = "temp1";

	public static String getLogin_name() {
		return login_name;
	}

	public static void setLogin_name(String login_name) {
		Login.login_name = login_name;
	}

	private static int login_password;
	private static Logger logger = Logger.getLogger(Login.class);

	public static void loginAccount() {

		try (Connection conn = ConnectionUtil.getConnection()) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter your username:");
			login_name = sc.nextLine();
			System.out.println("Enter your password:");
			login_password = sc.nextLine().hashCode();
			String sql = "select * from users WHERE user_name = ? AND user_password = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, login_name);
			stmt.setInt(2, login_password);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("user_password") == login_password) {
					System.out.println("you are now logged in");
				} else if (rs.getInt("user_password") != login_password) {
					System.out.println("Login failed. Press 1 to try again. Press 2 to return to main menu.");
					if (sc.nextLine() == "1") {
						loginAccount();
					} else if (sc.nextLine() == "2") {
						CreateAccount.createAccount();
					}
				}
			}
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.warn("Unable to find Account", e);
		}

	}

}
