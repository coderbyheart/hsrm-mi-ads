package testat03;

import java.math.BigDecimal;
import java.util.Iterator;

/**
 * Enthält und verwaltet eine Menge von einfachen Intervallen
 * 
 * @author Simon Franzen <simon.franzen@natelio.com>
 * @author Holger Oftring <holger.oftring@me.com>
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: LinkedInterval.java 191 2010-06-21 11:05:10Z m $
 */
public class LinkedInterval implements Interval {
	public static final String EMPTY = "{}";
	private LinkedIntervalItem first;
	private LinkedIntervalItem last;

	/**
	 * Hilfsmethode zum einfachen anhängen eines SimpleInterval
	 * 
	 * @param from
	 * @param to
	 * @return this
	 */
	LinkedInterval add(BigDecimal from, BigDecimal to) {
		SimpleInterval i = new SimpleInterval();
		i.from = from;
		i.to = to;
		addInterval(new LinkedIntervalItem(i));
		return this;
	}

	/**
	 * Hilfsmethode zum einfachen Anhängen eines SimpleInterval
	 * 
	 * @param from
	 * @param to
	 * @return this
	 */
	LinkedInterval add(long i, long j) {
		return add(BigDecimal.valueOf(i), BigDecimal.valueOf(j));
	}

	/**
	 * Hilfsmethode zum einfachen Anhängen eines SimpleInterval
	 * 
	 * @param SimpleInterval
	 * @return this
	 */
	LinkedInterval add(SimpleInterval i) {
		return add(i.from, i.to);
	}

	/**
	 * Fügt ein einfaches Interval hinzu Führt eine einfache überprüfung auf
	 * Überlappung aus.
	 * 
	 * Erwartet, dass die SimpleIntervalle aufsteigend sortiert eingefügt werden
	 * 
	 * Kann vorhandene Intervall erweitern.
	 * 
	 * @todo Exception werfen, wenn ungültige einfache Intervalle hinzugefügt
	 *       werden, z.B. kleinere als das Erste etc.
	 * @param linkedIntervalItem
	 */
	private void addInterval(LinkedIntervalItem linkedIntervalItem) {
		if (first == null) {
			first = linkedIntervalItem;
		}
		if (last != null) {
			if (linkedIntervalItem.getFrom().compareTo(last.getTo()) <= 0) {
				// Überlappung mit letztem Interval -> erweitern
				if (last.getData().to.compareTo(linkedIntervalItem.getTo()) <= 0) {
					// Aber nur, wenn Ende des einzufügenden Intervals größer
					// ist
					last.getData().to = linkedIntervalItem.getTo();
				}
				return;
			}
			last.setNext(linkedIntervalItem);
		}
		last = linkedIntervalItem;
	}

	/**
	 * Erzeugt ein neues Interval, das der Differenz zwischen diesem und ival
	 * entspricht
	 * 
	 * @param Interval
	 *            mit dem die Differenz zu erzeugen ist
	 * @return Resultierendes Interval
	 */
	@Override
	public Interval difference(Interval ival) {
		LinkedInterval i = (LinkedInterval) ival;

		// Ausnahme: Die beiden Intervalle überschneiden sich nicht
		// -> nichts zu tun
		if (!i.intersects(this))
			return (Interval) clone();

		LinkedInterval difference = new LinkedInterval();

		// Alle SimpleIntervalle dieses Intervalls auf Überscheidung mit ival
		// überprüfen
		LinkedIntervalItem i1 = getFirst();
		do {
			LinkedIntervalItem r = i1.clone();
			LinkedIntervalItem i2 = i.getFirst();
			do {
				if (r.equals(i2)) {
					// Intervalle sind gleich?
					r = null;
				} else if (r.intersects(i2)) {
					// Unterschied berechnen
					if (r.intersectsInner(i2)) {
						// Innere Überscheidung
						difference.add(r.getFrom(), i2.getFrom());
						r.getData().from = i2.getTo();
						r.getData().to = r.getTo();
						// difference.add(i2.getTo(), i1.getTo());
					} else if (r.intersectsOuter(i2)) {
						// Äußere überscheidung -> leer
						r = null;
					} else if (r.getFrom().compareTo(i2.getFrom()) <= 0) {
						// Überschneidung links
						// difference.add(i1.getFrom(), i2.getFrom());
						r.getData().from = r.getFrom();
						r.getData().to = i2.getFrom();
					} else {
						// Überschneidung rechts
						// difference.add(i2.getTo(), i1.getTo());
						r.getData().from = i2.getTo();
						r.getData().to = r.getTo();
					}
				} else {
					// Keine Überscheidung
					// Intervall aus erstem nehmen
					// difference.add(i1.getFrom(), i1.getTo());
				}
				i2 = i2.getNext();
			} while (i2 != null);
			if (r != null)
				difference.add(r.getFrom(), r.getTo());
			i1 = i1.getNext();
		} while (i1 != null);

		return difference;
	}

	/**
	 * Prüft, ob sich zwei Linkedintervalle überschneiden
	 * 
	 * @param linkedInterval
	 * @return Ob sich zwei LinkedIntervalle überschneiden
	 */
	private boolean intersects(LinkedInterval i) {
		return !(i.getLast().getTo().compareTo(getFirst().getFrom()) < 0 || i
				.getFirst().getFrom().compareTo(getLast().getTo()) > 0);
	}

