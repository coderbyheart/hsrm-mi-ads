package uebung08;

import java.util.LinkedList;

class SearchTrieInnerNode extends SearchTrieNode {
	private LinkedList<SearchTrieNode> childs = new LinkedList<SearchTrieNode>();
	private char c;
	private boolean isRoot = false;

	public SearchTrieInnerNode() {
		isRoot = true;
	}

	public SearchTrieInnerNode(char c) {
		this.c = c;
	}

	public char getChar() {
		return c;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void add(String s) {
		SearchTrieLeafNode leaf = getChild(s);
		leaf.inc();
	}

	public SearchTrieLeafNode getChild(String s) {
		return getChild(s, 0);
	}
	
	public void addChild(SearchTrieNode child) {
		childs.push(child);
	}

	private SearchTrieLeafNode getChild(String s, int index) {

		// Letzten Ast erreicht
		if (index == s.length() - 1) {
			// Blätter absuchen
			for (SearchTrieNode leafNode : childs) {
				if (leafNode instanceof SearchTrieInnerNode)
					continue;
				SearchTrieLeafNode leaf = (SearchTrieLeafNode) leafNode;
				if (leaf.getData().equals(s)) {
					// Blatt ist vorhanden
					return leaf;
				}
			}
			// Blatt ist nicht vorhanden
			SearchTrieLeafNode newLeaf = new SearchTrieLeafNode(s);
			addChild(newLeaf);
			
			// Innerer Knoten ist vorhanden
			/*
			if (getChar() == s.charAt(index)) {
				addChild(newLeaf);
			} else {
				SearchTrieInnerNode newBranch = new SearchTrieInnerNode(s.charAt(index));
				newBranch.addChild(newLeaf);
				addChild(newBranch);
			}
			*/
			return newLeaf;
		}

		// Childs durchgehen
		char searchChar = s.charAt(index);
		for (SearchTrieNode branchNode : childs) {
			if (branchNode instanceof SearchTrieLeafNode)
				continue;
			SearchTrieInnerNode branch = (SearchTrieInnerNode) branchNode;
			// Passt der char
			if (branch.getChar() == searchChar) {
				// Rekursion
				return branch.getChild(s, ++index);
			}
		}
		// Kein passenden Ast gefunden
		SearchTrieInnerNode newBranch = new SearchTrieInnerNode(searchChar);
		childs.push(newBranch);
		return newBranch.getChild(s, ++index);
	}

	public String toDot() {
		String dot = "";
		dot += "root [ label=\"\"]\n";
		return toDot(dot);
	}

	public String toDot(String dot) {
		String sourceNode = (isRoot) ? "root" : String.valueOf(c);

		for(SearchTrieNode currNode: childs) {
			String targetNode;
			SearchTrieInnerNode branchNode = null;
			if (currNode instanceof SearchTrieInnerNode) {
				branchNode = (SearchTrieInnerNode)currNode;
				targetNode = String.valueOf(branchNode.getChar());
				dot += targetNode + " [ shape=rect ]\n";
			} else {
				SearchTrieLeafNode leafNode = (SearchTrieLeafNode)currNode;
				targetNode = leafNode.getData();
				dot += targetNode + " [ label=\"" + targetNode + " (" + leafNode.getCount() + ")\"]\n";
			}
			dot += sourceNode + " -> " + targetNode + "\n";
			if (currNode instanceof SearchTrieInnerNode) dot = branchNode.toDot(dot);
		}
		return dot;
	}
}
