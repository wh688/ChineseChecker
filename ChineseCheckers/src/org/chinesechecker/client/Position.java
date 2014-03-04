package org.chinesechecker.client;

public class Position {

    public int row;
    public int col;
    
    final static private int[][] pos = {
    	{5,5}, 	//1
    	{5,6}, 	//2
    	{5,7}, 	//3
    	{5,8}, 	//4
    	{1,13}, 	//5
    	{2,13}, 	//6
    	{3,13}, 	//7
    	{4,13}, 	//8
    	{5,13}, 	//9
    	{5,14}, 	//10
    	{5,15}, 	//11
    	{5,16}, 	//12
    	{5,17}, 	//13
    	{10,13}, //14
    	{11,13}, //15
    	{12,13}, //16
    	{13,13}, //17
    };
    
    public static boolean IsLegalPosition(int row, int col) {
    	if ((row < 1) || (row > 17)) {
    		return false;
		}
    	if ((col < pos[row - 1][0]) || (col > pos[row - 1][1])) {
    		return false;
		}
		return true;		
	}
    
	public Position(Position p) {
		row = p.row;
		col = p.col;
	}

    public Position(int row, int col) throws IllegalMove {
    	if (!IsLegalPosition(row, col)) {
			throw new IllegalMove ("Position(" + row + "," + col + ") is not Legal!");
		}
    	this.row = row;
    	this.col = col;
    }
    
    public int getrow() {
		return row;
	}
	
	public int getcol() {
		return col;
	}
	
	public void Set(int row, int col) throws IllegalMove {
		if (!IsLegalPosition(row, col)) {
			throw new IllegalMove("Position(" + row + "," + col + ") is not Leagal");
		}
		this.row = row;
		this.col = col;
	}
	
	public Position getJoint(Direction direction) {
		int row = 0;
		int col = 0;
		if (direction == Direction.UpLeft) {
			row = this.row;
			col = this.col + 1;
		} else if (direction == Direction.UpRight) {
			row = this.row + 1;
			col = this.col + 1;
		} else if (direction == Direction.Left) {
			row = this.row - 1;
			col = this.col;
		} else if (direction == Direction.Right) {
			row = this.row + 1;
			col = this.col;
		} else if (direction == Direction.DownLeft) {
			row = this.row - 1;
			col = this.col - 1;
		} else if (direction == Direction.DownRight) {
			row = this.row;
			col = this.col - 1;
		}		
		if (IsLegalPosition(row, col)) {
			return new Position(row, col);
		} else {
			return null;	
		}				
	}
	
	public int getDistance(Position position) {
		int D1 = Math.abs(this.row - position.row) + Math.abs(this.col - position.col);
		int D2 = Math.abs(this.row - position.row) + Math.abs(this.col - (this.row - position.row) - position.col);
		int D3 = Math.abs(this.col - position.col) + Math.abs(this.row - (this.col - position.col) - position.row);
		
		return Math.min(Math.min(D1, D2), D3);		
	}	
	
    @Override
    public String toString() {
    	return "Position[" + row + "," + col + "]";
    }

    @Override
    public boolean equals(Object obj) {
    	if (this == obj) {
    		return true;
    	}
    	if (obj == null) {
    		return false;
    	}
    	if (!(obj instanceof Position)) {
    		return false;
    	}
    	Position other = (Position) obj;
    	return row == other.row && col == other.col;
    }

}