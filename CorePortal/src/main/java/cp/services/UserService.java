package cp.services;

import javax.ejb.Singleton;

import cp.models.User;
import cp.utils.UserRoles;

@Singleton
public class UserService {
	private User logedInUser = new User();
	
	public boolean logIn(String user, String pass){
		// TODO: Check in database if exist
		// TODO: If exist get userRole (and other data which should first complete fields in User class)
		logedInUser.setUserRole(UserRoles.ADMIN);
		logedInUser.setUsername(user);
		logedInUser.setPassword(pass);
		return true;
		// TODO: If doesn't exist return false
		
		// (For HB return user data to return it on service response to populate session with user data)
	}

}
