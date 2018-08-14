
package dp.dao.concordancer;

import java.util.ArrayList;
import java.util.List;
import dp.concordancer.interfaces.ProjectDataAccessObject;
import dp.model.concordancer.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * class ProjectDao for database operations on Project objects.
 */


public class ProjectDao extends GetConnection implements ProjectDataAccessObject {


/*
 * 	(non-Javadoc)
 * @see dp.concordancer.interfaces.ProjectDataAccessObject#getProjects(dp.model.concordancer.User)
 */
	
	public List<Project> getProjects(User user) {

		Connection conn = null;
		List<Project> projects = new ArrayList<Project>();
		List<ProjectFile> filelist = new ArrayList<ProjectFile>();
		int user_id = user.getUser_id();

		try {
			String sql = "SELECT * FROM project WHERE user_id='" + user_id + "';";
			conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Project project = new Project();
				project.setProject_id(result.getInt("PROJECT_ID"));
				project.setProjectname(result.getString("PROJECT_NAME"));
				project.setUserid(result.getInt("USER_ID"));
				projects.add(project);
			}

			for (Project p : projects) {

				int project_id = p.getProject_id();
				String files = "SELECT * FROM files WHERE project_id='" + project_id + "';";
				PreparedStatement s = conn.prepareStatement(files);
				ResultSet set = s.executeQuery();
				while (set.next()) {
					ProjectFile f = new ProjectFile();
					f.setFile_id(set.getInt("FILE_ID"));
					f.setFile_name(set.getString("FILE_NAME"));
					f.setProject_id(set.getInt("PROJECT_ID"));
					filelist.add(f);
					p.setFiles(filelist);

				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			closeConnection(conn);
		}
		return projects;
	}
/*
 * 	(non-Javadoc)
 * @see dp.concordancer.interfaces.ProjectDataAccessObject#getProject(int, dp.model.concordancer.User)
 */
	
	public Project getProject(int id, User u) {
		Connection conn = null;
		Project project = new Project();
		List<ProjectFile> filelist = new ArrayList<ProjectFile>();
		try {
			conn = getConnection();
			String sql = "SELECT * from project WHERE PROJECT_ID='" + id + "' AND USER_ID='" + u.getUser_id() + "'";
			String sql2 = "SELECT * from files WHERE PROJECT_ID='" + id + "'";
			PreparedStatement statement = conn.prepareStatement(sql);
			PreparedStatement statement2 = conn.prepareStatement(sql2);
			ResultSet result = statement.executeQuery();
			ResultSet result2 = statement2.executeQuery();
			while (result.next()) {

				project.setProject_id(result.getInt("PROJECT_ID"));
				project.setProjectname(result.getString("PROJECT_NAME"));
				project.setUserid(result.getInt("USER_ID"));
			}

			while (result2.next()) {

				ProjectFile f = new ProjectFile();
				f.setFile_id(result2.getInt("FILE_ID"));
				f.setFile_name(result2.getString("FILE_NAME"));
				f.setProject_id(result2.getInt("PROJECT_ID"));
				filelist.add(f);

				project.setFiles(filelist);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			closeConnection(conn);
		}

		return project;
	}


	
	
/*
 * (non-Javadoc)
 * @see dp.concordancer.interfaces.ProjectDataAccessObject#deleteProject(dp.model.concordancer.User, int)
 * 
 * Method deleteProject to delete a project from a User's records
 * @param User : the user object
 * @int pid : the project's key on the database
 * 
 */
	public boolean deleteProject(User u, int pid) {
		Connection conn = null;
		int user_id = u.getUser_id();
		String query = "DELETE FROM PROJECT WHERE PROJECT_ID='" + pid + "' AND USER_ID='" + user_id + "';";
		String fileq = "DELETE FROM FILES WHERE PROJECT_ID='" + pid + "';";
		try {
			conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			PreparedStatement statement2 = conn.prepareStatement(fileq);
			statement2.execute();
			statement.execute();
			return true;
			
		} catch (SQLException ex) {
			
			ex.printStackTrace();
			return false;
		}

		finally {
			closeConnection(conn);
		}

	}
/*
 * (non-Javadoc)
 * @see dp.concordancer.interfaces.ProjectDataAccessObject#createProject(dp.model.concordancer.User, java.lang.String)
 */
		public int createProject(User user, String projectname) {
		Connection conn = null;
		int projectid = 0;

		try {
			String sql = "INSERT INTO project(PROJECT_NAME, USER_ID) VALUES(?, ?);";
			conn = getConnection();
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, projectname);
			statement.setInt(2, user.getUser_id());
			statement.execute();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs != null && rs.next()) {
				projectid = rs.getInt(1);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {

			closeConnection(conn);

		}
		return projectid;
	}

		
}
