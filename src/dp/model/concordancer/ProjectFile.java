package dp.model.concordancer;
import java.io.Serializable;



public class ProjectFile implements Serializable {

		private static final long serialVersionUID = 8614571457975965833L;
		private int file_id;
		private String file_name;
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

	public void setProject_id(int id)
	{
		this.project_id=id;
	}
	public int getProject_id()
	{
		return project_id;
	}
	@Override
	public String toString()
	{
		return file_name;
	}
	}



