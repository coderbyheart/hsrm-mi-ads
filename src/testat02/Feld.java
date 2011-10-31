package testat02;

/**
 * Repräsentiert ein Feld
 * 
 * @author Simon Franzen <simon.franzen@natelio.com>
 * @author Holger Oftring <holger.oftring@me.com>
 * @author Markus Tacker <m@tacker.org>
 */
public class Feld {
	private int[] zahlen;
	private int orientation = 0;
	private int nummer;

	/**
	 * Konstruktor
	 * 
	 * @param Die
	 *            vier Zahlen des Felds
	 * @param Feldnummer
	 */
	public Feld(int[] zahlen, int nummer) {
		this.zahlen = zahlen;
		this.nummer = nummer;
	}

	/**
	 * Obere Zahl zurück geben (Drehung wird berücksichtigt)
	 * 
	 * @return Zahl
	 */
	public int getTop() {
		return zahlen[getOrientedIndex(0)];
	}

	/**
	 * Rechte Zahl zurück geben (Drehung wird berücksichtigt)
	 * 
	 * @return Zahl
	 */
	public int getRight() {
		return zahlen[getOrientedIndex(1)];
	}

	/**
	 * Untere Zahl zurück geben (Drehung wird berücksichtigt)
	 * 
	 * @return Zahl
	 */
	public int getBottom() {
		return zahlen[getOrientedIndex(2)];
	}

	/**
	 * Linke Zahl zurück geben (Drehung wird berücksichtigt)
	 * 
	 * @return Zahl
	 */
	public int getLeft() {
		return zahlen[getOrientedIndex(3)];
	}

	/**
	 * Feld 90° nach rechts drehen
	 */
	public Feld rotate() {
		orientation++;
		if (orientation > 3)
			resetOrientation();
		return this;
	}

	/**
	 * Drehung zurücksetzen
	 */
	public void resetOrientation() {
		orientation = 0;
	}

	/**
	 * Gibt den Index angepasst an die Drehung zurück
	 * 
	 * @param int
	 * @return int
	 */
	private int getOrientedIndex(int index) {
		int orientedIndex = index - orientation;
		if (orientedIndex < 0)
			orientedIndex += 4;
		return orientedIndex;
	}

	/**
	 * Nummer des Feldes zurück geben
	 * 
	 * @return int
	 */
	public int getNummer() {
		return nummer;
	}

	/**
	 * Gibt die Drehung zurück
	 * 
	 * @return Drehung (0-3)
	 */
	public int getOrientation() {
		return orientation;
	}
}
