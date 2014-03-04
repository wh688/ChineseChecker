package org.chinesechecker.client;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.chinesechecker.client.ChessBoard;
import org.chinesechecker.client.Color;
import org.chinesechecker.client.GameApi.Operation;
import org.chinesechecker.client.GameApi.Set;
import org.chinesechecker.client.GameApi.SetTurn;
import org.chinesechecker.client.GameApi.SetVisibility;
import org.chinesechecker.client.GameApi.Shuffle;
import org.chinesechecker.client.GameApi.VerifyMove;
import org.chinesechecker.client.GameApi.VerifyMoveDone;
import org.chinesechecker.client.GameApi.EndGame;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public abstract class PlayerLogic {
	
	private static final String TURN = "0"; 
	private static final String CHESSBOARD = "chessBoard";
	private static final String MAKEMOVE = "makeMove";
	
	private String name = "anonymity";
	private Color color = null;	
	private BoardArea area = null;
	protected ChessBoard chessboard = null;
	protected Chess[] chesses = null;	
	protected Position[] path = null;
	protected Chess GoChess = null;
	protected PlayerLogic oppsitePlayer = null;
	
	
	public PlayerLogic(ChessBoard chessboard, String name, Color color, BoardArea area) {
		this.chessboard = chessboard;
		this.name = name;
		this.color = color;
		this.area = area;		
		chesses = CreateChesses();
	}
	
	public boolean Winned() {
		Position[] AreaPositions = area.getOppsiteArea().getAreaPositions();
		Chess chess  = null;
		
		for (int i = 0; i < AreaPositions.length; i++) {
			chess = chessboard.getChess(AreaPositions[i]);
			if (chess == null) {
				return false;
			}
			if (chess.getColor() != this.getColor()) {
				return false;
			}
		}
		return true;
	}
	
	public void makeMove(Chess chess, Position position) throws IllegalMove{
		if (chess.getColor() != this.getColor()) {
			throw new IllegalMove("Can not move the opponent's piece!");
		}
		chessboard.makeMove(chess, position);
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getName() {
		return name;
	}
	
	public BoardArea getArea() {
		return area;
	}
	
	public Chess getGoChess() {
		return GoChess;
	}
	
	public Position[] getPath() {
		return path;
	}
	
	public PlayerLogic getOppsite() {
		return oppsitePlayer;
	}
	
	public void setOppsite(PlayerLogic player) {
		oppsitePlayer = player;
	}
	
	private Chess[] CreateChesses() {
		Chess[] chesses = new Chess[10];
		int index = 0;		                            
		for (int i = 0; i < chessboard.getChessCount(); i++) {
			if (chessboard.getChess(i).getColor() == color) {
				chesses[index] = chessboard.getChess(i);
				index++;
				if (index == 10) {
					break;
				}
			}
		}
		return chesses;
	}
	
	public Chess[] getChesses() {
		return chesses;		
	}
	
	protected Position[] CreatePath(Chess GoChess, Position destPosition) {
		CheckerMap map = chessboard.CanGo(GoChess);
		int source = map.indexOfByData(chessboard.getPosition(GoChess));
		int dest = map.indexOfByData(destPosition);
		if (dest == -1) {
			GoChess = null;
			return null;
		}
		Node sourceNode = map.get(source);
		Node destNode = map.get(dest);
		
		Nodes nodes = map.getShotestPath(sourceNode, destNode);
		Position[] positions = new Position[nodes.size()];
		for (int i = 0; i < positions.length; i++) {
			positions[i] = (Position) nodes.get(positions.length - i - 1).data;		
		}		
		return positions;
	}
	
    @Override
    public String toString() {
    	String str = name + " " + color + " " + area;
    	return str;
    }
    
    public VerifyMoveDone verify(VerifyMove verifyMove) {
		try {
			checkMoveIsLegal(verifyMove);
			return new VerifyMoveDone();
		} catch (Exception e) {
			return new VerifyMoveDone(verifyMove.getLastMovePlayerId(), e.getMessage());
		}		
	}
    
    void checkMoveIsLegal(VerifyMove verifyMove) {
    	List<Operation> lastMove = verifyMove.getLastMove();
    	Map<String, Object> lastState = verifyMove.getLastState();
    	List<Operation> expectedOperations = getExpectedOperations(
            lastState, lastMove, verifyMove.getPlayerIds(), verifyMove.getLastMovePlayerId());
        check(expectedOperations.equals(lastMove), expectedOperations, lastMove);
        if (lastState.isEmpty()) {
        	check(verifyMove.getLastMovePlayerId() == verifyMove.getPlayerIds().get(0));
        }
    }
    
    List<Operation> getExpectedOperations(Map<String, Object> lastApiState, List<Operation> lastMove,
    		List<Integer> playerIds, int lastMovePlayerId) {
    	if (lastApiState.isEmpty()) {
    		return getMoveInitial(playerIds);
    	}    	    
    	State lastState = gameApiStateToCheckerState(lastApiState);
    	List<Operation> expectedOperations = Lists.newArrayList();
    	if (lastMove.get(1) instanceof Set) {
    		Set move = (Set) lastMove.get(1);
    		if (move.getKey() == MAKEMOVE) {
    			//Moving a chess
    			expectedOperations = getMakeMoveOperation(lastState);
    		} 
    	}
    	return expectedOperations;
    }
    
    List<Operation> getMakeMoveOperation(State state) {
        String chessboard = state.chessBoard.toString();
        int turn = state.currentPlayIndex; 
        List<Operation> expectedOperations;
        expectedOperations = Lists.newArrayList();
        expectedOperations.add(new Set(TURN, turn));
        expectedOperations.add(new Set(CHESSBOARD, chessboard));
        return expectedOperations; 
    }
    
    List<Operation> getMoveInitial(List<Integer> playerIds) {
        int redPlayerId = playerIds.get(0);
        List<Operation> operations = Lists.newArrayList();
        operations.add(new SetTurn(redPlayerId));
        //Set the chessboard
        PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
		PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
		PlayerInfo [] playerInfo = {player1, player2};
		State initialState = new State(playerInfo);
        operations.add(new Set(CHESSBOARD, initialState.chessBoard));
        return operations;
    }
    
    State gameApiStateToCheckerState(Map<String, Object> gameApiState) {
    	PlayerInfo player1 = new PlayerInfo ("player1", Color.R, BoardArea.Area2);
		PlayerInfo player2 = new PlayerInfo ("player2", Color.B, BoardArea.Area5);
		PlayerInfo [] playerInfo = {player1, player2};
		State state = new State(playerInfo);
    	List<Optional<ChessBoard>> chessboard = Lists.newArrayList();
    	state.chessBoard = (ChessBoard) gameApiState.get(CHESSBOARD);
    	return state;
    }
    	
    
    private void check(boolean val, Object... debugArguments) {
    	if (!val) {
    		throw new RuntimeException("We have a hacker! debugArguments="
    				+ Arrays.toString(debugArguments));
    	}
    }	
}
