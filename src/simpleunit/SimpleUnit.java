package simpleunit;

/**
 * Einfache Unit-Tests
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: SimpleUnit.java 170 2010-06-13 14:24:43Z m $
 * 
 * @param <T>
 */
public class SimpleUnit<T> {

	private int numAsserts = 0;

	/**
	 * Sicherstellen, dass x und y gleich sind
	 * 
	 * @param x
	 * @param y
	 * @throws SimpleUnitAssertException
	 */
	public void assertEquals(int x, int y) throws SimpleUnitAssertException {
		if (x != y)
			throw new SimpleUnitAssertException("Failed asserting that " + x
					+ " equals " + y);
		ok();
	}

	/**
	 * Sicherstellen, dass x und y gleich sind
	 * 
	 * @param x
	 * @param y
	 * @throws SimpleUnitAssertException
	 */
	public void assertEquals(T x, T y) throws SimpleUnitAssertException {
		assertEquals(x, y, "");
	}

	/**
	 * Sicherstellen, dass x und y gleich sind
	 * 
	 * @param x
	 * @param y
	 * @param Zusätzliche
	 *            Nachricht für die Fehlermeldung
	 * @throws SimpleUnitAssertException
	 */
	public void assertEquals(T expected, T actual, String message)
			throws SimpleUnitAssertException {
		if (!expected.equals(actual)) {
			if (message.length() > 0)
				message = " (" + message + ")";
			message = "Failed asserting that " + expected + " equals " + actual
					+ " " + message;
			throw new SimpleUnitAssertException(message);
		}
		ok();
	}

	/**
	 * Zählt erfolgreiche asserts() und gibt einen Punkt aus.
	 */
	private void ok() {
		numAsserts++;
		System.out.print(".");
	}

	/**
	 * Sicherstellen, dass x und y gleich sind
	 * 
	 * @param x
	 * @param y
	 * @throws SimpleUnitAssertException
	 */
	public void assertEquals(double x, double y)
			throws SimpleUnitAssertException {
		assertEquals(x, y, "");
	}

	/**
	 * Sicherstellen, dass x und y gleich sind
	 * 
	 * @param x
	 * @param y
	 * @param Zusätzliche
	 *            Nachricht für die Fehlermeldung
	 * @throws SimpleUnitAssertException
	 */
	public void assertEquals(double x, double y, String message)
			throws SimpleUnitAssertException {
		if (x != y) {
			if (message.length() > 0)
				message = " (" + message + ")";
			message = "Failed asserting that " + x + " equals " + y + " "
					+ message;
			throw new SimpleUnitAssertException(message);
		}
		ok();
	}

	/**
	 * Sicherstellen, dass b true ist
	 * 
	 * @param b
	 * @throws SimpleUnitAssertException
	 */
	public void assertTrue(boolean b) throws SimpleUnitAssertException {
		assertTrue(b, "");
	}

	/**
	 * Sicherstellen, dass b true ist
	 * 
	 * @param b
	 * @param Zusätzliche
	 *            Nachricht für die Fehlermeldung
	 * @throws SimpleUnitAssertException
	 */
	public void assertTrue(boolean b, String message)
			throws SimpleUnitAssertException {
		if (!b) {
			if (message.length() > 0)
				message = " (" + message + ")";
			message = "Failed asserting that " + b + " is true" + message;
			throw new SimpleUnitAssertException(message);
		}
		ok();
	}

	/**
	 * Sicherstellen, dass b false ist
	 * 
	 * @param b
	 * @throws SimpleUnitAssertException
	 */
	public void assertFalse(boolean b) throws SimpleUnitAssertException {
		assertFalse(b, "");
	}

	/**
	 * Sicherstellen, dass b false ist
	 * 
	 * @param b
	 * @param Zusätzliche
	 *            Nachricht für die Fehlermeldung
	 * @throws SimpleUnitAssertException
	 */
	public void assertFalse(boolean b, String message)
			throws SimpleUnitAssertException {
		if (b) {
			if (message.length() > 0)
				message = " (" + message + ")";
			message = "Failed asserting that " + b + " is false" + message;
			throw new SimpleUnitAssertException(message);
		}
		ok();
	}
}
