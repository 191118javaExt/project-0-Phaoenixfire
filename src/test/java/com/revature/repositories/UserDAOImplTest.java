package com.revature.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.Login;
import com.revature.models.User;
import com.revature.services.EmployeeService;

public class UserDAOImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Login.setLogin_id(1);
		User u = new User("Test", 1216985755, "Tester", "Mctesty");
		UserDAOImpl id = new UserDAOImpl();
		id.updateUser(u);
	}

	@After
	public void tearDown() throws Exception {
	}
	@Ignore
	@Test
	public void testDeposit() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testWithdrawal() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testLogIn() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUserName() {
		User u2 = new User("Superhero", 12345, "John", "Wick");
		UserDAOImpl id = new UserDAOImpl();
		EmployeeService es = new EmployeeService();
		u2 = id.updateUser(u2);
		User u = es.findByUserName("Superhero");
		assertEquals(u.getUser_name(), u2.getUser_name());
		
	}

	@Test
	public void testUpdateUserPassword() {
		User u2 = new User("Superhero", 12345, "John", "Wick");
		UserDAOImpl id = new UserDAOImpl();
		EmployeeService es = new EmployeeService();
		u2 = id.updateUser(u2);
		User u = es.findByUserName("Superhero");
		assertNotEquals(u.getUser_password(), u2.getUser_password());
		
	}
	@Test
	public void testUpdateUserFirstName() {
		User u2 = new User("Superhero", 12345, "John", "Wick");
		UserDAOImpl id = new UserDAOImpl();
		EmployeeService es = new EmployeeService();
		u2 = id.updateUser(u2);
		User u = es.findByUserName("Superhero");
		assertEquals(u.getFirst_name(), u2.getFirst_name());
		
	}
	@Test
	public void testUpdateUserLastName() {
		User u2 = new User("Superhero", 12345, "John", "Wick");
		UserDAOImpl id = new UserDAOImpl();
		EmployeeService es = new EmployeeService();
		u2 = id.updateUser(u2);
		User u = es.findByUserName("Superhero");
		assertEquals(u.getLast_name(), u2.getLast_name());
		
	}
}
