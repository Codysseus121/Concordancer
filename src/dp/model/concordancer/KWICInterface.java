package dp.model.concordancer;

public interface KWICInterface {

	void setLcontext(String l);

	String getLcontext();

	void setRcontext(String r);

	String getRcontext();

	void setKeyword(String k);

	String getKeyword();

	void setFilename(String f);

	String getFilename();

	int getIndex1();

	void setIndex1(int index);

	int getIndex2();

	void setIndex2(int index);

	String toString();

}