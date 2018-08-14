package dp.model.concordancer;

import java.util.List;

public class Concordances {
	
	private List<Kwic> concordances;
	
	
	public Concordances(List<Kwic> concordances)
	{
		this.concordances = concordances;
	
	}

public List<Kwic> getConcordances()

{
	return concordances;
}

public void setConcordances(List<Kwic> concs)
{
	concordances = concs;
}
	
}
