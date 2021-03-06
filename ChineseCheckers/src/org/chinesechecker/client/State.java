package org.chinesechecker.client;
import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

import static com.google.common.base.Preconditions.checkNotNull;

public class State {
	
	public static final int BOARD_HEIGHT = 17;
	public static final int BOARD_WIDTH = 17;
	public ManLogic[] players = null;
	public int currentPlayIndex = 0;
	public ChessBoard chessBoard = null;
	
	public State(PlayerInfo[] playerInfos) {
		chessBoard = new ChessBoard(playerInfos);
		players = new ManLogic[playerInfos.length];				

		for (int i = 0; i < playerInfos.length; i++) {
			players[i] = new ManLogic(chessBoard, playerInfos[i].name, playerInfos[i].color, playerInfos[i].Area);
		}		
		currentPlayIndex = 0;
	}
	
	public State(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}
	
	public PlayerLogic getCurentPlay() {
		return players[currentPlayIndex];
	}
	
	public PlayerLogic getNextPlay() {
		currentPlayIndex += 1;
		currentPlayIndex %= players.length;
		return getCurentPlay();
	}
	
	public PlayerLogic getPlayByIndex(int index) {
		return players[index];
	}
	
	public int getPlayCount() {
		return players.length;
	}
	
	public ChessBoard getChessBoard() {
		return chessBoard;
	}
	
	@Override
    public boolean equals(Object obj) {
    	if (this == obj) {
    		return true;
    	}
    	if (obj == null || !(obj instanceof ChessBoard)) {
    		return false;
    	}
    	State other = (State) obj;
    	return players.equals(other.players)
    			&& currentPlayIndex == other.currentPlayIndex
    			&& chessBoard.equals(other.chessBoard);
    }
    
    @Override
    public String toString() {
    	ChessBoard chessBoard = getChessBoard();
    	String str = currentPlayIndex + "\n" + getChessBoard();
    	for (int i = 0; i < getPlayCount(); i++){
    		str += getPlayByIndex(i) + "\n";
    	}
    	return str;
    }
}
