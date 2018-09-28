package dp.concordancer.interfaces;

import dp.model.concordancer.UserInterface;
/*
 * UserdataAccessObject interface for user registration and login
 */
public interface UserDataAccessObject {

	UserInterface getUser(String name, String password);

	/*
	 * checkUserName for checking if the username is available.
	 * 
	 * @param username: the user name.
	 * 
	 * @return boolean: false if the user exists.
	 * 
	 */
	boolean checkUserName(String username);

	/*
	 * Method registerUser to register a user
	 * 
	 * @param username: the user name.
	 * 
	 * @param password: the password.
	 */

	boolean registerUser(String username, String password);

}
