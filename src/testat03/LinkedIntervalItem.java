package testat03;

import java.math.BigDecimal;

import testat03.Interval.SimpleInterval;

/**
 * Repräsentiert ein einfaches Interval in einem LinkedInterval
 * 
 * Ein einfaches Intervall ist eine Menge von rationalen Zahlen, die sowohl
 * größer oder gleich einer Untergrenze a als auch kleiner oder gleich einer
 * Obergrenze b sind. Die Untergrenze muss immer kleiner gleich der Obergrenze
 * sein.
 * 
 * @author Simon Franzen <simon.franzen@natelio.com>
 * @author Holger Oftring <holger.oftring@me.com>
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: LinkedIntervalItem.java 191 2010-06-21 11:05:10Z m $
 */
class LinkedIntervalItem {

	private LinkedIntervalItem next = null;
	private SimpleInterval data = null;

	/**
	 * Erzeugt ein einfaches mit einem Element
	 */
	public LinkedIntervalItem(SimpleInterval data) {
		this.data = data;
	}

	/**
	 * Gibt das eigentliche einfache Intervall zurück
	 * 
	 * @return SimpleInterval
	 */
	public SimpleInterval getData() {
		return data;
	}

	/**
	 * Gibt den Startwert dieses Intervalls zurück
	 * 
	 * @return Startwert
	 */
	public BigDecimal getFrom() {
		return getData().from;
	}

	/**
	 * Gibt den Endwert dieses Intervalls zurück
	 * 
	 * @return Endwert
	 */
	public BigDecimal getTo() {
		return getData().to;
	}

	/**
	 * Setzt den nächsten (nachfolgende) Intervall
	 * 
	 * @param nächster
	 *            Interval
	 */
	public void setNext(LinkedIntervalItem next) {
		this.next = next;
	}

	/**
	 * Gibt den nächsten (nachfolgende) Intervall zurück
	 * 
	 * @return nächster Interval
	 */
	public LinkedIntervalItem getNext() {
		return next;
	}

	/**
	 * Prüft, ob sich dieses Intervall mit dem übergebenen Intervall
	 * überscheiden
	 * 
	 * @param zu
	 *            Überschneidendes Interval
	 * @return Ob sich dieses Intervall und i überschneiden
	 */
	public boolean intersects(LinkedIntervalItem i) {
		if (getFrom().compareTo(i.getFrom()) >= 0
				&& getFrom().compareTo(i.getTo()) <= 0)
			return true;
		if (getTo().compareTo(i.getFrom()) >= 0
				&& getTo().compareTo(i.getTo()) <= 0)
			return true;
		if (getTo().compareTo(i.getFrom()) >= 0
				&& getFrom().compareTo(i.getFrom()) <= 0)
			return true;
		if (getTo().compareTo(i.getFrom()) <= 0
				&& getFrom().compareTo(i.getFrom()) >= 0)
			return true;
		return false;
	}

	/**
	 * Gibt die Überschneidung zwischen diesem Intervall und i1 zurück
	 * 
	 * @param i1
	 * @return Überscheidung
	 */
	public LinkedIntervalItem getIntersection(LinkedIntervalItem i1) {
		if (!intersects(i1))
			return null;
		SimpleInterval iData = new SimpleInterval();
		iData.from = getFrom().compareTo(i1.getFrom()) > 0 ? getFrom() : i1
				.getFrom();
		iData.to = getTo().compareTo(i1.getTo()) < 0 ? getTo() : i1.getTo();
		return new LinkedIntervalItem(iData);
	}

	/**
	 * Gibt eine String-repräsentation des Intervals zurück
	 * 
	 * @return String
	 */
	public String toString() {
		String s = "[" + getFrom().toString();
		if (getTo().compareTo(getFrom()) != 0) {
			s += "|" + getTo().toString();
		}
		s += "]";
		return s;
	}

	/**
	 * Prüft ob eine innere Überschneidung mit i vorliegt
	 * 
	 * @param i
	 * @return Innere Überschneidung liegt vor
	 */
	public boolean intersectsInner(LinkedIntervalItem i) {
		return getFrom().compareTo(i.getFrom()) <= 0
				&& getTo().compareTo(i.getFrom()) >= 0
				&& getFrom().compareTo(i.getTo()) <= 0
				&& getTo().compareTo(i.getTo()) >= 0;
	}

	/**
	 * Prüft ob eine äußere Überschneidung mit i vorliegt
	 * 
	 * @param i
	 * @return Innere Überschneidung liegt vor
	 */
	public boolean intersectsOuter(LinkedIntervalItem i) {
		return getFrom().compareTo(i.getFrom()) >= 0
				&& getTo().compareTo(i.getFrom()) >= 0
				&& getFrom().compareTo(i.getTo()) <= 0
				&& getTo().compareTo(i.getTo()) <= 0;
	}

	/**
	 * Clone-Methode
	 * 
	 * @return Clon
	 */
	public LinkedIntervalItem clone() {
		SimpleInterval d = new SimpleInterval();
		d.from = getFrom();
		d.to = getTo();
		LinkedIntervalItem i = new LinkedIntervalItem(d);
		return i;
	}

	/**
	 * Vergleicht zwei Intervall
	 * 
	 * @return Ob beide gleich sind
	 */
	public boolean equals(LinkedIntervalItem i) {
		return getFrom().equals(i.getFrom()) && getTo().equals(i.getTo());
	}
}