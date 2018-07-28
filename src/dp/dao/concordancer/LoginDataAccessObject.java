package dp.dao.concordancer;

import dp.concordancer.forms.UserForm;;

public interface LoginDataAccessObject {
	
	public UserForm getUser (String name, String password);

}
