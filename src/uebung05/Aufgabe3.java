package uebung05;

import de.medieninf.ads.ADSTool;

/**
 * Sudoku-Löser (Backtracking)
 * 
 * @author Markus Tacker <m@tacker.org>
 */
public class Aufgabe3 {

	private static int steps = 0;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Matrix einlesen
		int[][] matrix = new int[9][9];
		int[] zahlen = ADSTool.readIntArray("data/	puzzle7.sudoku");
		int j = 0;
		int k = 0;
		for(int i = 0; i < zahlen.length; i++) {
			matrix[j][k] = zahlen[i];
			k++;
			if (k == 9) {
				j++;
				k = 0;
			}
		}
		
		System.out.println("Eingabe");
		System.out.println();
		printField(matrix);
		ADSTool.resetTime();
		loese(matrix);
		long timeElapsed = ADSTool.getTimeMikro();
		System.out.println();
		System.out.println();
		System.out.println("Ergebnis");
		System.out.println();
		printField(matrix);
		System.out.println(steps + " Platzierungen probiert in " + timeElapsed + "ms");
	}

	private static void printField(int[][] field)
	{
		int k = 0;
		for(int i = 0; i < field.length; i++) {
			if (k == 3) {
				System.out.println("-----------------------------");
				k = 0;
			}
			int n = 0;
			for(int j = 0; j < field[i].length; j++) {
				if (n == 3) {
					System.out.print("|");
					n = 0;
				}
				int val = field[i][j];
				System.out.print(" " + (val == 0 ? " " : val) + " ");
				n++;
			}
			System.out.println();
			k++;
		}
	}
	
	private static boolean loese(int[][] matrix) {
		return loese(matrix, 0, 0);
	}
	
	private static boolean loese(int[][] matrix, int row, int col) {
		if (col == matrix.length) {
			// Eine Zeile weiter gehen
			col = 0;
			row++;
			if (row == matrix.length) {
				// Nix mehr zu tun
				return true;
			}
		}
		
		if (matrix[row][col] != 0) {
			// Aktuelle Zelle ist schon gelöst oder vorgegeben
			// Nächste Zelle lösen
			return loese(matrix, row, col + 1);
		}
		
		// 0-9 Zahlen durchprobieren
		for(int n = 1; n <= 9; n++) {
			if (testNumber(matrix, row, col, n)) {
				// Zahl n ist eine möglich Lösung
				// Merken
				matrix[row][col] = n;
				// Und nächste Zelle lösen
				if (loese(matrix, row, col + 1)) {
					return true;
				}
			}
		}
		// Keine Lösung gefunden
		matrix[row][col] = 0;
		return false;
	}

	private static boolean testNumber(int[][] matrix, int row, int col, int number)
	{
		steps++;
		// Prüfe Zeile
		for(int i = 0; i < matrix[row].length; i++) {
			if (matrix[row][i] == number) return false;
		}
		// Prüfe Spalte
		for(int i = 0; i < matrix.length; i++) {
			if (matrix[i][col] == number) return false;
		}
		// Prüfe Region
		int regionWidth = matrix.length / 3;
        int myRegionRow = (row / regionWidth) * 3;
        int myRegionCol = (col / regionWidth) * 3;
        for (int i = 0; i < regionWidth; i++) {
            for (int j = 0; j < regionWidth; j++) {
            	if (matrix[i + myRegionRow][j + myRegionCol] == number) return false;
            }
        }
		return true;
	}
}
