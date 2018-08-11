package DaoTests;

import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

import dp.model.concordancer.*;
import dp.dao.concordancer.*;
import java.util.*;

public class ProjectDaoTest extends Mockito

{

	@Test
	public void getProjectsTest() throws SQLException {

		
		ProjectDao pdao = mock(ProjectDao.class);
		User user = mock(User.class);
		List<Project> projects = mock(ArrayList.class);
		when(pdao.getProjects(user)).thenReturn(projects);
		
				
		
	}

	
}
