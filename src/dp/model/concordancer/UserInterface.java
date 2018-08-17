package dp.model.concordancer;

public interface UserInterface {

	int getUser_id();

	String getUsername();

	String getPassword();

	void setUsername(String user);

	void setPassword(String pass);

	void setUserid(int id);

	void setIsRegistered(boolean b);

	boolean getIsRegistered();

}