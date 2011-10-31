package testat02;

import java.util.ArrayList;

import de.medieninf.ads.ADSTool;

/**
 * Repräsentiert ein Puzzle
 * 
 * @author Simon Franzen <simon.franzen@natelio.com>
 * @author Holger Oftring <holger.oftring@me.com>
 * @author Markus Tacker <m@tacker.org>
 */
public class Puzzle {
	private Feld[] felder;
	private int[] felderMap;
	private int[] solveOrder;
	private int n = 0;
	public static final int PUZZLE_SIZE = 3;
	private int tries = 0;
	private long time = 0;
	private ArrayList<String> solutions;
	private boolean solveAll = false; 

	/**
	 * Konstruktur
	 * 
	 * Initialisiert lokale Variable und setz die Standard-Reihenfolge zum Lösen
	 * des Puzzles (von links oben nach rechts unten, Zeilenweisen)
	 */
	public Puzzle() {
		felder = new Feld[PUZZLE_SIZE * PUZZLE_SIZE];
		felderMap = new int[PUZZLE_SIZE * PUZZLE_SIZE];
		solveOrder = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
	}

	/**
	 * Erzeugt ein Puzzle aus einer Datei
	 * 
	 * @param Dateiname
	 * @return Puzzle
	 */
	public static Puzzle fromFile(String file) {
		Puzzle puzzle = new Puzzle();
		int[][] felder = ADSTool.readInt2Array(file);
		int i = 0;
		for (int[] feldZahlen : felder) {
			Feld feld = new Feld(feldZahlen, i);
			puzzle.addField(feld);
			i++;
		}
		return puzzle;
	}

	/**
	 * Fügt dem Puzzle ein Feld hinzu
	 * 
	 * @param feld
	 */
	public void addField(Feld feld) {
		felder[n] = feld;
		felderMap[n] = n;
		n++;
	}

	/**
	 * Das Puzzle auf der console ausgeben
	 */
	public void print() {
		for (int i = 0; i < PUZZLE_SIZE; i++) {
			System.out.println("|-----------||-----------||-----------|");
			for (int j = 0; j < PUZZLE_SIZE; j++) {
				int feldId = felderMap[i * PUZZLE_SIZE + j];
				if (feldId > -1) {
					Feld currFeld = felder[feldId];
					System.out.print("|    " + formatZahl(currFeld.getTop())
							+ "     |");
				}
			}
			System.out.println();
			System.out.println("|           ||           ||           |");
			for (int j = 0; j < PUZZLE_SIZE; j++) {
				int feldId = felderMap[i * PUZZLE_SIZE + j];
				if (feldId > -1) {
					Feld currFeld = felder[feldId];
					System.out.print("|" + formatZahl(currFeld.getLeft())
							+ "   " + currFeld.getNummer() + "   "
							+ formatZahl(currFeld.getRight()) + "|");
				}
			}
			System.out.println();
			System.out.println("|           ||           ||           |");
			for (int j = 0; j < PUZZLE_SIZE; j++) {
				int feldId = felderMap[i * PUZZLE_SIZE + j];
				if (feldId > -1) {
					Feld currFeld = felder[feldId];
					System.out.print("|    " + formatZahl(currFeld.getBottom())
							+ "     |");
				}
			}
			System.out.println();
			System.out.println("|-----------||-----------||-----------|");
		}
	}

	/**
	 * Formatiert eine Zahl zur Ausgabe
	 * 
	 * @param zahl
	 * @return Formatierte Zahl
	 */
	private String formatZahl(int zahl) {
		String formattedZahl = String.valueOf(zahl);
		if (formattedZahl.length() < 2)
			formattedZahl = " " + formattedZahl;
		return formattedZahl;
	}
	
	private void initSolve()
	{
		// Zähler für Platzierungen zurück setzen
		tries = 0;
		resetMap();
	}
	
	private void resetMap()
	{
		// Platzierung der Felder zurück setzen
		for (int i = 0; i < felderMap.length; i++)
			felderMap[i] = -1;
	}

	/**
	 * Das Puzzle mittels Backtracking lösen
	 */
	public boolean solve() {
		initSolve();
		// Lösen und dabei die Zeit messen
		ADSTool.resetTime();
		boolean solved = solve(0);
		time = ADSTool.getTime();
		return solved;
	}
	
	/**
	 * Alle Lösungen des Puzzles ermitteln
	 * 
	 * @return Ob Lösungen gefunden wurden
	 */
	public boolean solveAll()
	{
		solveAll = true;
		solutions = new ArrayList<String>();
		initSolve();
		// Lösen und dabei die Zeit messen
		ADSTool.resetTime();
		boolean solved;
		do {
			resetMap();
			solved = solve(0);
		} while(solved);
		time = ADSTool.getTime();
		return solutions.size() > 0;
	}

