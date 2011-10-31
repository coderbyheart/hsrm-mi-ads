package testat03;

import java.math.BigDecimal;

import simpleunit.SimpleUnit;
import simpleunit.SimpleUnitAssertException;
import testat03.Interval.SimpleInterval;

/**
 * Unit-Tests für LinkedIntervalItem
 * 
 * @author Simon Franzen <simon.franzen@natelio.com>
 * @author Holger Oftring <holger.oftring@me.com>
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: LinkedIntervalItemTest.java 191 2010-06-21 11:05:10Z m $
 */
public class LinkedIntervalItemTest {
	public LinkedIntervalItemTest() throws SimpleUnitAssertException {
		testIntersects();
		testGetIntersection();
	}

	/**
	 * Tests für LinkedIntervalItem.intersects()
	 * 
	 * @throws SimpleUnitAssertException
	 * 
	 * @see LinkedIntervalItem#intersects(SimpleInterval i1, SimpleInterval i2)
	 * @throws SimpleUnitAssertException
	 */
	private void testIntersects() throws SimpleUnitAssertException {
		SimpleUnit<LinkedIntervalItem> test = new SimpleUnit<LinkedIntervalItem>();

		SimpleInterval s1 = new SimpleInterval();
		s1.from = BigDecimal.valueOf(3);
		s1.to = BigDecimal.valueOf(5);
		LinkedIntervalItem i1 = new LinkedIntervalItem(s1);
		SimpleInterval s2 = new SimpleInterval();
		s2.from = BigDecimal.valueOf(4);
		s2.to = BigDecimal.valueOf(6);
		LinkedIntervalItem i2 = new LinkedIntervalItem(s2);
		SimpleInterval s3 = new SimpleInterval();
		s3.from = BigDecimal.valueOf(2);
		s3.to = BigDecimal.valueOf(7);
		LinkedIntervalItem i3 = new LinkedIntervalItem(s3);
		SimpleInterval s4 = new SimpleInterval();
		s4.from = BigDecimal.valueOf(1);
		s4.to = BigDecimal.valueOf(2);
		LinkedIntervalItem i4 = new LinkedIntervalItem(s4);

		test.assertTrue(i1.intersects(i2));
		test.assertTrue(i2.intersects(i1));
		test.assertTrue(i1.intersects(i3));
		test.assertTrue(i3.intersects(i1));
		test.assertFalse(i1.intersects(i4));
		test.assertFalse(i4.intersects(i1));
	}

	/**
	 * Tests für LinkedIntervalItem.getIntersection()
	 * 
	 * @throws SimpleUnitAssertException
	 * 
	 * @see LinkedIntervalItem#getIntersection(SimpleInterval i1, SimpleInterval
	 *      i2)
	 * @throws SimpleUnitAssertException
	 */
	private void testGetIntersection() throws SimpleUnitAssertException {
		SimpleUnit<LinkedIntervalItem> test = new SimpleUnit<LinkedIntervalItem>();

		SimpleInterval s1 = new SimpleInterval();
		s1.from = BigDecimal.valueOf(3);
		s1.to = BigDecimal.valueOf(5);
		LinkedIntervalItem i1 = new LinkedIntervalItem(s1);
		SimpleInterval s2 = new SimpleInterval();
		s2.from = BigDecimal.valueOf(4);
		s2.to = BigDecimal.valueOf(6);
		LinkedIntervalItem i2 = new LinkedIntervalItem(s2);
		SimpleInterval s3 = new SimpleInterval();
		s3.from = BigDecimal.valueOf(2);
		s3.to = BigDecimal.valueOf(7);
		LinkedIntervalItem i3 = new LinkedIntervalItem(s3);

		// [3|5] ^ [4|6] = [4|5]
		test.assertTrue(i1.getIntersection(i2).toString().equals("[4|5]"));
		test.assertTrue(i2.getIntersection(i1).toString().equals("[4|5]"));
		// [3|5] ^ [2|7] = [3|5]
		test.assertTrue(i1.getIntersection(i3).toString().equals("[3|5]"));
		test.assertTrue(i3.getIntersection(i1).toString().equals("[3|5]"));
	}

}
