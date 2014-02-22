package org.chinesechecker.client;

public class CheckerMap extends Nodes {
	
	public CheckerMap() {

	}
	
	public Node create(Object data, int step) {
		Node node = new Node(this, data, step);
		Add(node);
		return node;		
	}
	
	public Nodes getShotestPath(Node sourceNode, Node destNode) {
		Nodes nodes = new Nodes();
		Node node = destNode;		
		
		while (node != sourceNode) {
			nodes.Add(node);
			int step = node.getStep();
			for (int i = 0; i < node.getSides().size(); i++) {
				if (node.getSides().get(i).getStep() == step - 1) {
					node = node.getSides().get(i);
					break;
				}
			}
		}
		nodes.Add(node);		
		return nodes;
	}
}
