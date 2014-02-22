package org.chinesechecker.client;

import java.util.List;

import org.chinesechecker.client.GameApi.Container;
import org.chinesechecker.client.GameApi.Operation;
import org.chinesechecker.client.GameApi.SetTurn;
import org.chinesechecker.client.GameApi.UpdateUI;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class Presenter {
	
	interface View {		
		void setPresenter(Presenter cheatPresenter);		
		void setViewerState(Chess[][] chessesIndex);		
		void setPlayerState(Chess[][] chessesIndex);
		void setBoard(ChessBoard board);
		void setMessage(String msg);
		void setWhoseTurn(int index);
	}
	
	State currentState;
	ChessBoard chessboard;
	
	final View view;
	
	public Presenter(View view) {
		this.view = view;
		PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
		PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
		PlayerInfo [] playerInfo = {player1, player2};
		currentState = new State(playerInfo);
		view.setPresenter(this);
	}
	
	void updateUI(Position from, Position to){
		try {
			currentState.players[currentState.currentPlayIndex].SelectChess(from);
			currentState.players[currentState.currentPlayIndex].GoChess(to);
			currentState.getNextPlay();
			updateInfo();
			updateChessBoard(currentState.chessBoard);
		} catch (IllegalMove e) {
            view.setMessage(e.getMessage());
		}		
	}
	
	private void updateChessBoard(ChessBoard newChessBoard) {
		view.setBoard(newChessBoard);
		chessboard = newChessBoard;
	}
	
	void setState(State state) {
		currentState = state;
		ChessBoard newChessBoard = currentState.chessBoard;
        updateInfo();
        updateChessBoard(newChessBoard);        
	}
	
	private void updateInfo() {
		view.setMessage("");
		view.setWhoseTurn(currentState.currentPlayIndex);
	}
}
