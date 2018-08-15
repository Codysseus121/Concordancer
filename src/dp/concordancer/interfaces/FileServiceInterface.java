package dp.concordancer.interfaces;

import java.io.IOException;

import javax.servlet.http.Part;

public interface FileServiceInterface {

	String getText (Part part, String fextension) throws IOException;
	
}
