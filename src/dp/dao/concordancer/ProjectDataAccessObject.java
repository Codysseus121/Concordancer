package dp.dao.concordancer;

import java.util.List;

import dp.model.concordancer.Project;
import dp.model.concordancer.User;

public interface ProjectDataAccessObject {

public List<Project> getProjects(User user);	
	
}
