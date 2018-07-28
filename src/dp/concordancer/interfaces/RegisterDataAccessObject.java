package dp.concordancer.interfaces;

import dp.concordancer.forms.UserForm;

public interface RegisterDataAccessObject {

	
	public boolean checkUserName(String username);
	public void registerUser(String username, String password);
	public UserForm getUser (String name, String password);
	
}
