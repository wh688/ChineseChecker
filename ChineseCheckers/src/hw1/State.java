package hw1;

import static hw1.GameResult.BLACK_WIN;
import static hw1.GameResult.WHITE_WIN;
import static hw1.Gamer.BLACK;
import static hw1.Gamer.EMPTY;
import static hw1.Gamer.WHITE;
import static hw1.Gamer.INVALID;
import static hw1.CheckerBoard.COLS;
import static hw1.CheckerBoard.INIT_BOARD;
import static hw1.CheckerBoard.ROWS;
import static hw1.CheckerBoard.inBoard;

import hw1.GameApi.VerifyMove;
import hw1.GameApi.VerifyMoveDone;

import java.io.Serializable;
import java.util.Arrays;

import com.google.common.base.Objects;

public class State implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private final Gamer[][] board;
    private final Gamer whoseTurn;
    private GameOver gameOver;

    public State() {
    	board = INIT_BOARD;
    	whoseTurn = BLACK;
    	gameOver = null;

    }

    public State(Gamer[][] board, Gamer whoseTurn, GameOver gameOver) {
    	this.board = board;
    	this.whoseTurn = whoseTurn;
    	this.gameOver = gameOver;
    }
    
    public boolean checkMoveIsLegal (VerifyMove verifyMove) {
    	// TODO: I will implement this method in HW2
    	return true;
    }
    
    public State makeMove (Move move) {
    	// TODO: I will implement this method in HW2
    	return new State();
    }
    
    public VerifyMoveDone verify(VerifyMove verifyMove) {
        // TODO: I will implement this method in HW2
        return new VerifyMoveDone();
      }

    public Gamer[][] getBoard() {
    	Gamer[][] newBoard = new Gamer[ROWS][COLS];
    	for (int i = 0; i < ROWS; i++) {
    		for (int j = 0; j < COLS; j++) {
    			newBoard[i][j] = board[i][j];
    		}
    	}
    	return newBoard;
    }
    
    public Gamer whoseTurn() {
    	return this.whoseTurn;
    }

    public GameOver getGameOver() {
    	return gameOver;
    }

    @Override
    public boolean equals(Object obj) {
    	if (this == obj) {
    		return true;
    	}
    	if (obj == null || !(obj instanceof State)) {
    		return false;
    	}
    	State other = (State) obj;
    	return Arrays.deepEquals(board, other.board)
    			&& whoseTurn.equals(other.whoseTurn)
    			&& Objects.equal(gameOver, other.gameOver);
    }

    @Override
    public String toString() {
    	Gamer[][] board = getBoard();
    	String str = whoseTurn.toString() + "-";
    	if (gameOver != null) {
    		str += GameOver.serialize(gameOver) + "-";
    	} else {
    		str += "?-";
    	}
    	
    	StringBuilder strb = new StringBuilder(str);
    	for (Gamer[] row : board) {
    		for (Gamer gamer : row) {
    			if (gamer != null) {
    				strb.append(gamer);
    			} else {
    				strb.append("E");
    			}
    		}
    	}
    	return strb.toString();
    }

    public static String serialize(State state) {
    	return (state != null ? state.toString() : null);
    }

    public static State deserialize(String str) {  	
    	if (str == null || !str.matches("[BW]-(\\?|([xo]_[0-9]*_[0-9]*))-[WBEI]{289}")) {
    		return new State();
    	}
    	String[] data = str.split("-");
    	Gamer whoseTurn = (data[0].equals("B")) ? BLACK : WHITE;
    	GameOver gameOver;
    	if (data[1].equals("?")) {
    		gameOver = null;
    	} else {
    		gameOver = GameOver.deserialize(data[1]);
    	}
    	String boardStr = data[2];
    	Gamer[][] board = new Gamer[ROWS][COLS];
    	for (int i = 0; i < boardStr.length(); i++) {
    		char cell = boardStr.charAt(i);
    		if (cell == 'W') {
    			board[i / ROWS][i % COLS] = WHITE;
    		} else if (cell == 'B') {
    			board[i / ROWS][i % COLS] = BLACK;
    		} else if (cell == 'I') {
    			board[i / ROWS][i % COLS] = INVALID;
    		} else {
    			board[i / ROWS][i % COLS] = EMPTY;
    		}
    	}
    	return new State(board, whoseTurn, gameOver);
    }
}

