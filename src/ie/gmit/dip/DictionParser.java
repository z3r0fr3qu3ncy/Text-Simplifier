package ie.gmit.dip;

import java.io.*;
import java.util.*;

/**
 * 
 * @author John Heffernan
 * 
 *         The DictionParserClass is child of Parser class which implements
 *         parse() method from parent class. This class is a helper/utility
 *         class and is not called directly by Runner and is abstracted from the
 *         user. We use an instance of this class to build our mapping of
 *         googlewords to thesaurus.
 * 
 *
 */
public class DictionParser extends Parser {
	private static Map<String, String> map = new TreeMap<>();
	private static Set<String> set = new TreeSet<>(); //Sets enforce uniqueness constraint
	private String googleWordsFile = "./google-1000.txt";
	private String mobyThesaurus2File = "./MobyThesaurus2.txt";

	public DictionParser() {
		super();
	}

	public DictionParser(String googleWordsFile, String mobyThesaurus2File) {
		super();
		this.googleWordsFile = googleWordsFile;
		this.mobyThesaurus2File = mobyThesaurus2File;
	}

	/**
	 * The DictionParser class implementation of abstract parse() method from
	 * Parser. This method builds our mapping of google words to thesaurus words.
	 */
	public void parse() throws IOException {
		BufferedReader br = null;
		String line = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(googleWordsFile))));
			while ((line = br.readLine()) != null) {
				map.put(line, line); // Big O of O(log n) as we are using TreeMap and the underlying architecture is
										// a red black binary tree. Could do better with HashMap but not ordered and
										// collision possible in standard implementation.
				set.add(line); // Guaranteed Big O of O(log n) with TreeSet for basic operations like add,
								// contains.
			}
		} catch (IOException e) {
			System.err.println("AnIOException was caught");
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.err.println("AnIOException was caught");
				e.printStackTrace();
			}
		}
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(mobyThesaurus2File))));
			while ((line = br.readLine()) != null) {
				String[] words = line.split(",");
				String googleWord = null;
				for (String word : words) {
					if (set.contains(word)) { // Big O of O(log n) for set.contains operation on TreeSet 'set'.
						googleWord = word;
						break;
					}
				}

				if (googleWord != null) {
					addAll(words, googleWord);
				}
			}
		} catch (IOException e) {
			System.err.println("AnIOException was caught");
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.err.println("AnIOException was caught");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Adds items to Treemap with the put() function iteratively. Takes a String
	 * array of words and creates a mapping of googleword equivaluents.
	 * 
	 * 
	 * @param words the word we want mapped
	 * @param googleWord the google word equivalent word
	 */
	private void addAll(String[] words, String googleWord) {
		for (String word : words) {
			map.put(word, googleWord); // Big O of O(log(n))
		}
	}

	/**
	 * Method getGoogleWordEquivalent(String word) does what it says on tin.
	 * Replacing and lowercasing erudite words with commonplace version in return.
	 * 
	 * @param word - the word we want to compare with our mapping
	 * @return the map reference
	 */
	public String getGoogleEquivalent(String word) {
		if (map.containsKey(word)) { // Big O of O(log(n))
			return map.get(word.toLowerCase()); // Big O of O(log(n))
		} else {
			return ("\033[0;31m" + word.toLowerCase() + "\033[1;96m");
		}
	}

	/**
	 * This is a simple map copy or get map function so we work with copies rather
	 * than handling our stores directly. Defensive cloning. Returns a copy of the
	 * Treemap.
	 * 
	 * @return - returns a copy of the map
	 */
	public static Map<String, String> getMap() {
		Map<String, String> mapCopy = new TreeMap<>();
		mapCopy.putAll(map); // iterates over add @ O(log(n)) for n times.
		return mapCopy;
	}

	/**
	 * This is the copy method for our TreeSet so we can defend the original object
	 * which is private instance variable.
	 * 
	 * @return - returns a copy of the set
	 */
	public Set<String> getSet() {
		Set<String> setCopy = new TreeSet<>();
		setCopy.addAll(set); // O(log(n)) for n iterations
		return setCopy;
	}
}
