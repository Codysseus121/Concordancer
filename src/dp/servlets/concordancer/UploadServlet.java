package dp.servlets.concordancer;

import java.io.IOException;
//import java.io.File;
//import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

import dp.concordancer.interfaces.ProjectDataAccessObject;
import dp.dao.concordancer.ProjectDao;
import dp.model.concordancer.Project;
import dp.model.concordancer.User;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * Credit: Servlets & JSP: a tutorial, by Budi Kurniawan
	 * 
	 * @param part
	 * @return
	 */
	private String getFilename(Part part) {
		String contentDispositionHeader = part.getHeader("content-disposition");
		String[] elements = contentDispositionHeader.split(";");
		for (String element : elements) {
			if (element.trim().startsWith("filename")) {
				return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		RequestDispatcher dispatcher = null;
		response.setContentType("text/html");		
		ProjectDataAccessObject pdao = new ProjectDao();		
		String projectname = request.getParameter("projectname");
		int projectid = 0;
		User user = (User) session.getAttribute("currentSessionUser");
		Collection<Part> parts = request.getParts();
		
		//If collection not empty, create new project.
		if (parts != null) {
			projectid = pdao.createProject(user, projectname);
		}

		boolean validfname = false;
		for (Part part : parts) {
			if (part.getContentType() != null) {
				

				String fileName = getFilename(part);
				String fextension = getFileExtension(fileName);
				validfname = validFile(fileName);// server side validation of filename
				if (fileName != null && !fileName.isEmpty() && (validfname == true)) {

					try {

						pdao.addFiles(fileName, projectid, fextension, part);
						Project project = pdao.getProject(projectid, user);
						session.setAttribute("currentproject", project);
						
						
						
					} catch (SQLException ex) {
						ex.printStackTrace();
					}

				}
			}
		}
		dispatcher = getServletContext().getRequestDispatcher("/ConcordancerServlet");
		dispatcher.forward(request, response);
		return;
	}

	private boolean validFile(String fileName) {
		return Arrays.asList("txt", "pdf", "html").contains(FilenameUtils.getExtension(fileName));
	}

	private String getFileExtension(String filename) {
		String extension = "";
		if (filename.endsWith("txt"))
			extension = "txt";
		else if (filename.endsWith("pdf"))
			extension = "pdf";
		else
			extension = "html";
		return extension;
	}

}
