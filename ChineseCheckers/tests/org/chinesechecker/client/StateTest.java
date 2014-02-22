package org.chinesechecker.client;

import static org.junit.Assert.assertEquals;

import org.chinesechecker.client.BoardArea;
import org.chinesechecker.client.Color;
import org.chinesechecker.client.IllegalMove;
import org.chinesechecker.client.PlayerInfo;
import org.chinesechecker.client.Position;
import org.chinesechecker.client.State;
import org.junit.Test;

public class StateTest {
	/**
	 * test 01: test for the initial state
	 */
	@Test 
	public void testInitialState() {
		String expectedstr = "0\n" + 
				"EEEE0REEEEEEEEEEEE\n" + 
				"EEEE1R2REEEEEEEEEEE\n" +
				"EEEE3R4R5REEEEEEEEEE\n" +
				"EEEE6R7R8R9REEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEE19B18B17B16BEEEE\n" +
				"EEEEEEEEEE15B14B13BEEEE\n" +
				"EEEEEEEEEEE12B11BEEEE\n" +
				"EEEEEEEEEEEE10BEEEE\n" +
				"player1 R Area2\n" +
				"player2 B Area5\n";
		PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
		PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
		
		PlayerInfo [] playerInfo = {player1, player2};
		State state = new State(playerInfo);
		assertEquals(state.toString(), expectedstr);
	}
	
	/**
	 * test 02: test for the initial turn
	 */
    @Test 
    public void testInitialStateWhoseTurn() {
    	PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
		PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
		PlayerInfo [] playerInfo = {player1, player2};
		State state = new State(playerInfo);
    	assertEquals(state.currentPlayIndex, 0);
    }
    
	/**
	 * test 03: test for the initial Red move (valid)
	 */
    @Test 
    public void testInitialValidRedMove() {
    	String expectedstr = "1\n" + 
				"EEEE0REEEEEEEEEEEE\n" + 
				"EEEE1R2REEEEEEEEEEE\n" +
				"EEEE3R4R5REEEEEEEEEE\n" +
				"EEEEE7R8R9REEEEEEEEE\n" +
				"EEEE6REEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEE19B18B17B16BEEEE\n" +
				"EEEEEEEEEE15B14B13BEEEE\n" +
				"EEEEEEEEEEE12B11BEEEE\n" +
				"EEEEEEEEEEEE10BEEEE\n" +
				"player1 R Area2\n" +
				"player2 B Area5\n";
    	PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
		PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
		PlayerInfo [] playerInfo = {player1, player2};
		State state = new State(playerInfo);
		state.players[state.currentPlayIndex].SelectChess(new Position(4,5));
		state.players[state.currentPlayIndex].GoChess(new Position(5,5));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 1);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 04: test for the initial Blue move (valid)
	 */
    @Test 
    public void testInitialValidBlueMove() {
    	String expectedstr = "0\n" + 
				"EEEE0REEEEEEEEEEEE\n" + 
				"EEEE1R2REEEEEEEEEEE\n" +
				"EEEE3R4R5REEEEEEEEEE\n" +
				"EEEEE7R8R9REEEEEEEEE\n" +
				"EEEE6REEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEEEEEEEEEE\n" +
				"EEEEEEEEE19BEEEEEEE\n" +
				"EEEEEEEEEE18B17B16BEEEE\n" +
				"EEEEEEEEEE15B14B13BEEEE\n" +
				"EEEEEEEEEEE12B11BEEEE\n" +
				"EEEEEEEEEEEE10BEEEE\n" +
				"player1 R Area2\n" +
				"player2 B Area5\n";
    	PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
		PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
		PlayerInfo [] playerInfo = {player1, player2};
		State state = new State(playerInfo);
		state.players[state.currentPlayIndex].SelectChess(new Position(4,5));
		state.players[state.currentPlayIndex].GoChess(new Position(5,5));
		state.getNextPlay();
		state.players[state.currentPlayIndex].SelectChess(new Position(14,10));
		state.players[state.currentPlayIndex].GoChess(new Position(13,10));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 0);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 05: test for the illegal move for Red (out of bound)
	 */
    @Test (expected = IllegalMove.class)
    public void testIllegalMoveRedOutOfBound() {
    	PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
		PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
		PlayerInfo [] playerInfo = {player1, player2};
		State state = new State(playerInfo);
		assertEquals(state.currentPlayIndex, 0);
		state.players[state.currentPlayIndex].SelectChess(new Position(4,5));
		state.players[state.currentPlayIndex].GoChess(new Position(4,4));
		state.getNextPlay();
    }
    
