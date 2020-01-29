package ie.gmit.dip;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author John Heffernan
 * @version 0.1
 * @since 1.8
 * 
 *        Common Parlance - codename #SpeakBigly text simplifier. A
 *        colloquialism for every occasion # a average with all work !
 * 
 *        This is the <b>Runner Class</b> of our application. It contains the
 *        main method and handles our basic user interface and high level driver
 *        functionality.
 */
public class Runner {
	// private static List<String> tokenized = new ArrayList<>();
	private static boolean keepRunning = true;
	// private static InputType InputType;

	/**
	 * Our main method runs our showMenu loop for user interaction and farewells the
	 * user before shutdown.
	 * 
	 * @param args
	 * @throws Exception if input incorrect.
	 */
	public static void main(String[] args) throws Exception {
		// System.out.println(ConsoleColour.BLUE_BRIGHT);
		try {
			while (keepRunning) {
				showMenu();
			}
		} catch (Exception e) {
			e.getMessage();
			System.out.println(ConsoleColour.RESET);
			System.out.println("\nError launching translation service. Please check input selections and try again.");
		}
		System.out.print(ConsoleColour.PURPLE);
		System.out.println("Thank you for using GmitBabble - #SpeakBigly");
		System.out.print(ConsoleColour.PURPLE_BOLD_BRIGHT);
		System.out.println("Goodbye");
		System.out.println(ConsoleColour.RESET);
		System.exit(0);
	}

	/**
	 * Runs our menu selection functionality. The user makes a numeric selection
	 * from the options and is then prompted for additional input parameters based
	 * on the mode. showMenu then hands off execution to the relevant class and when
	 * the task has completed the menu will be presented again.
	 * 
	 * @throws IOException
	 */
	public static void showMenu() throws IOException {
		int selection;
		Scanner inNumbers = new Scanner(System.in);
		System.out.println(ConsoleColour.BLUE_BRIGHT);
		System.out.println("***************************************************");
		System.out.println("*          GmitBabble - New Speak Labs            *");
		System.out.println("*             Common Parlance V0.1                *");
		System.out.println("*             Codename: SpeakBigly                *");
		System.out.println("***************************************************");
		System.out.println("            Please select an option");
		System.out.println("---------------------------------------------------");
		System.out.println("1 - Translate user input from console.");
		System.out.println("2 - Specify inputs and output files.");
		System.out.println("3 - Quit");
		System.out.println("\nEnter Selection >> ");
		try {
			selection = inNumbers.nextInt();
			switch (selection) {
			case 1:
				System.out.print(ConsoleColour.GREEN_BRIGHT);
				System.out.println(
						"Please type your input including carriage returns and use chars '##' & hit enter to finish >> ");
				System.out.print("Enter Text>\n");
				System.out.println(ConsoleColour.BLACK_BOLD_BRIGHT);
				Parser p = new UserInputParser();
				p.parse();
				break;
			case 2:
				// System.out.println("Option 2 selected");
				System.out.println(ConsoleColour.GREEN_BRIGHT);
				System.out.println("Please enter the filepath of the file for translation: ");
				Scanner filesin = new Scanner(System.in);
				String fileInput = filesin.nextLine().trim();
				System.out.println("Please enter the Path for the simplified words file: ");
				String googWord = filesin.nextLine().trim();
				System.out.println("Please enter the Path for the thesaurus file: ");
				String thesaurus = filesin.nextLine().trim();
				System.out.println("Please enter the Name for the output file: ");
				String output = filesin.nextLine().trim();
				System.out.println("Thank you for your co-operation..Translating...");
				Parser q = new FileParser(fileInput, googWord, thesaurus, output);
				// ((FileParser) q).bulidDiction();
				q.parse();
				System.out.println(ConsoleColour.YELLOW_UNDERLINED);
				System.out.println("Translation logged to file > " + output + ".txt < in the current directory \n");
				break;
			case 3:
				System.out.print(ConsoleColour.PURPLE);
				System.out.println("Shutting down...");
				keepRunning = false;
				break;
			default:
				System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
				System.out.println("Please input a number between 1 - 3: ");
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}