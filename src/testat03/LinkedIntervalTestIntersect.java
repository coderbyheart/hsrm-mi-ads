package testat03;

import java.math.BigDecimal;

import simpleunit.SimpleUnit;
import simpleunit.SimpleUnitAssertException;

/**
 * Tests für LinkedInterval#intersect(Interval)
 * 
 * @author Simon Franzen <simon.franzen@natelio.com>
 * @author Holger Oftring <holger.oftring@me.com>
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: LinkedIntervalTestIntersect.java 191 2010-06-21 11:05:10Z m $
 */
public class LinkedIntervalTestIntersect {
	/**
	 * Führt alle Tests aus
	 * 
	 * @throws SimpleUnitAssertException
	 */
	public LinkedIntervalTestIntersect() throws SimpleUnitAssertException {
		testIntersectRight();
		testIntersectLeft();
		testIntersectMiddle();
		testIntersectInner();
		testIntersectNoOverlap();
		testIntersectOuter();
		testIntersectTwoInner();
		testIntersectInnerRight();
		testIntersectInnerLeft();
	}

	/**
	 * Testet ein Intersect mit rechter und linker Überlappung
	 * 
	 * [10|14]<br />
	 * & [7|11][13|15]<br />
	 * = [10|11][13|14]
	 * 
	 * @see LinkedInterval#intersect(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testIntersectOuter() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(10, 14);
		LinkedInterval i2 = LinkedIntervalFactory.create(7, 11).add(13, 15);
		LinkedInterval i3 = (LinkedInterval) i1.intersect(i2);

		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(11)));
		test.assertTrue(i3.getLast().getFrom().equals(BigDecimal.valueOf(13)));
		test.assertTrue(i3.getLast().getTo().equals(BigDecimal.valueOf(14)));
		test.assertTrue(i3.toString().equals("[10|11][13|14]"));
		test.assertTrue(i3.numIntervals() == 2);

	}

	/**
	 * Testet ein Intersect ohne Überlappungen
	 * 
	 * [4|6][10|12]<br />
	 * & [1|3][7|9][13|15]<br />
	 * = {}
	 * 
	 * @see LinkedInterval#intersect(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testIntersectNoOverlap() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(4, 6).add(10, 12);
		LinkedInterval i2 = LinkedIntervalFactory.create(1, 3).add(7, 9).add(
				13, 15);
		LinkedInterval i3 = (LinkedInterval) i1.intersect(i2);

		test.assertTrue(i3.isEmpty());
		test.assertTrue(i3.toString().equals(LinkedInterval.EMPTY));
		test.assertTrue(i3.numIntervals() == 0);
	}

	/**
	 * Testet ein Intersect mit innerer Überlappung
	 * 
	 * [2|10]<br />
	 * & [4|5]<br />
	 * = [4|5]
	 * 
	 * @see LinkedInterval#intersect(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testIntersectInner() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 10);
		LinkedInterval i2 = LinkedIntervalFactory.create(4, 5);

		LinkedInterval i3 = (LinkedInterval) i1.intersect(i2);
		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(4)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(5)));
		test.assertTrue(i3.toString().equals("[4|5]"));
		test.assertTrue(i3.numIntervals() == 1);

		LinkedInterval i4 = (LinkedInterval) i2.intersect(i1);
		test.assertTrue(i4.getFirst().getFrom().equals(BigDecimal.valueOf(4)));
		test.assertTrue(i4.getFirst().getTo().equals(BigDecimal.valueOf(5)));
		test.assertTrue(i4.toString().equals("[4|5]"));
		test.assertTrue(i4.numIntervals() == 1);
	}

	/**
	 * Testet ein Intersect mit innerer Überlappung von zwei Intervallen
	 * 
	 * [2|20]<br />
	 * & [5|10][15|18]<br />
	 * = [5|10][15|18]
	 * 
	 * @see LinkedInterval#intersect(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testIntersectTwoInner() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 20);
		LinkedInterval i2 = LinkedIntervalFactory.create(5, 10).add(15, 18);

		LinkedInterval i3 = (LinkedInterval) i1.intersect(i2);
		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(5)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i3.getLast().getFrom().equals(BigDecimal.valueOf(15)));
		test.assertTrue(i3.getLast().getTo().equals(BigDecimal.valueOf(18)));
		test.assertTrue(i3.toString().equals("[5|10][15|18]"));
		test.assertTrue(i3.numIntervals() == 2);

		LinkedInterval i4 = (LinkedInterval) i2.intersect(i1);
		test.assertTrue(i4.getFirst().getFrom().equals(BigDecimal.valueOf(5)));
		test.assertTrue(i4.getFirst().getTo().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i4.getLast().getFrom().equals(BigDecimal.valueOf(15)));
		test.assertTrue(i4.getLast().getTo().equals(BigDecimal.valueOf(18)));
		test.assertTrue(i4.toString().equals("[5|10][15|18]"));
		test.assertTrue(i4.numIntervals() == 2);
	}

	/**
	 * Testet ein Intersect mit innerer und rechter Überlappung
	 * 
	 * [2|20]<br />
	 * & [5|10][15|25]<br />
	 * = [5|10][15|20]
	 * 
	 * @see LinkedInterval#intersect(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testIntersectInnerRight() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 20);
		LinkedInterval i2 = LinkedIntervalFactory.create(5, 10).add(15, 25);

		LinkedInterval i3 = (LinkedInterval) i1.intersect(i2);
		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(5)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i3.getLast().getFrom().equals(BigDecimal.valueOf(15)));
		test.assertTrue(i3.getLast().getTo().equals(BigDecimal.valueOf(20)));
		test.assertTrue(i3.toString().equals("[5|10][15|20]"));
		test.assertTrue(i3.numIntervals() == 2);

		LinkedInterval i4 = (LinkedInterval) i2.intersect(i1);
		test.assertTrue(i4.getFirst().getFrom().equals(BigDecimal.valueOf(5)));
		test.assertTrue(i4.getFirst().getTo().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i4.getLast().getFrom().equals(BigDecimal.valueOf(15)));
		test.assertTrue(i4.getLast().getTo().equals(BigDecimal.valueOf(20)));
		test.assertTrue(i4.toString().equals("[5|10][15|20]"));
		test.assertTrue(i4.numIntervals() == 2);
	}

	/**
	 * Testet ein Intersect mit innerer und linker Überlappung
	 * 
	 * [2|20]<br />
	 * & [1|5][10|15]<br />
	 * = [2|5][10|15]
	 * 
	 * @see LinkedInterval#intersect(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testIntersectInnerLeft() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 20);
		LinkedInterval i2 = LinkedIntervalFactory.create(1, 5).add(10, 15);

		LinkedInterval i3 = (LinkedInterval) i1.intersect(i2);
		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(2)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(5)));
		test.assertTrue(i3.getLast().getFrom().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i3.getLast().getTo().equals(BigDecimal.valueOf(15)));
		test.assertTrue(i3.toString().equals("[2|5][10|15]"));
		test.assertTrue(i3.numIntervals() == 2);

		LinkedInterval i4 = (LinkedInterval) i2.intersect(i1);
		test.assertTrue(i4.getFirst().getFrom().equals(BigDecimal.valueOf(2)));
		test.assertTrue(i4.getFirst().getTo().equals(BigDecimal.valueOf(5)));
		test.assertTrue(i4.getLast().getFrom().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i4.getLast().getTo().equals(BigDecimal.valueOf(15)));
		test.assertTrue(i4.toString().equals("[2|5][10|15]"));
		test.assertTrue(i4.numIntervals() == 2);
	}

	/**
	 * Testet ein Intersect mit linker Überlappung
	 * 
	 * [2|6] [8|10]<br />
	 * & [1|5]<br />
	 * = [2|5]
	 * 
	 * @see LinkedInterval#intersect(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testIntersectLeft() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 6).add(8, 10);
		LinkedInterval i2 = LinkedIntervalFactory.create(1, 5);
		LinkedInterval i3 = (LinkedInterval) i1.intersect(i2);

		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(2)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(5)));
		test.assertTrue(i3.toString().equals("[2|5]"));
		test.assertTrue(i3.numIntervals() == 1);
	}

	/**
	 * Testet ein Intersect mit rechter Überlappung
	 * 
	 * [2|6] [8|10]<br />
	 * & [9|12]<br />
	 * = [9|10]
	 * 
	 * @see LinkedInterval#intersect(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testIntersectRight() throws SimpleUnitAssertException {

		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 6).add(8, 10);
		LinkedInterval i2 = LinkedIntervalFactory.create(9, 12);
		LinkedInterval i3 = (LinkedInterval) i1.intersect(i2);

		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(9)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i3.toString().equals("[9|10]"));
		test.assertTrue(i3.numIntervals() == 1);
	}

	/**
	 * Testet ein Intersect mit rechter und linker (mittlerer) Überlappung
	 * 
	 * [2|6] [8|10]<br />
	 * & [5|9]<br />
	 * = [5|6][8|9]
	 * 
	 * @see LinkedInterval#intersect(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testIntersectMiddle() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 6).add(8, 10);
		LinkedInterval i2 = LinkedIntervalFactory.create(5, 9);
		LinkedInterval i3 = (LinkedInterval) i1.intersect(i2);

		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(5)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(6)));
		test.assertTrue(i3.getLast().getFrom().equals(BigDecimal.valueOf(8)));
		test.assertTrue(i3.getLast().getTo().equals(BigDecimal.valueOf(9)));
		test.assertTrue(i3.toString().equals("[5|6][8|9]"));
		test.assertTrue(i3.numIntervals() == 2);
	}
}
