package dp.servlets.concordancer;


import java.io.IOException;
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

import dp.concordancer.ConcFacade.ConcFacadeImpl;
import dp.concordancer.ConcFacade.ConcordancerFacade;

import dp.model.concordancer.Project;
import dp.model.concordancer.User;

/**
 * Servlet implementation class UploadServlet. A servlet that handles requests
 * and responses for the creation of a new project by a registered User and the
 * uploading of the files requested.
 */
@WebServlet("/UploadServlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) // the maximum limit of the files that can be uploaded

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
	 * Code adapted from: Servlets & JSP: a tutorial, by Budi Kurniawan Method
	 * getFilename() to get the filename of the request for file upload.
	 * 
	 * @param part:
	 *            the file to be uploaded.
	 * @return the filename as String object.
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

		// First set the context and get necessary parameters for the creation of the
		// new project.
		HttpSession session = request.getSession(true);
		RequestDispatcher dispatcher = null;
		response.setContentType("text/html");
		ConcordancerFacade service = new ConcFacadeImpl();
		
		String projectname = request.getParameter("projectname");
		int projectid = 0;
		User user = (User) session.getAttribute("currentSessionUser");
		Collection<Part> parts = request.getParts();// get all Part objects sent

		// If collection not empty, create new project.
		if (parts != null) {
			projectid = service.createProject(user, projectname);
		}

		boolean validfname = false;
		for (Part part : parts) {
			if (part.getContentType() != null) {

				String fileName = getFilename(part);// get the filename of the part from the request
				String fextension = getFileExtension(fileName);// get the extension of the filename
				validfname = validFile(fileName);// validate filename extension and if valid add file.
				if (fileName != null && !fileName.isEmpty() && (validfname == true)) {

					try {

						String text = service.getText(part, fextension);
						service.addFiles(fileName, projectid, text);// call method addFiles() of FileDao
						Project project = service.getProject(projectid, user);// get project and set it as attribute
						session.setAttribute("currentproject", project);

					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}
			}
		}
		dispatcher = getServletContext().getRequestDispatcher("/ConcordancerServlet");
		dispatcher.forward(request, response);
		return;
	}

	/*
	 * Method validFile() to check filename extensions. Server-side validation.
	 * Method To be extended with MIME-type checking. Uses Apache Commons
	 * FileNameUtils Library.
	 * 
	 * @boolean: true if the the filename String parameter contains one of the
	 * accepted file extensions.
	 * 
	 */
	private boolean validFile(String fileName) {
		return Arrays.asList("txt", "pdf", "html").contains(FilenameUtils.getExtension(fileName));
	}

	/*
	 * Method getFileExtension to get a String representation of the filename's extension.
	 * 
	 * @return: String.
	 */
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
