package org.chinesechecker.client;

public class Node {
	public Object data = null;
	private CheckerMap parent = null;
	private Nodes Sides = new Nodes();
	private int step = 0;
	
	public Node(CheckerMap map) {
		this.parent = map;
	}
	
	public Node(CheckerMap map, Object data, int step) {
		this.parent = map;
		this.data = data;
		this.step = step; 
	}
	
	public int getStep() {
		return step;
	}
	
	public CheckerMap getParent() {
		return parent;
	}
	
	public void JointTo(Node node) {
		Sides.Add(node);		
	}
	
	public void JointWith(Node node) {
		this.JointTo(node);
		node.JointTo(this);
	}
	
	public void CutFrom(Node node) {
		Sides.remove(node);
	}
	
	public void CutEachOther(Node node) {
		this.CutFrom(node);
		node.CutFrom(this);
	}
	
	public void CutFromMap() {
		while (Sides.size() > 0){
			CutEachOther(Sides.get(0));
		}
	}
	
	public Nodes getSides() {
		return Sides;
	}	
}
