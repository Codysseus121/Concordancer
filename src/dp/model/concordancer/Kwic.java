package dp.model.concordancer;

import java.io.Serializable;

public class Kwic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8439524116323436712L;
	private String lcontext;
	private String keyword;
	private String rcontext;
	private String filename;
	
	public Kwic(String lcontext, String keyword, String rcontext, String filename)
	{
		this.lcontext = lcontext;
		this.keyword = keyword;
		this.rcontext = rcontext;
		this.filename = filename;
	}
	
	public Kwic()
	{
		
	}
	
	public void setLcontext (String l)
	{
		lcontext = l;
	}
	
	public String getLcontext ()
	{
		return lcontext;
		}
	
	public void setRcontext (String r)
	{
		rcontext = r;
	}
	
	public String getRcontext ()
	{
		return rcontext;
	}
	
	public void setKeyword (String k)
	{
		keyword = k;
	}
	
	public String getKeyword ()
	{
		return keyword;
	}
	
	public void setFilename (String f)
	{
		filename = f;
	}
	
	public String getFilename()
	{
		return filename;
		}
	
	
}
