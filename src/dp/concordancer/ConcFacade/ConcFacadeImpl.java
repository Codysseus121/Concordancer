package dp.concordancer.ConcFacade;


import java.io.IOException;
import java.util.List;
import java.util.TreeMap;
import javax.servlet.http.Part;

import dp.concordancer.interfaces.*;
import dp.dao.concordancer.*;
import dp.model.concordancer.KWICInterface;
import dp.model.concordancer.Kwic;
import dp.model.concordancer.Project;
import dp.model.concordancer.ProjectFile;
import dp.model.concordancer.ProjectInterface;
import dp.model.concordancer.UserInterface;

public class ConcFacadeImpl implements ConcordancerFacade {

	private UserDataAccessObject udao = new UserDao();
	private ProjectDataAccessObject pdao = new ProjectDao();
	private FileDao fdao = new FileDao();
	private TextServiceInterface textservice = new TextService();
	private FileServiceInterface fileservice = new FileService();

	public UserDataAccessObject getUserDao() {
		return udao;
	}

	public ProjectDataAccessObject PDao() {
		return pdao;
	}

	public FileDataAccessObject FDao() {
		return fdao;
	}

	public UserInterface getUser(String name, String password) {
		return udao.getUser(name, password);
	}

	public boolean checkUserName(String username) {
		return udao.checkUserName(username);
	}

	public boolean registerUser(String name, String password) {
		return udao.registerUser(name, password);
	}

	public List<Project> getProjects(UserInterface user) {
		return pdao.getProjects(user);
	}

	public ProjectInterface getProject(int id, UserInterface u) {
		return pdao.getProject(id, u);
	}

	public boolean deleteProject(UserInterface u, int pid) {
		return pdao.deleteProject(u, pid);
	}

	public int createProject(UserInterface user, String projectname) {
		return pdao.createProject(user, projectname);
	}

	public List<ProjectFile> getFiles(ProjectInterface project, UserInterface user) {
		return fdao.getFiles(project, user);
	}

	public String getFile(ProjectInterface project, UserInterface user, String filename) {
		return fdao.getFile(project, user, filename);
	}

	public boolean addFiles(String filename, int projectid, String text) {
		return fdao.addFiles(filename, projectid, text);
	}

	public List<KWICInterface> getConcordances(UserInterface u, ProjectInterface p, String query) {
		return textservice.getConcordances(u, p, query);
	}

	public List<KWICInterface> getCollocates(List<KWICInterface> concordances, List<String> permutations) {

		return textservice.getCollocates(concordances, permutations);

	}

	public TreeMap<String, Integer> generateIndex(ProjectInterface project, UserInterface user) {

		return textservice.generateIndex(project, user);
	}

	
	public String getText(Part part, String fextension) throws IOException {
		return fileservice.getText(part, fextension);
	}

	
}
