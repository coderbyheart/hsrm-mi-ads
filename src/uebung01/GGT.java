package uebung01;

import java.util.Date;

import de.medieninf.ads.ADSTool;

/**
 * Größter gemeinsamer Teiler nach Euklid
 * 
 * Pseudo-Code:
 * 1. Lese a, Lese b
 * 2. Wenn a = b dann gebe b zurück
 * 3. wenn a kleiner b gehe zu 1. mit a = a und b = b-a
 * 4. wenn b kleiner a gehe zu 1. mit a = a-b und b = b
 * 
 * @author Markus Tacker <m@tacker.org> *
 */
public class GGT {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		GGT myGGT = new GGT();
		int[] testZahlen = ADSTool.readIntArray("data/ggts.dat");
		/*
		System.out.println(myGGT.ggTRecursive(10, 2, true));
		System.out.println(myGGT.ggTFast(10, 2, true));
		*/
		myGGT.runRecursive(testZahlen);
		myGGT.runStruct(testZahlen);
		myGGT.runFast(testZahlen);
	}
	
	/**
	 * GGTs mit der rekursiven Methode berechnen
	 */
	private void runRecursive(int[] testZahlen)
	{
		System.out.println("Rekursive Methode");
		double ggtSum = 0;
		double numGGT = 0;
		Date startZeit = new Date();
		for(int i = 0; i < testZahlen.length; i += 2) {
			ggtSum += this.ggTRecursive(testZahlen[i], testZahlen[i+1]);
			numGGT++;
		}
		Date endZeit = new Date();
		System.out.println("Der Durchschnittswert aller berechneten ggTs is: " + (ggtSum / numGGT));
		System.out.println("Dauer der Berechnung: " + (endZeit.getTime() - startZeit.getTime()) + "ms");
	}
	
	/**
	 * GGTs mit der strukturierten Methode berechnen
	 */
	private void runStruct(int[] testZahlen)
	{
		System.out.println("Strukturiert Methode");
		double ggtSum = 0;
		double numGGT = 0;
		Date startZeit = new Date();
		for(int i = 0; i < testZahlen.length; i += 2) {
			ggtSum += this.ggTStruct(testZahlen[i], testZahlen[i+1]);
			numGGT++;
		}
		Date endZeit = new Date();
		System.out.println("Der Durchschnittswert aller berechneten ggTs is: " + (ggtSum / numGGT));
		System.out.println("Dauer der Berechnung: " + (endZeit.getTime() - startZeit.getTime()) + "ms");
	}
	
	/**
	 * GGTs mit der strukturierten Methode berechnen
	 */
	private void runFast(int[] testZahlen)
	{
		System.out.println("Schnelle Methode");
		double ggtSum = 0;
		double numGGT = 0;
		Date startZeit = new Date();
		for(int i = 0; i < testZahlen.length; i += 2) {
			ggtSum += this.ggTFast(testZahlen[i], testZahlen[i+1], false);
			numGGT++;
		}
		Date endZeit = new Date();
		System.out.println("Der Durchschnittswert aller berechneten ggTs is: " + (ggtSum / numGGT));
		System.out.println("Dauer der Berechnung: " + (endZeit.getTime() - startZeit.getTime()) + "ms");
	}
	
	public int ggTRecursive(int a, int b) 
	{
		return this.ggTRecursive(a, b, false);
	}
	
	public int ggTRecursive(int a, int b, boolean debug) 
	{
		if (debug) System.out.println(a + ", " + b);
		if (a == b) {
			if (debug) System.out.println("GGT:\t" + a);
			return a;
		}
		if (a < b) a = this.ggTRecursive(a, b-a, debug);
		if (b < a) a = this.ggTRecursive(a-b, b, debug);
		return a;
	}
	
	public int ggTStruct(int a, int b) 
	{
		while (a != b) {
			if (a < b) {
				b = b - a;	
			} else if (b < a) {
				a = a - b;
			}
		}
		return a;
	}
	
	public int ggTFast(int a, int b, boolean debug) 
	{
		if (a == b) return a;
		if (a < b) {
			b = b % a;	
		} else if (b < a) {
			a = a % b;
		}
		if (a == 0) return b;
		if (b == 0) return a;
		return ggTFast(b, a, debug);
	}
}
