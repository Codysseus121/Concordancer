package dp.model.concordancer;

import java.util.*;
import java.io.Serializable;

/*
 * class Project: a class for kwic projects,
 * implementing standard mutator and accessor methods for all
 * class fields.
 */


public class Project implements Serializable, ProjectInterface {


	private static final long serialVersionUID = 1766822754768265393L;
	private String projectname;
	private int project_id;
	private int userid;
	private List<ProjectFile> files;
	
	

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectInterface#setProjectname(java.lang.String)
	 */
	@Override
	public void setProjectname (String name)
	{
		this.projectname=name;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectInterface#getProjectname()
	 */
	@Override
	public String getProjectname()
	{
		return projectname;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectInterface#setProject_id(int)
	 */
	@Override
	public void setProject_id(int id)
	{
		this.project_id=id;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectInterface#getProject_id()
	 */
	@Override
	public int getProject_id()
	{
		return project_id;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectInterface#setUserid(int)
	 */
	@Override
	public void setUserid (int userid)
	{
		this.userid=userid;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectInterface#getUserid()
	 */
	@Override
	public int getUserid ()
	{
		return userid;
	}
/*
 * Mutator method to set the files private field of this class
 * for Inversion of Control.
 */
	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectInterface#setFiles(java.util.List)
	 */
	@Override
	public void setFiles(List<ProjectFile> f)
	{
		this.files=f;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectInterface#getFiles()
	 */
	@Override
	public List<ProjectFile> getFiles()
	{
		return files;
	}
}
