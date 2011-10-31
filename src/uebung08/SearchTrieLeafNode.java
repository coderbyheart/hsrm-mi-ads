package uebung08;

public class SearchTrieLeafNode extends SearchTrieNode {

	private String data = "";
	private int count = 0;
	
	public SearchTrieLeafNode(String data)
	{
		this.data = data;
	}
	
	public String getData()
	{
		return data;
	}
	
	public int getCount()
	{
		return count;
	}

	public void inc() {
		count++;
		
	}

}
