package org.chinesechecker.client;

public class ManLogic extends PlayerLogic {
	
	private Chess Selected = null;
	private Position[] CanGo = null;
	
	public ManLogic(ChessBoard chessboard, String name, Color color, BoardArea area) {
		super(chessboard, name, color, area);
	}
	
	public void SelectChess(Position position) throws IllegalMove {		
		Chess chess = chessboard.getChess(position);
		if (chess == null) {
			return;
		}
		if (chess.GetColor() != this.GetColor()) {
			throw new IllegalMove("Can not move the opponent's piece!");
		}
		
		Selected = chess;
		GoChess = chess;
		CheckerMap map = chessboard.CanGo(chess); 
		CanGo = new Position[map.size() - 1];
		for (int i = 0; i < map.size() - 1; i++) {
			CanGo[i] = (Position) map.get(i + 1).data;
		}
	}
	
	public boolean GoChess(Position position) throws IllegalMove {
		if (!IsCanGo(position)) {
			throw new IllegalMove("Can not go there!");
		}
		path = CreatePath(GoChess, position);
		
		makeMove(Selected, position);		
		Selected = null;
		CanGo = null;
		return true;
	}
	
	private void CancelSelect() {
		CanGo = null;
		Selected = null;
		GoChess = null;
	}
	
	public Position[] getCanGo() {
		return CanGo;
	}
	
	public Chess getSelected() {
		return Selected;
	}
	
	private boolean IsCanGo(Position position) {
		boolean re = false;
		
		if (CanGo != null) {		
			for (int i = 0; i < CanGo.length; i++) {
				if (CanGo[i].equals(position)) {
					re = true;
					break;
				}
			}
		}
		
		return re;		
	}

}
