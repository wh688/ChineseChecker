package chinesechecker.client;

import java.util.Vector;

public class Nodes {
	private Vector vector = new Vector();
	
	public Nodes() {
		
	}
	
	public void Add(Node node) {
		if (vector.indexOf(node) == -1) {
			vector.add(node);
		}
	}
	
	public int size() {
		return vector.size();
	}
	
	public Node get(int index) {
		return (Node) vector.get(index);
	}
	
	public void clear() {
		vector.clear();
	}
	
	public void remove(Node node) {
		vector.remove(node);
	}	
	
	public int indexOf(Node node) {
		return vector.indexOf(node);
	}
	
	public int indexOfByData(Object data) {
		for (int i = 0; i < vector.size(); i++) {
			if (get(i).data.equals(data)) {
				return i;
			}
		}
		return -1;
	}

}
