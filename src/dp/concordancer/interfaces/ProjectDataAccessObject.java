package dp.concordancer.interfaces;


import java.util.List;

import dp.model.concordancer.Project;
import dp.model.concordancer.User;

/*
 * Interface ProjectDataAccessObject defines methods for accessing projects in the database
 */

public interface ProjectDataAccessObject {
/*
 * Method getProjects for getting all projects of a single user.
 * @user: the user.
 * @return a List with the User's projects.
 */
public List<Project> getProjects(User user);
/*
 * Method deleteProject for deleting a single project from the database.
 * @user: the user.
 * @pid: the project's primary key.
 */

public void deleteProject(User u, int pid);

/*Method createProject to create a new Project.
 * @param user: the user.
 * @param projectname: the name of the project.
 * 
 */

public int createProject(User user, String projectname);
/*
 * Method getProject for getting a single project of a single user from the database.
 * @user: the user.
 * @return a Project object.
 */
public Project getProject(int id, User u);

	
}
