package uebung08;

import simpleunit.SimpleUnit;
import simpleunit.SimpleUnitAssertException;

public class Aufgabe2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleUnit test = new SimpleUnit();
		SearchTrie trie = new SearchTrie();
		
		trie.add("a");
		trie.add("a");
		trie.add("a");
		
		trie.add("b");
		trie.add("b");
		
		trie.add("c");
		
		trie.add("test");
		trie.add("test");
		
		try {
			test.assertEquals(trie.count("a"), 3);
			test.assertEquals(trie.count("b"), 2);
			test.assertEquals(trie.count("c"), 1);
			test.assertEquals(trie.count("d"), 0);
			test.assertEquals(trie.count("test"), 2);
		} catch (SimpleUnitAssertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(trie.toDot());

	}

}
