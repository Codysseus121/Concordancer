package dp.concordancer.ConcFacade;

/*
 * The FileService class is a service class providing methods
 * for file operations, converting files to String objects.
 */

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.http.Part;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper; 
import org.apache.fontbox.FontBoxFont;
import org.apache.commons.io.FilenameUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import dp.concordancer.interfaces.FileServiceInterface;

public class FileService implements FileServiceInterface {

	/*
	 * (non-Javadoc)
	 * @see dp.concordancer.interfaces.FileServiceInterface#getText(javax.servlet.http.Part, java.lang.String)
	 */
	
	public  String getText (Part part, String fextension) throws IOException
	{
		String text = "";
		
		switch (fextension) 
		{
		case "txt":
			text = convertTxt(part);
			break;

		case "pdf":
			text = convertPdf(part);
			break;

		case "html":
			text = convertHtml(part);
			break;
		}

		
		return text;
	}
		
		

		
		/*
		 * Method convertTxt to pre-process plain text files.
		 * @param Part filecontent: the Part object that contains the contents of the file uploaded.
		 */

		private String convertTxt(Part filecontent) throws IOException {
			// Source: https://docs.oracle.com/javase/tutorial/i18n/text/stream.html
			StringBuffer buffer = new StringBuffer();
			InputStreamReader isr = new InputStreamReader(filecontent.getInputStream());
			Reader in = new BufferedReader(isr);
			int ch;
			while ((ch = in.read()) > -1) {
				buffer.append((char) ch);
			}
			in.close();
			String result = buffer.toString();

			return result;

		}
		
		public String convertFileName(String filename) throws IOException {
			// Source: https://docs.oracle.com/javase/tutorial/i18n/text/stream.html
			StringBuffer buffer = new StringBuffer();
			InputStream isr = new  ByteArrayInputStream(filename.getBytes());
			InputStreamReader reader = new InputStreamReader(isr);
			Reader in = new BufferedReader(reader);
			int ch;
			while ((ch = in.read()) > -1) {
				buffer.append((char) ch);
			}
			in.close();
			String result = buffer.toString();
			System.out.println(result);

			return result;

		}
		
		
		/*
		 * Method convertPdf to pre-process and convert PDF files to text.
		 * Uses the ApachePDFBox library for the conversion
		 * @param Part filecontent: the Part object that contains the contents of the file uploaded.
		 */
		private String convertPdf(Part filecontent) throws IOException {

			System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");// necessary due to
																							// incompatibility
			String text = "";
			PDDocument document = null;
			InputStream stream = null;
			try {
				stream = filecontent.getInputStream();
				document = PDDocument.load(stream);
				PDFTextStripper pdfStripper = new PDFTextStripper();
				text = pdfStripper.getText(document);

			} finally {

				document.close();
				stream.close();
			}
			return text;
		}
		/*
		 * Method convertHtml to pre-process and convert HTML files to plain text.
		 * Use the JSoup library.
		 * @param Part filecontent: the Part object that contains the contents of the file uploaded.
		 */
		private String convertHtml(Part filecontent) throws IOException {

			InputStream stream = null;
			String finaltext = "";

			try {
				stream = filecontent.getInputStream();
				Document doc = Jsoup.parse(stream, "UTF-8", "");
				String plaintxt = doc.toString();
				finaltext = Jsoup.parse(plaintxt).body().text();
				

			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				stream.close();

			}
			return finaltext;
		}
	




}
