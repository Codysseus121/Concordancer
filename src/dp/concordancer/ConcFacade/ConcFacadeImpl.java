package dp.concordancer.ConcFacade;


import java.io.IOException;
import java.util.List;
import java.util.TreeMap;
import javax.servlet.http.Part;

import dp.concordancer.interfaces.*;
import dp.dao.concordancer.*;
import dp.model.concordancer.Kwic;
import dp.model.concordancer.Project;
import dp.model.concordancer.ProjectFile;
import dp.model.concordancer.User;

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

	public User getUser(String name, String password) {
		return udao.getUser(name, password);
	}

	public boolean checkUserName(String username) {
		return udao.checkUserName(username);
	}

	public boolean registerUser(String name, String password) {
		return udao.registerUser(name, password);
	}

	public List<Project> getProjects(User user) {
		return pdao.getProjects(user);
	}

	public Project getProject(int id, User u) {
		return pdao.getProject(id, u);
	}

	public boolean deleteProject(User u, int pid) {
		return pdao.deleteProject(u, pid);
	}

	public int createProject(User user, String projectname) {
		return pdao.createProject(user, projectname);
	}

	public List<ProjectFile> getFiles(Project project, User user) {
		return fdao.getFiles(project, user);
	}

	public String getFile(Project project, User user, String filename) {
		return fdao.getFile(project, user, filename);
	}

	public boolean addFiles(String filename, int projectid, String text) {
		return fdao.addFiles(filename, projectid, text);
	}

	public List<Kwic> getConcordances(User u, Project p, String query) {
		return textservice.getConcordances(u, p, query);
	}

	public List<Kwic> getCollocates(List<Kwic> concordances, List<String> permutations) {

		return textservice.getCollocates(concordances, permutations);

	}

	public TreeMap<String, Integer> generateIndex(Project project, User user) {

		return textservice.generateIndex(project, user);
	}

	
	public String getText(Part part, String fextension) throws IOException {
		return fileservice.getText(part, fextension);
	}

	
}
