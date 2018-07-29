package dp.servlets.concordancer;

import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

/**
 * Credit: Servlets & JSP: a tutorial, by Budi Kurniawan	
 * @param part
 * @return
 */
	  private String getFilename(Part part) {
	        String contentDispositionHeader =
	                part.getHeader("content-disposition");
	        String[] elements = contentDispositionHeader.split(";");
	        for (String element : elements) {
	            if (element.trim().startsWith("filename")) {
	                return element.substring(element.indexOf('=') + 1)
	                        .trim().replace("\"", "");
	            }
	        }
	        return null;
	    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String uploadpath = "C:\\Users\\Mitsos\\TempUserFiles";
       Collection<Part> parts = request.getParts();
       boolean validfname = false;
        for (Part part : parts) {
            if (part.getContentType() != null) {
                // save file Part to disk
                String fileName = getFilename(part);
                validfname = validFile(fileName);
                if (fileName != null && !fileName.isEmpty() && (validfname==true)) {
                    part.write(uploadpath + File.separator + fileName);
                    writer.print("<br/>Uploaded file name: " +
                            fileName);
                    writer.print("<br/>Size: " + part.getSize());
                }
            } else {
                // print field name/value
                String partName = part.getName();
                String fieldValue = request.getParameter(partName);
                writer.print("<br/>" + partName + ": " +
                        fieldValue);
            }
        }
    }

	private boolean validFile(String fileName) {
	    return Arrays.asList("txt", "pdf", "html")
	        .contains(org.apache.commons.io.FilenameUtils.getExtension(fileName));
	}
	
}


