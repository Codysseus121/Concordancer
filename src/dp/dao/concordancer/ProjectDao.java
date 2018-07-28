package dp.dao.concordancer;

import java.util.ArrayList;
import java.util.List;

import dp.concordancer.interfaces.ProjectDataAccessObject;
import dp.model.concordancer.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProjectDao extends GetConnection implements ProjectDataAccessObject {
	
		
	public List<Project> getProjects(User user)
	{
		
			Connection conn=null;
			List <Project> projects = new ArrayList<Project>();
			List <File> filelist = new ArrayList<File>();
			int user_id=user.getUser_id();
			
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
					
					int project_id = p.getProject_id();
					String files = "SELECT * FROM files WHERE project_id='" + project_id + "';";
					PreparedStatement s = conn.prepareStatement(files);
					ResultSet set = s.executeQuery();
					while (set.next())
					{
						File f = new File();
						f.setFile_id(set.getInt("FILE_ID"));
						f.setFile_name(set.getString("FILE_NAME"));
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
	
	public void deleteProject(User u, int pid)
	{
		Connection conn=null;
		int user_id=u.getUser_id();
		String query = "DELETE FROM PROJECT WHERE PROJECT_ID='" + pid + "' AND USER_ID='" + user_id+ "';";
		String fileq = "DELETE FROM FILES WHERE PROJECT_ID='" + pid + "';";
		try
		{
		conn=getConnection();
		PreparedStatement statement = conn.prepareStatement(query);
		PreparedStatement statement2 = conn.prepareStatement(fileq);
		statement.execute();
		statement2.execute();
	}
		catch (SQLException ex)
		{ex.printStackTrace();}

		finally
		{
			closeConnection(conn);
		}

}
	}



