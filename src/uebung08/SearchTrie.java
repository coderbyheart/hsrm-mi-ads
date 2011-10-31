package uebung08;

import java.util.Iterator;

public class SearchTrie implements STrie {
	
	private SearchTrieInnerNode root;
	
	public SearchTrie()
	{
		root = new SearchTrieInnerNode();
	}
	
	@Override
	public boolean add(String s) {
		root.add(s);
		return true;
	}

	@Override
	public int count(String s) {
		return root.getChild(s).getCount();
	}
	
	public String toDot()
	{
		return root.toDot();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int noOccurences() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int noWords() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean remove(String s) {
		// TODO Auto-generated method stub
		return false;
	}

}
