package org.chinesechecker.graphics;

import static org.chinesechecker.client.Color.R;
import static org.chinesechecker.client.Color.B;

import org.chinesechecker.client.BoardArea;
import org.chinesechecker.client.Chess;
import org.chinesechecker.client.Color;
import org.chinesechecker.client.ChessBoard;
import org.chinesechecker.client.PlayerInfo;
import org.chinesechecker.client.Position;
import org.chinesechecker.client.State;


import com.google.gwt.event.dom.client.DragEnterEvent;
import com.google.gwt.event.dom.client.DragEnterHandler;
import com.google.gwt.event.dom.client.DragLeaveEvent;
import com.google.gwt.event.dom.client.DragLeaveHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.event.dom.client.DragDropEventBase;
import com.google.gwt.event.dom.client.HasAllDragAndDropHandlers;
/*
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchEvent;*/

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.dom.client.AudioElement;
import com.google.gwt.media.client.Audio;
import com.google.gwt.uibinder.client.UiHandler;

public class Graphics extends Composite implements Presenter.View {
	
	static final int STONE_SIZE = 10;
	static final int ANIMATION_DURATION = 2000;
	
	interface GraphicsUiBinder extends UiBinder<Widget, Graphics> {
    }
	
	interface MyStyle extends CssResource {
		String cellHover();
	}
	private static GraphicsUiBinder uiBinder = GWT.create(GraphicsUiBinder.class);

	@UiField
	MyStyle style;
	//@UiField
	//HorizontalPanel outerBoard;
	//@UiField
	//Grid gameBoard;
	
	@UiField
	Grid gameBoard1;
	@UiField
	Grid gameBoard2;
	@UiField
	Grid gameBoard3;
	@UiField
	Grid gameBoard4;
	@UiField
	Grid gameBoard5;
	@UiField
	Grid gameBoard6;
	@UiField
	Grid gameBoard7;
	@UiField
	Grid gameBoard8;
	@UiField
	Grid gameBoard9;
	@UiField
	Grid gameBoard10;
	@UiField
	Grid gameBoard11;
	@UiField
	Grid gameBoard12;
	@UiField
	Grid gameBoard13;
	@UiField
	Grid gameBoard14;
	@UiField
	Grid gameBoard15;
	@UiField
	Grid gameBoard16;
	@UiField
	Grid gameBoard17;
	
	@UiField
	Button restartBtn;
	@UiField
	Image whoseTurnImage;
	@UiField
	Label messageLabel;
	@UiField
	HTML statusHTML;
	//@UiField
	//Image gameLogo;
	@UiField public static
	GameCss css;
	
	public CheckerResources checkerResources;
	public Presenter presenter;
	public AbsolutePanel innerBoard;	
	public String innerBoardWidth = "340px";
	public String innerBoardHeight = "340px";
	
	public Image[][] cells = new Image[ChessBoard.ROWS][ChessBoard.COLS];
	public boolean isGameOver;
	public Color whoseTurn;
	private Image[][] squareImages = new Image[ChessBoard.ROWS][ChessBoard.COLS];
	private Image[][] pieceImages = new Image[ChessBoard.ROWS][ChessBoard.COLS];
	public Audio pieceCaptured = Audio.createIfSupported();
	public Audio pieceDown = Audio.createIfSupported();
	public Audio errorSound = Audio.createIfSupported();
	public Audio restartSound = Audio.createIfSupported();
	
