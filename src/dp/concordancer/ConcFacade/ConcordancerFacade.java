package dp.concordancer.ConcFacade;
/*
 * Interface ConcordancerFacade.
 * An interface for the business layer.
 */

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.Part;

import dp.model.concordancer.KWICInterface;
import dp.model.concordancer.Project;
import dp.model.concordancer.ProjectFile;
import dp.model.concordancer.ProjectInterface;
import dp.model.concordancer.UserInterface;

public interface ConcordancerFacade {
	

	UserInterface getUser(String name, String password);
	boolean checkUserName(String username);
	boolean registerUser(String name, String password);
	List<Project> getProjects(UserInterface user);
	ProjectInterface getProject(int id, UserInterface u);
	boolean deleteProject(UserInterface u, int pid);
	int createProject(UserInterface user, String projectname);
	List<ProjectFile> getFiles(ProjectInterface project, UserInterface user);
	String getFile(ProjectInterface project, UserInterface user, String filename);
	boolean addFiles(String filename, int projectid, String text);
	List<KWICInterface> getConcordances(UserInterface u, ProjectInterface p, String query);
	List<KWICInterface> getCollocates(List<KWICInterface> concordances, List<String> permutations);
	TreeMap<String, Integer> generateIndex(ProjectInterface project, UserInterface user);
	String getText (Part part, String fextension) throws IOException;
	
	
	

}

	


