package org.chinesechecker.client;

public class Chess {
	private Color color = null;
	private int index = -1;
	
	public Chess (Color color, int index) {
		this.color = color;
		this.index = index;
	}
	
	public int getindex() {
		return this.index;
	}
	
	public Color GetColor() {
		return color;
	}
	
	@Override
	public String toString() {
		String str = index + color.toString();		
		return str;  
	}
}
