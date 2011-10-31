package testat02;

import simpleunit.SimpleUnit;
import simpleunit.SimpleUnitAssertException;

/**
 * Unit-Test für die Klasse Feld
 * 
 * @author Markus Tacker <m@tacker.org>
 */
public class FeldTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] zahlen = new int[4];
		zahlen[0] = -4;
		zahlen[1] = 1;
		zahlen[2] = -3;
		zahlen[3] = 2;
		Feld feld = new Feld(zahlen, 0);
		try {
			SimpleUnit test = new SimpleUnit();
			test.assertEquals(feld.getTop(), -4);
			test.assertEquals(feld.getRight(), 1);
			test.assertEquals(feld.getBottom(), -3);
			test.assertEquals(feld.getLeft(), 2);
			// 90° drehen
			feld.rotate();
			test.assertEquals(feld.getTop(), 2);
			test.assertEquals(feld.getRight(), -4);
			test.assertEquals(feld.getBottom(), 1);
			test.assertEquals(feld.getLeft(), -3);
			// 90° drehen
			feld.rotate();
			test.assertEquals(feld.getTop(), -3);
			test.assertEquals(feld.getRight(), 2);
			test.assertEquals(feld.getBottom(), -4);
			test.assertEquals(feld.getLeft(), 1);
			// 90° drehen
			feld.rotate();
			test.assertEquals(feld.getTop(), 1);
			test.assertEquals(feld.getRight(), -3);
			test.assertEquals(feld.getBottom(), 2);
			test.assertEquals(feld.getLeft(), -4);
			// 90° drehen, Ausgangsstellung erreicht
			feld.rotate();
			test.assertEquals(feld.getTop(), -4);
			test.assertEquals(feld.getRight(), 1);
			test.assertEquals(feld.getBottom(), -3);
			test.assertEquals(feld.getLeft(), 2);
			// Reset testen
			feld.rotate();
			feld.rotate();
			feld.resetOrientation();
			test.assertEquals(feld.getTop(), -4);
			test.assertEquals(feld.getRight(), 1);
			test.assertEquals(feld.getBottom(), -3);
			test.assertEquals(feld.getLeft(), 2);
		} catch (SimpleUnitAssertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
