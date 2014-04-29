package org.chinesechecker.graphics;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface GameConstants extends Constants {        
	@DefaultStringValue("Restart")
	String restart();
	@DefaultStringValue("Still On ...")
	String stillOn();
	@DefaultStringValue("Cannot Move Opponent's Piece")
	String OppError();
	@DefaultStringValue("Cannot Move There")
	String MoveError();
	@DefaultStringValue("Current Player: ")
	String CurrentPlayer();
	@DefaultStringValue("wins!")
	String win();	
	@DefaultStringValue("Red ")
	String red();	
	@DefaultStringValue("Blue ")
	String blue();	
}

