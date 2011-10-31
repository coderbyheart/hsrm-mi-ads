package uebung04;

import de.medieninf.ads.ADSTool;

public class Aufgabe5 
{
	public static void main(String[] args) 
	{
		String[] files = new String[3];
		files[0] = "maxsubsimple.dat";
		files[1] = "maxsub.dat";
		files[2] = "maxsublarge.dat";
		
		for(int i = 0; i < files.length; i++) {
			System.out.print(files[i]);
			int[] maxSub = maxSubRecursive(ADSTool.readIntArray(files[i]));
			System.out.print(": " + maxSub[0]);
			System.out.print(" (" + maxSub[1] + "-" + maxSub[2] + ")");
			System.out.println();
		}
	}
	
	public static int[] maxSubRecursive(int[] zahlen)
	{
		int[] result = new int[3];
		if (zahlen.length == 1) {
			result[0] = zahlen[0];
			result[1] = 0;
			result[2] = 0;
			return result;
		}
		
		int[] linkeMenge = new int[zahlen.length / 2];
		for (int i = 0; i < linkeMenge.length; i++) linkeMenge[i] = zahlen[i];
		int[] rechteMenge = new int[zahlen.length - linkeMenge.length];
		for (int i = 0; i < rechteMenge.length; i++) rechteMenge[i] = zahlen[i + linkeMenge.length];
		
		int[] maxRightSum = maxRightSum(linkeMenge);
		int[] maxLeftSum = maxLeftSum(rechteMenge);
		int[] sumLeft = maxSubRecursive(linkeMenge);
		int[] sumRight = maxSubRecursive(rechteMenge);
		int sumTeilmengen = maxRightSum[0] + maxLeftSum[0];
		if (sumTeilmengen >= sumLeft[0] && sumTeilmengen >= sumRight[0]) {
			result[0] = sumTeilmengen;
			result[1] = maxRightSum[1];
			result[2] = maxLeftSum[1] + linkeMenge.length;
			return result;
		}
		if (sumLeft[0] >= sumRight[0]) return sumLeft;
		return sumRight;
	}
	
	public static int[] maxLeftSum(int[] zahlen)
	{
		int[] result = new int[2];
		result[0] = 0;
		result[1] = 0;
		int sum = 0;
		for(int i = 0; i < zahlen.length; i++){
			sum += zahlen[i];
			if(sum > result[0]) {
				result[0] = sum;
				result[1] = i;
			}
		}
		return result;
	}
	
	public static int[] maxRightSum(int[] zahlen)
	{
		int[] result = new int[2];
		result[0] = 0;
		result[1] = 0;
		int sum = 0;		
		for(int i = zahlen.length - 1; i >= 0; i--){
			sum += zahlen[i];
			if(sum > result[0]) {
				result[0] = sum;
				result[1] = i;
			}
		}
		return result;
	}
	
	public static void printArray(int[] zahlen)
	{
		for (int i = 0; i < zahlen.length; i++) System.out.println(zahlen[i]);
		
	}
}
