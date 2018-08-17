package dp.model.concordancer;

import java.io.Serializable;

/* Class ProjectFile to handle file objects 
 * for a Project object, implementing standard
 * accessor and mutator methods for all fields
 * of the class.
 * 
 */

public class ProjectFile implements Serializable, ProjectFileInterface {

	private static final long serialVersionUID = 8614571457975965833L;
	private int file_id;
	private String file_name;
	private String filecontent;
	private int project_id;

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectFileInterface#setFile_id(int)
	 */
	@Override
	public void setFile_id(int id) {
		this.file_id = id;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectFileInterface#getFile_id()
	 */
	@Override
	public int getFile_id() {
		return file_id;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectFileInterface#setFile_name(java.lang.String)
	 */
	@Override
	public void setFile_name(String file) {
		this.file_name = file;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectFileInterface#getFile_name()
	 */
	@Override
	public String getFile_name() {
		return file_name;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectFileInterface#setProject_id(int)
	 */
	@Override
	public void setProject_id(int id) {
		this.project_id = id;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectFileInterface#getProject_id()
	 */
	@Override
	public int getProject_id() {
		return project_id;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectFileInterface#setFilecontent(java.lang.String)
	 */
	@Override
	public void setFilecontent(String content) {
		filecontent = content;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectFileInterface#getFilecontent()
	 */
	@Override
	public String getFilecontent() {
		return filecontent;
	}

	/* (non-Javadoc)
	 * @see dp.model.concordancer.ProjectFileInterface#toString()
	 */
	@Override
	public String toString() {
		return file_name;
	}
}
