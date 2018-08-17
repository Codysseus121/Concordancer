package dp.model.concordancer;

import java.util.List;

public interface ProjectInterface {

	void setProjectname(String name);

	String getProjectname();

	void setProject_id(int id);

	int getProject_id();

	void setUserid(int userid);

	int getUserid();

	/*
	 * Mutator method to set the files private field of this class
	 * for Inversion of Control.
	 */
	void setFiles(List<ProjectFile> f);

	List<ProjectFile> getFiles();

}