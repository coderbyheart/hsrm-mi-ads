package uebung07;

/**
 * Die Schnittstelle Queue
 * @author barth
 */
public interface Queue<T> {
	/**
	 * Fügt ein Objekt einer Warteschlange hinten hinzu 
	 * @param o Hinzuzufügendes Objekt
	 * @throws QueueException (nur bei Realisierung mit maximaler Größe)
	 */
	public void enter(T o) throws QueueException;
	
	/**
	 * Entfernt das vorderste Objekt in einer Warteschlang
	 * @throws QueueException, wenn kein Element enthalten
	 */
	public void leave() throws QueueException;
	
	/**
	 * @return gibt das vorderste Element zurück
	 * @throws QueueException, wenn kein Element enthalten
	 */
	public T front() throws QueueException;
	
	/**
	 * @return true gdw Queue leer ist
	 */
	public boolean isEmpty();
}
