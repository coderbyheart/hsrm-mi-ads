package testat03;

import java.util.Iterator;

import testat03.Interval.SimpleInterval;

/**
 * Iterator für die Intervalle
 * 
 * @author Simon Franzen <simon.franzen@natelio.com>
 * @author Holger Oftring <holger.oftring@me.com>
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: LinkedIntervalIterator.java 189 2010-06-20 12:59:04Z m $
 */
public class LinkedIntervalIterator implements Iterator<SimpleInterval> {

	private LinkedIntervalItem current;

	/**
	 * Erzeugt den Iterator
	 * 
	 * @param Das
	 *            erste Element des Intervalles
	 */
	public LinkedIntervalIterator(LinkedIntervalItem first) {
		current = first;
	}

	/**
	 * Prüft, ob es ein nächstes Element gibt
	 */
	@Override
	public boolean hasNext() {
		return current != null;
	}

	/**
	 * Gibt das nächste Element zurück
	 */
	@Override
	public SimpleInterval next() {
		SimpleInterval i = current.getData();
		current = current.getNext();
		return i;
	}

	/**
	 * Unsupported.
	 * 
	 * @see Iterator#remove()
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
