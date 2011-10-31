package testat02;

/**
 * Algorithmen und Datenstrukturen Testat 2
 * 
 * Aufgabe 3: Alle Lösungen eines Puzzles ermitteln
 * 
 * @author Simon Franzen <simon.franzen@natelio.com>
 * @author Holger Oftring <holger.oftring@me.com>
 * @author Markus Tacker <m@tacker.org>
 */
public class Aufgabe3 {

	/**
	 * Ein Puzzle aus einer Datei einlesen und alle möglichen Lösungen ermitteln
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String filename = args.length > 0 ? args[0] : "data/sz1.dat";
		Puzzle puzzle = Puzzle.fromFile(filename);
		
		// Switche durchgehen
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-o")) {
				// -o Switch
				System.out.println("Verwende optimierten Lösungsansatz.");
				puzzle.optimize();
			}
		}

		// Lösen
		boolean solved = puzzle.solveAll();
		
		// Ausgeben
		System.out.println("Lösung für " + filename);
		if (!solved) {
			System.out.println("Nicht gelöst.");
		}
		if (solved) {
			System.out.println("Anzahl der Lösungen: " + puzzle.getSolutions().size());
		}
		
		System.out.println("Knoten: " + puzzle.getTries());
		System.out.println("Dauer:  " + puzzle.getTime() + " Millisekunden");
	}

}