	public Graphics() {
		presenter = new Presenter(this, null);
		this.checkerResources = GWT.create(CheckerResources.class);
		innerBoard = new AbsolutePanel();
	  	innerBoard.setSize(innerBoardWidth, innerBoardHeight);		
		initWidget(uiBinder.createAndBindUi(this));
		//gameLogo.setResource(checkerResources.title());	    
	    pieceCaptured.setSrc("sounds/pieceCaptured.mp3");
	    pieceDown.setSrc("sounds/pieceDown.mp3");
	    errorSound.setSrc("sounds/error.mp3");
	    restartSound.setSrc("sounds/restart.mp3");
	    isGameOver = false;
		whoseTurn = R;
		/*
		gameBoard.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard.setWidth("170px");
		gameBoard.setHeight("170px");
		gameBoard.setCellPadding(0);
	    gameBoard.setCellSpacing(0);
	    gameBoard.setBorderWidth(0);
				
		for (int i = 1; i < ChessBoard.ROWS; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;	

				cell.addTouchStartHandler(new TouchStartHandler() {
                    @Override
                    public void onTouchStart(TouchStartEvent event) {
                    	presenter.selectCell(row, col);
                    	//cell.getElement().addClassName(style.cellHover());                    	
                    }
				});
				cell.addTouchMoveHandler(new TouchMoveHandler() {
                    @Override
                    public void onTouchMove(TouchMoveEvent event) {
                    	//presenter.selectCell(row, col);
                    }
				});				
				
				cell.addTouchEndHandler(new TouchEndHandler() {
                    @Override
                    public void onTouchEnd(TouchEndEvent event) {
                    	presenter.selectCell(row, col);
                    	//cell.getElement().removeClassName(style.cellHover());
                    }
				});
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				
				if ((i == 1 && j == 5)||(i == 2 && j == 5)||
						(i == 2 && j == 6)||(i == 3 && j == 5)||
						(i == 3 && j == 6)||(i == 3 && j == 7)||
						(i == 4 && j == 5)||(i == 4 && j == 6)||
						(i == 4 && j == 7)||(i == 4 && j == 8)) {
					cell.setResource(checkerResources.redStone());
				} else if ((i == 17 && j == 13)||(i == 16 && j == 12)||
						(i == 16 && j == 13)||(i == 15 && j == 11)||
						(i == 15 && j == 12)||(i == 15 && j == 13)||
						(i == 14 && j == 10)||(i == 14 && j == 11)||
						(i == 14 && j == 12)||(i == 14 && j == 13)) {
					cell.setResource(checkerResources.blueStone());
				} else  {				
					cell.setResource(getBlank(i,j));
				}				
				gameBoard.setWidget(i, j, cell);											
			}						
		}
		*/
		
		gameBoard1.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard1.setWidth("170px");
		gameBoard1.setHeight("10px");
		gameBoard1.setCellPadding(0);
		gameBoard1.setCellSpacing(0);
		gameBoard1.setBorderWidth(0);
				
		for (int i = 1; i <= 1; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				
				if (j == 5 ) {
					cell.setResource(checkerResources.redStone());
				} else  {				
					cell.setResource(getBlank(i,j));
				}				
				gameBoard1.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard2.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard2.setWidth("170px");
		gameBoard2.setHeight("10px");
		gameBoard2.setCellPadding(0);
		gameBoard2.setCellSpacing(0);
		gameBoard2.setBorderWidth(0);
				
		for (int i = 2; i <= 2; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				
				if (j == 5 || j == 6) {
					cell.setResource(checkerResources.redStone());
				} else  {				
					cell.setResource(getBlank(i,j));
				}				
				gameBoard2.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard3.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard3.setWidth("170px");
		gameBoard3.setHeight("10px");
		gameBoard3.setCellPadding(0);
		gameBoard3.setCellSpacing(0);
		gameBoard3.setBorderWidth(0);
				
		for (int i = 3; i <= 3; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				
				if (j == 5 || j == 6 || j == 7) {
					cell.setResource(checkerResources.redStone());
				} else  {				
					cell.setResource(getBlank(i,j));
				}				
				gameBoard3.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard4.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard4.setWidth("170px");
		gameBoard4.setHeight("10px");
		gameBoard4.setCellPadding(0);
		gameBoard4.setCellSpacing(0);
		gameBoard4.setBorderWidth(0);
				
		for (int i = 4; i <= 4; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				
				if (j == 5 || j == 6 || j == 7 || j == 8) {
					cell.setResource(checkerResources.redStone());
				} else  {				
					cell.setResource(getBlank(i,j));
				}				
				gameBoard4.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard5.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard5.setWidth("170px");
		gameBoard5.setHeight("10px");
		gameBoard5.setCellPadding(0);
		gameBoard5.setCellSpacing(0);
		gameBoard5.setBorderWidth(0);
				
		for (int i = 5; i <= 5; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				cell.setResource(getBlank(i,j));				
				gameBoard5.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard6.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard6.setWidth("170px");
		gameBoard6.setHeight("10px");
		gameBoard6.setCellPadding(0);
		gameBoard6.setCellSpacing(0);
		gameBoard6.setBorderWidth(0);
				
		for (int i = 6; i <= 6; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				cell.setResource(getBlank(i,j));				
				gameBoard6.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard7.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard7.setWidth("170px");
		gameBoard7.setHeight("10px");
		gameBoard7.setCellPadding(0);
		gameBoard7.setCellSpacing(0);
		gameBoard7.setBorderWidth(0);
				
		for (int i = 7; i <= 7; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				cell.setResource(getBlank(i,j));				
				gameBoard7.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard8.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard8.setWidth("170px");
		gameBoard8.setHeight("10px");
		gameBoard8.setCellPadding(0);
		gameBoard8.setCellSpacing(0);
		gameBoard8.setBorderWidth(0);
				
		for (int i = 8; i <= 8; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				cell.setResource(getBlank(i,j));				
				gameBoard8.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard9.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard9.setWidth("170px");
		gameBoard9.setHeight("10px");
		gameBoard9.setCellPadding(0);
		gameBoard9.setCellSpacing(0);
		gameBoard9.setBorderWidth(0);
				
		for (int i = 9; i <= 9; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				cell.setResource(getBlank(i,j));				
				gameBoard9.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard10.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard10.setWidth("170px");
		gameBoard10.setHeight("10px");
		gameBoard10.setCellPadding(0);
		gameBoard10.setCellSpacing(0);
		gameBoard10.setBorderWidth(0);
				
		for (int i = 10; i <= 10; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;	
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				cell.setResource(getBlank(i,j));				
				gameBoard10.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard11.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard11.setWidth("170px");
		gameBoard11.setHeight("10px");
		gameBoard11.setCellPadding(0);
		gameBoard11.setCellSpacing(0);
		gameBoard11.setBorderWidth(0);
				
		for (int i = 11; i <= 11; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				cell.setResource(getBlank(i,j));				
				gameBoard11.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard12.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard12.setWidth("170px");
		gameBoard12.setHeight("10px");
		gameBoard12.setCellPadding(0);
		gameBoard12.setCellSpacing(0);
		gameBoard12.setBorderWidth(0);
				
		for (int i = 12; i <= 12; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				cell.setResource(getBlank(i,j));				
				gameBoard12.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard13.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard13.setWidth("170px");
		gameBoard13.setHeight("10px");
		gameBoard13.setCellPadding(0);
		gameBoard13.setCellSpacing(0);
		gameBoard13.setBorderWidth(0);
				
		for (int i = 13; i <= 13; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;	
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				cell.setResource(getBlank(i,j));				
				gameBoard13.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard14.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard14.setWidth("170px");
		gameBoard14.setHeight("10px");
		gameBoard14.setCellPadding(0);
		gameBoard14.setCellSpacing(0);
		gameBoard14.setBorderWidth(0);
				
		for (int i = 14; i <= 14; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				
				if (j == 10 || j == 11 || j == 12 || j == 13) {
					cell.setResource(checkerResources.blueStone());
				} else  {				
					cell.setResource(getBlank(i,j));
				}				
				gameBoard14.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard15.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard15.setWidth("170px");
		gameBoard15.setHeight("10px");
		gameBoard15.setCellPadding(0);
		gameBoard15.setCellSpacing(0);
		gameBoard15.setBorderWidth(0);
				
		for (int i = 15; i <= 15; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;	
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				
				if (j == 11 || j == 12 || j == 13) {
					cell.setResource(checkerResources.blueStone());
				} else  {				
					cell.setResource(getBlank(i,j));
				}				
				gameBoard15.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard16.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard16.setWidth("170px");
		gameBoard16.setHeight("10px");
		gameBoard16.setCellPadding(0);
		gameBoard16.setCellSpacing(0);
		gameBoard16.setBorderWidth(0);
				
		for (int i = 16; i <= 16; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;	
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				
				if (j == 12 || j == 13) {
					cell.setResource(checkerResources.blueStone());
				} else  {				
					cell.setResource(getBlank(i,j));
				}				
				gameBoard16.setWidget(i, j, cell);											
			}						
		}
		
		gameBoard17.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard17.setWidth("170px");
		gameBoard17.setHeight("10px");
		gameBoard17.setCellPadding(0);
		gameBoard17.setCellSpacing(0);
		gameBoard17.setBorderWidth(0);
				
		for (int i = 17; i <= 17; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;
				
				cell.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				cell.addDragStartHandler(new DragStartHandler() {
                    @Override
                    public void onDragStart(DragStartEvent event) {
                    	presenter.selectCell(row, col);
                    }
				});
				cell.addDragOverHandler(new DragOverHandler() {
                    @Override
                    public void onDragOver(DragOverEvent event) {
                    }
				});
				cell.addDragEnterHandler(new DragEnterHandler() {
					@Override
					public void onDragEnter(DragEnterEvent event) {
						cell.getElement().addClassName(style.cellHover());
					}
				});
				cell.addDragLeaveHandler(new DragLeaveHandler() {
					@Override
					public void onDragLeave(DragLeaveEvent event) {
						cell.getElement().removeClassName(style.cellHover());
					}
				});
				cell.addDropHandler(new DropHandler() {
                    @Override
                    public void onDrop(DropEvent event) {
                    	presenter.selectCell(row, col);
                    	cell.getElement().removeClassName(style.cellHover());
                    }
				});
				cell.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						presenter.selectCell(row, col);						
					}
				});
				
				if (j == 13) {
					cell.setResource(checkerResources.blueStone());
				} else  {				
					cell.setResource(getBlank(i,j));
				}				
				gameBoard17.setWidget(i, j, cell);											
			}						
		}
		
		whoseTurnImage.setResource(checkerResources.redStone());
		
		restartBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				presenter.restartGame();
			}
		});
	}
	/*
	@Override
	public void setPresenter(Presenter abalonePresenter) {
		this.presenter = abalonePresenter;
		abaloneDragController = new MyDragController(innerBoard, false, abalonePresenter);
		abaloneDragController.setBehaviorConstrainedToBoundaryPanel(true);
		abaloneDragController.setBehaviorMultipleSelection(false);
		abaloneDragController.setBehaviorDragStartSensitivity(1);
	}
	*/
	public Presenter getPresenter() {
		return presenter;
	}
	
	@Override
	public void setCell(int row, int col, Chess chess) {
		if (chess.getColor().isRed()) {
			cells[row][col].setResource(checkerResources.redStone());
		} else if (chess.getColor().isBlue()) {
			cells[row][col].setResource(checkerResources.blueStone());
		} else {
			cells[row][col].setResource(getBlank(row,col));
		}
	}	
	
	private ImageResource getBlank(int i, int j) {
		if ((i == 1 && j == 5)||(i == 2 && j == 5)||
				(i == 2 && j == 6)||(i == 3 && j == 5)||
				(i == 3 && j == 6)||(i == 3 && j == 7)||
				(i == 4 && j == 5)||(i == 4 && j == 6)||
				(i == 4 && j == 7)||(i == 4 && j == 8)||
				(i == 5 && j >= 1 && j <= 13)||
				(i == 6 && j >= 2 && j <= 13)||
				(i == 7 && j >= 3 && j <= 13)||
				(i == 8 && j >= 4 && j <= 13)||
				(i == 9 && j >= 5 && j <= 13)||
				(i == 10 && j >= 5 && j <= 14)||
				(i == 11 && j >= 5 && j <= 15)||
				(i == 12 && j >= 5 && j <= 16)||
				(i == 13 && j >= 5 && j <= 17)||
				(i == 17 && j == 13)||(i == 16 && j == 12)||
				(i == 16 && j == 13)||(i == 15 && j == 11)||
				(i == 15 && j == 12)||(i == 15 && j == 13)||
				(i == 14 && j == 10)||(i == 14 && j == 11)||
				(i == 14 && j == 12)||(i == 14 && j == 13))	{
			return checkerResources.positionStone();
		} else {
			return checkerResources.invalidStone();
		}
	}
	
	@Override
	public void setWhoseTurn(Color color) {
		if (color == R) {
			whoseTurnImage.setResource(checkerResources.redStone());
		} else {
			whoseTurnImage.setResource(checkerResources.blueStone());
		}

	}
	
	@Override
	public void setMessage(String msg) {
		messageLabel.setText(msg);
	}
	
	@Override
	public void showStatus(String html) {
		statusHTML.setHTML(html);
	}
	
	@Override
	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	boolean isGameOver() {
		return isGameOver;
	}
	
	@Override
	public void setButton(String str) {
		restartBtn.setText(str);

	}

	@Override
	public void setHighlight(int row, int col, boolean highlighted) {
		Element elem = cells[row][col].getElement();
		if (highlighted) {
			elem.setClassName(css.highlighted());
		} else {
	      elem.removeClassName(css.highlighted());
		}
	}
	
	@Override
	public void animateSetStone(Position p) {
		Image i = cells[p.row][p.col];
		SetStoneAnimation animation = new SetStoneAnimation(i);
		animation.run(ANIMATION_DURATION);
	}
	
	@Override
    public void pieceCapturedSound() {
		pieceCaptured.play();
    }
	@Override
    public void pieceDownSound() {
		pieceDown.play();
    }	
	@Override
    public void errorSound() {
		errorSound.play();
    }
	@Override
    public void restartSound() {
		restartSound.play();
    }
}
