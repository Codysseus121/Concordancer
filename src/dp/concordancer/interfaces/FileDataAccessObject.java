package dp.concordancer.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import dp.model.concordancer.Project;
import dp.model.concordancer.ProjectFile;
import dp.model.concordancer.User;

/*Interface FileDataAccessObject for accessing files from the database 
 */

public interface FileDataAccessObject {

	/*
	 * Method addFiles for adding files to the database.
	 * @param filename: the name of the file.
	 * @param projectid: the key of the project to which this file belongs.
	 * @param text: the contents of the file as String object.
	 */
	public boolean addFiles(String filename, int projectid, String text) throws SQLException, IOException;
	
	/*
	 * Method getFiles for accessing files from the database.
	 * @param project: the name of the project.
	 * @param user: the user of the project.
	 * @return: a List  with ProjectFile objects.
	 */
	public List<ProjectFile> getFiles(Project project, User user);
	/*
	 * Method getFile for getting a single file from the database.
	 * @param project: the name of the project.
	 * @param user: the user of the project.
	 * @param filename: the name of the file.
	 * @return: a String representation of the content
	 */
	public String getFile (Project project, User user, String filename);
	
	
}
