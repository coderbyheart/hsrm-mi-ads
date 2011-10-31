package testat03;

import java.math.BigDecimal;
import java.util.Iterator;

/**
 * Intervallbereich von Zahlen. Der Datentyp ist immutable.
 * @author pb
 */
public interface Interval extends Iterable<Interval.SimpleInterval> {

	class SimpleInterval {
		BigDecimal from;
		BigDecimal to;
	}
	
	boolean contains(BigDecimal number);
	boolean isEmpty();
	
	Interval union(BigDecimal from, BigDecimal to);
	Interval union(Interval ival);	
	Interval intersect(Interval ival);
	Interval difference(Interval ival);

	Iterator<SimpleInterval> iterator();
}


