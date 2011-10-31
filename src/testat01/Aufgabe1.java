package testat01;

import de.medieninf.ads.ADSTool;

public class Aufgabe1 
{
	public static void main(String[] args) 
	{
		String[] minLists = null;
		String[] maxLists = null;
		if (args.length == 0) {
			minLists = new String[7];
			minLists[0] = "zahlen" + 10 + ".dat";
			minLists[1] = "zahlen" + 1000 + ".dat";
			minLists[2] = "zahlen" + 10000 + ".dat";
			minLists[3] = "zahlen" + 100000 + ".dat";
			minLists[4] = "zahlen" + 200000 + ".dat";
			minLists[5] = "zahlen" + 400000 + ".dat";
			minLists[6] = "zahlen" + 800000 + ".dat";
			maxLists = minLists;
		} else {
			// String[] minLists = args[0];
			// String[] maxLists = args[1];
		}
		
		int summeMin = 0;
		int summeMax = 0;
		for(int i = 0; i < minLists.length; i++) {
			summeMin += minimum(ADSTool.readIntArray(minLists[i]));
		}		
		for(int i = 0; i < maxLists.length; i++) {
			summeMax += maximum(ADSTool.readIntArray(maxLists[i]));
		}
		System.out.println("Summe Min: " + summeMin);
		System.out.println("Summe Max: " + summeMax);
	}
	
	private static int minimum(int[] zahlen)
	{
		int min = zahlen[0];		
		for(int i = 0; i < zahlen.length; i++) {
			if (zahlen[i] < min) min = zahlen[i]; 
		}
		return min;
	}
	
	private static int maximum(int[] zahlen)
	{
		int max = zahlen[0];		
		for(int i = 0; i < zahlen.length; i++) {
			if (zahlen[i] > max) max = zahlen[i]; 
		}
		return max;
	}
}
