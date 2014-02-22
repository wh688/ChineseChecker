package org.chinesechecker.client;

/*public class Color {
	final static public Color Brown = new Color("Brown");
	final static public Color Yellow = new Color("Yellow");
	final static public Color R = new Color("R"); //Red
	final static public Color B = new Color("B"); //Blue
	final static public Color Purple = new Color("Purple");
	final static public Color Green = new Color("Green");
  
	public String ColorStr = null;
  
	private Color(String str) {
		ColorStr = str;
	}
	
	public static Color getColor(int Index) {
		switch (Index) {
		case 1:
			return Brown;
		case 2:
			return Yellow;
		case 3:
			return R;
		case 4:
			return B;
		case 5:
			return Purple;	
		default:
			return Green;
		}
	}
	@Override
	public String toString() {
		return ColorStr;  
	}
}*/

public enum Color {
	R, B;
	
	public boolean isRed() {
		return this == R;
	}

	public boolean isBlue() {
		return this == B;
	}
	
	public Color getOppositeColor() {
		return this == R ? B : R;
	}
}

