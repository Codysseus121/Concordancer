package dp.model.concordancer;

/*
 * class Kwic: a class for generating keywords in context,
 * implementing standard mutator and accessor methods for all
 * class fields.
 */

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
	private int index1;
	private int index2;
	
	
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
	
	public int getIndex1()
	{
		return index1;
	}
	
	public void setIndex1(int index)
	{
		index1 = index;
	}
	
	public int getIndex2()
	{
		return index2;
	}
	
	public void setIndex2(int index)
	{
		index2 = index;
	}
	
	
	
@Override
	public String toString()
	{
		return lcontext + keyword + rcontext;
	}
	
	
}
