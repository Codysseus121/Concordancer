package dp.concordancer.interfaces;

import dp.concordancer.forms.UserForm;

/*
* Interface RegisterDataAccessObject defines methods
* for user registration.
*/

public interface RegisterDataAccessObject {

	/* checkUserName for checking if the username is available.
	 * @param username: the user name.
	 * @return boolean: false if the user exists.
	 * 
	 */
	public boolean checkUserName(String username);
	
	/*
	 * Method registerUser to register a user
	 * @param username: the user name.
	 * @param password: the password.
	 */
	
	public void registerUser(String username, String password);
	
	
	
	
}
