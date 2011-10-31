package uebung08;

import java.util.Iterator;

/**
 * Schnittstelle für den ADT STrie
 * @author barth
 */
public interface STrie extends Iterable<String> {
	
	/**
	 * Füge ein Vorkommen von s hinzu
	 * @param s Wort
	 * @return true gdw das Wort war neu
	 */
	boolean add(String s); 
	
	/**
	 *  Wie häufig kommt s vor?
	 * @param s Wort
	 * @return Häufigkeit Vorkommen
	 */
	int count(String s); 
	
	/**
	 * Anzahl der Vorkommen aller Wörter
	 * @return Anzahl alle Vorkommen
	 */
	int noOccurences(); 

	/**
	 * Anzahl der verschiedenen Wörter
	 * @return Anzahl verschiedener Wörter
	 */
	int noWords(); 

	/**
	 * Test ob STrie leer ist
	 * @return true gdw STrie ist leer
	 */
	boolean isEmpty();
	
	/**
	 * Entferne alle Vorkommen
	 * @param s Wort
	 * @return true gdw s mindestens einmal vorkam
	 */
	boolean remove(String s); 


	/**
	 * Iteriere über die vorkommenden Objekte
	 * @return Iterator<String> über alle Worte
	 */
	Iterator<String> iterator(); 
	
}
