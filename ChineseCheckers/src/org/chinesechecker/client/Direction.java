package org.chinesechecker.client;

public class Direction {
	  
	final static public Direction UpLeft = new Direction("UpLeft");
	  final static public Direction UpRight = new Direction("UpRight");
	  final static public Direction Left = new Direction("Left");
	  final static public Direction Right = new Direction("Right");
	  final static public Direction DownLeft = new Direction("DownLeft");
	  final static public Direction DownRight = new Direction("DownRight");
	  final static public Direction[] directions = {UpLeft, UpRight, Left, Right, DownLeft, DownRight};
	  
	  private String DirectorStr = null;
	  
	  private Direction(String str) {
		  DirectorStr = str;
	  }	  
	  
	  public String ToString() {
		  return DirectorStr;  
	  }
}
