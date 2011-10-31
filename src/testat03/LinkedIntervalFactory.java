package testat03;

import java.math.BigDecimal;

/**
 * Hilfsklasse zum erzeugen von LinkedIntervals
 * 
 * Kommt vor allem bei den Unittest zur Vereinfachten Darstellung des Codes zum
 * Einsatz
 * 
 * @author Simon Franzen <simon.franzen@natelio.com>
 * @author Holger Oftring <holger.oftring@me.com>
 * @author Markus Tacker <m@tacker.org>
 * @version $Id: LinkedIntervalFactory.java 189 2010-06-20 12:59:04Z m $
 * 
 */
public class LinkedIntervalFactory extends LinkedInterval {
	/**
	 * Erzeugt ein neues, leeres LinkedInterval
	 * 
	 * @return LinkedInterval
	 */
	public static LinkedInterval create() {
		return new LinkedInterval();
	}

	/**
	 * Erzeugt ein neues LinkedInterval mit einem einfachen Interval mit nur
	 * einem Element
	 * 
	 * @return LinkedInterval
	 */
	public static LinkedInterval create(BigDecimal el) {
		return create(el, el);
	}

	/**
	 * Erzeugt ein neues LinkedInterval mit einem einfachen Interval
	 * 
	 * @return LinkedInterval
	 */
	public static LinkedInterval create(BigDecimal from, BigDecimal to) {
		LinkedInterval li = new LinkedInterval();
		li.add(from, to);
		return li;
	}

	/**
	 * Erzeugt ein neues LinkedInterval mit einem einfachen Interval mit nur
	 * einem Element
	 * 
	 * @return LinkedInterval
	 */
	public static LinkedInterval create(long i) {
		return create(BigDecimal.valueOf(i));
	}

	/**
	 * Erzeugt ein neues LinkedInterval mit einem einfachen Interval
	 * 
	 * @return LinkedInterval
	 */
	public static LinkedInterval create(long i, long j) {
		return create(BigDecimal.valueOf(i), BigDecimal.valueOf(j));
	}

}
