package org.chinesechecker.graphics;

import org.chinesechecker.client.BoardArea;
import org.chinesechecker.client.Chess;
import org.chinesechecker.client.ChessBoard;
import org.chinesechecker.client.Color;
import org.chinesechecker.client.ManLogic;
import org.chinesechecker.client.PlayerInfo;
import org.chinesechecker.client.Position;
import org.chinesechecker.client.State;
import org.chinesechecker.client.IllegalMove;
import org.game_api.GameApi.UpdateUI;
import org.game_api.GameApi.Container;
import org.game_api.GameApi.Operation;
import org.game_api.GameApi.SetTurn;
import org.game_api.GameApi.UpdateUI;

import com.google.gwt.core.shared.GWT;

/**
 * The presenter that controls the Chinese checkers graphics.
 * We use the MVP pattern:
 * the model is {@link State},
 * the view will have the cheat graphics and it will implement {@link Presenter.View},
 * and the presenter is {@link Presenter}.
 */

public class Presenter {
	public State currentState;
	public Position selected;	
	public final View view;
	private final Container container;
	public boolean isMyTurn = true;
	public Chess[][] chessboard;
	private static GameConstants constants = GWT.create(GameConstants.class);
	
	public interface View {
		void setHighlight(int row, int col, boolean highlighted);
		void setCell(int row, int col, Chess chess);
		void setWhoseTurn(Color color);
		void setMessage(String msg);
		void showStatus(String html);
		void setButton(String str);
		void setGameOver(boolean isGameOver);
		void animateSetStone(Position p);
		void pieceCapturedSound();
		void pieceDownSound();
		void errorSound();
		void restartSound();
		//void setPresenter(Presenter abalonePresenter);
	}
	
	public Presenter(View view, Container container) {
		this.view = view;
		this.container = container;
		//view.setPresenter(this);
		PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
		PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
		PlayerInfo [] playerInfo = {player1, player2};
		this.currentState = new State(playerInfo);
		chessboard = currentState.chessBoard.getChessesIndex();
	}	
	
	public boolean selectCell(int row, int col) {
	    if (cannotMove()) {
	    	return false;
	    } 
	    view.pieceDownSound();
	    updateUI(selected, new Position(row, col));
	    updateChessBoard(currentState.chessBoard.getChessesIndex());
	    return true;
	}
	
	public void updateUI(Position from, Position to) {
		if (selected == null) {
			if (isEmpty(to.row, to.col)){
				return;
			}
			if (! currentState.chessBoard.getChess(to).getColor().equals(currentState.players[currentState.currentPlayIndex].getColor())) {
		    	view.errorSound();
				view.setMessage(constants.OppError());
				return;
			}
			selectPiece(to);
		} else {			
			unselectPiece(from);
			try {
				currentState.players[currentState.currentPlayIndex].SelectChess(from);
				view.animateSetStone(from);
				
				currentState.players[currentState.currentPlayIndex].GoChess(to);
				view.animateSetStone(to);
				if (currentState.players[currentState.currentPlayIndex].Winned() == false) {
					currentState.getNextPlay();
				}
		        updateInfo();
			} catch (IllegalMove e) {
		    	view.errorSound();
	            view.setMessage(constants.MoveError());
			}
		}			
	}	
	
	public void selectPiece(Position pos) {
		selected = pos;
		((ManLogic) currentState.players[currentState.currentPlayIndex]).SelectChess(pos);
		Position[] CanGo = ((ManLogic) currentState.getCurentPlay()).getCanGo();
		if (CanGo != null) {
			for (int i = 0; i < CanGo.length; i++) {
				view.setHighlight(CanGo[i].row, CanGo[i].col, true);
			}
		}		
	}
	
	public void unselectPiece(Position pos) {
		selected = null;
		((ManLogic) currentState.players[currentState.currentPlayIndex]).SelectChess(pos);
		Position[] CanGo = ((ManLogic) currentState.getCurentPlay()).getCanGo();
		if (CanGo != null) {
			for (int i = 0; i < CanGo.length; i++) {
				view.setHighlight(CanGo[i].row, CanGo[i].col, false);
			}
		}   
	}
	
	public void updateChessBoard(Chess[][] newChessBoard) {
		for (int i = 1; i < 18; i++) {
			for (int j = 1; j < 18; j++) {
				if (newChessBoard[i - 1][j - 1] == null) {
					view.setCell(i, j, currentState.chessBoard.emptyChess);
				}
				else {
					view.setCell(i, j, newChessBoard[i-1][j-1]);
				}
			}	
		}
	}
	
	public void setState(State state) {
		currentState = state;
		ChessBoard newChessBoard = currentState.chessBoard;
        updateChessBoard(newChessBoard.getChessesIndex());        
	}
	
	public boolean isEmpty(int row, int col) {
		return currentState.chessBoard.getChess(new Position(row, col)) == null;
	}
	
	public void setMyTurn(boolean isMyTurn) {
		this.isMyTurn = isMyTurn;
	}
	
	public boolean isMyTurn() {
		return isMyTurn;
	}
	
	public boolean cannotMove() {		
	    return currentState.players[currentState.currentPlayIndex].Winned() || !isMyTurn();
	}
	
	
	
	public void updateInfo() {
		view.setMessage("");
		view.setWhoseTurn(currentState.getCurentPlay().getColor());
		if (currentState.players[currentState.currentPlayIndex].Winned() == true) {
			updateEndGameInfo();
		} else {
			view.showStatus(constants.stillOn());
			view.setGameOver(false);
		}
	}
	
	void updateEndGameInfo() {
		String str = "";        
        if (currentState.players[currentState.currentPlayIndex].Winned() == true) {
        	if (currentState.players[currentState.currentPlayIndex].getColor().toString() == "B") {
        		str += constants.blue();
        	} else {
        		str += constants.red();
        	}
        	str += constants.win();
        } 
        view.showStatus(str);
        view.setButton(constants.restart());
        view.setGameOver(true);
	}
	
	public void restartGame() {
		PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
		PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
		PlayerInfo [] playerInfo = {player1, player2};
		currentState = new State(playerInfo);
		Chess[][] newBoard = currentState.chessBoard.getChessesIndex();
		updateInfo();
		updateChessBoard(newBoard);
		view.restartSound();
	}

	public void updateUI(UpdateUI updateUI) {
		// TODO Auto-generated method stub
		
	}	             
}