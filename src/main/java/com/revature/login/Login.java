package com.revature.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.util.ConnectionUtil;

public class Login {

	private static String first_name;
	private static Logger logger = Logger.getLogger(Login.class);

	public static void loginAccount() {

		try (Connection conn = ConnectionUtil.getConnection()) {

			Scanner scan = new Scanner(System.in);
			System.out.println("What is your first name?");
			String query = scan.nextLine();
			String sql = "SELECT * FROM employee WHERE first_name = '" + query.toString() + "'";
			System.out.println(sql);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			first_name = rs.getString("first_name");
			System.out.println(first_name);
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.warn("Unable to find Username");
		}

	}

}
