package dp.concordancer.interfaces;

/*
 * Interface GetUserDataAccessObject defines a single method
 * for user login
 */
import dp.model.concordancer.User;

public interface GetUserDataAccessObject {

	/*Method getUser for user login.
	 * @name: the username.
	 * @password: the password.
	 * 
	 */
		
		 User getUser (String name, String password);

	}

	

