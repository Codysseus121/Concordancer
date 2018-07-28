package dp.concordancer.interfaces;

import java.util.List;

import dp.model.concordancer.Project;
import dp.model.concordancer.User;

public interface ProjectDataAccessObject {

public List<Project> getProjects(User user);
public void deleteProject(User u, int pid);
	
}