	/**
	 * Feld an der Position pos lösen
	 * 
	 * @param Position
	 * @return Ob das Feld an der Stelle Position gelöst werden konnte
	 */
	private boolean solve(int pos) {

		if (pos >= PUZZLE_SIZE * PUZZLE_SIZE) {
			if (solveAll) {
				if (solutions.contains(getSolution())) return false; // Diese Lösung wurde bereits gefunden
				solutions.add(getSolution());
				return true; // Nichts mehr zu tun
			} else {
				return true; // Nichts mehr zu tun
			}
		}

		// Position des zu lösenden Feldes im Puzzle ermitteln
		int solvePos = solveOrder[pos];

		// Felder durchprobieren
		for (int f = 0; f < felder.length; f++) {
			// Feld schon verwendet?
			boolean fieldUsed = false;
			for (int fm = 0; fm < felderMap.length; fm++) {
				if (felderMap[fm] == felder[f].getNummer())
					fieldUsed = true;
			}
			if (fieldUsed)
				continue;

			// Aktuelle Feld-Id im Puzzle merken
			felderMap[solvePos] = felder[f].getNummer();

			// Alle Drehungen durchprobieren
			felder[f].resetOrientation();
			for (int d = 0; d < 4; d++) {
				if (d > 0) {
					felder[f].rotate();
				}
				if (check(solvePos)) { // Teil passt
					tries++;
					if (solve(pos + 1)) // Die nächsten Felder rekursiv lösen
						return true;
				}
			}

			// Für die aktuelle Position wurde keine passendes Feld gefunden
			// Feld frei geben
			felderMap[solvePos] = -1;

		}

		// Keine Lösung gefunden
		return false;
	}

	/**
	 * Prüft ob das Feld an der Postion pos richtig platziert ist
	 * 
	 * @param pos
	 * @return Ob es passt
	 */
	private boolean check(int pos) {
		// Linke Felder
		if (pos == 0 || pos == 3 || pos == 6) {
			if (felderMap[pos + 1] > -1
					&& felder[felderMap[pos]].getRight()
							+ felder[felderMap[pos + 1]].getLeft() != 0) {
				return false;
			}
		}
		// Mittlere Felder
		if (pos == 1 || pos == 4 || pos == 7) {
			if (felderMap[pos + 1] > -1
					&& felder[felderMap[pos]].getRight()
							+ felder[felderMap[pos + 1]].getLeft() != 0) {
				return false;
			}
			if (felderMap[pos - 1] > -1
					&& felder[felderMap[pos]].getLeft()
							+ felder[felderMap[pos - 1]].getRight() != 0) {
				return false;
			}
		}
		// Rechte Felder
		if (pos == 2 || pos == 5 || pos == 8) {
			if (felderMap[pos - 1] > -1
					&& felder[felderMap[pos]].getLeft()
							+ felder[felderMap[pos - 1]].getRight() != 0) {
				return false;
			}
		}
		// Obere Felder
		if (pos < PUZZLE_SIZE) {
			if (felderMap[pos + PUZZLE_SIZE] > -1
					&& felder[felderMap[pos]].getBottom()
							+ felder[felderMap[pos + PUZZLE_SIZE]].getTop() != 0) {
				return false;
			}
		}
		// Mittlere Felder
		if (pos >= PUZZLE_SIZE && pos < PUZZLE_SIZE * 2) {
			if (felderMap[pos + PUZZLE_SIZE] > -1
					&& felder[felderMap[pos]].getBottom()
							+ felder[felderMap[pos + PUZZLE_SIZE]].getTop() != 0) {
				return false;
			}
			if (felderMap[pos - PUZZLE_SIZE] > -1
					&& felder[felderMap[pos]].getTop()
							+ felder[felderMap[pos - PUZZLE_SIZE]].getBottom() != 0) {
				return false;
			}
		}
		// Untere Felder
		if (pos >= PUZZLE_SIZE * 2) {
			if (felderMap[pos - PUZZLE_SIZE] > -1
					&& felder[felderMap[pos]].getTop()
							+ felder[felderMap[pos - PUZZLE_SIZE]].getBottom() != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gibt die Anzahl der Platzierungsversuche zurück
	 * 
	 * @return int
	 */
	public int getTries() {
		return tries;
	}

	/**
	 * Gibt die benötigte Zeit zum Lösen des Puzzles in Millisekunden zurück
	 * 
	 * @return long
	 */
	public long getTime() {
		return time;
	}

	/**
	 * Optimierte Reihenfolge zum Lösen verwenden
	 * 
	 * Hier Lösen wir die Felder so, dass wir möglichst schnell zwei
	 * Test-Möglichkeit für ein zu setzendes Feld haben. Dadurch kann man
	 * schneller unpassenden Felder ausschließen.
	 */
	public void optimize() {
		solveOrder = new int[] { 0, 1, 3, 4, 2, 5, 6, 7, 8 };
	}

	/**
	 * Gibt die aktuelle Lösung zurück
	 * 
	 * @return String mit 012345678:012301230, wobei vor dem Doppelpunkt die
	 *         Reihenfolge der Felder steht und nach dem Doppelpunkt die
	 *         Drehung.
	 */
	public String getSolution() {
		String fields = "";
		String rotation = "";
		for (int i = 0; i < felderMap.length; i++) {
			Feld currField = felder[felderMap[i]];
			fields += currField.getNummer();
			rotation += currField.getOrientation();
		}
		return fields + ":" + rotation;
	}
	
	/**
	 * Gibt alle gefundenen Lösungen zurück
	 * 
	 * @return ArrayList mit Lösungs-Strings
	 */
	public ArrayList<String> getSolutions() {
		return solutions;
	}
}
