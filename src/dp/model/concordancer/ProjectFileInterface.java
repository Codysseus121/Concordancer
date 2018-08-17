package dp.model.concordancer;

public interface ProjectFileInterface {

	void setFile_id(int id);

	int getFile_id();

	void setFile_name(String file);

	String getFile_name();

	void setProject_id(int id);

	int getProject_id();

	void setFilecontent(String content);

	String getFilecontent();

	String toString();

}