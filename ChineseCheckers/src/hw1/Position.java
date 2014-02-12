package hw1;

public class Position {

    int row;
    int col;

    public Position(int row, int col) {
    	this.row = row;
    	this.col = col;
    }

    public Position west() {
    	return new Position(row, col - 1);
    }	

    public Position east() {
    	return new Position(row, col + 1);
    }

    public Position northwest() {
    	return new Position(row - 1, col - 1);
    }
    
    public Position northeast() {
    	return new Position(row - 1, col);
    }
    
    public Position southwest() {
    	return new Position(row + 1, col);
    }

    public Position southeast() {
    	return new Position(row + 1, col + 1);
    }

    @Override
    public String toString() {
    	return "(" + row + ", " + col + ")";
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