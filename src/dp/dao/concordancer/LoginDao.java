package dp.dao.concordancer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dp.model.concordancer.*;
import java.util.*;

public class LoginDao {

	static
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException ex)
		{
		}
	}

	private Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/concordances?autoReconnect=true&useSSL=FALSE&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "");
	}

	private void closeConnection(Connection connection)
	{
		if (connection == null)
			return;
		try
		{
			connection.close();
		}
		catch (SQLException ex)
		{
		}
		}


	public User getUser(String name, String password)
	{
		User user= new User();
		//String username = u.getUsername();
		//String password = u.getPassword();
		//System.out.println("DAO"+ username + password);
		Connection conn = null;
		String query = "SELECT * FROM users WHERE USER_NAME='" + name + "' AND PASSWORD='" + password + "';";
		try
		{
		conn=getConnection();
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet result = statement.executeQuery();
		boolean uexists = result.next();
		//System.out.println(uexists);

		if (!uexists)
		{
			user.setIsRegistered(false);
			System.out.println("Not found");
		}
		else
		{

			user.setUserid(result.getInt("USER_ID"));
			user.setUsername(result.getString("USER_NAME"));
			user.setPassword(result.getString("PASSWORD"));
			user.setIsRegistered(true);
		}


		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}


		finally
		{
			closeConnection(conn);

		}


			return user;

	}

	public boolean checkUserName(String username)
	{
		boolean available=false;

		Connection conn = null;
		String query = "SELECT * FROM users WHERE USER_NAME='" + username + "';";
		try
		{
		conn=getConnection();
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet result = statement.executeQuery();
		boolean uexists = result.next();

		if (!uexists)
		{
			System.out.println("Username available");
			available=true;
		}
		else
		{

			available= false;
		}

		}

		catch (SQLException ex)
		{
			ex.printStackTrace();
		}


		finally
		{
			closeConnection(conn);

		}

		return available;
	}



	public void registerUser(String name, String password)
	{
		Connection conn=null;

		try
		{
			String sql= "INSERT INTO users (USER_NAME, PASSWORD) VALUES (?, ?)";
			conn=getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, password);
			statement.execute();
		}
		catch (SQLException ex)
		{ex.printStackTrace();}

		finally
		{
			closeConnection(conn);
		}
		}

	public List <Project> getProjects(User user)
	{
		Connection conn=null;
		List <Project> projects = new ArrayList<Project>();
		List <File> filelist = new ArrayList<File>();
		int user_id=user.getUser_id();
		//String username=user.getUsername();
		try
		{
			String sql= "SELECT * FROM project WHERE user_id='" + user_id + "';";
			conn=getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
			Project project = new Project();
			project.setProject_id(result.getInt("PROJECT_ID"));
			project.setProjectname(result.getString("PROJECT_NAME"));
			project.setUserid(result.getInt("USER_ID"));
			projects.add(project);
			}

			for (Project p :projects)
			{
				//System.out.println("DAO Project:" + p.getProjectname() + p.getProject_id());
				int project_id = p.getProject_id();
				String files = "SELECT * FROM files WHERE project_id='" + project_id + "';";//getuserid
				PreparedStatement s = conn.prepareStatement(files);
				ResultSet set = s.executeQuery();
				while (set.next())
				{
					File f = new File();
					f.setFile_id(set.getInt("FILE_ID"));
					f.setFile_name(set.getString("FILE_NAME"));
					//f.setFile_path(set.getString("FILE_PATH"));
					f.setProject_id(set.getInt("PROJECT_ID"));
					filelist.add(f);
					p.setFiles(filelist);

				}
			}

		}
		catch (SQLException ex)
		{ex.printStackTrace();}

		finally
		{
			closeConnection(conn);
		}
		return projects;
		}

	public Project getProject(int id, User u)
	{
		Connection conn=null;
		Project project = new Project();
		List <File> filelist = new ArrayList<File>();
		try
		{
			conn=getConnection();
			String sql = "SELECT * from project WHERE PROJECT_ID='" + id + "' AND USER_ID='" + u.getUser_id() +"'";
			String sql2 = "SELECT * from files WHERE PROJECT_ID='" + id +"'";
			PreparedStatement statement = conn.prepareStatement(sql);
			PreparedStatement statement2 = conn.prepareStatement(sql2);
			ResultSet result = statement.executeQuery();
			ResultSet result2 = statement2.executeQuery();
			while (result.next())
			{

			project.setProject_id(result.getInt("PROJECT_ID"));
			project.setProjectname(result.getString("PROJECT_NAME"));
			project.setUserid(result.getInt("USER_ID"));
			}

			while (result2.next())
			{


				File f = new File();
				f.setFile_id(result2.getInt("FILE_ID"));
				f.setFile_name(result2.getString("FILE_NAME"));
				f.setFile_path(result2.getString("FILE_PATH"));
				f.setProject_id(result2.getInt("PROJECT_ID"));
				filelist.add(f);



				project.setFiles(filelist);
			}


		}
		catch (SQLException ex)
		{ex.printStackTrace();}

		finally
		{
			closeConnection(conn);
		}

		return project;
	}


	public void createProject(User user, String projectname)
	{
		Connection conn=null;

		try
		{
			String sql = "INSERT INTO project(PROJECT_NAME, USER_ID) VALUES(?, ?);";
			conn=getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, projectname);
			statement.setInt(2, user.getUser_id());
			statement.execute();
		}
		catch (SQLException ex)
		{ex.printStackTrace();}

		finally
		{
			closeConnection(conn);
		}

	}

	}