	/**
	 * test 06: test for the illegal move for Blue (out of bound)
	 */
    @Test (expected = IllegalMove.class)
    public void testIllegalMoveBlueOutOfBound() {
    	PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
		PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
		PlayerInfo [] playerInfo = {player1, player2};
		State state = new State(playerInfo);
		state.players[state.currentPlayIndex].SelectChess(new Position(4,5));
		state.players[state.currentPlayIndex].GoChess(new Position(5,5));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 1);
		state.players[state.currentPlayIndex].SelectChess(new Position(14,10));
		state.players[state.currentPlayIndex].GoChess(new Position(14,9));
		state.getNextPlay();
    }
    
	/**
	 * test 07: test for the illegal move for Red (can not go)
	 */
    @Test (expected = IllegalMove.class)
    public void testIllegalMoveRedToCannotGo() {
    	PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
		PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
		PlayerInfo [] playerInfo = {player1, player2};
		State state = new State(playerInfo);
		assertEquals(state.currentPlayIndex, 0);
		state.players[state.currentPlayIndex].SelectChess(new Position(4,5));
		state.players[state.currentPlayIndex].GoChess(new Position(10,5));
		state.getNextPlay();
    }
    
	/**
	 * test 08: test for the illegal move for Blue (can not go)
	 */
    @Test (expected = IllegalMove.class)
    public void testIllegalMoveBlueToCannotGo() {
    	PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
		PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
		PlayerInfo [] playerInfo = {player1, player2};
		State state = new State(playerInfo);
		state.players[state.currentPlayIndex].SelectChess(new Position(4,5));
		state.players[state.currentPlayIndex].GoChess(new Position(5,5));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 1);
		state.players[state.currentPlayIndex].SelectChess(new Position(14,10));
		state.players[state.currentPlayIndex].GoChess(new Position(11,10));
		state.getNextPlay();
    }
    
	/**
	 * test 09: test for the illegal move (can not move opponent's chess)
	 */
    @Test (expected = IllegalMove.class)
    public void testMoveOpponentChess() {
    	PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
		PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
		PlayerInfo [] playerInfo = {player1, player2};
		State state = new State(playerInfo);
		assertEquals(state.currentPlayIndex, 0);
		state.players[state.currentPlayIndex].SelectChess(new Position(14,10));
		state.players[state.currentPlayIndex].GoChess(new Position(13,10));
		state.getNextPlay();
    }
    
	/**
	 * test 10: test for the single move by Red
	 */    
    @Test
    public void testRedSingleMove() {
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
    	String expectedstr = "1\n" + 
    			"EEEE19BEEEEEEEEEEEE\n"+
    			"EEEE18BEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEE17BE16BEEEEEEEEE\n" + 
    			"EEEEEEEE0REEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEE1RE2REEEEEEEE\n" + 
    			"EEEEEE15B3REEEEEEEEE\n" + 
    			"EEEEEEE14BEE13BEEEEEE\n" + 
    			"EEEEEEE4RE12B5REEEEEE\n" + 
    			"EEEEEEEEEEE6REEEEE\n" + 
    			"EEEEEEEEE11BEEEEEEE\n" + 
    			"EEEEEEEEEE7R10B8REEEE\n" + 
    			"EEEEEEEEE9REEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";	
    	state.players[state.currentPlayIndex].SelectChess(new Position(13,10));
		state.players[state.currentPlayIndex].GoChess(new Position(14,10));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 1);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 11: test for the single move by Blue
	 */    
    @Test
    public void testBlueSingleMove() {
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
    	state.currentPlayIndex = 1;
    	String expectedstr = "0\n" + 
    			"EEEE19BEEEEEEEEEEEE\n"+
    			"EEEE18BEEEEEEEEEEEE\n" + 
    			"EEEE17BEEEEEEEEEEEE\n" + 
    			"EEEEEEE16BEEEEEEEEE\n" + 
    			"EEEEEEEE0REEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEE1RE2REEEEEEEE\n" + 
    			"EEEEEE15B3REEEEEEEEE\n" + 
    			"EEEEEEE14BEE13BEEEEEE\n" + 
    			"EEEEEEE4RE12B5REEEEEE\n" + 
    			"EEEEEEEEEEE6REEEEE\n" + 
    			"EEEEEEEEE11BEEEEEEE\n" + 
    			"EEEEEEEEE9R7R10B8REEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";	
    	state.players[state.currentPlayIndex].SelectChess(new Position(4,6));
		state.players[state.currentPlayIndex].GoChess(new Position(3,5));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 0);
		assertEquals(state.toString(), expectedstr);
    } 
    
	/**
	 * test 12: test for the single jump by Red over Red
	 */    
    @Test
    public void testRedSingleJumpOverRed() {
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
    	String expectedstr = "1\n" + 
    			"EEEE19BEEEEEEEEEEEE\n"+
    			"EEEE18BEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEE17BE16BEEEEEEEEE\n" + 
    			"EEEEEEEE0REEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEE2REEEEEEEE\n" + 
    			"EEEEEE15B3REEEEEEEEE\n" + 
    			"EEEEEEE14B1RE13BEEEEEE\n" + 
    			"EEEEEEE4RE12B5REEEEEE\n" + 
    			"EEEEEEEEEEE6REEEEE\n" + 
    			"EEEEEEEEE11BEEEEEEE\n" + 
    			"EEEEEEEEE9R7R10B8REEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";	
    	state.players[state.currentPlayIndex].SelectChess(new Position(7,7));
		state.players[state.currentPlayIndex].GoChess(new Position(9,9));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 1);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 13: test for the single jump by Red over Blue
	 */
    @Test
    public void testRedSingleJumpOverBlue() {
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
    	String expectedstr = "1\n" + 
    			"EEEE19BEEEEEEEEEEEE\n"+
    			"EEEE18BEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEE17BE16BEEEEEEEEE\n" + 
    			"EEEEEEEE0REEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEE2REEEEEEEE\n" + 
    			"EEEEEE15B3REEEEEEEEE\n" + 
    			"EEEEEE1R14BEE13BEEEEEE\n" + 
    			"EEEEEEE4RE12B5REEEEEE\n" + 
    			"EEEEEEEEEEE6REEEEE\n" + 
    			"EEEEEEEEE11BEEEEEEE\n" + 
    			"EEEEEEEEE9R7R10B8REEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";	
    	state.players[state.currentPlayIndex].SelectChess(new Position(7,7));
		state.players[state.currentPlayIndex].GoChess(new Position(9,7));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 1);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 14: test for the single jump by Blue over Blue
	 */
    @Test
    public void testBlueSingleJumpOverBlue() {
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
    	state.currentPlayIndex = 1;
    	String expectedstr = "0\n" + 
    			"EEEE19BEEEEEEEEEEEE\n"+
    			"EEEE18BEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEE17BE16BEEEEEEEEE\n" + 
    			"EEEEEEEE0REEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEE14B1RE2REEEEEEEE\n" + 
    			"EEEEEE15B3REEEEEEEEE\n" + 
    			"EEEEEEEEEE13BEEEEEE\n" + 
    			"EEEEEEE4RE12B5REEEEEE\n" + 
    			"EEEEEEEEEEE6REEEEE\n" + 
    			"EEEEEEEEE11BEEEEEEE\n" + 
    			"EEEEEEEEE9R7R10B8REEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";	
    	state.players[state.currentPlayIndex].SelectChess(new Position(9,8));
		state.players[state.currentPlayIndex].GoChess(new Position(7,6));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 0);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 15: test for the single jump by Blue over Red
	 */
    @Test
    public void testBlueSingleJumpOverRed() {
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
    	state.currentPlayIndex = 1;
    	String expectedstr = "0\n" + 
    			"EEEE19BEEEEEEEEEEEE\n"+
    			"EEEE18BEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEE17BE16BEEEEEEEEE\n" + 
    			"EEEEEEEE0REEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEE1R14B2REEEEEEEE\n" + 
    			"EEEEEE15B3REEEEEEEEE\n" + 
    			"EEEEEEEEEE13BEEEEEE\n" + 
    			"EEEEEEE4RE12B5REEEEEE\n" + 
    			"EEEEEEEEEEE6REEEEE\n" + 
    			"EEEEEEEEE11BEEEEEEE\n" + 
    			"EEEEEEEEE9R7R10B8REEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";	
    	state.players[state.currentPlayIndex].SelectChess(new Position(9,8));
		state.players[state.currentPlayIndex].GoChess(new Position(7,8));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 0);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 16: test for the remote jump by Red over Red
	 */
    @Test
    public void testRedRemoteJumpOverRed() {
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
    	String expectedstr = "1\n" + 
    			"EEEE19BEEEEEEEEEEEE\n"+
    			"EEEE18BEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEE17BE16BEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEE1RE2REEEEEEEE\n" + 
    			"EEEEEE15B3REEEEEEEEE\n" + 
    			"EEEEEEE14B0RE13BEEEEEE\n" + 
    			"EEEEEEE4RE12B5REEEEEE\n" + 
    			"EEEEEEEEEEE6REEEEE\n" + 
    			"EEEEEEEEE11BEEEEEEE\n" + 
    			"EEEEEEEEE9R7R10B8REEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";	
    	state.players[state.currentPlayIndex].SelectChess(new Position(5,9));
		state.players[state.currentPlayIndex].GoChess(new Position(9,9));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 1);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 17: test for the remote jump by Red over Blue
	 */
    @Test
    public void testRedRemoteJumpOverBlue() {
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
    	String expectedstr = "1\n" + 
    			"EEEE19BEEEEEEEEEEEE\n"+
    			"EEEE18BEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEE17BE16BEEEEEEEEE\n" + 
    			"EEEEEEEE0REEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEE1REEEEEEEEEE\n" + 
    			"EEEEEE15B3REEEEEEEEE\n" + 
    			"EEEEEEE14BEE13BEEEEEE\n" + 
    			"EEEEEEE4RE12B5REEEEEE\n" + 
    			"EEEEEEEEEEE6R2REEEE\n" + 
    			"EEEEEEEEE11BEEEEEEE\n" + 
    			"EEEEEEEEE9R7R10B8REEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";	
    	state.players[state.currentPlayIndex].SelectChess(new Position(7,9));
		state.players[state.currentPlayIndex].GoChess(new Position(11,13));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 1);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 18: test for the remote jump by Blue over Blue
	 */
    @Test
    public void testBlueRemoteJumpOverBlue() {
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
    	state.currentPlayIndex = 1;
    	String expectedstr = "0\n" + 
    			"EEEE19BEEEEEEEEEEEE\n"+
    			"EEEE18BEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEE17BE16BEEEEEEEEE\n" + 
    			"EEEEEEEE0REEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEE1RE2REEEEEEEE\n" + 
    			"EEEEEE15B3RE11BEEEEEEE\n" + 
    			"EEEEEEE14BEE13BEEEEEE\n" + 
    			"EEEEEEE4RE12B5REEEEEE\n" + 
    			"EEEEEEEEEEE6REEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEE9R7R10B8REEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";
    	state.players[state.currentPlayIndex].SelectChess(new Position(12,10));
		state.players[state.currentPlayIndex].GoChess(new Position(8,10));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 0);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 19: test for the remote jump by Blue over Red
	 */
    @Test
    public void testBlueRemoteJumpOverRed() {
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
    	state.currentPlayIndex = 1;
    	String expectedstr = "0\n" + 
    			"EEEE19BEEEEEEEEEEEE\n"+
    			"EEEE18BEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEE17BE16BEEEEEEEEE\n" + 
    			"EEEEEEEE0REEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEE1RE2REEEEEEEE\n" + 
    			"EEEEE11B15B3REEEEEEEEE\n" + 
    			"EEEEEEE14BEE13BEEEEEE\n" + 
    			"EEEEEEE4RE12B5REEEEEE\n" + 
    			"EEEEEEEEEEE6REEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEE9R7R10B8REEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";	
    	state.players[state.currentPlayIndex].SelectChess(new Position(12,10));
		state.players[state.currentPlayIndex].GoChess(new Position(8,6));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 0);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 20: test for the continuous jump by Red
	 */
    @Test
    public void testRedContinousJump() {
        PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
    	PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
    	PlayerInfo [] playerInfo = {player1, player2};
    	State state = new State(playerInfo);	
    	state.chessBoard.makeMove(state.getChessBoard().getChess(0), new Position(5,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(1), new Position(5,9));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(2), new Position(7,7));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(3), new Position(7,9));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(4), new Position(8,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(5), new Position(10,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(6), new Position(11,12));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(7), new Position(12,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(8), new Position(13,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(9), new Position(13,13));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(10), new Position(13,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(11), new Position(13,12));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(12), new Position(10,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(13), new Position(10,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(14), new Position(9,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(15), new Position(9,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(16), new Position(8,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(17), new Position(4,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(18), new Position(4,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(19), new Position(2,5));
    	state.currentPlayIndex = 0;
    	String expectedstr = "1\n" + 
    			"EEEEEEEEEEEEEEEEE\n"+
    			"EEEE19BEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEE17BE18BEEEEEEEEE\n" + 
    			"EEEEE0REE1REEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEE3REEEEEEEE\n" + 
    			"EEEEE16BE4REEEEEEEEE\n" + 
    			"EEEEEEE14BEE15BEEEEEE\n" + 
    			"EEEEEEE12BE13B5REEEEEE\n" + 
    			"EEEEEEEEEE2R6REEEEE\n" + 
    			"EEEEEEEEE7REEEEEEE\n" + 
    			"EEEEEEEEE8R10B11B9REEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";	
    	state.players[state.currentPlayIndex].SelectChess(new Position(7,7));
		state.players[state.currentPlayIndex].GoChess(new Position(11,11));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 1);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 21: test for the continuous jump by Blue
	 */
    @Test
    public void testBlueContinousJump() {
        PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
    	PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
    	PlayerInfo [] playerInfo = {player1, player2};
    	State state = new State(playerInfo);	
    	state.chessBoard.makeMove(state.getChessBoard().getChess(0), new Position(5,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(1), new Position(5,9));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(2), new Position(7,7));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(3), new Position(7,9));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(4), new Position(8,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(5), new Position(10,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(6), new Position(11,12));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(7), new Position(12,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(8), new Position(13,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(9), new Position(13,13));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(10), new Position(13,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(11), new Position(13,12));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(12), new Position(10,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(13), new Position(10,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(14), new Position(9,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(15), new Position(9,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(16), new Position(8,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(17), new Position(4,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(18), new Position(4,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(19), new Position(2,5));
    	state.currentPlayIndex = 1;
    	String expectedstr = "0\n" + 
    			"EEEEEEEEEEEEEEEEE\n"+
    			"EEEE19BEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEE17BE18BEEEEEEEEE\n" + 
    			"EEEEE0REE1REEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEE10BE2RE3REEEEEEEE\n" + 
    			"EEEEE16BE4REEEEEEEEE\n" + 
    			"EEEEEEE14BEE15BEEEEEE\n" + 
    			"EEEEEEE12BE13B5REEEEEE\n" + 
    			"EEEEEEEEEEE6REEEEE\n" + 
    			"EEEEEEEEE7REEEEEEE\n" + 
    			"EEEEEEEEE8RE11B9REEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";	
    	state.players[state.currentPlayIndex].SelectChess(new Position(13,11));
		state.players[state.currentPlayIndex].GoChess(new Position(7,5));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 0);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 22: test for the complex jump by Red
	 */
    @Test
    public void testRedComplexJump() {
        PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
    	PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
    	PlayerInfo [] playerInfo = {player1, player2};
    	State state = new State(playerInfo);	
    	state.chessBoard.makeMove(state.getChessBoard().getChess(0), new Position(5,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(1), new Position(5,9));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(2), new Position(7,7));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(3), new Position(7,9));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(4), new Position(8,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(5), new Position(10,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(6), new Position(11,12));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(7), new Position(12,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(8), new Position(13,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(9), new Position(13,13));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(10), new Position(13,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(11), new Position(13,12));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(12), new Position(10,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(13), new Position(10,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(14), new Position(9,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(15), new Position(9,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(16), new Position(8,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(17), new Position(4,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(18), new Position(4,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(19), new Position(2,5));
    	state.currentPlayIndex = 0;
    	String expectedstr = "1\n" + 
    			"EEEEEEEEEEEEEEEEE\n"+
    			"EEEE19BEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEE17BE18BEEEEEEEEE\n" + 
    			"EEEEE0REE1REEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEE3REEEEEEEE\n" + 
    			"EEEEE16BE4REEEEEEEEE\n" + 
    			"EEEEEEE14BEE15BEEEEEE\n" + 
    			"EEEEEEE12BE13B5REEEEEE\n" + 
    			"EEEEEEEEEEE6REEEEE\n" + 
    			"EEEEEEEEE7REEEEEEE\n" + 
    			"EEEEEEEEE8R10B11B9REEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEE2REEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";	
    	state.players[state.currentPlayIndex].SelectChess(new Position(7,7));
		state.players[state.currentPlayIndex].GoChess(new Position(15,11));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 1);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 23: test for the complex jump by Blue
	 */
    @Test
    public void testBlueComplexJump() {
        PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
    	PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
    	PlayerInfo [] playerInfo = {player1, player2};
    	State state = new State(playerInfo);	
    	state.chessBoard.makeMove(state.getChessBoard().getChess(0), new Position(5,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(1), new Position(5,9));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(2), new Position(7,7));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(3), new Position(7,9));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(4), new Position(8,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(5), new Position(10,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(6), new Position(11,12));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(7), new Position(12,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(8), new Position(13,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(9), new Position(13,13));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(10), new Position(13,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(11), new Position(13,12));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(12), new Position(10,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(13), new Position(10,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(14), new Position(9,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(15), new Position(9,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(16), new Position(8,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(17), new Position(4,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(18), new Position(4,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(19), new Position(2,5));
    	state.currentPlayIndex = 1;
    	String expectedstr = "0\n" + 
    			"EEEEEEEEEEEEEEEEE\n"+
    			"EEEE19BEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEE17BE18BEEEEEEEEE\n" + 
    			"EEEE10B0REE1REEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEE2RE3REEEEEEEE\n" + 
    			"EEEEE16BE4REEEEEEEEE\n" + 
    			"EEEEEEE14BEE15BEEEEEE\n" + 
    			"EEEEEEE12BE13B5REEEEEE\n" + 
    			"EEEEEEEEEEE6REEEEE\n" + 
    			"EEEEEEEEE7REEEEEEE\n" + 
    			"EEEEEEEEE8RE11B9REEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";	
    	state.players[state.currentPlayIndex].SelectChess(new Position(13,11));
		state.players[state.currentPlayIndex].GoChess(new Position(5,5));
		state.getNextPlay();
		assertEquals(state.currentPlayIndex, 0);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 24: test for the Red Wins
	 */
    @Test
    public void testRedWin() {
        PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
    	PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
    	PlayerInfo [] playerInfo = {player1, player2};
    	State state = new State(playerInfo);
    	state.chessBoard.makeMove(state.getChessBoard().getChess(10), new Position(8,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(11), new Position(7,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(12), new Position(7,7));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(13), new Position(6,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(14), new Position(6,7));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(15), new Position(6,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(16), new Position(5,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(17), new Position(5,7));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(18), new Position(5,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(19), new Position(5,5));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(0), new Position(13,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(1), new Position(14,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(2), new Position(14,12));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(3), new Position(14,13));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(4), new Position(15,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(5), new Position(15,12));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(6), new Position(15,13));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(7), new Position(16,12));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(8), new Position(16,13));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(9), new Position(17,13));
    	state.currentPlayIndex = 0;
    	String expectedstr = "0\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEE19B18B17B16BEEEEEEEEE\n" + 
    			"EEEEE15B14B13BEEEEEEEEE\n" + 
    			"EEEEEE12B11BEEEEEEEEE\n" + 
    			"EEEEEEE10BEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEE0R1R2R3REEEE\n" + 
    			"EEEEEEEEEE4R5R6REEEE\n" + 
    			"EEEEEEEEEEE7R8REEEE\n" + 
    			"EEEEEEEEEEEE9REEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";	
    	state.players[state.currentPlayIndex].SelectChess(new Position(13,10));
		state.players[state.currentPlayIndex].GoChess(new Position(14,10));
		assertEquals(state.currentPlayIndex, 0);
		assertEquals(state.players[state.currentPlayIndex].Winned(), true);
		assertEquals(state.toString(), expectedstr);
    }
    
	/**
	 * test 25: test for the Blue Wins
	 */
    @Test
    public void testBlueWin() {
        PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
    	PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
    	PlayerInfo [] playerInfo = {player1, player2};
    	State state = new State(playerInfo);	
    	state.chessBoard.makeMove(state.getChessBoard().getChess(0), new Position(10,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(1), new Position(11,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(2), new Position(11,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(3), new Position(12,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(4), new Position(12,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(5), new Position(12,12));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(6), new Position(13,10));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(7), new Position(13,11));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(8), new Position(13,12));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(9), new Position(13,13));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(10), new Position(5,8));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(11), new Position(4,7));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(12), new Position(4,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(13), new Position(4,5));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(14), new Position(3,7));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(15), new Position(3,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(16), new Position(3,5));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(17), new Position(2,6));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(18), new Position(2,5));
    	state.chessBoard.makeMove(state.getChessBoard().getChess(19), new Position(1,5));
    	state.currentPlayIndex = 1;
    	String expectedstr = "1\n" + 
    			"EEEE19BEEEEEEEEEEEE\n" + 
    			"EEEE18B17BEEEEEEEEEEE\n" + 
    			"EEEE16B15B14BEEEEEEEEEE\n" + 
    			"EEEE13B12B11B10BEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEE0REEEEEEE\n" + 
    			"EEEEEEEEE1R2REEEEEE\n" + 
    			"EEEEEEEEE3R4R5REEEEE\n" + 
    			"EEEEEEEEE6R7R8R9REEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"EEEEEEEEEEEEEEEEE\n" + 
    			"player1 R Area2\n" + 
    			"player2 B Area5\n";	
    	state.players[state.currentPlayIndex].SelectChess(new Position(5,8));
		state.players[state.currentPlayIndex].GoChess(new Position(4,8));
		assertEquals(state.currentPlayIndex, 1);
		assertEquals(state.players[state.currentPlayIndex].Winned(), true);
		assertEquals(state.toString(), expectedstr);
    }
}
