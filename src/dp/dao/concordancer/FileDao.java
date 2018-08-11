package dp.dao.concordancer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dp.concordancer.interfaces.FileDataAccessObject;
import dp.model.concordancer.Project;
import dp.model.concordancer.ProjectFile;
import dp.model.concordancer.User;

public class FileDao extends GetConnection implements FileDataAccessObject {

	public void addFiles(String filename, int projectid, String text) throws SQLException, IOException {

		PreparedStatement statement = null;
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			statement = conn
					.prepareStatement("insert into files (file_name, file_content, project_id) values ( ?, ?, ?)");
			statement.setString(1, filename);
			statement.setString(2, text);
			statement.setInt(3, projectid);
			statement.executeQuery("SET NAMES 'UTF8'");
			statement.executeQuery("SET CHARACTER SET 'UTF8'");
			statement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see dp.concordancer.interfaces.FileDataAccessObject#getFiles(dp.model.
	 * concordancer.Project, dp.model.concordancer.User)
	 */
	public List<ProjectFile> getFiles(Project project, User user) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		int project_id = project.getProject_id();

		List<ProjectFile> filelist = new ArrayList<ProjectFile>();// an arraylist with the filecontents per file

		try {
			// First get ids of all project files
			conn = getConnection();
			String sql = "SELECT file_id, file_name, file_content from files F, project P, users U where U.user_id=P.user_id AND P.project_id=F.project_id AND F.project_id="
					+ project_id + ";";
			statement = conn.prepareStatement(sql);
			set = statement.executeQuery();
			while (set.next())// add files to ArrayList
			{
				ProjectFile f = new ProjectFile();
				f.setFile_id(set.getInt("FILE_ID"));
				f.setFile_name(set.getString("FILE_NAME"));
				f.setFilecontent(set.getString("FILE_CONTENT"));
				filelist.add(f);

			}

		}

		catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				set.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return filelist;
	}

	public String getFile(Project project, User user, String filename) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		int project_id = project.getProject_id();
		String text = "";

		try {

			conn = getConnection();
			String sql = "SELECT file_content from files F, project P, users U where U.user_id=P.user_id AND P.project_id=F.project_id AND P.project_id="
					+ project_id + " AND F.file_name='" + filename + "';";
			statement = conn.prepareStatement(sql);
			set = statement.executeQuery();
			while (set.next())// add files ids to arraylist
			{
				text = set.getString("FILE_CONTENT");

			}

		}

		catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				set.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return text;
	}

}
