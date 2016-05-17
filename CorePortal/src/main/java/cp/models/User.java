package cp.models;

import cp.common.UserRoles;

public class User {
	
	private UserRoles userRole;
	private String username;
	private String password;
	
	
	public User() {}
	
	public User(UserRoles userRole, String username, String password) {
		super();
		this.userRole = userRole;
		this.username = username;
		this.password = password;
	}

	public UserRoles getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoles userRole) {
		this.userRole = userRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return username + ":" + password + ":" + userRole;
	}
}
