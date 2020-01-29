package ie.gmit.dip;

import java.io.IOException;

/**
 * The abstract class Parser is the parent class of our specific utility
 * parsers. It contains the parse() method which must be implemented by all
 * child parsers
 * 
 */
public abstract class Parser {

	/**
	 * Abstract method parse() has no method body, this will be implemented by child
	 * class parsers.
	 * 
	 * @throws IOException
	 */
	public abstract void parse() throws IOException; // Abstract method parse

}
