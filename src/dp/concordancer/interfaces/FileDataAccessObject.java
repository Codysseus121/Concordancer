package dp.concordancer.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.Part;

import dp.model.concordancer.Project;
import dp.model.concordancer.ProjectFile;
import dp.model.concordancer.User;

public interface FileDataAccessObject {

	public void addFiles(String filename, int projectid, String filetype, Part filecontent) throws SQLException, IOException;
	public void insertStream(String filename, int projectid, String text);
	public String convertTxt(Part filecontent) throws IOException;
	public String processText(String text);
	public String convertPdf(Part filecontent) throws IOException;
	public List<ProjectFile> getFiles(Project project, User user);
	public String getFile (Project project, User user, String filename);
	
	
}
