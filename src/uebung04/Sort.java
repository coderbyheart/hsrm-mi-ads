package uebung04;

import java.util.Arrays;

/**
 * Vaterklasse für Lösung zu Implementierung der Sortierverfahren
 * Vorgehen für Beispiel SelectionSort
 * <ul>
 * <li> Von Sort erben mit
 *             public class SelectionSort extends Sort {...
 * </li>
 * <li> In dieser Klasse SortierAlgorithmus als Methode sort implementieren
 *             public void sort(int[] a) {...
 * </li>
 * <li> Für Vergleiche lt, lte, gt, gte verwenden (int, int),
 *        zum Vertauschen swap(a, i, j) verwenden (int[], int, int)
 * </li>
 * <li> Sortieren eines Feldes zum Beispiel mit dem Aufruf
 *             new SelectionSort().run(a); </li>
 * </li>
 * <li> Automatische Überprüfung of korrekt und Ausgabe der Statistik 
 * </li>
 * </ul>
 * @author Peter Barth
 */
public abstract class Sort {

    /**
     * Die Methode sort sortiert das eigentliche Feld.
     * Die Methode sort muss implementiert werden
     * @param a Zu sortierendes Feld
     */
    abstract public void sort(int[] a);


    /**
     * Die Methode run muss aufgerufen werden aber nicht überschrieben werden.
     * @param a Zu sortiererndes Feld
     */
    public void run(int[] a) {
    	dorun(a);
    }

    /**
     * Vergleichsfunktion "less than". Statt < verwenden um zu zählen.
     * @param l Zahl
     * @param r Zahl
     * @return wahr gdw l < r
     */
    public boolean lt(int l, int r) {
    	comps++;
    	return l < r;
    }

    /**
     * Vergleichsfunktion "less than or equals". Statt <= verwenden um zu zählen.
     * @param l Zahl
     * @param r Zahl
     * @return wahr gdw l <= r
     */
    public boolean lte(int l, int r) {
    	comps++;
    	return l <= r;
    }

    /**
     * Vergleichsfunktion "greater than". Statt > verwenden um zu zählen.
     * @param l Zahl
     * @param r Zahl
     * @return wahr gdw l > r
     */
    public boolean gt(int l, int r) {
    	comps++;
    	return l > r;
    }

    /**
     * Vergleichsfunktion "greater than or equals". Statt >= verwenden um zu zählen.
     * @param l Zahl
     * @param r Zahl
     * @return wahr gdw l >= r
     */
    public boolean gte(int l, int r) { // größer gleich
    	comps++;
    	return l >= r;
    }

    /**
     * Vertauschen von zwei Zahlen in einem Feld. Kein Boundscheck.
     * @param a Feld von Zahlen
     * @param i Position von einer zu tauschender Zahl
     * @param j Position von anderer zu tauschender Zahl
     */
    public void swap(int[] a, int i, int j) {
    	swaps++;
    	int h = a[i];
    	a[i] = a[j];
    	a[j] = h;
    }

    /**
     * Vertauschen von zwei Zahlen in einem Feld. Kein Boundscheck.
     * @param a Ein Feld von Zahlen
     * @param i Position von zu tauschender Zahl in a
     * @param b Anderes Feld von Zahlen
     * @param j Position von zu tauschender Zahl in b
     */
    public void swap(int[] a, int i, int[] b, int j) {
    	swaps++;
    	int h = a[i];
    	a[i] = b[j];
    	b[j] = h;
    }

    /**
     * Testet ob in i das Bit bit gesetzt ist. Für Digitales Sortieren.
     * @param i Zahl
     * @param bit zu testendes Bit
     * @return wahr gdw das bit'te Bit in u gesetzt ist
     */
    public boolean bit(int i, int bit) {
    	comps++;
    	return ((1 << bit) & i) != 0;
    }

    // ab hier private Implementierung
    private long comps = 0;
    private long swaps = 0;
    private long ticks = 0;

    private boolean isSorted(int[] a, int[] a_) {
    	int[] b = new int[a_.length];
    	System.arraycopy(a_, 0, b, 0, a_.length);
    	Arrays.sort(b);  // b ist sortiertes Array von Original
    	for (int i = 0; i < a.length; i++) {
    		if (a[i] != b[i])
    			return false;
    	}
    	return true;
    }

    private void showlong(long l) {
    	long d = 1000000000;
    	while (d != 1) {
    		if (l / d == 0)
    			System.out.print(" ");
    		d = d / 10;
    	}
    	System.out.print(l);
    }

    private void showsecs(long ticks) {
    	showlong(ticks / 1000);
    	System.out.print(".");
    	long csecs = (ticks % 1000) / 10;
    	if (csecs < 10) {
    		System.out.print("0");
    	}
    	System.out.print(csecs);
    }

    private void dorun(int[] a) {
    	int[] unsorted = new int[a.length];
    	for (int i = 0; i < a.length; i++) // Kopie, damit Parameter nicht verändert wird
    		unsorted[i] = a[i];
    	long startTime = System.nanoTime(); // Zeitmessung starten
    	sort(a);
    	long stopTime = System.nanoTime(); // verbrauchte Zeit
    	ticks = (stopTime - startTime)/1000000;
    	if (!isSorted(a, unsorted)) {
    		System.out.println("Sorry, Feld ist nicht sortiert!");
    		return;
    	}
    	System.out.print("a[");
    	showlong(a.length);
    	System.out.print("] ");
    	showlong(comps);
    	System.out.print(" Comps");
    	showlong(swaps);
    	System.out.print(" Swaps");
    	showsecs(ticks);
    	System.out.print(" s");
    	System.out.println();
    }

}