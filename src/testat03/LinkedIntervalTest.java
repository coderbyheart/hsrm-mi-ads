package testat03;

import simpleunit.SimpleUnit;
import simpleunit.SimpleUnitAssertException;
import testat03.Interval.SimpleInterval;

import java.math.BigDecimal;

/**
 * Unit-Tests für LinkedInterval
 * 
 * @author Simon Franzen <simon.franzen@natelio.com>
 * @author Holger Oftring <holger.oftring@me.com>
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: LinkedIntervalTest.java 191 2010-06-21 11:05:10Z m $
 */
public class LinkedIntervalTest {

	/**
	 * Führt alle Tests aus
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedIntervalTest liTest = new LinkedIntervalTest();
		try {
			liTest.testIsEmpty();
			liTest.testContains();
			liTest.testToString();
			liTest.testGetFirstLast();
			liTest.testIterator();
			new LinkedIntervalTestUnion();
			new LinkedIntervalItemTest();
			new LinkedIntervalTestIntersect();
			new LinkedIntervalTestDifference();

		} catch (SimpleUnitAssertException e) {
			System.out.println();
			System.out.println("Testing failed!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Tests für LinkedInterval.isEmpty()
	 * 
	 * Getestete Fälle:<br />
	 * {} = true<br />
	 * [1] = false<br />
	 * [0|1] = false<br />
	 * [0|1][2|3] = false
	 * 
	 * @throws SimpleUnitAssertException
	 * @throws LinkedIntervalException
	 * @see LinkedInterval#isEmpty()
	 */
	public void testIsEmpty() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();

