package testat02;

/**
 * Algorithmen und Datenstrukturen Testat 2
 * 
 * Aufgabe 1: Puzzle ausgeben
 * 
 * @author Simon Franzen <simon.franzen@natelio.com>
 * @author Holger Oftring <holger.oftring@me.com>
 * @author Markus Tacker <m@tacker.org>
 */
public class Aufgabe1 {

	/**
	 * Ein Puzzle aus einer Datei einlesen und auf der Console ausgeben
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Puzzle puzzle = Puzzle.fromFile(
			args.length > 0 ? args[0] : "data/sz1.dat"
		);
		puzzle.print();
	}
}
