package dp.concordancer.ConcFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import dp.dao.concordancer.*;
import dp.model.concordancer.Kwic;
import dp.model.concordancer.Project;
import dp.model.concordancer.ProjectFile;
import dp.model.concordancer.SuffixArrayX;
import dp.model.concordancer.User;

public class ConcFacadeImpl implements ConcordancerFacade {
	private GetUserDao udao = new GetUserDao();
	private RegisterDao rdao = new RegisterDao();
	private ProjectDao pdao = new ProjectDao();
	private FileDao fdao = new FileDao();

	public GetUserDao GUDao() {
		return udao;
	}

	public RegisterDao RDao() {
		return rdao;
	}

	public ProjectDao PDao() {
		return pdao;
	}

	public FileDao FDao() {
		return fdao;
	}

	public User getUser(String name, String password) {
		return udao.getUser(name, password);
	}

	public boolean checkUserName(String username) {
		return rdao.checkUserName(username);
	}

	public void registerUser(String name, String password) {
		rdao.registerUser(name, password);
	}

	public List<Project> getProjects(User user) {
		return pdao.getProjects(user);
	}

	public Project getProject(int id, User u) {
		return pdao.getProject(id, u);
	}

	public void deleteProject(User u, int pid) {
		pdao.deleteProject(u, pid);
	}

	public int createProject(User user, String projectname) {
		return pdao.createProject(user, projectname);
	}

	public List<ProjectFile> getFiles(Project project, User user) {
		return fdao.getFiles(project, user);
	}

	public String getFile(Project project, User user, String filename) {
		return fdao.getFile(project, user, filename);
	}

	public void addFiles(String filename, int projectid, String text) {
		fdao.addFiles(filename, projectid, text);
	}

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
	public List<Kwic> getKwic(ProjectFile file, String query, int context) {
		String text = file.getFilecontent();
		int n = file.getFilecontent().length();
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
				k.setFilename(file.getFile_name());
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
							// requirements.
		List<Kwic> c = new ArrayList<Kwic>();
		List<Kwic> all = new ArrayList<Kwic>();

		// read in text per file
		List<ProjectFile> files = getFiles(p, u);

		for (ProjectFile file : files) {

			c = getKwic(file, query, context);
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
			
			List<ProjectFile> files = getFiles(project, user);

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
	 * for tokenization. Removes punctuation and numbers.
	 */
		private String processText(String text) {
			text = text.replaceAll("[\\p{Space}\\p{Punct}]", " ").trim();
			text = text.replaceAll("\\s{2,}", " ");
			text = text.replaceAll("[0-9]", " ");
			return text;
		}


	

}
