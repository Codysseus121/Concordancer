package dp.model.concordancer;

import java.io.Serializable;

/*
 * class User for User objects,
 * implementing standard accessor and mutator methods
 * for all class fields.
 */

public class User implements Serializable, UserInterface {


	private static final long serialVersionUID = 2354064310193024575L;
	private  String username;
	private  String password;
	private  int user_id;
	private boolean isRegistered;


	/* (non-Javadoc)
	 * @see dp.model.concordancer.UserInterface#getUser_id()
	 */
	@Override
	public int getUser_id()
	{
		return user_id;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.UserInterface#getUsername()
	 */
	@Override
	public String getUsername()
	{
		return username;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.UserInterface#getPassword()
	 */
	@Override
	public String getPassword()
	{
		return password;

	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.UserInterface#setUsername(java.lang.String)
	 */
	@Override
	public void setUsername(String user)
	{
		this.username=user;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.UserInterface#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String pass)
	{
		this.password=pass;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.UserInterface#setUserid(int)
	 */
	@Override
	public void setUserid(int id)
	{
		this.user_id=id;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.UserInterface#setIsRegistered(boolean)
	 */
	@Override
	public void setIsRegistered(boolean b)
	{
		this.isRegistered=b;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.UserInterface#getIsRegistered()
	 */
	@Override
	public boolean getIsRegistered()
	{
		return isRegistered;
	}
}
