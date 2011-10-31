package simpleunit;

/**
 * Exception für SimpleUnit
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: SimpleUnitAssertException.java 169 2010-06-13 14:24:20Z m $
 */
@SuppressWarnings("serial")
public class SimpleUnitAssertException extends Exception {

	public SimpleUnitAssertException(String message) {
		super(message);
	}

}
