package chinesechecker.client;
import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import static com.google.common.base.Preconditions.checkNotNull;

public class State {
	
	public Man[] players = null;
	public int currentPlayIndex = 0;
	public ChessBoard chessBoard = null;
	public PlayerInfo[] playerIns = null;
	
	public State(PlayerInfo[] playerInfos) {
		playerIns = playerInfos;
		chessBoard = new ChessBoard(playerInfos);
		players = new Man[playerInfos.length];				

		for (int i = 0; i < playerInfos.length; i++) {
			players[i] = new Man(chessBoard, playerInfos[i].name, playerInfos[i].color, playerInfos[i].Area);
		}		
		currentPlayIndex = 0;
	}
	
	public State(int currentPlayIndex, Man[] players, ChessBoard chessboard) {
		super();
		this.currentPlayIndex = checkNotNull(currentPlayIndex);
		this.players = checkNotNull(players);
		this.chessBoard = checkNotNull(chessBoard);
	}
	
	public PlayerInfo[] getPlayerInfor() {
		return playerIns;
	}
	
	public Player getCurentPlay() {
		return players[currentPlayIndex];
	}
	
	public Player getNextPlay() {
		currentPlayIndex += 1;
		currentPlayIndex %= players.length;
		return getCurentPlay();
	}
	
	public Player getPlayByIndex(int index) {
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
