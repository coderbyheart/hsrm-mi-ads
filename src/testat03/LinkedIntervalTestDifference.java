package testat03;

import java.math.BigDecimal;

import simpleunit.SimpleUnit;
import simpleunit.SimpleUnitAssertException;

/**
 * Unit-Tests für LinkedInterval#difference(Interval)
 * 
 * @author Simon Franzen <simon.franzen@natelio.com>
 * @author Holger Oftring <holger.oftring@me.com>
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: LinkedIntervalTestDifference.java 191 2010-06-21 11:05:10Z m $
 */
public class LinkedIntervalTestDifference {
	/**
	 * Führt alle Tests aus
	 * 
	 * @throws SimpleUnitAssertException
	 */
	public LinkedIntervalTestDifference() throws SimpleUnitAssertException {
		testDifferenceRight();
		testDifferenceLeft();
		testDifferenceMiddle();
		testDifferenceInner();
		testDifferenceNoOverlap();
		testDifferenceOuter();
		testDifferenceOne();
		testDifferenceOne2();
		testDifferenceOne3();
		testDifferenceComplex();
	}

	/**
	 * Testet eine Differenz mit rechter und linker Überlappung
	 * 
	 * [10|14]<br />
	 * / [7|11][13|15]<br />
	 * = [11|13]
	 * 
	 * @see LinkedInterval#difference(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testDifferenceOuter() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(10, 14);
		LinkedInterval i2 = LinkedIntervalFactory.create(7, 11).add(13, 15);
		LinkedInterval i3 = (LinkedInterval) i1.difference(i2);

		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(11)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(13)));
		test.assertTrue(i3.toString().equals("[11|13]"));
		test.assertTrue(i3.numIntervals() == 1);

	}

	/**
	 * Testet eine Differenz ohne Überlappungen
	 * 
	 * [4|6][10|12]<br />
	 * / [1|3][7|9][13|15]<br />
	 * = [4|6][10|12]
	 * 
	 * @see LinkedInterval#difference(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testDifferenceNoOverlap() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(4, 6).add(10, 12);
		LinkedInterval i2 = LinkedIntervalFactory.create(1, 3).add(7, 9).add(
				13, 15);
		LinkedInterval i3 = (LinkedInterval) i1.difference(i2);

		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(4)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(6)));
		test.assertTrue(i3.getLast().getFrom().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i3.getLast().getTo().equals(BigDecimal.valueOf(12)));
		test.assertTrue(i3.toString().equals("[4|6][10|12]"));
		test.assertTrue(i3.numIntervals() == 2);
	}

	/**
	 * Testet eine Differenz mit innerer Überlappung
	 * 
	 * [2|10]<br />
	 * / [4|5]<br />
	 * = [2|4][5|10]
	 * 
	 * @see LinkedInterval#difference(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testDifferenceInner() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 10);
		LinkedInterval i2 = LinkedIntervalFactory.create(4, 5);

		LinkedInterval i3 = (LinkedInterval) i1.difference(i2);
		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(2)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(4)));
		test.assertTrue(i3.getLast().getFrom().equals(BigDecimal.valueOf(5)));
		test.assertTrue(i3.getLast().getTo().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i3.toString().equals("[2|4][5|10]"));
		test.assertTrue(i3.numIntervals() == 2);

		// Unmgekehrter Fall
		LinkedInterval i4 = (LinkedInterval) i2.difference(i1);
		test.assertTrue(i4.isEmpty());
	}

	/**
	 * Testet eine Differenz mit linker Überlappung
	 * 
	 * [2|6] [8|10]<br />
	 * / [1|5]<br />
	 * = [5|6][8|10]
	 * 
	 * @see LinkedInterval#difference(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testDifferenceLeft() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 6).add(8, 10);
		LinkedInterval i2 = LinkedIntervalFactory.create(1, 5);
		LinkedInterval i3 = (LinkedInterval) i1.difference(i2);

		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(5)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(6)));
		test.assertTrue(i3.getLast().getFrom().equals(BigDecimal.valueOf(8)));
		test.assertTrue(i3.getLast().getTo().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i3.toString().equals("[5|6][8|10]"));
		test.assertTrue(i3.numIntervals() == 2);
	}

	/**
	 * Testet eine Differenz mit rechter Überlappung
	 * 
	 * [2|6] [8|11]<br />
	 * / [10|12]<br />
	 * = [2|6][8|10]
	 * 
	 * @see LinkedInterval#difference(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testDifferenceRight() throws SimpleUnitAssertException {

		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 6).add(8, 11);
		LinkedInterval i2 = LinkedIntervalFactory.create(10, 12);
		LinkedInterval i3 = (LinkedInterval) i1.difference(i2);

		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(2)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(6)));
		test.assertTrue(i3.getLast().getFrom().equals(BigDecimal.valueOf(8)));
		test.assertTrue(i3.getLast().getTo().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i3.toString().equals("[2|6][8|10]"));
		test.assertTrue(i3.numIntervals() == 2);
	}

	/**
	 * Testet eine Differenz mit rechter und linker (mittlerer) Überlappung
	 * 
	 * [2|6] [8|10]<br />
	 * / [5|9]<br />
	 * = [2|5][9|10]
	 * 
	 * @see LinkedInterval#difference(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testDifferenceMiddle() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 6).add(8, 10);
		LinkedInterval i2 = LinkedIntervalFactory.create(5, 9);
		LinkedInterval i3 = (LinkedInterval) i1.difference(i2);

		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(2)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(5)));
		test.assertTrue(i3.getLast().getFrom().equals(BigDecimal.valueOf(9)));
		test.assertTrue(i3.getLast().getTo().equals(BigDecimal.valueOf(10)));
		test.assertTrue(i3.toString().equals("[2|5][9|10]"));
		test.assertTrue(i3.numIntervals() == 2);
	}

	/**
	 * Testet eine Differenz, mit nur einem Element
	 * 
	 * [2|4]<br />
	 * / [3]<br />
	 * = [2|4]
	 * 
	 * @see LinkedInterval#difference(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testDifferenceOne() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 4);
		LinkedInterval i2 = LinkedIntervalFactory.create(3);

		LinkedInterval i3 = (LinkedInterval) i1.difference(i2);
		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(2)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(4)));
		test.assertTrue(i3.toString().equals("[2|4]"));
		test.assertTrue(i3.numIntervals() == 1);

		// Unmgekehrter Fall
		LinkedInterval i4 = (LinkedInterval) i2.difference(i1);
		test.assertTrue(i4.isEmpty());
	}

	/**
	 * Testet eine Differenz zwischen zweimal nur einem Element
	 * 
	 * [3]<br />
	 * / [3]<br />
	 * = {}
	 * 
	 * @see LinkedInterval#difference(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testDifferenceOne2() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(3);
		LinkedInterval i2 = LinkedIntervalFactory.create(3);
		LinkedInterval i3 = (LinkedInterval) i1.difference(i2);
		test.assertTrue(i3.isEmpty());
	}

	/**
	 * Testet eine Differenz zwischen zwei verschiedenen Einzel-Elementen
	 * 
	 * [3]<br />
	 * / [4]<br />
	 * = {}
	 * 
	 * @see LinkedInterval#difference(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testDifferenceOne3() throws SimpleUnitAssertException {
		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(3);
		LinkedInterval i2 = LinkedIntervalFactory.create(3);
		LinkedInterval i3 = (LinkedInterval) i1.difference(i2);
		test.assertTrue(i3.isEmpty());
	}

	/**
	 * Testet eine komplexe Differenz
	 * 
	 * [2|6][8|13]<br />
	 * / [5|9][10|11][12|14]<br />
	 * = [2|5][9|10][11|12]
	 * 
	 * @see LinkedInterval#difference(Interval)
	 * @throws SimpleUnitAssertException
	 */
	private void testDifferenceComplex() throws SimpleUnitAssertException {

		SimpleUnit<LinkedInterval> test = new SimpleUnit<LinkedInterval>();
		LinkedInterval i1 = LinkedIntervalFactory.create(2, 6).add(8, 13);
		LinkedInterval i2 = LinkedIntervalFactory.create(5, 9).add(10, 11).add(
				12, 14);
		LinkedInterval i3 = (LinkedInterval) i1.difference(i2);

		test.assertTrue(i3.getFirst().getFrom().equals(BigDecimal.valueOf(2)));
		test.assertTrue(i3.getFirst().getTo().equals(BigDecimal.valueOf(5)));
		test.assertTrue(i3.getFirst().getNext().getFrom().equals(
				BigDecimal.valueOf(9)));
		test.assertTrue(i3.getFirst().getNext().getTo().equals(
				BigDecimal.valueOf(10)));
		test.assertTrue(i3.getLast().getFrom().equals(BigDecimal.valueOf(11)));
		test.assertTrue(i3.getLast().getTo().equals(BigDecimal.valueOf(12)));
		test.assertTrue(i3.toString().equals("[2|5][9|10][11|12]"));
		test.assertTrue(i3.numIntervals() == 3);
	}

}
