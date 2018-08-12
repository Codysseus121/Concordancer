package dp.concordancer.interfaces;
import dp.concordancer.forms.UserForm;
/*
 * Interface GetUserDataAccessObject defines a single method
 * for user login
 */

public interface GetUserDataAccessObject {

	/*Method getUser for user login.
	 * @name: the username.
	 * @password: the password.
	 * 
	 */
		
		public UserForm getUser (String name, String password);

	}

	

