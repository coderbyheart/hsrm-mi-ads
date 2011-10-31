package uebung07;

/**
 * Queue-Ausnahme
 * @author barth
 */
@SuppressWarnings("serial")
public class QueueException extends RuntimeException {
	/**
	 * Queue-Ausnahme mit spezifischem Test(msg)
	 * @param msg
	 */
	public QueueException (String msg) { super(msg); }
	
	/**
	 * Queue-Ausnahme
	 */
	public QueueException() {};
}
