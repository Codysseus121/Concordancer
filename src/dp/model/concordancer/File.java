package dp.model.concordancer;

import java.io.Serializable;

public class File implements Serializable {

	private static final long serialVersionUID = 8614571457975965833L;
	private int file_id;
	private String file_name;
	private String file_path;
	private int project_id;


	
public void setFile_id(int id)
{
	this.file_id=id;
}
public int getFile_id()
{
	return file_id;
}
public void setFile_name(String file)
{
	this.file_name=file;
}
public String getFile_name()
{
	return file_name;
}
public void setFile_path(String path)
{
	this.file_path=path;
}
public String getFile_path()
{
	return file_path;
}
public void setProject_id(int id)
{
	this.project_id=id;
}
public int getProject_id()
{
	return project_id;
}
public String toString()
{
	return file_name;
}
}
