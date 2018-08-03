package dp.model.concordancer;

public class KWIC {

	/**
	 *  The {@code KWIK} class provides a {@link SuffixArray} client for computing
	 *  all occurrences of a keyword in a given string, with surrounding context.
	 *  This is known as <em>keyword-in-context search</em>.
	 *  <p>
	 *  For additional documentation,
	 *  see <a href="https://algs4.cs.princeton.edu/63suffix">Section 6.3</a> of
	 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
	 *
	 *  @author Robert Sedgewick
	 *  @author Kevin Wayne
	 */
	public class KWIK {

	    // Do not instantiate.
	    private KWIK() { }

	    /**
	     * Reads a string from a file specified as the first
	     * command-line argument; read an integer k specified as the
	     * second command line argument; then repeatedly processes
	     * use queries, printing all occurrences of the given query
	     * string in the text string with k characters of surrounding
	     * context on either side.
	     *
	     * @param args the command-line arguments
	     */
	    public void main(String[] args) {
	       ProjectDao pdao = new ProjectDao();
	        int context = 50;

	        // read in text
	        String text = pdao.getFile();
	        int n = text.length();

	        // build suffix array
	        SuffixArrayX sa = new SuffixArrayX(text);

	        // find all occurrences of queries and give context
	        while (StdIn.hasNextLine()) {
	            String query = StdIn.readLine();
	            for (int i = sa.rank(query); i < n; i++) {
	                int from1 = sa.index(i);
	                int to1   = Math.min(n, from1 + query.length());
	                if (!query.equals(text.substring(from1, to1))) break;
	                int from2 = Math.max(0, sa.index(i) - context);
	                int to2   = Math.min(n, sa.index(i) + context + query.length());
	                StdOut.println(text.substring(from2, to2));
	            }
	            StdOut.println();
	        }
	    } 
	} 

	
	
}
