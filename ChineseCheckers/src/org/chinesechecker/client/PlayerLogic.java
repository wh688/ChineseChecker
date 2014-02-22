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
			if (chess.GetColor() != this.GetColor()) {
				return false;
			}
		}
		return true;
	}
	
	public void makeMove(Chess chess, Position position) throws IllegalMove{
		if (chess.GetColor() != this.GetColor()) {
			throw new IllegalMove("Can not move the opponent's piece!");
		}
		chessboard.makeMove(chess, position);
	}
	
	public Color GetColor() {
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
			if (chessboard.getChess(i).GetColor() == color) {
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
    
   
	
}
