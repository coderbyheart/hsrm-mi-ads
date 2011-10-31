package uebung04;

import de.medieninf.ads.ADSTool;

/**
 * Testet die Implementierungen
 * 
 * @author Markus Tacker <m@tacker.org> *
 */
public class Test 
{
	public static void main(String[] args) {
		SelectionSort selectionSort = new SelectionSort();
		selectionSort.run(ADSTool.readIntArray("data/tosort.10.dat"));
		InsertionSort insertionSort = new InsertionSort();
		insertionSort.run(ADSTool.readIntArray("data/tosort.10.dat"));
	}
}
