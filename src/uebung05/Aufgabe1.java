package uebung05;

import java.math.BigInteger;

import simpleunit.SimpleUnit;
import simpleunit.SimpleUnitAssertException;

public class Aufgabe1 {

	public static void main(String[] args) {
		SimpleUnit Test = new SimpleUnit();
		try {
			Test.assertEquals(inc(BigInteger.ONE), new BigInteger("2"));
			Test.assertEquals(dec(BigInteger.ONE), BigInteger.ZERO);
			// add(0,n) = n
			Test.assertEquals(add(BigInteger.ZERO, BigInteger.ONE),
					BigInteger.ONE);
			// add(m,n) = m+n
			Test.assertEquals(add(new BigInteger("10"), new BigInteger("10")), new BigInteger("20"));
			// add(-1,1) = 0
			Test.assertEquals(add(new BigInteger("-1"), BigInteger.ONE), BigInteger.ZERO);			
			// add(1,-1) = 0
			Test.assertEquals(add(new BigInteger("-1"), BigInteger.ONE), BigInteger.ZERO);
			// pm(0) = 0
			Test.assertEquals(pm(BigInteger.ZERO), BigInteger.ZERO);
			// pm(-1) = 1
			Test.assertEquals(pm(new BigInteger("-1")), BigInteger.ONE);
			Test.assertEquals(pm(new BigInteger("-10")), new BigInteger("10"));
			// pm(1) = -1
			Test.assertEquals(pm(BigInteger.ONE), new BigInteger("-1"));
			Test.assertEquals(pm(new BigInteger("10")), new BigInteger("-10"));
			// mult(0,y) = 0
			Test.assertEquals(mult(BigInteger.ZERO, BigInteger.ONE), BigInteger.ZERO);
			// mult(x,0) = 0
			Test.assertEquals(mult(BigInteger.ONE, BigInteger.ZERO), BigInteger.ZERO);
			// mult(1,y) = y
			Test.assertEquals(mult(BigInteger.ONE, new BigInteger("2")), new BigInteger("2"));
			// mult(x,1) = x
			Test.assertEquals(mult(new BigInteger("2"), BigInteger.ONE), new BigInteger("2"));
			
			test(5, 10, 15, 50);
			test(-6, 5, -1, -30);
			test(2, -9, -7, -18);
			test(-4, -4, -8, 16);
			test(142, 369, 511, 52398);
			
		} catch (SimpleUnitAssertException e) {
			e.printStackTrace();
		}
	}
	
	private static void test(int x, int y, int addResult, int multResult) throws SimpleUnitAssertException
	{
		SimpleUnit Test = new SimpleUnit();
		Test.assertEquals(add(new BigInteger(String.valueOf(x)), new BigInteger(String.valueOf(y))), new BigInteger(String.valueOf(addResult)), x + "+" + y);
		Test.assertEquals(mult(new BigInteger(String.valueOf(x)), new BigInteger(String.valueOf(y))), new BigInteger(String.valueOf(multResult)), x + "x" + y);
	}

	public static BigInteger inc(BigInteger x) {
		return x.add(BigInteger.ONE);
	}

	public static BigInteger dec(BigInteger x) {
		return x.subtract(BigInteger.ONE);
	}

	public static BigInteger add(BigInteger m, BigInteger n) {
		if (m.compareTo(BigInteger.ZERO) == 0) {
			return n;
		} else if (m.compareTo(BigInteger.ZERO) < 0) {
			return add(inc(m), dec(n));
		} else { // if (m.compareTo(BigInteger.ZERO) > 0)
			return add(dec(m), inc(n));
		}
	}

	public static BigInteger mult(BigInteger x, BigInteger y) {
		if (x.compareTo(BigInteger.ZERO) == 0) {
			return BigInteger.ZERO;
		} else if (x.compareTo(BigInteger.ONE) > 0) {
			return add(y, mult(dec(x), y));
		} else if (x.compareTo(BigInteger.ONE) == 0) {
			return y;
		} else { // if (x.compareTo(new BigInteger("-1")) == -1)
			return mult(pm(x), pm(y));
		}
	}

	public static BigInteger pm(BigInteger x) {
		if (x.compareTo(BigInteger.ZERO) == -1)
			return inc(pm(inc(x)));
		if (x.compareTo(BigInteger.ZERO) == 1)
			return dec(pm(dec(x)));
		return x;
	}
}