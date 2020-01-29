package ie.gmit.dip;

import java.io.*;
import java.util.*;

/**
 * 
 * @author John Heffernan
 * 
 *         The <b>UserInputParser class</b> is for parsing user input from the
 *         console. It overrides the parent Parser class parse() method,
 *         implementing it in a <i>specific</i> manner. It is used for cases of
 *         low volume manual user input.
 */
public class UserInputParser extends Parser {
	private static List<String> tokenized = new ArrayList<>(); //Allows us to rapidly add our user input to storage.
	Parser builder = new DictionParser();

	public UserInputParser() throws IOException {
		super();
	}

	/**
	 * Our specific implementation of Parsers abstract parse() method. This
	 * implementation tokenizes manual user input adding it to storage before
	 * calling the translate method.
	 */
	public void parse() throws IOException {
		builder.parse(); // delegate parsing of dictionary thesaurus mappings to DictionParser class
							// instance.
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(System.in));
		String line;
		String[] items;
		while ((line = bufIn.readLine()) != null) {
			items = line.split(" ");
			for (String each : items) {
				tokenized.add(each); // Big O of O(1) or constant time to add item to Arraylist.
			}
			tokenized.add("\n"); // $tokenized is arraylist so Big O is O(1) for .add()
			if (line.contains("##")) {
				System.out.println("Shutting input & preparing translation..");
				break;
			}
		}
		translate();
		// sc.close();
	}

	/**
	 * The method translate takes our tokenized ArrayList of input and checks for
	 * match with the mapping built from our googlewords and thesaurus files. It
	 * then uses string builder to assemble the translated response before printing
	 * the output to the console. This method is for cases of small volume manual
	 * user input. We are not taking inputs as, bar this reveal, our storage methods
	 * would be further obfuscated. We are working with the private instance
	 * variable set up to hold our tokens then outputting directly before return
	 * control to Runner main.
	 */
	public void translate() {
		List<String> translated = new ArrayList<>();
		for (String word : tokenized) {
			translated.add(((DictionParser) builder).getGoogleEquivalent(word)); // O(1) here also.
		}
		StringBuilder sb = new StringBuilder();
		for (String i : translated) {
			sb.append(i);
			sb.append(" ");
		}
		System.out.println(ConsoleColour.YELLOW_BOLD_BRIGHT);
		System.out.println("\nTranslation >> ");
		System.out.println(ConsoleColour.CYAN_BOLD_BRIGHT);
		System.out.print(sb);
		System.out.println("\n");
		System.out.println(ConsoleColour.RESET);
		
	}

}
