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

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

//import com.google.inject.Inject;

public class Graphics extends Composite implements Presenter.View {
	
	static final int STONE_SIZE = 30;
	
	interface GraphicsUiBinder extends UiBinder<Widget, Graphics> {
    }
	
	interface MyStyle extends CssResource {
		String cellHover();
	}
	private static GraphicsUiBinder uiBinder = GWT.create(GraphicsUiBinder.class);

	@UiField
	MyStyle style;
	@UiField
	Grid gameBoard;
	@UiField
	Button restartBtn;
	@UiField
	Image whoseTurnImage;
	@UiField
	Label messageLabel;
	@UiField
	HTML statusHTML;
	@UiField
	Image gameLogo;
	@UiField public static
	GameCss css;
	
	
	public Presenter presenter;
	public CheckerImage checkerImages;
	public Image[][] cells = new Image[ChessBoard.ROWS][ChessBoard.COLS];
	public boolean isGameOver;
	public Color whoseTurn;

	int timer = 0;
	
	
	public Graphics() {
		presenter = new Presenter(this);
		this.checkerImages = GWT.create(CheckerImage.class);
		initWidget(uiBinder.createAndBindUi(this));
		timer ++;
		gameLogo.setResource(checkerImages.title());
		gameBoard.resize(ChessBoard.ROWS, ChessBoard.COLS);
		gameBoard.setWidth("540px");
		gameBoard.setHeight("540px");
		gameBoard.setCellPadding(0);
	    gameBoard.setCellSpacing(0);
	    gameBoard.setBorderWidth(0);
	    
	    isGameOver = false;
		whoseTurn = R;
				
		for (int i = 1; i < ChessBoard.ROWS; i++) {
			for (int j = 1; j < ChessBoard.COLS; j++) {
				final Image cell = new Image();
				cells[i][j] = cell;
				final int row = i;
				final int col = j;	
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
					cell.setResource(checkerImages.redStone());
				} else if ((i == 17 && j == 13)||(i == 16 && j == 12)||
						(i == 16 && j == 13)||(i == 15 && j == 11)||
						(i == 15 && j == 12)||(i == 15 && j == 13)||
						(i == 14 && j == 10)||(i == 14 && j == 11)||
						(i == 14 && j == 12)||(i == 14 && j == 13)) {
					cell.setResource(checkerImages.blueStone());
				} else  {				
					cell.setResource(getBlank(i,j));
				}				
				gameBoard.setWidget(i, j, cell);											
			}						
		}
		
		whoseTurnImage.setResource(checkerImages.redStone());
		
		restartBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				presenter.restartGame();
			}
		});
	}
	
	public Presenter getPresenter() {
		return presenter;
	}
	
	@Override
	public void setCell(int row, int col, Chess chess) {
		if (chess.getColor().isRed()) {
			cells[row][col].setResource(checkerImages.redStone());
		} else if (chess.getColor().isBlue()) {
			cells[row][col].setResource(checkerImages.blueStone());
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
			return checkerImages.positionStone();
		} else {
			return checkerImages.invalidStone();
		}
	}
	
	@Override
	public void setWhoseTurn(Color color) {
		if (color == R) {
			whoseTurnImage.setResource(checkerImages.redStone());
		} else {
			whoseTurnImage.setResource(checkerImages.blueStone());
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
}
