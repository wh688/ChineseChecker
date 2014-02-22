package org.chinesechecker.client;

public class BoardArea {
	  final static public BoardArea Area1 = new BoardArea(1);
	  final static public BoardArea Area2 = new BoardArea(2);
	  final static public BoardArea Area3 = new BoardArea(3);
	  final static public BoardArea Area4 = new BoardArea(4);
	  final static public BoardArea Area5 = new BoardArea(5);
	  final static public BoardArea Area6 = new BoardArea(6);	  
	  final static public BoardArea[] Areas = {Area1, Area2, Area3, Area4, Area5, Area6};
	  
	  private int AreaIndex = 0;
	  private Position[] areaPositions = null;
	  private String BoardAreaStr = null;
 
	  private BoardArea(int index) {
		  AreaIndex = index;
		  CreateAreaPositions();
	  }
	  
	  private void CreateAreaPositions() {
		  areaPositions = new Position[10];
		  switch (AreaIndex) {
		  case 1:			  
			  areaPositions[0] = new Position(5,1);
			  areaPositions[1] = new Position(5,2); 
			  areaPositions[2] = new Position(6,2);			  
			  areaPositions[3] = new Position(5,3);
			  areaPositions[4] = new Position(6,3); 
			  areaPositions[5] = new Position(7,3);			  
			  areaPositions[6] = new Position(5,4);
			  areaPositions[7] = new Position(6,4); 
			  areaPositions[8] = new Position(7,4);
			  areaPositions[9] = new Position(8,4);
			  BoardAreaStr = "Area1";
			  break;
		  case 2:
			  areaPositions[0] = new Position(1,5);
			  areaPositions[1] = new Position(2,5); 
			  areaPositions[2] = new Position(2,6);			  
			  areaPositions[3] = new Position(3,5);
			  areaPositions[4] = new Position(3,6); 
			  areaPositions[5] = new Position(3,7);			  
			  areaPositions[6] = new Position(4,5);
			  areaPositions[7] = new Position(4,6); 
			  areaPositions[8] = new Position(4,7);
			  areaPositions[9] = new Position(4,8);
			  BoardAreaStr = "Area2";
			  break;	
		  case 3:
			  areaPositions[0] = new Position(5,13);
			  areaPositions[1] = new Position(5,12); 
			  areaPositions[2] = new Position(6,13);			  
			  areaPositions[3] = new Position(5,11);
			  areaPositions[4] = new Position(6,12); 
			  areaPositions[5] = new Position(7,13);			  
			  areaPositions[6] = new Position(5,10);
			  areaPositions[7] = new Position(6,11); 
			  areaPositions[8] = new Position(7,12);
			  areaPositions[9] = new Position(8,13);
			  BoardAreaStr = "Area3";
			  break;
		  case 4:
			  areaPositions[0] = new Position(13,17);
			  areaPositions[1] = new Position(12,16); 
			  areaPositions[2] = new Position(13,16);			  
			  areaPositions[3] = new Position(11,15);
			  areaPositions[4] = new Position(12,15); 
			  areaPositions[5] = new Position(13,15);			  
			  areaPositions[6] = new Position(10,14);
			  areaPositions[7] = new Position(11,14); 
			  areaPositions[8] = new Position(12,14);
			  areaPositions[9] = new Position(13,14);
			  BoardAreaStr = "Area4";
			  break;
		  case 5:
			  areaPositions[0] = new Position(17,13);
			  areaPositions[1] = new Position(16,13); 
			  areaPositions[2] = new Position(16,12);			  
			  areaPositions[3] = new Position(15,13);
			  areaPositions[4] = new Position(15,12); 
			  areaPositions[5] = new Position(15,11);			  
			  areaPositions[6] = new Position(14,13);
			  areaPositions[7] = new Position(14,12); 
			  areaPositions[8] = new Position(14,11);
			  areaPositions[9] = new Position(14,10);
			  BoardAreaStr = "Area5";
			  break;	
		  case 6:
			  areaPositions[0] = new Position(13,5);
			  areaPositions[1] = new Position(13,6); 
			  areaPositions[2] = new Position(12,5);			  
			  areaPositions[3] = new Position(13,7);
			  areaPositions[4] = new Position(12,6); 
			  areaPositions[5] = new Position(11,5);			  
			  areaPositions[6] = new Position(13,8);
			  areaPositions[7] = new Position(12,7); 
			  areaPositions[8] = new Position(11,6);
			  areaPositions[9] = new Position(10,5);
			  BoardAreaStr = "Area6";
			  break;
		  default:
			  break;
		  }						  
	  }	  
	  public Position[] getAreaPositions() {
		  return areaPositions;
	  }
	  
	  public BoardArea getOppsiteArea(){
		  int OppsiteIndex = (this.AreaIndex + 3);
		  if (OppsiteIndex > 6) {
			  OppsiteIndex %= 6;
		  }
		  return Areas[OppsiteIndex - 1];	  
	  }
	  
	  public int Index() {
		  return AreaIndex;  
	  }

	  final static public  BoardArea[] AllocateArea(int PlayerCount) {
		  BoardArea[] boardArea = new BoardArea[PlayerCount];		  
		  switch (PlayerCount) {
		  case 2:
			  boardArea[0] = BoardArea.Area1;
			  boardArea[1] = BoardArea.Area4;
			  break;			
		  case 3:
			  boardArea[0] = BoardArea.Area1;
			  boardArea[1] = BoardArea.Area3;
			  boardArea[2] = BoardArea.Area5;
			  break;			
		  case 4:
			  boardArea[0] = BoardArea.Area1;
			  boardArea[1] = BoardArea.Area2;
			  boardArea[2] = BoardArea.Area4;
			  boardArea[3] = BoardArea.Area5;
			  break;			
		  case 5:
			  boardArea[0] = BoardArea.Area1;
			  boardArea[1] = BoardArea.Area2;
			  boardArea[2] = BoardArea.Area3;
			  boardArea[3] = BoardArea.Area5;
			  boardArea[4] = BoardArea.Area6;
			  break;			
		  case 6:
			  boardArea[0] = BoardArea.Area1;
			  boardArea[1] = BoardArea.Area2;
			  boardArea[2] = BoardArea.Area3;
			  boardArea[3] = BoardArea.Area4;
			  boardArea[4] = BoardArea.Area5;
			  boardArea[5] = BoardArea.Area6;
			  break;
		  default:
			  boardArea = null;
			  break;
		  }		
		  return boardArea;
	  }
	  public boolean isInclude (Position position){
		  for (int i = 0; i < areaPositions.length; i++) {
			  if (areaPositions[i].equals(position)) {
				  return true;
			  }
		  }
		  return false;
	  }
	  @Override
	  public String toString() {
		  return BoardAreaStr;  
	  }
}
