package uebung04;

/**
 * SelectionSort
 * 
 * Sei S der sortierte Teil des Arrays und U der unsortierte Teil. 
 * Am Anfang ist S noch leer, U entspricht dem ganzen Array. 
 * Das Sortieren durch Auswählen funktioniert so:
 *  - Suche das kleinste Element in U und vertausche es mit dem ersten Element.
 *  - Danach ist das Array bis zu dieser Position sortiert. 
 *  - Das kleinste Element wird in S verschoben. 
 *  S ist um ein Element gewachsen, U um ein Element kürzer geworden. 
 *  Anschließend wird das Verfahren solange wiederholt, bis das gesamte Array abgearbeitet worden ist.
 * 
 * @see http://de.wikipedia.org/wiki/SelectionSort
 * @author Markus Tacker <m@tacker.org>
 */
public class SelectionSort extends Sort 
{
	/**
	 * Implementiert den SelectionSort
	 */
	public void sort(int[] a) 
	{
		int k = 0;
		do {
			int smallestIndex = k;
			for(int i = k; i < a.length; i++) {
				if (lt(a[i], a[smallestIndex])) smallestIndex = i;
			}
			swap(a, smallestIndex, k);
			k++;
		} while(k < a.length - 1);
	}
}
