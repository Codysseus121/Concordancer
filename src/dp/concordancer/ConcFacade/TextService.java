package dp.concordancer.ConcFacade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import dp.concordancer.interfaces.FileDataAccessObject;
import dp.concordancer.interfaces.TextServiceInterface;
import dp.dao.concordancer.FileDao;
import dp.model.concordancer.Kwic;
import dp.model.concordancer.Project;
import dp.model.concordancer.ProjectFile;
import dp.model.concordancer.SuffixArrayX;
import dp.model.concordancer.User;

public class TextService implements TextServiceInterface {
	
	/*
	 * A method to calculate all possible case permutations of a String.
	 * 
	 * @Return: List.
	 * 
	 * @Author: https://www.geeksforgeeks.org/permute-string-changing-case/
	 * 
	 */
	public List<String> permute(String input) {

		List<String> permutations = new ArrayList<String>();
		int n = input.length();

		// Number of permutations is 2^n
		int max = 1 << n;

		// Converting string to lower case
		input = input.toLowerCase();

		// Using all subsequences and permuting them
		for (int i = 0; i < max; i++) {
			char combination[] = input.toCharArray();

			// If j-th bit is set, we convert it to upper case
			for (int j = 0; j < n; j++) {
				if (((i >> j) & 1) == 1)
					combination[j] = (char) (combination[j] - 32);
			}

			permutations.add(String.valueOf(combination));

		}
		return permutations;
	}
	/*
	 * Method getKwic to generate kwic lines.
	 * 
	 * @param ProjectFile file: the source text file.
	 * 
	 * @param String query: the pattern to be matched
	 * 
	 * @param int context: the number of characters for left and right context
	 * 
	 * @return: a List object.
	 */
	public List<Kwic> getKwic(String text, String filename, String query, int context) {
		
		int n = text.length();
		List<Kwic> words = new ArrayList<Kwic>();
		SuffixArrayX sa = new SuffixArrayX(text);
		List<String> perms = permute(query);

		// find all occurrences of queries with context and add to arraylist

		for (String permutation : perms) {

			for (int i = sa.rank(permutation); i < n; i++) {
				Kwic k = new Kwic();

				int from1 = sa.index(i);
				int to1 = Math.min(n, from1 + permutation.length());
				if (!permutation.equals(text.substring(from1, to1)))
					break;
				int from2 = Math.max(0, sa.index(i) - context);
				int to2 = Math.min(n, sa.index(i) + context + query.length());

				k.setKeyword(permutation);
				k.setFilename(filename);
				k.setLcontext(text.substring(from2, from1));
				k.setRcontext(text.substring(to1, to2));
				k.setIndex1(from1);
				k.setIndex2(to1);
				words.add(k);

			}
		}
		return words;
	}

	/*
	 * Method getConcordances to get all kwics in context for a Project
	 * 
	 * @return: List<Kwic> object with the kwics in context.
	 * 
	 */
	public List<Kwic> getConcordances(User u, Project p, String query) {

		int context = 55;// 55 characters on either side. Can be refactored according to user
							// requirements or to accept user input.
		List<Kwic> c = new ArrayList<Kwic>();
		List<Kwic> all = new ArrayList<Kwic>();
		ConcordancerFacade service = new ConcFacadeImpl();

		// read in text per file
		List<ProjectFile> files = service.getFiles(p, u);

		for (ProjectFile file : files) {

			c = getKwic(file.getFilecontent(), file.getFile_name(), query, context);
			all.addAll(c);
		}

		return all;
	}

	public List<Kwic> getCollocates(List<Kwic> concordances, List<String> permutations) {
		
		List<Kwic> collocates = new ArrayList<Kwic>();
		
		for (Kwic word : concordances) { // check for collocates
			
			String lcontext = word.getLcontext();
			String rcontext = word.getRcontext();
			
			for (String perm : permutations) 
			{
				if (lcontext.contains(perm) || rcontext.contains(perm))
					collocates.add(word);
			}
			
		}
		return collocates;

	}
	
	/*
	 * private method generateIndex() to generate the alphabetical index
	 * of all the files in a project.
	 */
		public TreeMap<String, Integer> generateIndex(Project project, User user) {

			// Get all Files for this Project.
			
			ConcordancerFacade service = new ConcFacadeImpl();
			List<ProjectFile> files = service.getFiles(project, user);

			// Create a Map to store the index-frequency list
			TreeMap<String, Integer> index = new TreeMap<String, Integer>();
			List<String> words = new ArrayList<String>();// an ArrayList to store the tokens

			// Scanner object to tokenize text
			Scanner scanner = null;

			for (ProjectFile file : files) {
				String text = file.getFilecontent();
				text = processText(text);
				scanner = new Scanner(text);

				while (scanner.hasNext()) {
					String word = scanner.next().trim();
					words.add(word);
				}
			}
			// Take tokenized text and create index
			for (String word : words) {

				if (index.containsKey(word)) {
					index.put(word, index.get(word) + 1);

				}

				else {
					index.put(word, 1);
				}
			}

			return index;
		}
	/*
	 * private method processText() to pre-process the text
	 * for tokenization. Removes punctuation and numbers. Used by generateIndex() method.
	 */
		private String processText(String text) {
			text = text.replaceAll("[\\p{Space}\\p{Punct}]", " ").trim();
			text = text.replaceAll("\\s{2,}", " ");
			text = text.replaceAll("[0-9]", " ");
			return text;
		}
		/*
		 * A method to generate more context for a given kwic line.
		 */
		public String moreContext(int findex, int lindex, int context, User u, Project p, String filename) {

			// Get content of file
			FileDataAccessObject fdao = new FileDao();
			String morecontext = "";
			String text = fdao.getFile(p, u, filename);
			if (findex - context >= 0 && lindex + context <= text.length()) {
				morecontext = text.substring(findex - context, lindex + context);
			}

			else if (findex - context < 0 && lindex + context <= text.length()) {
				morecontext = text.substring(0, lindex + context);
			}

			else if (lindex + context > text.length() && findex - context >= 0) {
				morecontext = text.substring(findex - context, text.length());
			} else {
				morecontext = text;
			}

			return morecontext;
		}

	
}
