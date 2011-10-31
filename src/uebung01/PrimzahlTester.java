package uebung01;
import de.medieninf.ads.ADSTool;

/**
 * Übung 01 - Aufgabe 2
 * 
 * @author Markus Tacker <m@tacker.org>
 */
public class PrimzahlTester {
	
	public static void main(String[] args) {
		PrimzahlTester Tester = new PrimzahlTester();
		int numPrim = 0;
		int[] testZahlen = ADSTool.readIntArray("data/isprim.dat");
		for(int i = 0; i < testZahlen.length; i++) {
			numPrim += Tester.isPrim(testZahlen[i]) ? 1 : 0;
		}
		System.out.println("Von " + testZahlen.length + " sind " + numPrim + " Primzahlen.");
	}
	
	public boolean isPrim(int zahl)
	{
		int n = 2;
		if (zahl < n) return false;
		boolean isPrim = true;
		while (n < zahl && isPrim) {
			if (zahl % n == 0) isPrim = false;
			n++;
		}
		return isPrim;
	}
}