	/**
	 * Erzeugt ein neues Interval, das der Überschneidungens zwischen diesem und
	 * ival entspricht
	 * 
	 * @param Interval
	 *            mit dem die Überschneidungens zu erzeugen ist
	 * @return Resultierendes Interval
	 */
	@Override
	public Interval intersect(Interval ival) {
		LinkedInterval intersection = new LinkedInterval();
		LinkedInterval i = (LinkedInterval) ival;

		// Ausnahme: Die beiden Intervalle überschneiden sich nicht
		// -> Ergbenis ist leer;
		if (!i.intersects(this))
			return intersection;

		// Alle SimpleIntervalle dieses Intervalls auf Überscheidung mit ival
		// überprüfen
		LinkedIntervalItem i1 = getFirst();
		do {
			LinkedIntervalItem i2 = i.getFirst();
			do {
				if (i1.intersects(i2)) {
					LinkedIntervalItem iData = i1.getIntersection(i2);
					intersection.addInterval(iData);
				}
				i2 = i2.getNext();
			} while (i2 != null);
			i1 = i1.getNext();
		} while (i1 != null);
		return intersection;
	}

	/**
	 * Prüft ob das Interval leer ist
	 */
	@Override
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Gibt einen Iterator über die Werte des Intervals zurück
	 */
	public Iterator<SimpleInterval> iterator() {
		return new LinkedIntervalIterator(this.first);
	}

	/**
	 * Erzeugt eine Vereinigung dieses Intervals mit einem Interval von from bis
	 * to
	 * 
	 * @param Startwert
	 *            des Intervals, das mit diesem vereinigt werden soll
	 * @param Endwert
	 *            des Intervals, das mit diesem vereinigt werden soll
	 * @return Resultierendes Interval
	 */
	@Override
	public Interval union(BigDecimal from, BigDecimal to) {
		LinkedInterval i;
		i = LinkedIntervalFactory.create(from, to);
		return union(i);
	}

	/**
	 * Erzeugt ein neues Interval, das dieses und ival vereinigt
	 * 
	 * @param Interval
	 *            mit dem dieses vereinigt werden soll
	 * @return Resultierendes Interval
	 */
	@Override
	public Interval union(Interval ival) {
		LinkedInterval union = new LinkedInterval();
		LinkedInterval i = (LinkedInterval) ival;

		// Ausnahme: eines der beiden Intervalle ist leer.
		if (isEmpty())
			return i.clone();
		if (i.isEmpty())
			return clone();

		// Die SimpleIntervals der beiden Intervall aufsteigend hinzufügen
		LinkedIntervalItem i1 = getFirst();
		LinkedIntervalItem i2 = i.getFirst();
		SimpleInterval si1 = i1.getData();
		SimpleInterval si2 = i2.getData();
		do {
			if (si2 == null
					|| (si1 != null && si1.from.compareTo(si2.from) <= 0)) {
				union.add(si1);
				i1 = i1.getNext();
				si1 = i1 != null ? i1.getData() : null;
			} else {
				union.add(si2);
				i2 = i2.getNext();
				si2 = i2 != null ? i2.getData() : null;
			}
		} while (si1 != null || si2 != null);
		return union;
	}

	/**
	 * Prüft ob diese Interval den Wert number enthält
	 */
	@Override
	public boolean contains(BigDecimal number) {
		// Leere Menge
		if (first == null)
			return false;

		// Ausnahme: number liegt vor oder hinter meinen Intervallen
		if (number.compareTo(getFirst().getFrom()) < 0
				|| number.compareTo(getLast().getTo()) > 0)
			return false;

		// Alle Elemente durchgehen
		LinkedIntervalItem i = getFirst();
		do {
			if (number.compareTo(i.getFrom()) >= 0
					&& number.compareTo(i.getTo()) <= 0)
				return true;
			i = i.getNext();
		} while (i != null);
		return false;
	}

	/**
	 * Prüft ob diese Interval den Wert i enthält
	 */
	public boolean contains(long i) {
		return contains(BigDecimal.valueOf(i));
	}

	/**
	 * Erzeugt eine String-Repräsentation dieses Intervals.
	 * 
	 * Ein einfaches Intervall, das nur ein Element enthält wird so dargestellt:
	 * [10]
	 * 
	 * Ein einfaches Intervall, das von 10 bis 100 geht wird so dargestellt:
	 * [10|100]
	 * 
	 * Bei einem Interval mit mehreren Intervallen werden diese Einfach
	 * nebeneinander dargstellt: [10|100][200|300]...
	 * 
	 * Leere Intervalle werden so dargstellt: {}
	 */
	public String toString() {
		if (first == null)
			return EMPTY;
		String s = "";
		LinkedIntervalItem i = getFirst();
		do {
			s += "[" + i.getFrom().toString();
			if (i.getTo().compareTo(i.getFrom()) != 0) {
				s += "|" + i.getTo().toString();
			}
			s += "]";
			i = i.getNext();
		} while (i != null);
		return s;
	}

	/**
	 * Gibt das erste Element des Intervalles zurück
	 * 
	 * @return Erstes Element
	 */
	LinkedIntervalItem getFirst() {
		return first;
	}

	/**
	 * Gibt das letzte Element des Intervalles zurück
	 * 
	 * @return Letztes Element
	 */
	LinkedIntervalItem getLast() {
		return last;
	}

	/**
	 * Zählt die Anzahl der einfachen Intervall dieses Intervalls
	 * 
	 * @return Anzahl der einfachen Intervalle
	 */
	public int numIntervals() {
		if (isEmpty())
			return 0;
		int num = 0;
		LinkedIntervalItem i = getFirst();
		do {
			num++;
			i = i.getNext();
		} while (i != null);
		return num;
	}

	/**
	 * Klont dieses Interval
	 * 
	 * @return LinkedInterval
	 */
	public LinkedInterval clone() {
		LinkedInterval clone = new LinkedInterval();
		// Alle Elemente durchgehen
		LinkedIntervalItem i = getFirst();
		do {
			clone.add(i.getData());
			i = i.getNext();
		} while (i != null);
		return clone;
	}
}
