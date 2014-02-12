package hw1;


import static hw1.Gamer.BLACK;
import static hw1.Gamer.B;
import static hw1.Gamer.WHITE;
import static hw1.Gamer.W;
import static hw1.Gamer.I;
import static hw1.Gamer._;
import static org.junit.Assert.assertEquals;
import hw1.GameOver;
import hw1.GameResult;
import hw1.Gamer;
import hw1.IllegalMove;
import hw1.Move;
import hw1.State;

import org.junit.Test;

public class StateTest {

	private final State initialState = new State();
	
	/**
	 * Test 01, test for initial board state
	 */
	
	@Test 
    public void testInitialStateInitialBoard() {
		Gamer[][] expected = {
			       // 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
				{ I, I, I, I, B, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
		        { I, I, I, I, B, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
		        { I, I, I, I, B, B, B, I, I, I, I, I, I, I, I, I, I }, // 2
		        { I, I, I, I, B, B, B, B, I, I, I, I, I, I, I, I, I }, // 3
		        { _, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 4
		        { I, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 5
		        { I, I, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 6
		        { I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
		        { I, I, I, I, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 8
		        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I }, // 9
		        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, I, I }, // 10
		        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, I }, // 11
		        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, _ }, // 12
		        { I, I, I, I, I, I, I, I, I, W, W, W, W, I, I, I, I }, // 13
		        { I, I, I, I, I, I, I, I, I, I, W, W, W, I, I, I, I }, // 14
		        { I, I, I, I, I, I, I, I, I, I, I, W, W, I, I, I, I }, // 15
		        { I, I, I, I, I, I, I, I, I, I, I, I, W, I, I, I, I }, // 16
		};
		State expectedState = new State(expected, BLACK, null);
		assertEquals(initialState, expectedState);
    }
	
	/**
	 * Test 02, test for initial turn
	 */
	
    @Test 
    public void testInitialStateWhoseTurn() {
    	assertEquals(initialState.whoseTurn(), BLACK);
    }
    
    /**
     * Test 03, test for black first move
     */
    
    @Test
    public void testValidFirstMoveForBlack() {
    	Gamer[][] expected = {
    		       // 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    	        { I, I, I, I, B, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
    	        { I, I, I, I, B, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
    	        { I, I, I, I, _, B, B, I, I, I, I, I, I, I, I, I, I }, // 2
    	        { I, I, I, I, B, B, B, B, I, I, I, I, I, I, I, I, I }, // 3
    	        { _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
    	        { I, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 5
    	        { I, I, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 6
    	        { I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
    	        { I, I, I, I, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 8
    	        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I }, // 9
    	        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, I, I }, // 10
    	        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, I }, // 11
    	        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, _ }, // 12
    	        { I, I, I, I, I, I, I, I, I, W, W, W, W, I, I, I, I }, // 13
    	        { I, I, I, I, I, I, I, I, I, I, W, W, W, I, I, I, I }, // 14
    	        { I, I, I, I, I, I, I, I, I, I, I, W, W, I, I, I, I }, // 15
    	        { I, I, I, I, I, I, I, I, I, I, I, I, W, I, I, I, I }, // 16
		};
    	State newState = initialState.makeMove(new Move());
    	State expectedState = new State(expected, WHITE, null);
    	assertEquals(newState.whoseTurn(), WHITE);
    	assertEquals(newState, expectedState);
    }
 
    /**
     * Test 04, test for white first move
     */
    
    @Test
    public void testValidFirstMoveForWhite() {
    	Gamer[][] before = {
 		       // 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
 	        { I, I, I, I, B, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
 	        { I, I, I, I, B, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
 	        { I, I, I, I, _, B, B, I, I, I, I, I, I, I, I, I, I }, // 2
 	        { I, I, I, I, B, B, B, B, I, I, I, I, I, I, I, I, I }, // 3
 	        { _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
 	        { I, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 5
 	        { I, I, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 6
 	        { I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
 	        { I, I, I, I, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 8
 	        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I }, // 9
 	        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, I, I }, // 10
 	        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, I }, // 11
 	        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, _ }, // 12
 	        { I, I, I, I, I, I, I, I, I, W, W, W, W, I, I, I, I }, // 13
 	        { I, I, I, I, I, I, I, I, I, I, W, W, W, I, I, I, I }, // 14
 	        { I, I, I, I, I, I, I, I, I, I, I, W, W, I, I, I, I }, // 15
 	        { I, I, I, I, I, I, I, I, I, I, I, I, W, I, I, I, I }, // 16
		};    	
    	Gamer[][] expected = {
    		       // 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    	        { I, I, I, I, B, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
    	        { I, I, I, I, B, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
    	        { I, I, I, I, _, B, B, I, I, I, I, I, I, I, I, I, I }, // 2
    	        { I, I, I, I, B, B, B, B, I, I, I, I, I, I, I, I, I }, // 3
    	        { _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
    	        { I, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 5
    	        { I, I, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 6
    	        { I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
    	        { I, I, I, I, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 8
    	        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I }, // 9
    	        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, I, I }, // 10
    	        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, I }, // 11
    	        { I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
    	        { I, I, I, I, I, I, I, I, I, _, W, W, W, I, I, I, I }, // 13
    	        { I, I, I, I, I, I, I, I, I, I, W, W, W, I, I, I, I }, // 14
    	        { I, I, I, I, I, I, I, I, I, I, I, W, W, I, I, I, I }, // 15
    	        { I, I, I, I, I, I, I, I, I, I, I, I, W, I, I, I, I }, // 16
		};
    	State beforeState = new State(before, WHITE, null);
        State afterState = beforeState.makeMove(new Move());        
        State expectedState = new State(expected, BLACK, null);
        assertEquals(afterState, expectedState);
    }
    
    /**
     * Test 05, test for black illegal move, out of bound
     */
  
    @Test (expected = IllegalMove.class)
    public void testIllegalMoveBlackOutOfBound() {
            assertEquals(initialState.whoseTurn(), BLACK);
            initialState.makeMove(new Move());
    }

    /**
     * Test 06, test for white illegal move, out of bound
     */
    
    @Test (expected = IllegalMove.class)
    public void testIllegalMoveWhiteOutOfBound() {
    	Gamer[][] before = {
  		       // 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
  	        { I, I, I, I, B, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
  	        { I, I, I, I, B, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
  	        { I, I, I, I, _, B, B, I, I, I, I, I, I, I, I, I, I }, // 2
  	        { I, I, I, I, B, B, B, B, I, I, I, I, I, I, I, I, I }, // 3
  	        { _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
  	        { I, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 5
  	        { I, I, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 6
  	        { I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
  	        { I, I, I, I, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 8
  	        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I }, // 9
  	        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, I, I }, // 10
  	        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, I }, // 11
  	        { I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, _ }, // 12
  	        { I, I, I, I, I, I, I, I, I, W, W, W, W, I, I, I, I }, // 13
  	        { I, I, I, I, I, I, I, I, I, I, W, W, W, I, I, I, I }, // 14
  	        { I, I, I, I, I, I, I, I, I, I, I, W, W, I, I, I, I }, // 15
  	        { I, I, I, I, I, I, I, I, I, I, I, I, W, I, I, I, I }, // 16
 		};   
    	State beforeState = new State(before, WHITE, null);
    	assertEquals(beforeState.whoseTurn(), WHITE);
    	beforeState.makeMove(new Move());
    }
    
    /**
     * Test 07, test for black illegal move, to invalid space
     */
    
    @Test (expected = IllegalMove.class)
    public void testIllegalMoveBlackToInvalidSpace() {
    	Gamer[][] before = {
   		       // 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
   	        { I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        { I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        { I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        { I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
   	        { _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        { I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
   	        { I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
   	        { I, I, I, _, _, _, _, _, B, _, _, _, _, I, I, I, I }, // 7
   	        { I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
   	        { I, I, I, I, _, _, _, _, _, W, _, _, _, _, I, I, I }, // 9
   	        { I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        { I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        { I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        { I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
   	        { I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        { I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        { I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
  		}; 
    	State beforeState = new State(before, BLACK, null);
    	assertEquals(beforeState.whoseTurn(), BLACK);
    	beforeState.makeMove(new Move());
    }

    /**
     * Test 08, test for white illegal move, to invalid space
     */
    
    @Test (expected = IllegalMove.class)
    public void testIllegalMoveWhiteToInvalidSpace() {
    	Gamer[][] before = {
   		       // 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
   	        { I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        { I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        { I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        { I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
   	        { _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        { I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
   	        { I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
   	        { I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
   	        { I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
   	        { I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
   	        { I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        { I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        { I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        { I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
   	        { I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        { I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        { I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
  		}; 
    	State beforeState = new State(before, WHITE, null);
    	assertEquals(beforeState.whoseTurn(), WHITE);
    	beforeState.makeMove(new Move());
    }
    
    /**
     * Test 09, test for black single move
     */
    
    @Test
    public void testBlackSingleMove() {
    	Gamer[][] before = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
    	};
    	Gamer[][] expected = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
    			{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
    	        { I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
    	        { I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
    	        { _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
    	        { I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
    	        { I, I, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 6
    	        { I, I, I, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 7
    	        { I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
    	        { I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
    	        { I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
    	        { I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
    	        { I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
    	        { I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
    	        { I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
    	        { I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
    	        { I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
   		};
        State beforeState = new State(before, BLACK, null);
        State afterState = beforeState.makeMove(new Move());
        State expectedState = new State(expected, WHITE, null);
        assertEquals(afterState, expectedState);
    }
    
    /**
     * Test 10, test for white single move
     */
    
    @Test
    public void testWhiteSingleMove() {
    	Gamer[][] before = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
    	};
    	Gamer[][] expected = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
    			{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
    	        { I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
    	        { I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
    	        { _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
    	        { I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
    	        { I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
    	        { I, I, I, _, _, _, _, _, W, _, _, _, _, I, I, I, I }, // 7
    	        { I, I, I, I, _, _, _, _, _, W, _, _, _, I, I, I, I }, // 8
    	        { I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
    	        { I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
    	        { I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
    	        { I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
    	        { I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
    	        { I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
    	        { I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
    	        { I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
   		};
        State beforeState = new State(before, WHITE, null);
        State afterState = beforeState.makeMove(new Move());
        State expectedState = new State(expected, BLACK, null);
        assertEquals(afterState, expectedState);
    }
    
    /**
     * Test 11, test for black regular jump
     */
    
    @Test
    public void testBlackSingleRegularJump() {
    	Gamer[][] before = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
    	};
    	Gamer[][] expected = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
    			{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
    	        { I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
    	        { I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
    	        { _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
    	        { I, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 5
    	        { I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
    	        { I, I, I, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 7
    	        { I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
    	        { I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
    	        { I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
    	        { I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
    	        { I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
    	        { I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
    	        { I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
    	        { I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
    	        { I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
   		};
        State beforeState = new State(before, BLACK, null);
        State afterState = beforeState.makeMove(new Move());
        State expectedState = new State(expected, WHITE, null);
        assertEquals(afterState, expectedState);
    }
    
    /**
     * Test 12, test for white regular jump
     */
    
    @Test
    public void testWhiteSingleRegularJump() {
    	Gamer[][] before = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
    	};
    	Gamer[][] expected = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, W, _, _, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, B, _, _, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
   		};
        State beforeState = new State(before, WHITE, null);
        State afterState = beforeState.makeMove(new Move());
        State expectedState = new State(expected, BLACK, null);
        assertEquals(afterState, expectedState);
    }
    
    /**
     * Test 13, test for black remote jump
     */
    
    @Test
    public void testBlackSingleRemoteJump() {
    	Gamer[][] before = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, _, B, B, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
    	};
    	Gamer[][] expected = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, B, _, B, _, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, _, B, B, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, B, B, W, _, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
   		};
        State beforeState = new State(before, BLACK, null);
        State afterState = beforeState.makeMove(new Move());
        State expectedState = new State(expected, WHITE, null);
        assertEquals(afterState, expectedState);
    }
    
    /**
     * Test 14, test for white remote jump
     */
    
    @Test
    public void testWhiteSingleRemoteJump() {
    	Gamer[][] before = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
    	};
    	Gamer[][] expected = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, W, W, W, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, _, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
   		};
        State beforeState = new State(before, WHITE, null);
        State afterState = beforeState.makeMove(new Move());
        State expectedState = new State(expected, BLACK, null);
        assertEquals(afterState, expectedState);
    }
    
    /**
     * Test 15, test for black continuous jump
     */
    
    @Test
    public void testBlackContinuousJump() {
    	Gamer[][] before = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, W, B, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
    	};
    	Gamer[][] expected = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, W, B, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, B, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, _, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
    	};
        State beforeState = new State(before, BLACK, null);
        State afterState = beforeState.makeMove(new Move());
        State expectedState = new State(expected, WHITE, null);
        assertEquals(afterState, expectedState);
    }
    
    /**
     * Test 16, test for white continuous jump
     */
    
    @Test
    public void testWhiteContinuousJump() {
    	Gamer[][] before = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, B, _, _, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, B, _, B, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, _, W, B, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, B, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
    	};
    	Gamer[][] expected = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, B, _, W, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, B, _, B, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, _, W, B, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, B, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
    	};
        State beforeState = new State(before, WHITE, null);
        State afterState = beforeState.makeMove(new Move());
        State expectedState = new State(expected, BLACK, null);
        assertEquals(afterState, expectedState);
    }
    
    /**
     * Test 17, test for black complex jump
     */
    
    @Test
    public void testBlackComplexJump() {
    	Gamer[][] before = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, W, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, _, B, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, W, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, _, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
    	};
    	Gamer[][] expected = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, W, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, _, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, B, _, B, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, _, B, _, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, _, B, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, B, W, _, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, W, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, B, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
    	};
        State beforeState = new State(before, BLACK, null);
        State afterState = beforeState.makeMove(new Move());
        State expectedState = new State(expected, WHITE, null);
        assertEquals(afterState, expectedState);
    }
    
    /**
     * Test 18, test for white complex jump
     */
    
    @Test
    public void testWhiteComplexJump() {
    	Gamer[][] before = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, _, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, B, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, _, _, _, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, _, _, B, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, _, W, B, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, W, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, B, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
    	};
    	Gamer[][] expected = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
   	        	{ I, I, I, I, W, B, I, I, I, I, I, I, I, I, I, I, I }, // 1
   	        	{ I, I, I, I, B, _, B, I, I, I, I, I, I, I, I, I, I }, // 2
   	        	{ I, I, I, I, _, _, _, B, I, I, I, I, I, I, I, I, I }, // 3
   	        	{ _, _, _, _, _, _, B, _, _, _, _, _, _, I, I, I, I }, // 4
   	        	{ I, _, _, _, _, _, B, B, _, _, _, _, _, I, I, I, I }, // 5
   	        	{ I, I, _, _, _, _, _, _, B, _, _, _, _, I, I, I, I }, // 6
   	        	{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
   	        	{ I, I, I, I, _, _, _, _, W, W, _, _, _, I, I, I, I }, // 8
   	        	{ I, I, I, I, _, _, _, _, _, W, B, _, _, _, I, I, I }, // 9
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, I, I }, // 10
   	        	{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, I }, // 11
   	        	{ I, I, I, I, _, _, _, _, _, W, _, _, _, _, _, _, _ }, // 12
   	        	{ I, I, I, I, I, I, I, I, I, W, B, W, _, I, I, I, I }, // 13
   	        	{ I, I, I, I, I, I, I, I, I, I, W, W, _, I, I, I, I }, // 14
   	        	{ I, I, I, I, I, I, I, I, I, I, I, _, _, I, I, I, I }, // 15
   	        	{ I, I, I, I, I, I, I, I, I, I, I, I, _, I, I, I, I }, // 16
    	};
        State beforeState = new State(before, WHITE, null);
        State afterState = beforeState.makeMove(new Move());
        State expectedState = new State(expected, BLACK, null);
        assertEquals(afterState, expectedState);
    }
    
    /**
     * Test 19, test for black wins
     */
    
    @Test 
    public void testBlackWin() {
    	Gamer[][] beforefinalBoard = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
    			{ I, I, I, I, W, W, I, I, I, I, I, I, I, I, I, I, I }, // 1
    			{ I, I, I, I, W, W, W, I, I, I, I, I, I, I, I, I, I }, // 2
    			{ I, I, I, I, W, W, W, W, I, I, I, I, I, I, I, I, I }, // 3
    			{ _, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 4
    			{ I, _, _, _, _, _, _, W, _, _, _, _, _, I, I, I, I }, // 5
    			{ I, I, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 6
    			{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 8
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I }, // 9
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, I, I }, // 10
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, I }, // 11
    			{ I, I, I, I, _, _, _, _, _, B, _, _, _, _, _, _, _ }, // 12
    			{ I, I, I, I, I, I, I, I, I, _, B, B, B, I, I, I, I }, // 13
    			{ I, I, I, I, I, I, I, I, I, I, B, B, B, I, I, I, I }, // 14
    			{ I, I, I, I, I, I, I, I, I, I, I, B, B, I, I, I, I }, // 15
    			{ I, I, I, I, I, I, I, I, I, I, I, I, B, I, I, I, I }, // 16
    	};       
    	Gamer[][] finalBoard = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, _, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
    			{ I, I, I, I, W, W, I, I, I, I, I, I, I, I, I, I, I }, // 1
    			{ I, I, I, I, W, W, W, I, I, I, I, I, I, I, I, I, I }, // 2
    			{ I, I, I, I, W, W, W, W, I, I, I, I, I, I, I, I, I }, // 3
    			{ _, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 4
    			{ I, _, _, _, _, _, _, W, _, _, _, _, _, I, I, I, I }, // 5
    			{ I, I, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 6
    			{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 8
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I }, // 9
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, I, I }, // 10
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, I }, // 11
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, _ }, // 12
    			{ I, I, I, I, I, I, I, I, I, B, B, B, B, I, I, I, I }, // 13
    			{ I, I, I, I, I, I, I, I, I, I, B, B, B, I, I, I, I }, // 14
    			{ I, I, I, I, I, I, I, I, I, I, I, B, B, I, I, I, I }, // 15
    			{ I, I, I, I, I, I, I, I, I, I, I, I, B, I, I, I, I }, // 16
    	}; 
    	State beforeState = new State(beforefinalBoard, BLACK, null);
    	State finalState = beforeState.makeMove(new Move());
    	State expectedState = new State(finalBoard, WHITE, new GameOver(GameResult.BLACK_WIN, 10, 9));
    	assertEquals(finalState, expectedState);
    }
    
    /**
     * Test 20, test for white wins
     */
    
    @Test 
    public void testWhiteWin() {
    	Gamer[][] beforefinalBoard = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, W, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
    			{ I, I, I, I, W, W, I, I, I, I, I, I, I, I, I, I, I }, // 1
    			{ I, I, I, I, W, W, W, I, I, I, I, I, I, I, I, I, I }, // 2
    			{ I, I, I, I, W, W, _, W, I, I, I, I, I, I, I, I, I }, // 3
    			{ _, _, _, _, _, _, _, W, _, _, _, _, _, I, I, I, I }, // 4
    			{ I, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 5
    			{ I, I, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 6
    			{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 8
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I }, // 9
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, I, I }, // 10
    			{ I, I, I, I, _, _, _, _, _, B, B, _, _, _, _, _, I }, // 11
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, _ }, // 12
    			{ I, I, I, I, I, I, I, I, I, _, _, B, B, I, I, I, I }, // 13
    			{ I, I, I, I, I, I, I, I, I, I, B, B, B, I, I, I, I }, // 14
    			{ I, I, I, I, I, I, I, I, I, I, I, B, B, I, I, I, I }, // 15
    			{ I, I, I, I, I, I, I, I, I, I, I, I, B, I, I, I, I }, // 16
    	};       
    	Gamer[][] finalBoard = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, W, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
    			{ I, I, I, I, W, W, I, I, I, I, I, I, I, I, I, I, I }, // 1
    			{ I, I, I, I, W, W, W, I, I, I, I, I, I, I, I, I, I }, // 2
    			{ I, I, I, I, W, W, W, W, I, I, I, I, I, I, I, I, I }, // 3
    			{ _, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 4
    			{ I, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 5
    			{ I, I, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 6
    			{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 8
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I }, // 9
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, I, I }, // 10
    			{ I, I, I, I, _, _, _, _, _, B, B, _, _, _, _, _, I }, // 11
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, _ }, // 12
    			{ I, I, I, I, I, I, I, I, I, _, _, B, B, I, I, I, I }, // 13
    			{ I, I, I, I, I, I, I, I, I, I, B, B, B, I, I, I, I }, // 14
    			{ I, I, I, I, I, I, I, I, I, I, I, B, B, I, I, I, I }, // 15
    			{ I, I, I, I, I, I, I, I, I, I, I, I, B, I, I, I, I }, // 16
    	}; 
    	State beforeState = new State(beforefinalBoard, WHITE, null);
    	State finalState = beforeState.makeMove(new Move());
    	State expectedState = new State(finalBoard, BLACK, new GameOver(GameResult.WHITE_WIN, 8, 10));
    	assertEquals(finalState, expectedState);
    }
    
    /**
     * Test 21, test for serialization
     */
    
    @Test
    public void testSerialization() {
    	Gamer[][] board = {
    			// 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
    			{ I, I, I, I, W, I, I, I, I, I, I, I, I, I, I, I, I }, // 0
    			{ I, I, I, I, W, W, I, I, I, I, I, I, I, I, I, I, I }, // 1
    			{ I, I, I, I, W, W, W, I, I, I, I, I, I, I, I, I, I }, // 2
    			{ I, I, I, I, W, W, _, W, I, I, I, I, I, I, I, I, I }, // 3
    			{ _, _, _, _, _, _, W, _, _, _, _, _, _, I, I, I, I }, // 4
    			{ I, _, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 5
    			{ I, I, _, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 6
    			{ I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 7
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, I, I, I, I }, // 8
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, I, I, I }, // 9
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, I, I }, // 10
    			{ I, I, I, I, _, _, _, _, _, B, B, _, _, _, _, _, I }, // 11
    			{ I, I, I, I, _, _, _, _, _, _, _, _, _, _, _, _, _ }, // 12
    			{ I, I, I, I, I, I, I, I, I, _, _, B, B, I, I, I, I }, // 13
    			{ I, I, I, I, I, I, I, I, I, I, B, B, B, I, I, I, I }, // 14
    			{ I, I, I, I, I, I, I, I, I, I, I, B, B, I, I, I, I }, // 15
    			{ I, I, I, I, I, I, I, I, I, I, I, I, B, I, I, I, I }, // 16
    	};
    	State state1 = new State(board, BLACK, null);
    	String str = State.serialize(state1);
    	State state2 = State.deserialize(str);
    	assertEquals(state1, state2);
    }
}