		test
				.assertTrue(LinkedIntervalFactory.create().isEmpty(),
						"Leere Menge");
		test.assertFalse(LinkedIntervalFactory.create(0).isEmpty(),
				"Menge mit einem Interval (1 Element)");
		test.assertFalse(LinkedIntervalFactory.create(0, 1).isEmpty(),
				"Menge mit einem Interval");
		test.assertFalse(
				LinkedIntervalFactory.create(0, 1).add(2, 3).isEmpty(),
				"Menge mit zwei Intervallen");

	}

	/**
	 * Tests für LinkedInterval.contains()
	 * 
	 * Getestete Fälle:<br />
	 * {} contains 3 = false<br />
	 * [0] contains 3 = false<br />
	 * [3] contains 3 = true<br />
	 * [4|7] contains 3 = false<br />
	 * [-1|2] contains 3 = false<br />
	 * [1|2][3|5] contains 4 = true<br />
	 * [1|2][4|5] contains 3 = false<br />
	 * [1|2][4|5] contains 1 = true<br />
	 * [1|2][4|5] contains 2 = true<br />
	 * [1|2][4|5] contains 4 = true<br />
	 * [1|2][4|5] contains 5 = true
	 * 
	 * @throws SimpleUnitAssertException
	 * @throws LinkedIntervalException
	 * @see LinkedInterval#contains(BigDecimal)
	 */
	public void testContains() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();

		test.assertFalse(LinkedIntervalFactory.create().contains(3),
				"Leere Menge");
		test.assertFalse(LinkedIntervalFactory.create(0).contains(3),
				"Menge mit einem Element");
		test.assertTrue(LinkedIntervalFactory.create(3).contains(3),
				"Menge mit genau dem einen Element");
		test.assertFalse(LinkedIntervalFactory.create(4, 7).contains(3),
				"Menge mit einem Intervall (größer als gesuchtes Element)");
		test.assertFalse(LinkedIntervalFactory.create(-1, 2).contains(3),
				"Menge mit mehreren Intervall (kleiner als gesuchtes Element)");
		test.assertTrue(LinkedIntervalFactory.create(1, 2).add(3, 5)
				.contains(4),
				"Menge mit mehreren Intervallen (enhält gesuchtes Element)");

		LinkedInterval i = LinkedIntervalFactory.create(1, 2).add(4, 5);
		test
				.assertFalse(
						i.contains(3),
						"Menge mit mehreren Intervallen vor und nach gesuchtem Element (enhält gesuchtes Element nicht)");
		test.assertTrue(i.contains(1),
				"Menge mit mehreren Intervallen (enhält gesuchtes Element)");
		test.assertTrue(i.contains(2),
				"Menge mit mehreren Intervallen (enhält gesuchtes Element)");
		test.assertTrue(i.contains(4),
				"Menge mit mehreren Intervallen (enhält gesuchtes Element)");
		test.assertTrue(i.contains(5),
				"Menge mit mehreren Intervallen (enhält gesuchtes Element)");

	}

	/**
	 * Tests für LinkedInterval.toString()
	 * 
	 * Getestete Fälle:<br />
	 * {}<br />
	 * [0]<br />
	 * [1|2]<br />
	 * [1|2][5|7]
	 * 
	 * @throws SimpleUnitAssertException
	 * @throws LinkedIntervalException
	 * @see LinkedInterval#toString()
	 */
	public void testToString() throws SimpleUnitAssertException {
		SimpleUnit<String> test = new SimpleUnit<String>();

		test.assertEquals(LinkedInterval.EMPTY, LinkedIntervalFactory.create()
				.toString(), "Leere Menge");
		test.assertEquals("[0]", LinkedIntervalFactory.create(0).toString(),
				"Menge mit einem Element");
		test.assertEquals("[1|2]", LinkedIntervalFactory.create(1, 2)
				.toString(), "Menge mit einem SimpleInterval");
		test.assertEquals("[1|2][5|7]", LinkedIntervalFactory.create(1, 2).add(
				5, 7).toString(), "Menge mit zwei SimpleIntervallen");

	}

	/**
	 * Tests für LinkedInterval.getFirst() Tests für LinkedInterval.getLast()
	 * 
	 * Getestete Fälle:<br />
	 * {}<br />
	 * [0]<br />
	 * [4|7]<br />
	 * [1|2][4|7]
	 * 
	 * @throws SimpleUnitAssertException
	 * @throws LinkedIntervalException
	 * @see LinkedInterval#getFirst()
	 * @see LinkedInterval#getLast()
	 */
	public void testGetFirstLast() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();

		test.assertTrue(LinkedIntervalFactory.create().getFirst() == null,
				"Leere Menge");
		test.assertTrue(LinkedIntervalFactory.create().getLast() == null,
				"Leere Menge");

		LinkedInterval i1 = LinkedIntervalFactory.create(0);
		test.assertTrue(i1.getFirst().getFrom() == BigDecimal.ZERO,
				"getFirst() bei Menge mit einem Element");
		test.assertTrue(i1.getLast().getFrom() == BigDecimal.ZERO,
				"getLast() bei Menge mit einem Element");
		test.assertTrue(i1.getLast().equals(i1.getFirst()),
				"getLast() === getFirst() bei Menge mit einem Element");

		LinkedInterval i2 = LinkedIntervalFactory.create(4, 7);
		test.assertTrue(i2.getFirst().getFrom() == BigDecimal.valueOf(4),
				"getFirst() bei Menge mit mehreren Elementen");
		test.assertTrue(i2.getLast().getTo() == BigDecimal.valueOf(7),
				"getLast() bei Menge mit mehreren Elementen");

		LinkedInterval i3 = LinkedIntervalFactory.create(1, 2).add(4, 7);
		test.assertTrue(i3.getFirst().getFrom() == BigDecimal.valueOf(1),
				"getFirst() bei Menge mit zwei Intervallen");
		test.assertTrue(i3.getFirst().getTo() == BigDecimal.valueOf(2),
				"getFirst() bei Menge mit zwei Intervallen");
		test.assertTrue(i3.getLast().getFrom() == BigDecimal.valueOf(4),
				"getFirst() bei Menge mit zwei Intervallen");
		test.assertTrue(i3.getLast().getTo() == BigDecimal.valueOf(7),
				"getFirst() bei Menge mit zwei Intervallen");
	}

	/**
	 * Test für den Iterator
	 * 
	 * @throws LinkedIntervalException
	 */
	private void testIterator() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();

		LinkedInterval i = LinkedIntervalFactory.create(4, 7).add(9, 11).add(
				15, 19);

		int n = 0;
		for (SimpleInterval si : i) {
			switch (n) {
			case 0:
				test.assertTrue(si.from.equals(BigDecimal.valueOf(4)));
				test.assertTrue(si.to.equals(BigDecimal.valueOf(7)));
				break;
			case 1:
				test.assertTrue(si.from.equals(BigDecimal.valueOf(9)));
				test.assertTrue(si.to.equals(BigDecimal.valueOf(11)));
				break;
			case 2:
				test.assertTrue(si.from.equals(BigDecimal.valueOf(15)));
				test.assertTrue(si.to.equals(BigDecimal.valueOf(19)));
				break;
			}
			n++;
		}
		test.assertTrue(n == 3);

	}
}
