package uebung04;

import de.medieninf.ads.ADSTool;

public class Aufgabe4 
{
	public static void main(String[] args) 
	{
		/*
		maxSubArray(ADSTool.readIntArray("maxsubsimple.dat"));
		System.out.println();
		maxSubArray(ADSTool.readIntArray("maxsub.dat"));
		System.out.println();
		*/
		maxSubArray(ADSTool.readIntArray("maxsublarge.dat"));
		System.out.println();
	}
	
	private static void maxSubArray(int[] zahlen)
	{
		int maxSum = 0;
		int startIndex = 0;
		int stopIndex = 0;
		for(int i = 0; i < zahlen.length; i++) {
			for(int j = i + 1; j < zahlen.length; j++) {
				int sum = 0;
				for (int k = i; k <= j; k++) {
					sum += zahlen[k];
				}
				if (sum > maxSum) {
					startIndex = i;
					stopIndex = j;
					maxSum = sum;
				}
			}
		}
		System.out.println(maxSum);
		System.out.println(startIndex);
		System.out.println(stopIndex);
	}
}
