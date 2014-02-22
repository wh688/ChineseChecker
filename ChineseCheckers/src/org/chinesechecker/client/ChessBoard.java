package org.chinesechecker.client;

import java.util.List;
import java.util.Arrays;
import java.util.Vector;

import com.google.common.base.Optional;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

public class ChessBoard implements Serializable {	

	private Chess[][] chessesIndex = new Chess[17][17];
	private Chess[] chesses = null;
	private Position[] chessesPosition = null;
	private static final int CHESSCOUNT = 10;
	
	public ChessBoard(PlayerInfo[] playInfos) {
		chesses = new Chess[CHESSCOUNT * playInfos.length];
		chessesPosition = new Position[CHESSCOUNT * playInfos.length];
		for (int i = 0; i < playInfos.length; i++) {
			Position[] positions = playInfos[i].Area.getAreaPositions();
			for (int j = 0; j < CHESSCOUNT; j++) {
				chesses[i * CHESSCOUNT + j] = new Chess(playInfos[i].color, i * CHESSCOUNT + j);
				chessesPosition[i * CHESSCOUNT + j] = new Position(positions[j]);
			}
		}			
		for (int i = 0; i < chesses.length; i++) {
			Position position =	getPosition(chesses[i]); 
			chessesIndex[position.getrow() - 1][position.getcol() - 1] = chesses[i];
		}		
	}	
	
	public ChessBoard(Chess[][] chessesIndex) {
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				this.chessesIndex[i][j] = chessesIndex[i][j];				
			}			
		}
	}
	
	public Chess[][] getChessesIndex() {
		Chess[][] newChessesIndex = new Chess[17][17];
        for (int i = 0; i < 17; i++) {
                for (int j = 0; j < 17; j++) {
                	newChessesIndex[i][j] = chessesIndex[i][j];
                }
        }
        return newChessesIndex;
	}

	public Chess getChess(Position position) {
		if (position == null){
			return null;
		}
		return chessesIndex[position.getrow() - 1][position.getcol() - 1];		
	}
	
	public Chess getChess(int index) {		
		return chesses[index];		
	}
	

	public int getChessCount() {
		return chesses.length;
	}
	
	public int getPlayerCount() {
		return chesses.length/CHESSCOUNT;
	}
	
	public Position getPosition(Chess chess) {		
		return chessesPosition[chess.getindex()];
	}
	
	public Position getPosition(int index) {		
		return chessesPosition[index];
	}
	
	public Chess[][] makeMove(Chess chess, Position chessPosition) {		
		if (chessPosition == null) {
			return chessesIndex;
		}
		Position OldPosition = getPosition(chess);
		chessesIndex[OldPosition.getrow() - 1][OldPosition.getcol() - 1] = null;
		chessesIndex[chessPosition.getrow() - 1][chessPosition.getcol() - 1] = chess;		
		chessesPosition[chess.getindex()] = new Position(chessPosition);
		return chessesIndex;
	}
	
    private Position CanJumpTo(Chess chess, Position chessPosition, Direction direction) {
		Position re = new Position(chessPosition);
		int Empty = 0;
		do {
			re = re.getJoint(direction);
			if (re == null) {
				return null;			
			}
			if ((this.getChess(re) != null) && (this.getChess(re) != chess)) {
				break;
			} else {
				Empty++;
			}
		} while (true);		
		
		do {
			re = re.getJoint(direction);
			if (re == null) {
				return null;			
			}
			if ((this.getChess(re) != null) && (this.getChess(re) != chess)) {
				return null;
			} else {
				Empty--;
			}			
		} while (Empty != -1);
	
		return re;		
	}
    
    private Position[] CanJumpTo(Chess chess, Position chessPosition) {
    	Vector vector = new Vector();    	
		for (int j = 0; j < Direction.directions.length; j++) {
			Position position = CanJumpTo(chess, chessPosition, Direction.directions[j]);
			if (position == null) {
				continue;				
			}
			vector.add(position);
		}	
		Position[] re = new Position[vector.size()];
		for (int i = 0; i < vector.size(); i++) {
			re[i] = (Position) vector.get(i);
		}
		return re;
	}
    
    private Position[] CanMoveTo(Chess chess, Position chessPosition) {
    	Vector vector = new Vector();
		for (int j = 0; j < Direction.directions.length; j++) {
			Position position = chessPosition.getJoint(Direction.directions[j]);
			if (position == null) {
				continue;				
			}
			if ((this.getChess(position) != null) && (this.getChess(position) != chess)) {
				continue;
			}
			vector.add(position);
		}				
		Position[] re = new Position[vector.size()];
		for (int i = 0; i < vector.size(); i++) {
			re[i] = (Position) vector.get(i);
		}
		return re;				
	}
    
	public CheckerMap CanGo(Chess chess) {
		CheckerMap map = new CheckerMap();
		Position chessPosition = getPosition(chess);
		// initial position
		map.create(chessPosition, 1);		
		int nodeIndex = 0;
		// Map Can JumpTo
		while (nodeIndex < map.size()) {
			Node node = map.get(nodeIndex);
			Position[] positions = CanJumpTo(chess, (Position) node.data);
			for (int i = 0, index = 0; i < positions.length; i++) {
				index = map.indexOfByData(positions[i]);
				Node subnode = null;
				if (index == -1) {
					subnode = map.create(positions[i], node.getStep() + 1);					
				} else {
					subnode = map.get(index);
				}				
				node.JointTo(subnode);
			}
			nodeIndex++;
		}
		// Map Can MoveTo
		nodeIndex = 0;
		Node node = map.get(nodeIndex);
		Position[] positions = CanMoveTo(chess, chessPosition);
		for (int i = 0; i < positions.length; i++) {
			Node subnode = map.create(positions[i], node.getStep() + 1);
			node.JointTo(subnode);
			subnode.JointTo(node);
		}		
		return map;
	}
	
	@Override
	public String toString() {
		Chess[][] chessesIndex = getChessesIndex();		
		String str = "";
		int i = 0;
		for (Chess[] row : chessesIndex) {
			for (Chess c : row) {
				if (c != null) {
                    str += c;
				} else {
					str += "E";
				}
				i++;
				if (i%17 == 0) str += '\n';
			}
		}
		return str;  
	}
}

