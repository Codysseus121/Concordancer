package dp.model.concordancer;
import java.io.Serializable;

public class Collocate {
	
	
	private static final long serialVersionUID = -8439514116323436712L;
	private String lcontext;
	private String keyword;
	private String rcontext;
	private String collocate;
	private String filename;
	private int index1;
	private int index2;
	
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
	
	public void setCollocate (String c)
	{
		collocate = c;
	}
	
	public String getCollocate ()
	{
		return collocate;
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
