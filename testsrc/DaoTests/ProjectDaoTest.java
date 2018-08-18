package DaoTests;

import org.mockito.Mockito;


import java.sql.SQLException;
import org.junit.Test;

import dp.model.concordancer.*;
import dp.dao.concordancer.*;
import java.util.*;

public class ProjectDaoTest extends Mockito

{

	@Test
	public void getProjectsTest() throws SQLException {

		
		ProjectDao pdao = mock(ProjectDao.class);
		UserInterface user = mock(User.class);
		List<Project> projects = mock(ArrayList.class);
		when(pdao.getProjects(user)).thenReturn(projects);
		
				
		
	}

	
}
