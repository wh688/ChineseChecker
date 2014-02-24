package org.chinesechecker.client;

import static org.junit.Assert.*;

import org.chinesechecker.client.Color;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import org.chinesechecker.client.GameApi.Container;
import org.chinesechecker.client.GameApi.Operation;
import org.chinesechecker.client.GameApi.SetTurn;
import org.chinesechecker.client.GameApi.UpdateUI;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/** Tests for {@link Presenter}.
 * Test plan:
 * There are several interesting states:
 * 1) initial state
 * 2) state to test single move, single jump, remote jump
 * 3) state to test continuous move, complex jump
 * 4) about-to-end
 * There are several interesting yourPlayerId:
 * 1) red player
 * 2) blue player
 * For each one of these states and for each yourPlayerId,
 * I will test what methods the presenters calls on the view.
 */

@RunWith(JUnit4.class)
public class PresenterTest {

	Presenter.View view;
	Container container;
	Presenter presenter;
	
	@Before
    public void runBefore() {
		view = Mockito.mock(Presenter.View.class);
		presenter = new Presenter(view, container);
    }
	
	@Test
    public void testValidRedFirstMove() {
		int whoseTurn = presenter.currentState.currentPlayIndex;		
		presenter.updateUI(new Position(4,5), new Position(5,5));
		int nextTurn = presenter.currentState.currentPlayIndex;
		Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn);
    }
	
	@Test
    public void testInvalidRedFirstMoveGoWhereCannotGo() {
		int whoseTurn = presenter.currentState.currentPlayIndex;		
		presenter.updateUI(new Position(4,5), new Position(6,4));
		Mockito.verify(view).setMessage("Can not go there!");
    }
	
	@Test
    public void testInvalidRedFirstMovePickWrongChess() {
		int whoseTurn = presenter.currentState.currentPlayIndex;		
		presenter.updateUI(new Position(14,10), new Position(13,10));
		Mockito.verify(view).setMessage("Can not move the opponent's piece!");
    }
	
	@Test
    public void testValidBlueFirstMove() {
		int whoseTurn = presenter.currentState.currentPlayIndex;
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(5,5));
		presenter.currentState.getNextPlay();
		presenter.updateUI(new Position(14,10), new Position(13,10));
		int nextTurn = presenter.currentState.currentPlayIndex;
		Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn);
    }
	
	@Test
    public void testInvalidBlueFirstMoveGoWhereCannotGo() {
		int whoseTurn = presenter.currentState.currentPlayIndex;
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(5,5));
		presenter.currentState.getNextPlay();
		presenter.updateUI(new Position(14,10), new Position(12,10));
		Mockito.verify(view).setMessage("Can not go there!");
    }
	
	@Test
    public void testInvalidBlueFirstMovePickWrongChess() {
		int whoseTurn = presenter.currentState.currentPlayIndex;
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(5,5));
		presenter.currentState.getNextPlay();
		presenter.updateUI(new Position(5,5), new Position(5,6));
		Mockito.verify(view).setMessage("Can not move the opponent's piece!");
    }
	
	@Test
    public void testRedSingleMove() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(5,9));
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(7,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(7,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(8,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(10,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(10,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(11,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(13,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(13,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(13,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(12,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(10,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(9,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(9,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(8,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(4,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(4,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(2,5));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(1,5));
    	presenter.currentState.currentPlayIndex = 0;    	
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(13,10), new Position(14,10));    	
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn);   	
	}
	
	@Test
    public void testBlueSingleMove() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(5,9));
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(7,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(7,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(8,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(10,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(10,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(11,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(13,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(13,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(13,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(12,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(10,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(9,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(9,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(8,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(4,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(4,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(2,5));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(1,5));
    	presenter.currentState.currentPlayIndex = 1;    	
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(4,6), new Position(3,5));    	
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn);   	
	}	
	
	@Test
    public void testRedSingleJumpOverRed() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(5,9));
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(7,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(7,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(8,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(10,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(10,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(11,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(13,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(13,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(13,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(12,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(10,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(9,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(9,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(8,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(4,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(4,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(2,5));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(1,5));
    	presenter.currentState.currentPlayIndex = 0;    	
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(7,7), new Position(9,9));    	
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn); 
	}
	
	@Test
    public void testRedSingleJumpOverBlue() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(5,9));
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(7,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(7,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(8,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(10,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(10,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(11,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(13,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(13,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(13,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(12,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(10,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(9,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(9,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(8,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(4,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(4,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(2,5));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(1,5));
    	presenter.currentState.currentPlayIndex = 0;    	
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(7,7), new Position(9,7));    	
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn); 
	}
	
	@Test
    public void testBlueSingleJumpOverBlue() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(5,9));
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(7,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(7,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(8,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(10,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(10,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(11,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(13,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(13,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(13,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(12,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(10,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(9,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(9,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(8,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(4,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(4,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(2,5));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(1,5));
    	presenter.currentState.currentPlayIndex = 1;    	
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(9,8), new Position(7,6));    	
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn); 
	}	
	
	@Test
    public void testBlueSingleJumpOverRed() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(5,9));
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(7,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(7,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(8,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(10,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(10,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(11,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(13,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(13,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(13,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(12,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(10,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(9,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(9,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(8,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(4,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(4,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(2,5));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(1,5));
    	presenter.currentState.currentPlayIndex = 1;    	
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(9,8), new Position(7,8));    	
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn); 
	}	
	
	@Test
    public void testRedRemoteJumpOverRed() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(5,9));
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(7,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(7,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(8,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(10,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(10,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(11,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(13,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(13,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(13,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(12,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(10,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(9,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(9,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(8,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(4,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(4,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(2,5));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(1,5));
    	presenter.currentState.currentPlayIndex = 0;    	
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(5,9), new Position(9,9));    	
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn); 
	}
	
	@Test
    public void testRedRemoteJumpOverBlue() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(5,9));
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(7,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(7,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(8,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(10,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(10,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(11,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(13,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(13,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(13,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(12,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(10,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(9,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(9,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(8,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(4,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(4,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(2,5));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(1,5));
    	presenter.currentState.currentPlayIndex = 0;    	
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(7,9), new Position(11,13));    	
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn); 
	}
	
	@Test
    public void testBlueRemoteJumpOverBlue() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(5,9));
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(7,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(7,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(8,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(10,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(10,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(11,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(13,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(13,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(13,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(12,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(10,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(9,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(9,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(8,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(4,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(4,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(2,5));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(1,5));
    	presenter.currentState.currentPlayIndex = 1;    	
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(12,10), new Position(8,10));    	
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn); 
	}	
	
	@Test
    public void testBlueRemoteJumpOverRed() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(5,9));
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(7,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(7,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(8,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(10,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(10,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(11,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(13,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(13,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(13,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(12,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(10,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(9,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(9,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(8,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(4,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(4,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(2,5));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(1,5));
    	presenter.currentState.currentPlayIndex = 1;    	
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(12,10), new Position(8,6));    	
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn); 
	}
	
	@Test
    public void testRedContinousJump() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(5,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(5,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(7,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(7,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(8,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(10,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(11,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(12,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(13,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(13,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(13,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(10,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(10,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(9,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(9,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(8,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(4,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(4,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(2,5));
    	presenter.currentState.currentPlayIndex = 0;	
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(7,7), new Position(11,11));    	
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn); 
	}
	
	@Test
    public void testBlueContinousJump() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(5,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(5,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(7,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(7,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(8,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(10,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(11,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(12,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(13,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(13,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(13,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(10,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(10,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(9,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(9,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(8,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(4,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(4,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(2,5));
    	presenter.currentState.currentPlayIndex = 1;	
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(13,11), new Position(7,5));    	
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn); 
	}
	
	@Test
    public void testRedComplexJump() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(5,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(5,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(7,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(7,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(8,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(10,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(11,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(12,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(13,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(13,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(13,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(10,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(10,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(9,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(9,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(8,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(4,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(4,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(2,5));
    	presenter.currentState.currentPlayIndex = 0;	
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(7,7), new Position(15,11));    	
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn); 
	}
	
	@Test
    public void testBlueComplexJump() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(5,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(5,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(7,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(7,9));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(8,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(10,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(11,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(12,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(13,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(13,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(13,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(10,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(10,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(9,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(9,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(8,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(4,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(4,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(2,5));
    	presenter.currentState.currentPlayIndex = 1;	
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(13,11), new Position(5,5));    	
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn); 
	}
	
	@Test
    public void testRedWin() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(8,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(7,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(7,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(6,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(6,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(6,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(5,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(5,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(5,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(5,5));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(14,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(14,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(14,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(15,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(15,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(15,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(16,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(16,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(17,13));
    	presenter.currentState.currentPlayIndex = 0;
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(13,10), new Position(14,10));
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn);
		Mockito.verify(view).showStatus("R wins!");
	}
	
	@Test
    public void testBlueWin() {
		presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(0), new Position(10,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(1), new Position(11,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(2), new Position(11,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(3), new Position(12,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(4), new Position(12,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(5), new Position(12,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(6), new Position(13,10));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(7), new Position(13,11));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(8), new Position(13,12));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(9), new Position(13,13));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(10), new Position(5,8));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(11), new Position(4,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(12), new Position(4,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(13), new Position(4,5));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(14), new Position(3,7));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(15), new Position(3,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(16), new Position(3,5));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(17), new Position(2,6));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(18), new Position(2,5));
    	presenter.currentState.chessBoard.makeMove(presenter.currentState.chessBoard.getChess(19), new Position(1,5));
    	presenter.currentState.currentPlayIndex = 1;
    	int whoseTurn = presenter.currentState.currentPlayIndex;
    	presenter.updateUI(new Position(5,8), new Position(4,8));
    	int nextTurn = presenter.currentState.currentPlayIndex;
    	Mockito.verify(view).setBoard(presenter.currentState.chessBoard);
		Mockito.verify(view).setMessage("");
		Mockito.verify(view).setWhoseTurn(nextTurn);
		Mockito.verify(view).showStatus("B wins!");
	}
	
	@Test
    public void testSetState() {
		PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
    	PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
    	PlayerInfo [] playerInfo = {player1, player2};
    	State state = new State(playerInfo);	
    	state.chessBoard.makeMove(state.getChessBoard().getChess(0), new Position(5,9));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(1), new Position(7,7));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(2), new Position(7,9));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(3), new Position(8,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(4), new Position(10,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(5), new Position(10,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(6), new Position(11,12));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(7), new Position(13,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(8), new Position(13,13));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(9), new Position(13,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(10), new Position(13,12));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(11), new Position(12,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(12), new Position(10,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(13), new Position(9,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(14), new Position(9,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(15), new Position(8,7));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(16), new Position(4,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(17), new Position(4,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(18), new Position(2,5));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(19), new Position(1,5));
    	state.currentPlayIndex = 0;
    	presenter.setState(state);
    	Mockito.verify(view).setMessage("");
    	Mockito.verify(view).setWhoseTurn(0);
	}
}
