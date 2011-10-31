package testat03;

import java.math.BigDecimal;

import simpleunit.SimpleUnit;
import simpleunit.SimpleUnitAssertException;

/**
 * Unit-Tests für LinkedInterval#union(Interval)
 * 
 * @author Simon Franzen <simon.franzen@natelio.com>
 * @author Holger Oftring <holger.oftring@me.com>
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: LinkedIntervalTestUnion.java 191 2010-06-21 11:05:10Z m $
 */
public class LinkedIntervalTestUnion {
	/**
	 * Führt alle Tests aus
	 * 
	 * @throws SimpleUnitAssertException
	 */
	public LinkedIntervalTestUnion() throws SimpleUnitAssertException {
		testUnionRight();
		testUnionMiddle();
		testUnionLeft();
		testUnionInner();
		testUnionNoOverlap();
		testUnionOuter();
	}

	/**
	 * Testet ein Union mit rechter und linker Überlappung
	 * 
	 * [10|14]<br />
	 * + [7|11][13|15]<br />
	 * = [7|15]
	 * 
	 * @see LinkedInterval#union(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testUnionOuter() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(10, 14);
		LinkedInterval i2 = LinkedIntervalFactory.create(7, 11).add(13, 15);
		LinkedInterval i3 = (LinkedInterval) i1.union(i2);

		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(7)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(15)));
		test.assertTrue(i3.toString().equals("[7|15]"));
		test.assertTrue(i3.numIntervals() == 1);

	}

	/**
	 * Testet ein Union ohne Überlappungen
	 * 
	 * [4|6][10|12]<br />
	 * + [1|3][7|9][13|15]<br />
	 * = [1|3][4|6][7|9][10|12][13|15]
	 * 
	 * @see LinkedInterval#union(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testUnionNoOverlap() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(4, 6).add(10, 12);
		LinkedInterval i2 = LinkedIntervalFactory.create(1, 3).add(7, 9).add(
				13, 15);
		LinkedInterval i3 = (LinkedInterval) i1.union(i2);

		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(1)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(3)));
		test.assertTrue(i3.getLast().getFrom().equals(BigDecimal.valueOf(13)));
		test.assertTrue(i3.getLast().getTo().equals(BigDecimal.valueOf(15)));
		test.assertTrue(i3.toString().equals("[1|3][4|6][7|9][10|12][13|15]"));
		test.assertTrue(i3.numIntervals() == 5);
	}

	/**
	 * Testet ein Union mit innerer Überlappung
	 * 
	 * [2|10]<br />
	 * + [4|5]<br />
	 * = [2|10]
	 * 
	 * @see LinkedInterval#union(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testUnionInner() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 10);
		LinkedInterval i2 = LinkedIntervalFactory.create(4, 5);

		LinkedInterval i3 = (LinkedInterval) i1.union(i2);
		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(2)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i3.toString().equals("[2|10]"));
		test.assertTrue(i3.numIntervals() == 1);

		// Unmgekehrter Fall
		LinkedInterval i4 = (LinkedInterval) i2.union(i1);
		test.assertTrue(i4.getFirst().getFrom().equals(BigDecimal.valueOf(2)));
		test.assertTrue(i4.getFirst().getTo().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i4.toString().equals("[2|10]"));
		test.assertTrue(i4.numIntervals() == 1);
	}

	/**
	 * Testet ein Union mit linker Überlappung
	 * 
	 * [2|6] [8|10]<br />
	 * + [1|5]<br />
	 * = [1|6][8|10]
	 * 
	 * @see LinkedInterval#union(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testUnionLeft() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 6).add(8, 10);
		LinkedInterval i2 = LinkedIntervalFactory.create(1, 5);
		LinkedInterval i3 = (LinkedInterval) i1.union(i2);

		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(1)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(6)));
		test.assertTrue(i3.getLast().getFrom().equals(BigDecimal.valueOf(8)));
		test.assertTrue(i3.getLast().getTo().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i3.toString().equals("[1|6][8|10]"));
		test.assertTrue(i3.numIntervals() == 2);
	}

	/**
	 * Testet ein Union mit rechter Überlappung
	 * 
	 * [2|6] [8|10]<br />
	 * + [9|12]<br />
	 * = [2|6][8|12]
	 * 
	 * @see LinkedInterval#union(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testUnionRight() throws SimpleUnitAssertException {

		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 6).add(8, 10);
		LinkedInterval i2 = LinkedIntervalFactory.create(9, 12);
		LinkedInterval i3 = (LinkedInterval) i1.union(i2);

		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(2)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(6)));
		test.assertTrue(i3.getLast().getFrom().equals(BigDecimal.valueOf(8)));
		test.assertTrue(i3.getLast().getTo().equals(BigDecimal.valueOf(12)));
		test.assertTrue(i3.toString().equals("[2|6][8|12]"));
		test.assertTrue(i3.numIntervals() == 2);
	}

	/**
	 * Testet ein Union mit rechter und linker (mittlerer) Überlappung
	 * 
	 * [2|6] [8|10]<br />
	 * + [5|9]<br />
	 * = [2|10]
	 * 
	 * @see LinkedInterval#union(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testUnionMiddle() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 6).add(8, 10);
		LinkedInterval i2 = LinkedIntervalFactory.create(5, 9);
		LinkedInterval i3 = (LinkedInterval) i1.union(i2);

		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(2)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i3.toString().equals("[2|10]"));
		test.assertTrue(i3.numIntervals() == 1);
	}
}
