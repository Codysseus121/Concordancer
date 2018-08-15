package dp.concordancer.ConcFacade;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.Part;

import dp.model.concordancer.Kwic;
import dp.model.concordancer.Project;
import dp.model.concordancer.ProjectFile;
import dp.model.concordancer.User;

public interface ConcordancerFacade {
	

	User getUser(String name, String password);
	boolean checkUserName(String username);
	boolean registerUser(String name, String password);
	List<Project> getProjects(User user);
	Project getProject(int id, User u);
	boolean deleteProject(User u, int pid);
	int createProject(User user, String projectname);
	List<ProjectFile> getFiles(Project project, User user);
	String getFile(Project project, User user, String filename);
	boolean addFiles(String filename, int projectid, String text);
	List<Kwic> getConcordances(User u, Project p, String query);
	List<Kwic> getCollocates(List<Kwic> concordances, List<String> permutations);
	TreeMap<String, Integer> generateIndex(Project project, User user);
	String getText (Part part, String fextension) throws IOException;
	
	
	

}

	


