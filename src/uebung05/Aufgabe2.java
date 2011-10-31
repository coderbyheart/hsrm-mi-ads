package uebung05;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.medieninf.ads.ADSTool;

import simpleunit.SimpleUnit;
import simpleunit.SimpleUnitAssertException;

/**
 * Übungsblatt 5 Aufgabe 2
 * 
 * Verschiedene Potenzierungsalgorithmen
 * 
 * @author Markus Tacker <m@tacker.org>
 */
public class Aufgabe2 {
	private static SimpleUnit test = new SimpleUnit();

	public static void main(String[] args) {
		try {
			testRecursive("10^0 = 1");
			testRecursive("0^0 = 1");
			testRecursive("0^1 = 0");
			testRecursive("1^1 = 1");
			testRecursive("10^1 = 10");
			testRecursive("2^2 = 4");
			
			// Testing laut Aufgabenblatt abgebrochen: Siehe https://read.mi.hs-rm.de/repository.php?ref_id=584&cmdClass=ilobjforumgui&cmd=showThreadFrameset&thr_pk=6&cmdNode=eo:bt
			// BigInteger testBig = new BigInteger("109418989131512359209");
			// test.assertTrue(testBig.doubleValue() == Math.pow(3, 42));
			ADSTool.resetTime();
			for (int n = 1; n <= 4; n++) {
				for (int p = 0; p <= 1024; p++) {
					BigInteger result = powRecursive(BigInteger.valueOf(n), p);
					// double mathPow = Math.pow(n, p);
					// test.assertTrue(result.compareTo(BigInteger.valueOf((int)mathPow)) == 0, n + "^" + p + "=" + result.toString() + ", math: " + (int)mathPow);
				}
			}
			System.out.println(ADSTool.getTime());
		} catch (SimpleUnitAssertException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rekursive Potenzierung
	 * 
	 * @param basis
	 * @param exponent
	 */
	public static BigInteger powRecursive(BigInteger n, int p) {
		if (p == 0) {
			return BigInteger.ONE;
		} else if (p >= 1 && p % 2 != 0) {
			BigInteger halfPow = powRecursive(n, p / 2);
			return n.multiply(halfPow.multiply(halfPow));
		} else { // if(p >= 2 && p % 2 == 0)
			BigInteger halfPow2 = powRecursive(n, p / 2);
			return halfPow2.multiply(halfPow2);
		}
	}

	/**
	 * Helper-Klasse zum Testen der Implementierung
	 * 
	 * @param equation
	 * @throws SimpleUnitAssertException
	 */
	private static void testRecursive(String equation)
			throws SimpleUnitAssertException, IllegalArgumentException {
		Pattern equationPattern = Pattern
				.compile("^([0-9]+)\\^([0-9]+) *= *([0-9]+)$");
		Matcher matcher = equationPattern.matcher(equation);
		if (!matcher.find())
			throw new IllegalArgumentException("Invalid format: " + equation);
		String base = equation.subSequence(matcher.start(1), matcher.end(1))
				.toString();
		int exponent = Integer.parseInt(equation.subSequence(matcher.start(2),
				matcher.end(2)).toString());
		String result = equation.subSequence(matcher.start(3), matcher.end(3))
				.toString();
		test.assertEquals(powRecursive(new BigInteger(base), exponent),
				new BigInteger(result), equation);
	}
}
