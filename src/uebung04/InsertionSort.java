package uebung04;

/**
 * Das Vorgehen ist mit der Sortierung eines Spielkartenblatts vergleichbar. 
 * Am Anfang liegen die Karten des Blatts verdeckt auf dem Tisch. 
 * Die Karten werden nacheinander aufgedeckt und an der korrekten Position 
 * in das Blatt, das in der Hand gehalten wird, eingefügt. 
 * Um die Einfügestelle für eine neue Karte zu finden wird diese 
 * sukzessive (von links nach rechts) mit den bereits einsortierten 
 * Karten des Blattes verglichen. 
 * Zu jedem Zeitpunkt sind die Karten in der Hand sortiert und bestehen 
 * aus den zuerst vom Tisch entnommenen Karten.
 * 
 * @see http://de.wikipedia.org/wiki/Insertionsort
 * @author Markus Tacker <m@tacker.org>
 */
public class InsertionSort extends Sort 
{
	public void sort(int[] a) 
	{
		int[] s = new int[a.length];
		
		for(int i = 0; i < a.length; i++) {
			int currVal = a[i];
			if (i == 0) {
				s[i] = currVal;
				continue;
			}
			for(int j = 0; j < s.length; j++) {
				if (lt(currVal, s[j])) {
					for(int k = s.length - 1; k > j; k--) {
						swap(s, k - 1, k);
					}
					s[j] = currVal;
					break;
				}
				if (gt(currVal, s[j]) && gt(s[j], s[j+1])) {
					s[j+1] = currVal;
					break;
				}
			}
		}
		a = s;
		printArray(a);
	}
	
	private static void printArray(int[] zahlen)
	{
		System.out.println(zahlen.length + " Zahlen");
		for(int k = 0; k < zahlen.length; k++) System.out.println(zahlen[k]);
		System.out.println();
	}
}
