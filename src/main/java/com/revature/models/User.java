package com.revature.models;

public class User {

	private int id;
	private String user_name;
	private int user_password;
	private String first_name;
	private String last_name;
	private boolean is_employee;
	private boolean is_admin;
	
	public User(int id, String user_name, int user_password, String first_name, String last_name,
			boolean is_employee, boolean is_admin) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.is_employee = is_employee;
		this.is_admin = is_admin;
	}
	public User(String user_name, int user_password, String first_name, String last_name) {
		super();

		this.user_name = user_name;
		this.user_password = user_password;
		this.first_name = first_name;
		this.last_name = last_name;
	
	}
	public void setUser_password(int user_password) {
		this.user_password = user_password;
	}
	
	public int getUser_password() {
		return user_password;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int user_id, String user_name2, String first_name2, String last_name2, boolean is_employee2,
			boolean is_admin2) {
		super();
		this.id = user_id;
		this.user_name = user_name2;
		this.first_name = first_name2;
		this.last_name = last_name2;
		this.is_employee = is_employee2;
		this.is_admin = is_admin2;
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public boolean isIs_employee() {
		return is_employee;
	}
	public void setIs_employee(boolean is_employee) {
		this.is_employee = is_employee;
	}
	public boolean isIs_admin() {
		return is_admin;
	}
	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + id;
		result = prime * result + (is_admin ? 1231 : 1237);
		result = prime * result + (is_employee ? 1231 : 1237);
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
		result = prime * result + user_password;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (id != other.id)
			return false;
		if (is_admin != other.is_admin)
			return false;
		if (is_employee != other.is_employee)
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		if (user_password != other.user_password)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + user_name + ", user_password=" + user_password + ", first_name="
				+ first_name + ", last_name=" + last_name + ", is_employee=" + is_employee + ", is_admin=" + is_admin
				+ "]";
	}
	
}