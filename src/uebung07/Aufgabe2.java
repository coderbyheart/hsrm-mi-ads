package uebung07;

import simpleunit.SimpleUnit;
import simpleunit.SimpleUnitAssertException;

public class Aufgabe2 {
	
	public static void main(String[] args)
	{
		SimpleUnit test = new SimpleUnit();
		
		try {
			ArrayQueue<String> a = new ArrayQueue<String>();
			test.assertTrue(a.isEmpty());
			a.enter("a");
			a.enter("b");
			test.assertTrue(a.front().equals("a"));
			a.leave();
			test.assertTrue(a.front().equals("b"));
			a.leave();
			test.assertTrue(a.isEmpty());
		} catch (QueueException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SimpleUnitAssertException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Test laut Aufgabe
		try {
			ArrayQueue<String> s = new ArrayQueue<String>();
			for(int k = 0; k < 1000; k++) {
				s.enter("a");s.enter("b");s.enter("c");
				s.leave();s.leave();s.leave();
				test.assertTrue(s.isEmpty());
			}
		} catch (SimpleUnitAssertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
