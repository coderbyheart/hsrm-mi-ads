package testat03;

import java.math.BigDecimal;

import simpleunit.SimpleUnit;
import simpleunit.SimpleUnitAssertException;

/**
 * Tests zum Darstellen der Funktionsweise der BigDecimal.compareTo()-Methode
 * 
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: BigDecimalTest.java 189 2010-06-20 12:59:04Z m $
 */
public class BigDecimalTest {
	public static void main(String args[])
	{
		SimpleUnit<BigDecimal> test = new SimpleUnit<BigDecimal>();
		
		try {
			test.assertTrue(BigDecimal.ONE.compareTo(BigDecimal.ZERO) > 0);
			test.assertTrue(BigDecimal.ONE.compareTo(BigDecimal.ZERO) >= 0);
			test.assertFalse(BigDecimal.ONE.compareTo(BigDecimal.ZERO) == 0);
			test.assertFalse(BigDecimal.ONE.compareTo(BigDecimal.ZERO) <= 0);
			test.assertFalse(BigDecimal.ONE.compareTo(BigDecimal.ZERO) < 0);
			
			test.assertTrue(BigDecimal.ZERO.compareTo(BigDecimal.ONE) < 0);
			test.assertTrue(BigDecimal.ZERO.compareTo(BigDecimal.ONE) <= 0);
			test.assertFalse(BigDecimal.ZERO.compareTo(BigDecimal.ONE) == 0);
			test.assertFalse(BigDecimal.ZERO.compareTo(BigDecimal.ONE) >= 0);
			test.assertFalse(BigDecimal.ZERO.compareTo(BigDecimal.ONE) > 0);
			
			test.assertFalse(BigDecimal.ONE.compareTo(BigDecimal.ONE) < 0);
			test.assertTrue(BigDecimal.ONE.compareTo(BigDecimal.ONE) <= 0);
			test.assertTrue(BigDecimal.ONE.compareTo(BigDecimal.ONE) == 0);
			test.assertTrue(BigDecimal.ONE.compareTo(BigDecimal.ONE) >= 0);
			test.assertFalse(BigDecimal.ONE.compareTo(BigDecimal.ONE) > 0);
		} catch (SimpleUnitAssertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
