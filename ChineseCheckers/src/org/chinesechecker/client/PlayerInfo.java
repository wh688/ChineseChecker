package org.chinesechecker.client;

public class PlayerInfo {
	
	public String name = null;
	public Color color = null;
	public BoardArea Area = null;
	
	public PlayerInfo(String name, Color color, BoardArea Area) {
		this.name = name;
		this.color = color;
		this.Area = Area;		
	}
	
	@Override
    public String toString() {
		return name + "-" + color + "-" + Area;
    }
}
