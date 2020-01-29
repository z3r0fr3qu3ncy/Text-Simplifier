package ie.gmit.dip;

import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author John Heffernan
 * 
 *         Our File Parser class extends parent Parser class and specifies the
 *         parse() method implementation from parent class. It lexes, it parses
 *         and as it's a file parser it can write an output file too.
 * 
 *         This class of parser is for working with larger volume text input
 *         files and writes the output to a text file.
 *
 */
public class FileParser extends Parser {
	private String filePath = "./TestFile.txt";
	private String googleWordsFile = "./google-1000.txt";
	private String mobyThesaurus2File = "./MobyThesaurus2.txt";
	private static List<String> wordsIn = new ArrayList<>();
	private List<String> translated = new ArrayList<>();
	private static Parser builder;
	private String outputFilename;

	public FileParser() {
		super();

	}

	public FileParser(String filepath, String googleWordsFile, String mobyThesaurus2File, String outputFile) {
		super();
		this.filePath = filepath;
		this.googleWordsFile = googleWordsFile;
		this.mobyThesaurus2File = mobyThesaurus2File;
		this.outputFilename = outputFile + ".txt";
	}

	/**
	 * The FileParser class implementation of parse() method delegates mapping to
	 * helper class before getting the google word equivalents and passing to a file
	 * writer method. Input variables for targets and output are handed to
	 * constructor from user interface.
	 */
	public void parse() throws IOException {
		builder = new DictionParser(googleWordsFile, mobyThesaurus2File);
		builder.parse(); // delegation of the mapping functionality to a DictionParser utility/helper
							// class.
		this.getWords();
		String toFile = this.translate();
		writeToFile(toFile, this.outputFilename);
	}

	/**
	 * Method getWords() reads input from a target file adding tokenized input to
	 * our storage List of String or more specifically - ArrayList and will do it at
	 * O(1) after it gets input.
	 * 
	 * @throws IOException
	 */
	public void getWords() throws IOException {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
			String currentLine;
			String[] wordsArray;

			while (true) {
				currentLine = br.readLine();
				if (currentLine == null) {
					break;
				} else {
					wordsArray = currentLine.split(" ");
					for (String item : wordsArray) {
						wordsIn.add(item); // Add to ArrayList - Big O - O(1)
					}
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * With our storage instance variables primed with tokens its time to translate.
	 * This method reads tokens and adds translated items to storage object at 0(1)
	 * after looking up the mapped reference from the DicionParser classes mapping
	 * of googleword equivalents. If there is no match the original input is passes.
	 * Method returns a string to driving parse() method which is consumed by
	 * writeToFile()
	 * 
	 * 
	 * @return - returns the translated version as a string.
	 */
	public String translate() {
		for (String item : wordsIn) { // ArrayList Translated.add() - Big O - O(1)
			translated.add(((DictionParser) builder).getGoogleEquivalent(item)); // DictionParser is polymorphic. It is
																					// a Parser and is a DictionParser
																					// so cast is acceptable here.
		} // Putting getGoogleEquivalent() in the Parser superclass seemed silly, some of
			// these parsers have nothing to do with that functionality directly.
		StringBuilder sb = new StringBuilder();
		for (String i : translated) {
			sb.append(i);
			sb.append(" ");
		}
		System.out.println(ConsoleColour.CYAN_BOLD_BRIGHT);
		System.out.println("\nTranslation >> ");
		System.out.print(sb);
		System.out.println("\n");
		System.out.println(ConsoleColour.RESET);
		return sb.toString();
	}

	/**
	 * This method takes a translated text String and a target output filename and
	 * writes output to that file.
	 * 
	 * @param text
	 * @param out
	 */
	public void writeToFile(String text, String out) {
		File outgoing = new File(System.getProperty("user.dir") + "/" + this.outputFilename);

		try (PrintStream outprint = new PrintStream(new FileOutputStream(outgoing))) {
			outprint.print(text);
			outprint.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
