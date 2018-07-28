package dp.concordancer.forms;

import java.util.ArrayList;
import java.util.List;

import dp.model.concordancer.File;

public class ProjectForm {

	private String projectname;
	private int project_id;
	private int userid;
	private List<File> files =  new ArrayList<File>();
	

	public void setProjectname (String name)
	{
		this.projectname=name;
	}

	public String getProjectname()
	{
		return projectname;
	}

	public void setProject_id(int id)
	{
		this.project_id=id;
	}

	public int getProject_id()
	{
		return project_id;
	}

	public void setUserid (int userid)
	{
		this.userid=userid;
	}

	public int getUserid ()
	{
		return userid;
	}

	public void setFiles(List<File> f)
	{
		this.files=f;
	}

	public List<File> getFiles()
	{
		return files;
	}
}

	
	

