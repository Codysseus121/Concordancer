package dp.model.concordancer;

/*
 * class Kwic: a class for generating keywords in context,
 * implementing standard mutator and accessor methods for all
 * class fields.
 */

import java.io.Serializable;

public class Kwic implements Serializable, KWICInterface, Comparable<KWICInterface> {

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
	
	/* (non-Javadoc)
	 * @see dp.model.concordancer.KWICInterface#setLcontext(java.lang.String)
	 */
	@Override
	public void setLcontext (String l)
	{
		lcontext = l;
	}
	
	/* (non-Javadoc)
	 * @see dp.model.concordancer.KWICInterface#getLcontext()
	 */
	@Override
	public String getLcontext ()
	{
		return lcontext;
		}
	
	/* (non-Javadoc)
	 * @see dp.model.concordancer.KWICInterface#setRcontext(java.lang.String)
	 */
	@Override
	public void setRcontext (String r)
	{
		rcontext = r;
	}
	
	/* (non-Javadoc)
	 * @see dp.model.concordancer.KWICInterface#getRcontext()
	 */
	@Override
	public String getRcontext ()
	{
		return rcontext;
	}
	
	/* (non-Javadoc)
	 * @see dp.model.concordancer.KWICInterface#setKeyword(java.lang.String)
	 */
	@Override
	public void setKeyword (String k)
	{
		keyword = k;
	}
	
	/* (non-Javadoc)
	 * @see dp.model.concordancer.KWICInterface#getKeyword()
	 */
	@Override
	public String getKeyword ()
	{
		return keyword;
	}
	
	/* (non-Javadoc)
	 * @see dp.model.concordancer.KWICInterface#setFilename(java.lang.String)
	 */
	@Override
	public void setFilename (String f)
	{
		filename = f;
	}
	
	/* (non-Javadoc)
	 * @see dp.model.concordancer.KWICInterface#getFilename()
	 */
	@Override
	public String getFilename()
	{
		return filename;
	}
	
	/* (non-Javadoc)
	 * @see dp.model.concordancer.KWICInterface#getIndex1()
	 */
	@Override
	public int getIndex1()
	{
		return index1;
	}
	
	/* (non-Javadoc)
	 * @see dp.model.concordancer.KWICInterface#setIndex1(int)
	 */
	@Override
	public void setIndex1(int index)
	{
		index1 = index;
	}
	
	/* (non-Javadoc)
	 * @see dp.model.concordancer.KWICInterface#getIndex2()
	 */
	@Override
	public int getIndex2()
	{
		return index2;
	}
	
	/* (non-Javadoc)
	 * @see dp.model.concordancer.KWICInterface#setIndex2(int)
	 */
	@Override
	public void setIndex2(int index)
	{
		index2 = index;
	}
	
	
	
/* (non-Javadoc)
 * @see dp.model.concordancer.KWICInterface#toString()
 */
@Override
	public String toString()
	{
		return lcontext + keyword + rcontext;
	}
/*
 * (non-Javadoc)
 * @see dp.model.concordancer.KWICInterface#compareTo(dp.model.concordancer.KWICInterface)
 */
@Override 
public int compareTo(KWICInterface kwic)
{
	String otherlcontext = kwic.getLcontext();
	Character otherlastchar = otherlcontext.charAt(otherlcontext.length()-1);
	Character lastchar = lcontext.charAt(lcontext.length()-1);
	return lastchar.compareTo(otherlastchar);
}

	
}
