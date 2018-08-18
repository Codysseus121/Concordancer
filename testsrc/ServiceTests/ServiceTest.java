package ServiceTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import dp.concordancer.ConcFacade.ConcFacadeImpl;
import dp.concordancer.ConcFacade.ConcordancerFacade;
import dp.model.concordancer.*;

class ServiceTest   {
private static ConcordancerFacade service = new ConcFacadeImpl();
	
	@Test
	void registerUserTest() {
		
		String name = "JohnSmith";
		String password = "password";
		boolean success = service.registerUser(name, password);
		assertTrue(success);
		
	}
	@Test
	void checkUserNameTest() {
		
		String name = "JohnSmith";
		boolean success = service.checkUserName(name);
		assertFalse(success);
		
	}
	@Test
	public void getUserTest()
	{
		String name = "JohnSmith";
		String password = "password";
		UserInterface user = service.getUser(name, password);
		assertEquals(user.getUsername(), name);
		assertEquals(user.getPassword(), password);
		assertTrue(user.getIsRegistered());
	}
	
	@Test
	public void createProjectTest()
	{
		
		UserInterface user = new User();
		user.setUserid(2000);
		String projectname = "NewProject";
		int projectcreated = service.createProject(user, projectname);
			
		ProjectInterface project = service.getProject(projectcreated, user);
		int project_id = project.getProject_id();
		assertEquals(projectcreated, project_id);
		
		
	}
	@Test
	public void deleteProjectTest()
	{
		
		UserInterface user = new User();
		user.setUserid(2);//existing in the database		
		int project_id = 178;
		boolean success = service.deleteProject(user, project_id);
		assertTrue(success);
				
	}

	@Test
	public void addFilesTest()
	{
		String filename = "TestFilename";
		int project_id = 177;//existing database project
		String text = "This is a DB integration Test";
		boolean success = service.addFiles(filename, project_id, text);
		assertTrue(success);
	}
	
	@Test
	public void getFileTest()
	{
		
		String filename = "TestFilename";
		String text = "This is a DB integration Test";
		ProjectInterface project = new Project();
		UserInterface user = new User();
		user.setUserid(2);
		project.setProject_id(177);
		String test = service.getFile(project, user, filename);
		assertEquals(text, test);	
		
		
	}
	
}
