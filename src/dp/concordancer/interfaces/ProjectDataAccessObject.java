package dp.concordancer.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dp.model.concordancer.Project;
import dp.model.concordancer.User;

public interface ProjectDataAccessObject {

public List<Project> getProjects(User user);
public void deleteProject(User u, int pid);
public int createProject(User user, String projectname);
public Project getProject(int id, User u);

	
}
