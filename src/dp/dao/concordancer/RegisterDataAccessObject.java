package dp.dao.concordancer;

public interface RegisterDataAccessObject {

	
	public boolean checkUserName(String username);
	public void registerUser(String username, String password);
}
