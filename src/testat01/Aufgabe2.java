package testat01;
import java.util.LinkedList;

public class Aufgabe2 
{
	public static void main(String[] args) 
	{
		/*
		String[] medLists = new String[7];
		medLists[0] = "extra\\zahlen" + 10 + ".dat";
		medLists[1] = "extra\\zahlen" + 1000 + ".dat";
		medLists[2] = "extra\\zahlen" + 10000 + ".dat";
		medLists[3] = "extra\\zahlen" + 100000 + ".dat";
		medLists[4] = "extra\\zahlen" + 200000 + ".dat";
		medLists[5] = "extra\\zahlen" + 400000 + ".dat";
		medLists[6] = "extra\\zahlen" + 800000 + ".dat";
		
		for(int i = 0; i < medLists.length; i++) {
			System.out.println("Median von " + medLists[i] + " ist " + medianSuch(ADSTool.readIntArray(medLists[i])));
		}
		*/
		/*
		int[] test = new int[6];
		test[0] = 2;
		test[1] = 3;
		test[2] = 3;
		test[3] = 5;
		test[4] = 7;
		test[5] = 10;
		*/
		/*
		int[] test = new int[4];
		test[0] = 1;
		test[1] = 4;
		test[2] = 3;
		test[3] = 2;
		*/
		int[] test = new int[5];
		test[0] = 9;
		test[1] = 4;
		test[2] = 2;
		test[3] = 1;
		test[4] = 3;
		/*
		int[] test = new int[5];
		test[0] = 9;
		test[1] = 1;
		test[2] = 1;
		test[3] = 1;
		test[4] = 1;
		*/
		System.out.println(medianSuch(test));
	}

	public static int medianSuch(int[] zahlen) 
	{
		return medianSuch(zahlen, zahlen.length, 0);
	}

	public static int medianSuch(int[] zahlen, int basisN, int leftIndex)
	{
		if (zahlen.length == 1) return zahlen[0];
		System.out.println();
		System.out.println("leftIndex: " + leftIndex);
		for(int x = 0; x < zahlen.length; x++) System.out.print(zahlen[x] + " ");
		System.out.println();
		int median = zahlen[zahlen.length / 2];
		System.out.println("median: " + median);
		LinkedList<Integer> linkeMenge = new LinkedList();
		LinkedList<Integer> rechteMenge = new LinkedList();
		LinkedList<Integer> medianMenge = new LinkedList();
		
		for (int n = 0; n < zahlen.length; n++) {
			int aktuelleZahl = zahlen[n];
			if (aktuelleZahl < median) {
				linkeMenge.add(aktuelleZahl);
			}
			if (aktuelleZahl == median) {
				medianMenge.add(aktuelleZahl);
			}
			if (aktuelleZahl > median) {
				rechteMenge.add(aktuelleZahl);
			}
		}
		printMenge(linkeMenge, "Linke Menge");
		printMenge(medianMenge, "Median Menge");
		printMenge(rechteMenge, "Rechte Menge");
		int k = linkeMenge.size() - 1 + leftIndex;
		System.out.println("k: " + k);
		int g = k + medianMenge.size() + 1 + leftIndex;
		System.out.println("g: " + g);
		int halb = basisN / 2;
		if (basisN % 2 == 0) halb -= 1;
		System.out.println("Halb: " + halb);
		if (halb <= k) return medianSuch(toIntArray(linkeMenge), basisN, 0);
		if (halb >= g) return medianSuch(toIntArray(rechteMenge), basisN, g);
		return median;
	}
	
	private static int[] toIntArray(LinkedList<Integer> list)
	{
		int[] neueMenge = new int[list.size()];
		for(int n = 0; n < list.size(); n++) neueMenge[n] = list.get(n);
		return neueMenge;
	}
	
	private static void printMenge(LinkedList<Integer> list, String title)
	{
		System.out.print(title + ": ");
		for(int x = 0; x < list.size(); x++) System.out.print(list.get(x) + " ");
		System.out.println();
	}
}