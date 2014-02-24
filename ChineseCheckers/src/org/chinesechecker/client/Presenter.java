package org.chinesechecker.client;

import java.util.List;

import org.chinesechecker.client.GameApi.Container;
import org.chinesechecker.client.GameApi.Operation;
import org.chinesechecker.client.GameApi.SetTurn;
import org.chinesechecker.client.GameApi.UpdateUI;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * The presenter that controls the Chinese checkers graphics.
 * We use the MVP pattern:
 * the model is {@link State},
 * the view will have the cheat graphics and it will implement {@link Presenter.View},
 * and the presenter is {@link Presenter}.
 */

public class Presenter {
	
	interface View {		
		void setPresenter(Presenter checkerPresenter);
		void setBoard(ChessBoard board);
		void setMessage(String msg);
		void setWhoseTurn(int index);
		void showStatus(String html);
		void setButton(String str);
		void setGameOver(boolean isGameOver);
	}
	
	State currentState;
	ChessBoard chessboard;
	
	final View view;
	final Container container;
	
	public Presenter(View view, Container container) {
		this.view = view;
		this.container = container;
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
			if (currentState.players[currentState.currentPlayIndex].Winned() == false) {
				currentState.getNextPlay();
			}			
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
		if (currentState.players[currentState.currentPlayIndex].Winned() == true) {
			updateEndGameInfo();
		} else {
			view.showStatus("Still on...");
			view.setGameOver(false);
		}
	}
	
	void updateEndGameInfo() {
		String str = "";        
        if (currentState.players[currentState.currentPlayIndex].Winned() == true) {
        	str += currentState.players[currentState.currentPlayIndex].GetColor().toString();
        	str += " wins!";
        } 
        view.showStatus(str);
        view.setButton("RESTART");
        view.setGameOver(true);
	}
}
