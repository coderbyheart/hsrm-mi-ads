package uebung07;

import java.util.Stack;

import de.medieninf.ads.ADSTool;

public class Aufgabe1 {
	public static void main(String[] args) {
		String[] strings = ADSTool.readStringArray("data/klammern.dat");
		int numStrings = 0;
		int correctStrings = 0;
		for (int i = 0; i < strings.length; i++) {
			numStrings++;
			if (isCorrect(strings[i])) {
				correctStrings++;
			}
		}
		System.out.println("Anzahl der getesten Zeichenketten: " + numStrings);
		System.out.println("Korrekt geklammert: " + correctStrings);

	}

	private static boolean isCorrect(String strings) {

		Stack<String> s = new Stack<String>();
		for (int j = 0; j < strings.length(); j++) {
			if (strings.charAt(j) == '(') {
				s.add(strings);
			} else {
				if (s.empty())
					return false;
				s.pop();
			}
		}
		return s.empty();
	}
}
