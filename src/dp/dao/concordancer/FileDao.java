package dp.dao.concordancer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Part;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import dp.concordancer.interfaces.FileDataAccessObject;
import dp.model.concordancer.Project;
import dp.model.concordancer.ProjectFile;
import dp.model.concordancer.User;

public class FileDao extends GetConnection implements FileDataAccessObject{

	public void addFiles(String filename, int projectid, String filetype, Part filecontent)
			throws SQLException, IOException {

		String instring="";
		
		switch (filetype) {
		case "txt":
			instring = convertTxt(filecontent);
			insertStream(filename, projectid, instring);
			break;
		case "pdf":
			instring = convertPdf(filecontent);
			insertStream(filename, projectid, instring);
			break;
		case "html":
			instring = convertHtml(filecontent);
			insertStream(filename, projectid, instring);
			break;
		}
		
	}

	public void insertStream(String filename, int projectid, String text) {

		PreparedStatement statement = null;
		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			statement = conn.prepareStatement("insert into files (file_name, file_content, project_id) values ( ?, ?, ?)");
			statement.setString(1, filename);
			statement.setString(2, text);
			statement.setInt(3, projectid);
			statement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}

	public String convertTxt(Part filecontent) throws IOException
	{
		//Credit for the conversion: stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
		String result = new BufferedReader(new InputStreamReader(filecontent.getInputStream())).lines().collect(Collectors.joining("\n"));
		
		result = processText(result);       
		return result;
		
	}
	
	public String processText(String text)
	{
		text = text.replaceAll("[^a-zA-Z]"," ").trim();  
		return text;
	}
	

	public String convertPdf(Part filecontent) throws IOException
	{
		
		System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");// necessary due to incompatibility
		String text = "";
		PDDocument document = null;
		InputStream stream = null;
		try {
			stream = filecontent.getInputStream();
			document = PDDocument.load(stream);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			text = pdfStripper.getText(document);
			text = processText(text);
			

		} finally {

			document.close();			
			stream.close();
		}
		return text;
	}

	public String convertHtml(Part filecontent) throws IOException
	{
		
		InputStream stream = null;
		String finaltext="";
		
		try
		{
		stream = filecontent.getInputStream();		
		Document doc = Jsoup.parse(stream, "UTF-8", "");
        String plaintxt = doc.toString();
        finaltext = Jsoup.parse(plaintxt).body().text();
        finaltext = processText(finaltext);
        
		}
		catch (IOException ex) {ex.printStackTrace();}
		finally
		{
        stream.close();
        
        		}
		return finaltext;
	}

	public List<ProjectFile> getFiles(Project project, User user)
	{
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		int project_id = project.getProject_id();
		
		List <ProjectFile> filelist = new ArrayList<ProjectFile>();//an arraylist with the filecontents per file
		
		
		
		try
		{
			//First get ids of all project files
			conn = getConnection();			 
			String sql = "SELECT file_id, file_name, file_content from files F, project P, users U where U.user_id=P.user_id AND P.project_id=F.project_id AND F.project_id=" + project_id + ";";
			statement = conn.prepareStatement(sql);
			set = statement.executeQuery();
			while (set.next())//add files ids to arraylist
			{				
				ProjectFile f = new ProjectFile();
				f.setFile_id(set.getInt("FILE_ID"));
				f.setFile_name(set.getString("FILE_NAME"));
				f.setFilecontent(set.getString("FILE_CONTENT"));
				filelist.add(f);
			
			
			}							
			
		}
		
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			try {
				set.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return filelist;
	}
	public String getFile (Project project, User user, String filename)
	{
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		int project_id = project.getProject_id();
		String text = "";
		
		
		try
		{
			
			conn = getConnection();			 
			String sql = "SELECT file_content from files F, project P, users U where U.user_id=P.user_id AND P.project_id=F.project_id AND P.project_id="+project_id+ " AND F.file_name='" + filename + "';";
			statement = conn.prepareStatement(sql);
			set = statement.executeQuery();
			while (set.next())//add files ids to arraylist
			{				
				text = set.getString("FILE_CONTENT");			
			
			}							
			
		}
		
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			try {
				set.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return text;
	}

	
	
	
	
}
