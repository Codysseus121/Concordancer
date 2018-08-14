package dp.concordancer.ConcFacade;

import java.util.List;

import dp.concordancer.forms.UserForm;
import dp.dao.concordancer.*;
import dp.model.concordancer.Project;
import dp.model.concordancer.ProjectFile;
import dp.model.concordancer.User;

public class ConcFacadeImpl implements ConcordancerFacade {
	private GetUserDao udao = new GetUserDao();
	private RegisterDao rdao = new RegisterDao();
	private ProjectDao pdao = new ProjectDao();
	private FileDao fdao = new FileDao();
	
	public GetUserDao GUDao()
	{
		return udao;
	}
	
	public RegisterDao RDao()
	{
		return rdao;
	}
	
	public ProjectDao PDao()
	{
		return pdao;
	}
	
	public FileDao FDao()
	{
		return fdao;
	}
	
	
	public User getUser(String name, String password)
	{
		return udao.getUser(name, password);
	}
	
	public boolean checkUserName(String username)
	{
		return rdao.checkUserName(username);
	}
	
	
	public void registerUser(String name, String password)
	{
		rdao.registerUser(name, password);
	}
	
	public List<Project> getProjects(User user)
	{
		return pdao.getProjects(user);
	}
	
	public Project getProject(int id, User u)
	{
		return pdao.getProject(id, u);
	}
	
	public void deleteProject(User u, int pid)
	{
		pdao.deleteProject(u, pid);
	}
	public int createProject(User user, String projectname)
	{
		return pdao.createProject(user, projectname);
	}
	
	public List<ProjectFile> getFiles(Project project, User user)
	{
		return fdao.getFiles(project, user);
	}
	
	public String getFile(Project project, User user, String filename)
	{
		return fdao.getFile(project, user, filename);
	}
	public void addFiles(String filename, int projectid, String text) 
	{
		fdao.addFiles(filename, projectid, text);
	}
		

}
