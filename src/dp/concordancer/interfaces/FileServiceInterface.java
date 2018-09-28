package dp.concordancer.interfaces;

import java.io.IOException;

import javax.servlet.http.Part;

/*
 * Interface FileServiceInterface for converting files to String objects.
 * 
 */
public interface FileServiceInterface {

	String getText (Part part, String fextension) throws IOException;
	
}
